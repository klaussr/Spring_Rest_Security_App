package com.semkin.spring_rest_security_app.rest;

import com.semkin.spring_rest_security_app.dto.UserDto;
import com.semkin.spring_rest_security_app.model.User;
import com.semkin.spring_rest_security_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/users/")
public class UserRestControllerV1 {
    private final UserService userService;

    @Autowired
    public UserRestControllerV1(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        User user = userService.getById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        UserDto result = UserDto.fromUser(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        List<User> users = userService.getAll();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<UserDto> userDtos = UserDto.toUserDtos(users);
        return new ResponseEntity(userDtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) {
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        User resultUser = userService.create(user);
        return new ResponseEntity(resultUser, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
        if (id == null || user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        User updateUser = userService.update(user);
        return new ResponseEntity(updateUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        User user = this.userService.getById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        userService.deleteById(id);
        return new ResponseEntity(user,HttpStatus.OK);
    }
}

