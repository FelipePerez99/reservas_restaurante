package com.app.reservasR.application.Controller;

import com.app.reservasR.application.Exception.EntityNotFoundException;
import com.app.reservasR.application.service.AuthenticationService;
import com.app.reservasR.domain.dto.AuthenticationDto;
import com.app.reservasR.domain.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public record AuthenticationController(
        AuthenticationService authenticationService
) {

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto){
        String token = authenticationService.register(userDto);
        return new ResponseEntity<>(token, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationDto authenticationDto) throws EntityNotFoundException {
        String token = authenticationService.login(authenticationDto);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
