package com.vendingmachine.daos;

import com.vendingmachine.dtos.Item;
import java.util.List;
import java.util.Map;

public interface VendingDao {

    public List<Item> getAllItems();

    public void loadFile() throws VendingPersistenceException;

    public Item getItem(String itemName) throws VendingPersistenceException;

    public Item getUpdate() throws VendingPersistenceException;

    public boolean writeFile(List<Item> allItem) throws VendingPersistenceException;

    public Map<String, Item> getInventoryItems();

    public void updateProduct(Item itemId) throws VendingPersistenceException;

}
