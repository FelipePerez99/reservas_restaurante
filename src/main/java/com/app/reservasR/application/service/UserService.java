package com.app.reservasR.application.service;

import com.app.reservasR.application.Exception.EntityNotFoundException;
import com.app.reservasR.application.lasting.EMessage;
import com.app.reservasR.domain.entity.User;
import com.app.reservasR.domain.dto.UserDto;
import com.app.reservasR.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public record UserService(UserRepository userRepository) {
    public void createUser(UserDto userDto){
        User user = User.builder().firstName(userDto.firstName())
                .lastName(userDto.lastName())
                .email(userDto.email())
                .password(userDto.password())
                .enable(userDto.enable())
                .build();

        userRepository.save(user);
    }

    public UserDto findUserById(Integer id) throws EntityNotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(EMessage.DATA_NOT_FOUND));
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getEnable(),
                user.getRole()
        );
    }

    public UserDto updateUser(Integer id, UserDto userDto) throws EntityNotFoundException{
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(EMessage.DATA_NOT_FOUND));

        user.setFirstName(userDto.firstName());
        user.setLastName(userDto.lastName());
        user.setEmail(userDto.email());
        user.setPassword(userDto.password());
        user.setEnable(userDto.enable());
        user.setRole(userDto.role());

        User updateUser =  userRepository.save(user);

        return new UserDto(
                updateUser.getId(),
                updateUser.getFirstName(),
                updateUser.getLastName(),
                updateUser.getEmail(),
                updateUser.getPassword(),
                updateUser.getEnable(),
                updateUser.getRole()
        );
    }

    public List<UserDto> AllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> new UserDto(
                        user.getId(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        user.getPassword(),
                        user.getEnable(),
                        user.getRole()
                ))
                .collect(Collectors.toList());
    }

    public void deleteById(Integer id)throws EntityNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            userRepository.deleteById(id);
        }else{
            throw new EntityNotFoundException(EMessage.DATA_NOT_FOUND);
        }
    }
}
