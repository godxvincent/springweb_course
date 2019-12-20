package com.godxvincent.springweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.godxvincent.springweb.model.UserCredential;

@Controller
public class LoginController {

	@GetMapping("/")
	public String redirectToLogin() {
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String showLoginForm(Model model,
			@RequestParam(name="error", required = false) String error,
			@RequestParam(name="logout", required = false) String logout) {
		model.addAttribute("userCredential", new UserCredential());
		model.addAttribute("logout", logout);
		model.addAttribute("error", error);
		return "login";
	}
	
	@PostMapping("logincheck")
	public String loginCheck(@ModelAttribute(name="userCredential") UserCredential userCredentia) {
		if (userCredentia.getUsername().equals("user") && userCredentia.getPassword().equals("user")) {			
			return "contacts";
		}
		return "redirect:/login?error";
	}
}
