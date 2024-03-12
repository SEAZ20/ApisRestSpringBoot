package com.seaz.proyectoseaz.controller.api.v1.user;

import com.seaz.proyectoseaz.entity.User;
import com.seaz.proyectoseaz.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    //Create a new user
    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@Validated @RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    //read user
    @GetMapping("/getUser/{id}")
    public ResponseEntity <User> readUser(@PathVariable(value = "id") Long userId){
        Optional<User> oUser = userService.findById(userId);
        if(!oUser.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(oUser.get());
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id,@RequestBody User user){
        return ResponseEntity.ok(userService.update(id,user));
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id){

        if(!userService.findById(id).isPresent()){
            return ResponseEntity.notFound().build();
        }
        userService.deleteById(id);
        return ResponseEntity.ok().build();

    }

    //Read all user
    @GetMapping("/getUsers")
    public List<User> getAllUser(){
        List<User> listUser = StreamSupport.stream(userService.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return listUser;
    }

    //Paginacion
    @GetMapping("/getUsersP")
    public Page<User> getProducts(Pageable pageable) {
        return userService.findAll(pageable);
    }
}
