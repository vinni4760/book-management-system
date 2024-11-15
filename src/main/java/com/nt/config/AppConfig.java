package com.nt.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.nt.entity.User;
import com.nt.jwt.JwtFilter;
import com.nt.service.CustomUserDetailsService;

import jakarta.annotation.PostConstruct;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class AppConfig {

	@Autowired
	private JwtFilter jwtFilter;
	 @Bean
	 public ModelMapper modelmapper() {
		 return new ModelMapper();
	 }
	 
	    @PostConstruct
	    public void init() {
	        System.out.println("SecurityConfig loaded successfully");
	    }
	    
	 @Bean
	 public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		 http.
		 csrf(csrf->csrf.disable())
		 .authorizeHttpRequests(auth->auth
				 .requestMatchers("/jwt/home","/jwt/register","/jwt/authenticate").permitAll()
				 .anyRequest().authenticated())
		 .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		 .httpBasic(Customizer.withDefaults())
		 .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		 return http.build();
	 }
	 
	 @Bean
	 public DaoAuthenticationProvider authenticationProvider() {
		 DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		  authenticationProvider.setUserDetailsService(customUserDetailsService());
		  authenticationProvider.setPasswordEncoder(passwordEncoder());
		 
		 return authenticationProvider;
	 }
	 
	 @Bean
	 public CustomUserDetailsService customUserDetailsService() {
		 return new CustomUserDetailsService();
	 }
	 
	 @Bean 
	 public PasswordEncoder passwordEncoder() {
		 return new BCryptPasswordEncoder();
	 }
	 
	 @Bean
	 public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		 return authenticationConfiguration.getAuthenticationManager();
	 }
	 
	 @Bean
	 public User user() {
		 return new User();
	 }
}
