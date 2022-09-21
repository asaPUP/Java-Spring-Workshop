package com.uabc.services;

import java.util.Optional;

import com.uabc.entities.Language;

public interface ILanguageService {

	public Optional<Language> findById(Integer id);
}