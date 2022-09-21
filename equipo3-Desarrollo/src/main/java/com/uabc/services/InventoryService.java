package com.uabc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uabc.entities.Inventory;
import com.uabc.repository.InventoryRepository;

@Service
public class InventoryService implements IInventoryService {

	@Autowired
	InventoryRepository inventoryRepository;

	@Override
	public Inventory findByInventoryId(Integer inventoryId) {
		return inventoryRepository.findByInventoryId(inventoryId);
	}

}