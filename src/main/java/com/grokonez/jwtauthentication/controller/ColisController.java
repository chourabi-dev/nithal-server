package com.grokonez.jwtauthentication.controller;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grokonez.jwtauthentication.entitys.Colis;
import com.grokonez.jwtauthentication.entitys.Course;
import com.grokonez.jwtauthentication.entitys.Notifications;
import com.grokonez.jwtauthentication.message.response.CordsResponse;
import com.grokonez.jwtauthentication.model.User;
import com.grokonez.jwtauthentication.repository.ColisRepository;
import com.grokonez.jwtauthentication.repository.CourseRepository;
import com.grokonez.jwtauthentication.repository.NotificationsRepository;
import com.grokonez.jwtauthentication.repository.UserRepository;
import com.grokonez.jwtauthentication.security.jwt.JwtProvider;

 
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/colis")
public class ColisController {

	  @Autowired
	  ColisRepository colisRepository;
	  
	  
	  @Autowired
	  CourseRepository courseRepository;
	
	  @Autowired
	    JwtProvider jwtProvider;
	  
	    @Autowired
	    UserRepository userRepository;
	    
	    @Autowired
	    NotificationsRepository notificationsRepository;
  
	  @GetMapping("/list") 
	  public ResponseEntity <List <Colis>>getAllColis(HttpServletRequest req) {
	        Optional<User> current;
	        String token = req.getHeader("authorization").replace("Bearer " ,"");
	        System.out.println(token);
	        String username=this.jwtProvider.getUserNameFromJwtToken(token);
	        current=this.userRepository.findByUsername(username);
	         
	         
		  
		  
		  
		  List<Colis> colis = colisRepository.findByUser(current.get()); 
		  return new ResponseEntity<>(colis,HttpStatus.OK); 
	  }
	  @GetMapping("/tous/list") 
	  public ResponseEntity <List <Colis>>getAllColisRequests( ) {
 
		  
		  
		  List<Colis> colis = colisRepository.findAll();
		  
		  return new ResponseEntity<>(colis,HttpStatus.OK); 
	  }
	  
 
	  
	  @GetMapping("/find/{id}") 
	  public ResponseEntity <Colis>getColisById(@PathVariable("id") Long id){
		  Colis colis = colisRepository.findById(id).get(); 
		  return new ResponseEntity<>(colis,HttpStatus.OK); 
	  }
	  
	  
	  @GetMapping("/cords/{id}") 
	  public  CordsResponse findLivreurCords(@PathVariable("id") Long id){
		  CordsResponse cords= new CordsResponse();
		  
		  Colis colis = colisRepository.findById(id).get(); 
		  
		  //search for the colis livreur
		  Course course = this.courseRepository.findByColis(colis).get(0);
		  
		  User livreur = course.getLivreur();
		  
		  cords.setLatitude(livreur.getLatitute() );
		  cords.setLongitude(livreur.getLongitde()); 
		  

		  
		  return cords;
	  }
	  
	  
	  
	  @PostMapping("/add") public ResponseEntity<Colis> addColis(@RequestBody Colis colis,HttpServletRequest req) {
	        Optional<User> current;
	        String token = req.getHeader("authorization").replace("Bearer " ,"");
	        System.out.println(token);
	        String username=this.jwtProvider.getUserNameFromJwtToken(token);
	        current=this.userRepository.findByUsername(username);
	         
	        
	        colis.setUser(current.get());
	        
	        
	        // notifcation for the admin
	        long millis=System.currentTimeMillis();  
	        
			Notifications parcNotif = new Notifications();
			
			parcNotif.setTitle("Nouvel Colis");
			parcNotif.setMessage("Un client a ajouté une nouvelle commande, consultez-la.");
			 
			parcNotif.setAdddate(   new Date(millis)  );
			parcNotif.setSeen(false);
			parcNotif.setUser( this.userRepository.findByUsername("tchourabi@gmail.com").get() ) ;
			this.notificationsRepository.save(parcNotif);
		  
		  
		  Colis newColis = colisRepository.save(colis); 
		  
		  
		  
		  return new ResponseEntity<>(newColis, HttpStatus.OK);
	  }
	  
	  @PostMapping("/update") public ResponseEntity<Colis>updateColis(@RequestBody Colis tmp){ 
		  
		  
		  
		  Colis old = this.colisRepository.findById(tmp.getId()).get();
		  
		  old.setAdrDistinataire(tmp.getAdrDistinataire());
		  old.setAdrExpediteur(tmp.getAdrExpediteur());
		  old.setCodePostalDistinataire(tmp.getCodePostalDistinataire());
		  old.setCollect(tmp.isCollect());
		  old.setDescription(tmp.getDescription());
		  old.setGovDistinataire(tmp.getGovDistinataire());
		  old.setHauteur(tmp.getHauteur());
		  old.setLargeur(tmp.getLargeur());
		  old.setPoids(tmp.getPoids());
		  old.setUrgence(tmp.getUrgence());
		  old.setType(tmp.getType());
		  old.setVilleDistinataire(tmp.getVilleDistinataire());
		  
		  
		  colisRepository.save(old); 
		  
		  
		  
		  return new ResponseEntity<>(old, HttpStatus.OK);
	  }
	  
	  @GetMapping("/delete/{id}") public ResponseEntity<?> deleteColis(@PathVariable("id") Long id){ 
		  colisRepository.delete(colisRepository.findById(id).get());
	      return new ResponseEntity<>(HttpStatus.OK); 
	  } 
	  
	  
	  
	  @GetMapping("/delivered/{id}") 
	  public ResponseEntity <Colis>delivered(@PathVariable("id") Long id){
		  Colis colis = colisRepository.findById(id).get(); 
		  colis.setEtat(2);
		  
		  // send notif to user
	        long millis=System.currentTimeMillis();  
	        
			Notifications parcNotif = new Notifications();
			
			parcNotif.setTitle("Livraison de colis");
			parcNotif.setMessage("votre colis a été livré avec succès, merci d'utiliser nos services.");
			 
			parcNotif.setAdddate(   new Date(millis)  );
			parcNotif.setSeen(false);
			parcNotif.setUser( colis.getUser() ) ;
			this.notificationsRepository.save(parcNotif);
		  
		  
		  return new ResponseEntity<>(this.colisRepository.save(colis),HttpStatus.OK); 
	  }
	  
	  
}
