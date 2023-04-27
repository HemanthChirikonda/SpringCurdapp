package com.learning;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import com.learning.entities.User;
import com.learning.repositories.UserRepository;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
	
	@Autowired UserRepository repo;

	
	@Test
	public void testAddNew() {
		User user = new User();
		user.setEmail("nani3@gmail.com");
		user.setEnabled(true);
		user.setFirstName("nani");
		user.setLastName("G");
		user.setPassword("98765432");
		
		User savedUser = repo.save(user);
		
		Assertions.assertThat(savedUser).isNotNull();
		Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
	}
	
	
	@Test 
	public void testListAll() {
		Iterable<User> users = repo.findAll();
		
		Assertions.assertThat(users).hasSizeGreaterThan(0);
		
		for (User user : users) {
			System.out.println(user);
		}
	}
	
	@Test
	public void testUpdate() {
		Integer userId= 3;
		Optional<User> optionalUser = repo.findById(userId);
		User user= optionalUser.get();
		user.setEmail("alex.steven@gmail.com");
		user.setFirstName("alex");
		user.setLastName("steven");
		user.setEnabled(false);
		user.setPassword("12345vhh");
		repo.save(user);
		
		User updatedUser = repo.findById(userId).get();
		Assertions.assertThat(updatedUser.getFirstName()).isEqualTo("alex");
	}
	
	
	@Test
	public void testGet() {
		Integer userId= 1;
		Optional<User> optionalUser = repo.findById(userId);
		
		Assertions.assertThat(optionalUser).isPresent();
		System.out.println(optionalUser.get());
	}
	
	@Test
	public void testDelete() {
		Integer userId= 1;
		repo.deleteById(userId);
		Optional<User> optionalUser = repo.findById(userId);
		
		Assertions.assertThat(optionalUser).isNotPresent();
	}
	
}
