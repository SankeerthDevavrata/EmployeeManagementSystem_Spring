package com.gl.assignment4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.gl.assignment4.DTO.UserRegistrationDto;
import com.gl.assignment4.service.UserService;

@Controller
public class UserRegistrationController {

	@Autowired
	private UserService userService;

//	public UserRegistrationController(UserService userService) {
//		super();
//		this.userService = userService;
//	}

//	@ModelAttribute("user")
//    public UserRegistrationDto userRegistrationDto() {
//        return new UserRegistrationDto();
//    }

	@GetMapping("/registration")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new UserRegistrationDto());
		return "registration";
	}

	@PostMapping("/registration")
	public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
		userService.save(registrationDto);
		return "redirect:/registration?success";
	}

	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}

}