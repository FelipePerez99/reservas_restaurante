package com.app.reservasR.application.Controller;

import com.app.reservasR.application.Exception.EntityNotFoundException;
import com.app.reservasR.application.service.UserService;
import com.app.reservasR.domain.dto.UserDto;
import org.hibernate.sql.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public record UserController(UserService userService) {

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto){
        userService.createUser(userDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findUserById(@PathVariable Integer id) throws EntityNotFoundException {
        UserDto UserDto = userService.findUserById(id);
        return new ResponseEntity<>(UserDto, HttpStatus.FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody UserDto userDto) throws EntityNotFoundException{
        UserDto updateUser = userService.updateUser(id, userDto);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> allUsers() throws EntityNotFoundException{
        List<UserDto> users = userService.AllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) throws EntityNotFoundException{
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
