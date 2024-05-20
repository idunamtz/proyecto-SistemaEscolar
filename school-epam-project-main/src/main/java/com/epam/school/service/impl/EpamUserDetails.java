package com.epam.school.service.impl;

import com.epam.school.entities.EpamUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
public class EpamUserDetails implements UserDetails{

	private static final long serialVersionUID = 1L;
	private final EpamUser epamUser;
	private Collection<? extends GrantedAuthority> authorities;
	
	public EpamUserDetails() {
		this.epamUser = new EpamUser();
	}
	
	public EpamUserDetails(EpamUser epamUser){
		this.epamUser=epamUser;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities= authorities;
	}
	
	public String getName() {
		if(epamUser.getStudent() != null) {
			return epamUser.getStudent().getName();
		}
		else {
			return epamUser.getTeacher().getName();
		}
	}
	
	public String getLastname() {
		if(epamUser.getStudent() != null) {
			return epamUser.getStudent().getLastname();
		}
		else {
			return epamUser.getTeacher().getLastname();
		}
	}
	
	@Override
	public String getPassword() {
		//noop is to avoid implementing password encoder
		//return "{noop}"+epamUser.getPassword();
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		return encoder.encode(epamUser.getPassword());
	}
	
	public EpamUser getEpamUser() {
		return epamUser;
	}

	@Override
	public String getUsername() {
		return epamUser.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
