package com.uabc.services;

import com.uabc.entities.Inventory;

public interface IInventoryService {
	
	public Inventory findByInventoryId(Integer inventoryId);
}
