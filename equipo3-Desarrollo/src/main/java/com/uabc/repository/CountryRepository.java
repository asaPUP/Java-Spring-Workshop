package com.uabc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uabc.entities.City;
import com.uabc.entities.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {

}
