package com.godxvincent.springweb.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.godxvincent.springweb.model.ContactModel;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")
public class RestController {

	@GetMapping("/checkrest")
	public ResponseEntity<ContactModel> checkRest() {
		ContactModel contactModel = new ContactModel(1,"Ricardo","Vargas","3114467265","Bogota");
		return new ResponseEntity<ContactModel>(contactModel, HttpStatus.OK);
	}
}
