package com.vnpt.shopguard.controller;

import com.vnpt.shopguard.model.User;
import com.vnpt.shopguard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "site/login"; 
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model) {
        User user = userService.login(username, password);
        if (user != null) {
            if ("ADMIN".equalsIgnoreCase(user.getRole())) {
                return "redirect:/admin/dashboard";
            } else {
                return "redirect:/home";
            }
        }
        model.addAttribute("error", "Sai tài khoản hoặc mật khẩu");
        return "site/login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "site/register"; 
    }

   @PostMapping("/register")
public String register(@RequestParam String username,
                       @RequestParam String password,
                       Model model) {
    User newUser = new User();
    newUser.setUsername(username);
    newUser.setPassword(password);
    newUser.setRole("USER"); 

    if (userService.register(newUser) != null) {
        return "redirect:/login";
    }

    model.addAttribute("error", "Tài khoản đã tồn tại");
    return "site/register";
}

}
