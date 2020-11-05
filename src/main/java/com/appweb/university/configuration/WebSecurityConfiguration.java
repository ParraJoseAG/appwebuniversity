package com.appweb.university.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	String[] resources = new String[] { "/css/**", "/imgs/**", "/js/**" };

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMINISTRADOR").and()
				.withUser("95937395").password("9593").roles("ALUMNO").and().withUser("95977779").password("9597")
				.roles("ALUMNO").and().withUser("18232212").password("1823").roles("ALUMNO");
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(resources).permitAll().antMatchers("/", "/home").permitAll()
				.antMatchers("/alumno*").hasRole("ALUMNO").antMatchers("/admin*").hasRole("ADMINISTRADOR").anyRequest()
				.authenticated().and().formLogin().permitAll().defaultSuccessUrl("/home");
	}
}
