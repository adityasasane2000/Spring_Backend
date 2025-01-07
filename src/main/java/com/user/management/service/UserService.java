package com.user.management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.management.model.user;
import com.user.management.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public user createUser(user user){
        return userRepository.save(user);
    }

    public List<user> getAll(){
        List<user> list = userRepository.findAll();
        
        for (user u : list) {
            System.out.println("User ID: " + u.getId() + ", Name: " + u.getName() + ", Age: " + u.getAge());
        }
        
        return list;
    }

    public user getUserById(String id){
        Optional<user> optionaluser = userRepository.findById(id);

        if(optionaluser.isPresent()){
            return optionaluser.get();
        }

        return null;
    }

    public user updateUser(String id,user updatedUser){
        if(userRepository.existsById(id)){
            updatedUser.setId(id);
            return userRepository.save(updatedUser);
        }
        
        return null;
    }

    public String deleteUser(String id){
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);

            return "User Deleted!";
        }

        return "User Not Found!";
    }

}

