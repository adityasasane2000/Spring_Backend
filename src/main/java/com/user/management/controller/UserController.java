package com.user.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.management.DTO.AuthencticationResponse;
import com.user.management.model.user;
import com.user.management.service.Jwt;
import com.user.management.service.UserService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private Jwt jwt;

    // @PostMapping
    // public ResponseEntity<user> create(@RequestBody user newUser){
    //     user temp = userService.createUser(newUser);
    //     return ResponseEntity.ok(temp);
    // }

    @CrossOrigin(origins = "http://localhost:5173")  // Allow only this origin for this method
    @PostMapping("/createNewUser")
    public ResponseEntity<user> createUser(@RequestBody user newUser) {
        user temp = userService.createUser(newUser);
        return ResponseEntity.ok(temp);
    }

    @CrossOrigin(origins = "http://localhost:5173")  // Allow only this origin for this method
    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody user user) {
        user temp = userService.signin(user);
        
        if(temp == null){
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        String token = jwt.generateToken(temp.getEmail());

        AuthencticationResponse response = new AuthencticationResponse(temp, token,true);

        return ResponseEntity.ok(response);
    }
    

    @CrossOrigin(origins = "http://localhost:5173") 
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<user> updateUser(@RequestBody user updatedUser, @PathVariable String id){
        user updatUser = userService.updateUser(id, updatedUser);
        return ResponseEntity.ok(updatUser);
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<List<user>> getAll() {
        List<user> data = userService.getAll();
        return ResponseEntity.ok(data);
    }

    // @GetMapping("/{id}")
    // public ResponseEntity<user> getUserById(@PathVariable String id) {
    //     user data = userService.getUserById(id);
    //     return ResponseEntity.ok(data);
    // }


    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id){
        // System.out.println(id);
        String response = userService.deleteUser(id);
        System.out.println(response);
        return ResponseEntity.ok(response);
    }

    
}
