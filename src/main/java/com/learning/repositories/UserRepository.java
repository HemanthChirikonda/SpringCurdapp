package com.learning.repositories;

import org.springframework.data.repository.CrudRepository;

import com.learning.entities.User;

public interface UserRepository extends CrudRepository<User, Integer>{
   
}
