package com.gb.pma.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Autowired
	BCryptPasswordEncoder bCryptEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
				.usersByUsernameQuery("select username, password, enabled " + "from user_accounts where username= ?")
				.authoritiesByUsernameQuery("select username, role " + "from user_accounts where username = ?")
				.dataSource(this.dataSource).passwordEncoder(this.bCryptEncoder);

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/projects/new").hasRole("USER").antMatchers("/projects/save")
				.hasRole("USER").antMatchers("/employees/new").hasRole("USER").antMatchers("/employees/save")
				.hasRole("USER").antMatchers("/", "/**").permitAll().and().formLogin();

	}
}
