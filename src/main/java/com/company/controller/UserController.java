package com.company.controller;

import com.company.entity.User;
import com.company.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @Autowired
    IUserService userService;

    @RequestMapping("/rejestracja")
    public String showRegisterPage (Model model){
        User user = new User();
        model.addAttribute("user", user);

        return "/user/user-registration";
    }

    @RequestMapping("rejestracja/save")
    public String saveUser (@ModelAttribute("user") User user, Model model){
        boolean saved = userService.save(user);
        model.addAttribute("saved", saved);

        return "/user/user-registration-result";
    }
}
