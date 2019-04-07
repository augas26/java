package com.sg.blog.Blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Stuart
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginForm() {
        
        return "login";
    }
}
