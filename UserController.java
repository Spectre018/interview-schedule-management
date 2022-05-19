package com.login.demo.controller;

import com.login.demo.model.User;
import com.login.demo.repository.UserRepository;
import com.login.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "reg_form";
    }

    @PostMapping("/submitRegistration")
    public String submitRegistration(@ModelAttribute(value = "user") User user , Model model) {
        String password = user.getPassword();
        System.out.println(password);
        user.setPassword(encoder().encode(password));
        userRepository.save(user);
        model.addAttribute("id", user.getId());
        return "reg_success";
    }



}
