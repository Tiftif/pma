package com.gb.pma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gb.pma.dao.UserAccountRepository;
import com.gb.pma.entities.UserAccount;

@Controller
public class SecurityController {

	@Autowired
	UserAccountRepository accountRepo;

	@Autowired
	BCryptPasswordEncoder bCryptEncoder;

	// Login form
	@RequestMapping("/login")
	public String login() {
		return "security/login";
	}

	// Login form with error
	@RequestMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "security/login";
	}

	@GetMapping("/register")
	public String register(Model model) {
		UserAccount userAccount = new UserAccount();
		model.addAttribute("userAccount", userAccount);
		return "security/register";
	}

	@PostMapping("/register/save")
	public String saveUser(Model model, UserAccount user) {
		user.setPassword(this.bCryptEncoder.encode(user.getPassword()));
		user.setRole("ROLE_USER");
		this.accountRepo.save(user);
		return "redirect:/login";
	}
}
