package com.grokonez.jwtauthentication.controller;

import java.nio.file.attribute.UserPrincipal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.grokonez.jwtauthentication.message.request.CordsUpdate;
import com.grokonez.jwtauthentication.message.request.EditRequest;
import com.grokonez.jwtauthentication.message.request.LoginForm;
import com.grokonez.jwtauthentication.message.request.SignUpForm;
import com.grokonez.jwtauthentication.message.response.JwtResponse;
import com.grokonez.jwtauthentication.model.Role;
import com.grokonez.jwtauthentication.model.RoleName;
import com.grokonez.jwtauthentication.model.User;
 
import com.grokonez.jwtauthentication.repository.RoleRepository;
import com.grokonez.jwtauthentication.repository.UserRepository;
import com.grokonez.jwtauthentication.security.jwt.JwtProvider;
import com.grokonez.jwtauthentication.security.services.FilesStorageService;
import com.grokonez.jwtauthentication.services.EmailService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {

	private final Path root = Paths.get("uploads");
	
	
	@Autowired
	  FilesStorageService storageService;
	
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;
    

 

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;
    
    
    @Autowired
    EmailService emailService;
    
 

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }
    
    @GetMapping("/info")
    public Optional<User>   getCurrentUser(HttpServletRequest req) {
        Optional<User> current;
        String token = req.getHeader("authorization").replace("Bearer " ,"");
        System.out.println(token);
        String username=this.jwtProvider.getUserNameFromJwtToken(token);
        current=this.userRepository.findByUsername(username);
        return current;
        
    }
    
    
    
    

    
    
    
    
    
    
    
    @PostMapping("/signup")
    public ResponseEntity <?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<String>("Fail -> Email is already taken!",
                    HttpStatus.BAD_REQUEST);
        }

 

        // Creating user's account
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),  encoder.encode( "123456" ));
        
        user.setCinId(signUpRequest.getCindId());
        user.setPhone(signUpRequest.getPhone());
        
        
        
        

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
        	switch(role) {
	    		case "admin":
	    			Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
	                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
	    			roles.add(adminRole);
	    			
	    			break;
	    		case "pm":
	            	Role pmRole = roleRepository.findByName(RoleName.ROLE_PM)
	                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
	            	roles.add(pmRole);
	            	
	    			break;
	    		default:
	        		Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
	                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
	        		roles.add(userRole);        			
        	}
        });
        
        user.setRoles(roles);
        userRepository.save(user);
        
        try {
        	this.emailService.sendNewPasswordEmail(user.getName(), "123456", user.getUsername());
        }catch(Exception e) {
        	System.out.print(e.getMessage());
        }
        
        
        
        HashMap res = new HashMap();
        res.put("success", true);

        return ResponseEntity.ok().body(res);
    }
    
    @PostMapping("/update")
    public ResponseEntity <?> updateUser(@Valid @RequestBody EditRequest EditRequest) {
       
       

        // Creating user's account
        User user = this.userRepository.findById(EditRequest.getId()).get();
        
        user.setCinId(EditRequest.getCindId());

        Set<String> strRoles = EditRequest.getRole();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
        	switch(role) {
        		// admin
	    		case "ROLE_ADMIN":
	    			Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
	                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
	    			roles.add(adminRole);
	    			
	    			break;
	    		// livreur
	    		case "ROLE_PM":
	            	Role pmRole = roleRepository.findByName(RoleName.ROLE_PM)
	                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
	            	roles.add(pmRole);
	            	
	    			break;
	    		// user
	    		default:
	        		Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
	                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
	        		roles.add(userRole);        			
        	}
        });
        
        user.setRoles(roles);
        user.setName(EditRequest.getName()); 
        
        
        
        
        
        System.out.println(user.getName());
        
        userRepository.save(user);
        
        HashMap res = new HashMap();
        res.put("success", true);

        return ResponseEntity.ok().body(res);
    }
    
    /*@PostMapping("/upload-cv/{id}")
    public void uploadCV(@PathVariable(value ="id") Long id,@RequestParam("file") MultipartFile file) {
    	
    	
    	 
        try {
          storageService .save(file,"employee-".concat(id.toString()));
          User u = this.userRepository.findById(id).get();
      	  u.setCvPath("/api/auth/files/"+"employee-".concat(id.toString()).concat(file.getOriginalFilename())  );
      	  

      	this.userRepository.save(u);

        } catch (Exception e) { 

        }
        
        
    }*/
    
    /*
    @PostMapping("/project/upload-file/{project-id}")
    public void uploadProjectFile(@PathVariable(value ="project-id") Long project_id,@RequestParam("file") MultipartFile file) {
    	
    	
    	 
        try {
          storageService .save(file,"project-".concat(project_id.toString()));
          Projects p = this.projectsRepository.findById(project_id).get();
          
          
          
          ProjectFiles pf = new ProjectFiles();
          pf.setUrl("/api/auth/files/"+"project-".concat(project_id.toString()).concat(file.getOriginalFilename()) );
          pf.setProject(p);
          
          this.projectFilesRepository.save(pf);
      	  
          

        } catch (Exception e) { 

        }
        
        
    }
    
    
    
    @GetMapping("/files/{filename}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
      Resource file = storageService.load(filename);
      return ResponseEntity.ok()
          .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
    */
    
    
    
    @PostMapping("/cords/update")
    public ResponseEntity <?> updateUserCords(@Valid @RequestBody CordsUpdate cords, HttpServletRequest req) {
        Optional<User> current;
        String token = req.getHeader("authorization").replace("Bearer " ,"");
        System.out.println(token);
        String username=this.jwtProvider.getUserNameFromJwtToken(token);
        current=this.userRepository.findByUsername(username);
         
       

        // Creating user's account
         User user = current.get();
         
        
        user.setLatitute(cords.getAltitude());
        user.setLongitde(cords.getLongitde());
        
         
        
        
        userRepository.save(user);
        
        HashMap res = new HashMap();
        res.put("success", true);

        return ResponseEntity.ok().body(res);
    }

    
}