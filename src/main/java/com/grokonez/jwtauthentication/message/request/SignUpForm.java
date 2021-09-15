package com.grokonez.jwtauthentication.message.request;

import java.util.Set;

import javax.validation.constraints.*;

public class SignUpForm {
    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @NotBlank
    @Size(min = 3, max = 50)
    @Email
    private String username;

 
    private Set<String> role;
    
 
    
    @NotBlank
    @Size(min = 6, max = 40)
    private String cindId;
    
    
   
    private String phone;
    
    
    

    

    public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCindId() {
		return cindId;
	}

	public void setCindId(String cindId) {
		this.cindId = cindId;
	}


	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

 

 

 
    public Set<String> getRole() {
    	return this.role;
    }
    
    public void setRole(Set<String> role) {
    	this.role = role;
    }
}