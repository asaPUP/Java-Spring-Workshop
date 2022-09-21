package com.uabc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uabc.entities.Staff;


public interface StaffRepository extends JpaRepository<Staff, Integer>{
	
	public abstract Staff findByUsername(String userName);
}
