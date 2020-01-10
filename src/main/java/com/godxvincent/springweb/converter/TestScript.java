package com.godxvincent.springweb.converter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestScript {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
		System.out.println(bcpe.encode("user"));
	}

}
