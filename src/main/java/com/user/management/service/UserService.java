package com.user.management.service;

import java.lang.StackWalker.Option;
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
        
        // for (user u : list) {
        //     System.out.println("User ID: " + u.getId() + ", Name: " + u.getName() + ", Age: " + u.getAge());
        // }
        
        return list;
    }

    public user signin(user user){
        String empId = user.getempId();
        System.out.println(empId);
        
        Optional<user> optionalUser = userRepository.findById(empId);
        

        if(optionalUser.isPresent()){
            if(optionalUser.get().getPassword().equals(user.getPassword())){
                return optionalUser.get();
            }else{
                return null;
            }
        }
        
        return null;
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
            updatedUser.setempId(id);
            return userRepository.save(updatedUser);
        }
        
        return null;
    }

    public String deleteUser(String email){
        if(userRepository.existsByEmail(email)){
            userRepository.deleteByEmail(email);

            return "User Deleted!";
        }

        return "User Not Found!";
    }

}

