package com.grokonez.jwtauthentication.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.grokonez.jwtauthentication.entitys.Vehicules;

public interface VehiculesRepository extends JpaRepository<Vehicules, Long> {

}
