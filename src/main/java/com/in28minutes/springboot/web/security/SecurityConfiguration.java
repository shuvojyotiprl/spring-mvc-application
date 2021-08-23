package com.in28minutes.springboot.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	//Create User - in28Minutes/dummy
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(userDetailsService);
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/login", "/h2-console/**").permitAll()
                .antMatchers("/", "/*todo*/**", "/*helpline/**").access("hasRole('USER')")
                .antMatchers("/*manage/**").access("hasRole('ADMIN')")
                .and()
                .formLogin();
        
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
	
	@Bean
	public PasswordEncoder getPasswordEncode() {
		return NoOpPasswordEncoder.getInstance();
	}
}
