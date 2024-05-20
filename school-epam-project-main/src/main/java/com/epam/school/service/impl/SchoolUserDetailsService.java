package com.epam.school.service.impl;

import com.epam.school.entities.EpamUser;
import com.epam.school.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SchoolUserDetailsService implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final EpamUser user = userRepository.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException(username);
		}
		
//		UserDetails userDetails = User.withDefaultPasswordEncoder().username(user.getUsername()).
//				password(user.getPassword()).authorities(user.getRole()).build();
		//GrantedAuthority granted  = new SimpleGrantedAuthority("ROL"); // send all the collection
		EpamUserDetails userDetails = new EpamUserDetails(user);
		List<GrantedAuthority> list = new ArrayList<>();
		GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
		list.add(authority);
		userDetails.setAuthorities(list);
		return userDetails;						
	}
	
	public EpamUser findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public void save(EpamUser user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}
	
	public EpamUser findById(Integer id) {
		return userRepository.findById(id).get();
	}
	
}
