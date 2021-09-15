package com.grokonez.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grokonez.jwtauthentication.entitys.Colis;
import com.grokonez.jwtauthentication.entitys.Course;
import com.grokonez.jwtauthentication.model.User;

public interface CourseRepository extends JpaRepository<Course,Long> {
	public List<Course> findByLivreur(User livreur);
	public List<Course> findByColis(Colis colis);
	
	
	
}
