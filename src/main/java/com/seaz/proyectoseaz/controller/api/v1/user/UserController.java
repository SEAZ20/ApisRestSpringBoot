package com.seaz.proyectoseaz.controller.api.v1.user;

import com.seaz.proyectoseaz.entity.User;
import com.seaz.proyectoseaz.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    //Create a new user
    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@Validated @RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    //read user
    @GetMapping("/getUser/{id}")
    public ResponseEntity <?> readUser(@PathVariable(value = "id") Long userId){
        Optional<User> oUser = userService.findById(userId);
        if(!oUser.isPresent()){
            return ResponseEntity.notFound().build();
        }
        //test
        return ResponseEntity.ok(oUser);
    }

}
