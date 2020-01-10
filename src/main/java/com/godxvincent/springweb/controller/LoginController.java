package com.godxvincent.springweb.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.godxvincent.springweb.constant.ViewConstant;

@Controller
public class LoginController {
	
	private static final Log LOGGER = LogFactory.getLog(LoginController.class);

	
	@GetMapping("/login")
	public String showLoginForm(Model model,
			@RequestParam(name="error", required = false) String error,
			@RequestParam(name="logout", required = false) String logout) {
		LOGGER.info("METHOD: showLoginForm() -- PARMS: error= "+error + "logout: "+logout );
		model.addAttribute("logout", logout);
		model.addAttribute("error", error);
		LOGGER.info("Returning to login view");
		return ViewConstant.LOGIN;
	}
	
	@GetMapping({"loginsuccess","/"})
	public String loginCheck() {
		LOGGER.info("METHOD: loginCheck()");
		LOGGER.info("Returning to contacs view");
		return "redirect:/contacts/showcontacts";
	}
}
