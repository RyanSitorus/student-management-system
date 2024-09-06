package com.example.sms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.sms.entity.UserModel;
import com.example.sms.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@GetMapping("/login")
	public String getLoginPage() {
		return "login_page";
	}

	@GetMapping("/registration")
	public String getRegistrationPage(Model model) {
		model.addAttribute("user", new UserModel());
		return "registration_page";
	}

	@PostMapping("/registration")
	public String registerUser(@ModelAttribute UserModel userModel) {
		userService.register(userModel);
		return "redirect:/login?success";
	}

}
