package com.learning.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.Exceptions.UserNotFoundException;
import com.learning.entities.User;
import com.learning.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired private UserRepository repo;
	
	public List<User> listAll(){
		return (List<User>)repo.findAll();
		
	}
	
	public void save(User user) {
		repo.save(user);
	}

	public User get(Integer id) throws UserNotFoundException {
	 Optional<User> user = repo.findById(id);
	 
	 if(user.isPresent()) {
		 return user.get();
	 }
	 throw new UserNotFoundException("Couldn't find the user with id: "+ id);
		
	}
	
	
	public void delete(Integer id) throws UserNotFoundException {
		 Optional<User> user = repo.findById(id);
		
		if(user.get() == null) {
			 throw new UserNotFoundException("Couldn't find the user with id: "+ id);
		}
		repo.deleteById(id);
	}
	
}
