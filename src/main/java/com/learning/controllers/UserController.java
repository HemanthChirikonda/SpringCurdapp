package com.learning.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.learning.Exceptions.UserNotFoundException;
import com.learning.entities.User;
import com.learning.services.UserService;

@Controller
public class UserController {
	
	@Autowired private UserService userService;
	
	
	@GetMapping("/users")
	public String showUserList(Model model) {
		List<User> listUsers= userService.listAll();
		model.addAttribute("listUsers", listUsers);
		
		return "users";
	}
	
	@GetMapping("/users/new")
	public String showNewUserForm(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("pageTitle", "Add New User");
		return "user_form";
	}
	
	@PostMapping("/users/save")
	public String saveUser(User user, RedirectAttributes ra) {
		
		userService.save(user);
		ra.addFlashAttribute("message","The user has been added successfully!");
		
		return "redirect:/users";
		
	}
	
	@GetMapping("/users/edit/{id}")
	public String  showEditUserForm(@PathVariable Integer id, Model model, RedirectAttributes ra) {
		
		try {
			User user = userService.get(id);
			model.addAttribute("user",user);
			model.addAttribute("pageTitle", "Edit User");
			return "user_form";
		} catch (UserNotFoundException e) {
			ra.addFlashAttribute("message",e.getMessage());
			return "redirect:/users";
			
		}
		
	}
	
	@GetMapping("/users/delete/{id}")
	public String  deleteUser(@PathVariable Integer id, RedirectAttributes ra) {
		try {
			userService.delete(id);
			ra.addFlashAttribute("message","The user deleted successfully!");
		
		} catch (UserNotFoundException e) {
			ra.addFlashAttribute("message",e.getMessage());
			
		}
		return "redirect:/users";
		
	}

}
