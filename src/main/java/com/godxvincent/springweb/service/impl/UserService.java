package com.godxvincent.springweb.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.godxvincent.springweb.entity.UserRole;
import com.godxvincent.springweb.repository.UserRepository;

@Service("userService")
public class UserService implements UserDetailsService {

	
	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository; 
	
	@Override
	// Aqui tambien se puede poner el PreAuthorize
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		com.godxvincent.springweb.entity.User user =  userRepository.findUserByUsername(username);
		List<GrantedAuthority> authorities = this.buildAuthorities(user.getUserRole());
		
		return this.buildUser(user, authorities);
	}
	
	private User buildUser(com.godxvincent.springweb.entity.User user, List<GrantedAuthority> authorities) {
		
		//return new User(user.getUsername(), user.getPassword(), user.isEnabled(), accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
		
	}
	
	private List<GrantedAuthority> buildAuthorities (Set<UserRole> userRoles) {
		
		Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();
		
		for (UserRole userRol : userRoles) {
			auths.add(new SimpleGrantedAuthority(userRol.getRole()));
		}
		return new ArrayList<GrantedAuthority>(auths);
	}

}
