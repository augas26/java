package com.vendingmachine.services;

import com.vendingmachine.daos.VendingPersistenceException;
import com.vendingmachine.daos.VendingDao;
import com.vendingmachine.daos.vendingDaoImpl;

import static com.vendingmachine.daos.vendingDaoImpl.DELIMITER;
import static com.vendingmachine.daos.vendingDaoImpl.VENDING_FILE;
import com.vendingmachine.dtos.Change;
import com.vendingmachine.dtos.Item;
import com.vendingmachine.dtos.VendingMachine;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VendingMachineService {

    public static final String VENDING_FILE = "items.txt";
    VendingDao daos = new vendingDaoImpl(VENDING_FILE);

    VendingMachine machine = new VendingMachine();

    public VendingMachineService(VendingDao daos) {
        this.daos = daos;
    }

    public VendingMachineService() {

    }

    public List<Item> getAllItems() {
        return daos.getAllItems();

    }

    public Change getChange(Item itemCost, int userMoney) {
        int returnChange = userMoney - itemCost.getPriceInPennies();
        Change changeReturn = new Change(returnChange);
        return changeReturn;
    }

    public void checkInventory(Item item) throws NoInventoryException {
        if (item.getQuantity() <= 0) {
            throw new NoInventoryException("Item not available");
        }

    }

    public boolean checkFunds(BigDecimal itemCost, BigDecimal userMoney) throws InsufficentFundsException {
        if (itemCost.compareTo(userMoney) > 0) {
            throw new InsufficentFundsException("insufficent funds");
        }
        return true;

    }

    public void updateProduct(Item itemId, List<Item> allItems) throws VendingPersistenceException {
        itemId.setQuantity(itemId.getQuantity() - 1);
        daos.writeFile(allItems);
    }

    public void enterMoney(BigDecimal userChange) {
        machine.enterMoney(userChange);
    }

    public void checkInventory(Object choice) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
