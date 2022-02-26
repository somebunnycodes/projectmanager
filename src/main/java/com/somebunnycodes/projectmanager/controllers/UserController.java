package com.somebunnycodes.projectmanager.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.somebunnycodes.projectmanager.models.LoginUser;
import com.somebunnycodes.projectmanager.models.User;
import com.somebunnycodes.projectmanager.services.UserService;

@Controller
public class UserController {
	
    @Autowired
    private UserService userService;
    
    @GetMapping("/")
    public String index(HttpSession session, Model model) {
    	User user = (User)session.getAttribute("user");
    	if (user != null) {
    		return "redirect:/dashboard";
    	}
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "index.jsp";
    }
 
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser, 
            BindingResult result, Model model, HttpSession session) {
    	User createdUser = userService.register(newUser, result);
        if (createdUser == null) {
        	model.addAttribute("newUser", newUser);
        	model.addAttribute("newLogin", new LoginUser());
        	return "index.jsp";
        }   
        session.setAttribute("user", createdUser);
        return "redirect:/dashboard";
    }
    
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
            BindingResult result, Model model, HttpSession session) {
    	User loggedInUser = userService.login(newLogin, result);
        if (loggedInUser == null) {
        	model.addAttribute("newUser", new User());
        	model.addAttribute("newLogin", newLogin);
        	return "index.jsp";
        }   
        session.setAttribute("user", loggedInUser);
        return "redirect:/dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
    	session.setAttribute("user", null);
    	return "redirect:/";
    }
}

