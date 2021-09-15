package com.grokonez.jwtauthentication.controller;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grokonez.jwtauthentication.entitys.Colis;
import com.grokonez.jwtauthentication.entitys.Course;
import com.grokonez.jwtauthentication.entitys.Notifications;
import com.grokonez.jwtauthentication.message.request.CourseAddModel;
import com.grokonez.jwtauthentication.model.User;
import com.grokonez.jwtauthentication.repository.ColisRepository;
import com.grokonez.jwtauthentication.repository.CourseRepository;
import com.grokonez.jwtauthentication.repository.NotificationsRepository;
import com.grokonez.jwtauthentication.repository.UserRepository;
import com.grokonez.jwtauthentication.repository.VehiculesRepository;
import com.grokonez.jwtauthentication.security.jwt.JwtProvider;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/courses")
public class CourseController {

	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	VehiculesRepository vehiculesRepository;
	
	@Autowired
	ColisRepository colisRepository;
	
	@Autowired
	NotificationsRepository notificationsRepository;
	
	
	  @Autowired
	    JwtProvider jwtProvider;
	  
 
	    
	    
	
	
	
	@PostMapping("/add")
	public Course addNewCourse(@RequestBody CourseAddModel model) {
		Course course = new Course();
		
		course.setDescription(model.getDescription());
		course.setLivreur(this.userRepository.findById(model.getId_livreur()).get());
		course.setVehicule(this.vehiculesRepository.findById(model.getId_vehicule()).get() );
		course.setColis(this.colisRepository.findById(model.getId_colis()).get());
		
		// update colis state
		Colis old = this.colisRepository.findById(model.getId_colis()).get();
		
		old.setEtat(1);
		this.colisRepository.save(old);
		
		// send notification employee
        long millis=System.currentTimeMillis();  
        
		Notifications parcNotif = new Notifications();
		
		parcNotif.setTitle("Nouvel commande");
		parcNotif.setMessage("Vous avez une nouvelle commande à livrer");
		 
		parcNotif.setAdddate(   new Date(millis)  );
		parcNotif.setSeen(false);
		parcNotif.setUser( this.userRepository.findById(model.getId_livreur()).get() ) ;
		this.notificationsRepository.save(parcNotif);
		
		
		// send notification to client


        
		Notifications clientNotif = new Notifications();
		
		clientNotif.setTitle("commande");
		clientNotif.setMessage("Votre commande est en cours de traitement");
		 
		clientNotif.setAdddate(   new Date(millis)  );
		clientNotif.setSeen(false);
		clientNotif.setUser( this.colisRepository.findById(model.getId_colis()).get().getUser() ) ;
		this.notificationsRepository.save(clientNotif);
		
	  
		
		return this.courseRepository.save(course);
	}
	
	
	@GetMapping("/livreur/list")
	public List<Course> listDesLivrisan(HttpServletRequest req) {
        Optional<User> current;
        String token = req.getHeader("authorization").replace("Bearer " ,"");
        System.out.println(token);
        String username=this.jwtProvider.getUserNameFromJwtToken(token);
        current=this.userRepository.findByUsername(username);
        
        
		return this.courseRepository.findByLivreur(current.get());
	}
	
}
