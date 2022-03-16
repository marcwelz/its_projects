package com.axa.ch.its.inventory.dao;


import com.axa.ch.its.inventory.model.InventoryDataNotFoundException;

public interface DaoInventoryGeneric<T> {
	public void save(T entry) throws InventoryDataNotFoundException;
	public void update(T entry) throws InventoryDataNotFoundException;
	public void delete(T entry) throws InventoryDataNotFoundException;
	public T getById(T entry) throws InventoryDataNotFoundException;
}
