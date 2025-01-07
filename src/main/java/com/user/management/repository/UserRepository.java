package com.user.management.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.user.management.model.user;

public interface UserRepository extends MongoRepository <user,String>{

}
