package com.app.reservasR.application.service;

import com.app.reservasR.application.Exception.EntityNotFoundException;
import com.app.reservasR.application.lasting.EMessage;
import com.app.reservasR.application.lasting.ERole;
import com.app.reservasR.domain.dto.AuthenticationDto;
import com.app.reservasR.domain.dto.UserDto;
import com.app.reservasR.domain.entity.User;
import com.app.reservasR.domain.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public record AuthenticationService(
        UserRepository userRepository,
        JwtService jwtService,
        PasswordEncoder passwordEncoder,
        AuthenticationManager authenticationManager
) {

    public String register(UserDto userDto) {
        User user = User.builder()
                .firstName(userDto.firstName())
                .lastName(userDto.lastName())
                .email(userDto.email())
                .enable(true)
                .role(ERole.USER)
                .password(passwordEncoder().encode(userDto.password()))
                .build();
        userRepository.save(user);
        return jwtService.generateToken(user);
    }

    public String login(AuthenticationDto authenticationDto) throws EntityNotFoundException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationDto.email(),
                        authenticationDto.password()
                )
        );
        User user = userRepository.findUserByEmail(
                authenticationDto.email()
        ).orElseThrow(
                () -> new EntityNotFoundException(EMessage.USER_NOT_FOUND)
        );
        return jwtService().generateToken(user);
    }

}
