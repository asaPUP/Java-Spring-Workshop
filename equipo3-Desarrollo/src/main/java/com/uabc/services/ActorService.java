package com.uabc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uabc.entities.Actor;
import com.uabc.repository.ActorRepository;

@Service
public class ActorService {
	
	@Autowired
	private ActorRepository actorRepository;
    
    public List<Actor> findAll() {
        return actorRepository.findAll();
    }
    
    public Actor insertActor(Actor actor) {
        return actorRepository.save(actor);
    }
}
