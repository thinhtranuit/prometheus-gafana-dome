package com.thinhtran.demoapi.Controller;

import com.thinhtran.demoapi.User;
import com.thinhtran.demoapi.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@Component
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<User> getProductById(
            @PathVariable("id") Integer id) {
        Optional<User> user = Optional.of(userService.getUserById(id));

        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }

}
