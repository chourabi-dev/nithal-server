package com.grokonez.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grokonez.jwtauthentication.entitys.Colis;
import com.grokonez.jwtauthentication.model.User;

public interface ColisRepository extends JpaRepository<Colis,Long> {
	public List<Colis> findByUser(User user);
}
