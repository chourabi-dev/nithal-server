package com.grokonez.jwtauthentication.repository;

import java.util.List;

import com.grokonez.jwtauthentication.entitys.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NotificationsRepository extends JpaRepository<Notifications, Long>{


}
