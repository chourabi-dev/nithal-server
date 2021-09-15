package com.grokonez.jwtauthentication.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.grokonez.jwtauthentication.entitys.Notifications;
import com.grokonez.jwtauthentication.model.User;
import com.grokonez.jwtauthentication.repository.NotificationsRepository;
import com.grokonez.jwtauthentication.repository.UserRepository;
import com.grokonez.jwtauthentication.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/notifications")
public class NotificationsController {

	@Autowired
	private NotificationsRepository  notificationsRepository;
	
    @Autowired
    JwtProvider jwtProvider;
    
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/list")
	public List<Notifications> getAuthNotifications( HttpServletRequest req ){
		List<Notifications> tmp =  this.notificationsRepository.findAll();
		
        Optional<User> current;
        String token = req.getHeader("authorization").replace("Bearer " ,"");
        System.out.println(token);
        String username=this.jwtProvider.getUserNameFromJwtToken(token);
        current=this.userRepository.findByUsername(username);
        
        List<Notifications> notifications =  new ArrayList<Notifications>();
        
        for(Notifications n:tmp) {
        	if( n.getUser().getId() == current.get().getId() ) {
        		notifications.add(n);
        	}
        }
        
		return notifications;
	}
	
	
	@GetMapping("/update")
	public List<Notifications> updateConnectedUserNotifications( HttpServletRequest req ){
		List<Notifications> tmp =  this.notificationsRepository.findAll();
		
        Optional<User> current;
        String token = req.getHeader("authorization").replace("Bearer " ,"");
        System.out.println(token);
        String username=this.jwtProvider.getUserNameFromJwtToken(token);
        current=this.userRepository.findByUsername(username);
        
        List<Notifications> notifications =  new ArrayList<Notifications>();
        
        for(Notifications n:tmp) {
        	if( n.getUser().getId() == current.get().getId() ) {
        		n.setSeen(true);
        		this.notificationsRepository.save(n);
        	}
        }
        
		return notifications;
	}
}
