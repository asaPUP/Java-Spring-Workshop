package com.uabc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uabc.entities.Language;
import com.uabc.repository.LanguageRepository;

@Service
public class LanguageService implements ILanguageService{

	@Autowired
	private LanguageRepository languageRepository;
	
	public List<Language> findAll(){
		return languageRepository.findAll();
	}
	
	@Override
	public Optional<Language> findById(Integer id) {
		return languageRepository.findById(id);
	}

}
