package com.godxvincent.springweb.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.godxvincent.springweb.constant.ViewConstant;
import com.godxvincent.springweb.model.UserCredential;

@Controller
public class LoginController {
	
	private static final Log LOGGER = LogFactory.getLog(LoginController.class);

	@GetMapping("/")
	public String redirectToLogin() {
		LOGGER.info("METHOD: redirectToLogin()");
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String showLoginForm(Model model,
			@RequestParam(name="error", required = false) String error,
			@RequestParam(name="logout", required = false) String logout) {
		LOGGER.info("METHOD: showLoginForm() -- PARMS: error= "+error + "logout: "+logout );
		model.addAttribute("userCredential", new UserCredential());
		model.addAttribute("logout", logout);
		model.addAttribute("error", error);
		LOGGER.info("Returning to login view");
		return ViewConstant.LOGIN;
	}
	
	@PostMapping("logincheck")
	public String loginCheck(@ModelAttribute(name="userCredential") UserCredential userCredentia) {
		LOGGER.info("METHOD: loginCheck() -- PARMS: "+userCredentia.toString());
		if (userCredentia.getUsername().equals("user") && userCredentia.getPassword().equals("user")) {
			LOGGER.info("Returning to contacs view");
			// return ViewConstant.CONTACTS;
			return "redirect:/contacts/showcontacts";
		}
		
		LOGGER.info("Redirect to error view");
		return "redirect:/login?error";
	}
}
