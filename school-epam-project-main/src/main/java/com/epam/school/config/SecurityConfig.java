package com.epam.school.config;

import com.epam.school.service.impl.SchoolUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig { //extends WebSecurityConfigurerAdapter {
	
	@Autowired 
	SchoolUserDetailsService userDetails;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> {
				try {
					requests
						.antMatchers("/images/**","signUp.html").permitAll()
						.antMatchers("/", "/index").authenticated()
						.antMatchers("/student/*").hasRole("STUDENT")
						.antMatchers("/teacher/*").hasRole("TEACHER")
						.anyRequest().authenticated()
						.and()
						.exceptionHandling().accessDeniedPage("/error.html");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			)
			.formLogin((form) -> form
				.loginPage("/login")
				.defaultSuccessUrl("/index")
				.permitAll()	
			)
			
			.logout((logout) -> logout.permitAll());
		return http.build();
	}
	
	
//	@Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    @Override
//    protected AuthenticationManager authenticationManager() throws Exception {
//        return super.authenticationManager();
//    }
    
	
//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails user =
//			 User.withDefaultPasswordEncoder()
//				.username("user")
//				.password("password")
//				.roles("USER")
//				.build();
//
//		return new InMemoryUserDetailsManager(user);
//	}
	
}
