package com.somebunnycodes.projectmanager.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.somebunnycodes.projectmanager.models.LoginUser;
import com.somebunnycodes.projectmanager.models.User;
import com.somebunnycodes.projectmanager.repositories.UserRepository;
    
@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepo;
    
    public User register(User newUser, BindingResult result) {
    		
    	if (!newUser.getPassword().equals(newUser.getConfirm())) {
    	    result.rejectValue("confirm", "Matches", "Passwords must match!");
    	}
    	
    	if (result.hasErrors()) {
    		return null;
    	}
    	
    	Optional<User> potentialUser = userRepo.findByEmail(newUser.getEmail());
    	if (potentialUser.isPresent()) {
    		result.rejectValue("email", "Exists", "A user with this email address already exists!");
    	}

    	if (result.hasErrors()) {
    	    return null;
    	}

    	String hash = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
    	
    	newUser.setPassword(hash);
    	
    	userRepo.save(newUser);

    	return newUser;
    }
    
    public User login(LoginUser newLogin, BindingResult result) {
    	if (result.hasErrors()) {
    		return null;
    	}
    
    	Optional<User> potentialUser = userRepo.findByEmail(newLogin.getEmail());
    	if (!potentialUser.isPresent()) {
    		result.rejectValue("email", "DoesNotExist", "No user with this email address exists!");
    	} else {
	    	if(!BCrypt.checkpw(newLogin.getPassword(), potentialUser.get().getPassword())) {
	    	    result.rejectValue("password", "Matches", "Password is incorrect! Try again.");
	    	}
    	}
    	
    	if (result.hasErrors()) {
    		return null;
    	}
    
    	return potentialUser.get();
    }
    
}
