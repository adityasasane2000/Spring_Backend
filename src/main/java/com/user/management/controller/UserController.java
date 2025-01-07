package com.user.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.management.model.user;
import com.user.management.service.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<user> create(@RequestBody user newUser){
        user temp = userService.createUser(newUser);
        return ResponseEntity.ok(temp);
    }

    @GetMapping
    public ResponseEntity<List<user>> getAll() {
        List<user> data = userService.getAll();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<user> getUserById(@PathVariable String id) {
        user data = userService.getUserById(id);
        return ResponseEntity.ok(data);
    }

    // @RequestMapping(value = "/updateUser", method = RequestMethod.PUT)
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<user> updateUser(@RequestBody user updatedUser, @PathVariable String id){
        user updatUser = userService.updateUser(id, updatedUser);
        return ResponseEntity.ok(updatUser);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id){
        String response = userService.deleteUser(id);
        return ResponseEntity.ok(response);
    }

    
}
