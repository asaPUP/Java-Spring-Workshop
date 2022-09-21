package com.uabc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uabc.entities.Actor;
import com.uabc.entities.Category;

public interface ActorRepository extends JpaRepository<Actor, Integer>{
	
	public Optional<Actor> findById(Integer id);

}
