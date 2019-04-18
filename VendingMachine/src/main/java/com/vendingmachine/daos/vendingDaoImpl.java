package com.vendingmachine.daos;

import com.vendingmachine.dtos.Item;
import com.vendingmachine.dtos.VendingMachine;
import com.vendingmachine.services.InsufficentFundsException;
import com.vendingmachine.services.VendingMachineService;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class vendingDaoImpl implements VendingDao {

    public static final String VENDING_FILE = "items.txt";
    Map<String, Item> inventoryItems = new HashMap();
    private Object daos;

    public Map<String, Item> getInventoryItems() {
        return inventoryItems;
    }

    public void setInventoryItems(Map<String, Item> inventoryItems) {
        this.inventoryItems = inventoryItems;
    }

    public static final String DELIMITER = "::";

    String path;

    public vendingDaoImpl(String path) {
        this.path = path;
    }

    @Override
    public List<Item> getAllItems() {
        List<Item> toReturn = new ArrayList<Item>();

        try {

            File ItemFile = new File(path);

            if (!ItemFile.exists()) {
                ItemFile.createNewFile();
            }

            Scanner scan = new Scanner(
                    new BufferedReader(
                            new FileReader(ItemFile)));

            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] cells = line.split("::");
                Item toAdd = new Item();
                toAdd.setId(Integer.parseInt(cells[0]));
                toAdd.setName(cells[1]);
                toAdd.setQuantity(Integer.parseInt(cells[2]));
                toAdd.setPriceInPennies(Integer.parseInt(cells[3]));
                toReturn.add(toAdd);
            }
        } catch (FileNotFoundException ex) {
            toReturn = null;
        } catch (IOException ex) {
            toReturn = null;
        }

        return toReturn;
    }

    @Override
    public void loadFile() throws VendingPersistenceException {
        Scanner scan;
        try {
            scan = new Scanner(new BufferedReader(new FileReader(VENDING_FILE)));
        } catch (FileNotFoundException ex) {
            throw new VendingPersistenceException(
                    "Could not save student data.", ex);

        }

        String currentLine;
        String[] currentTokens;

        while (scan.hasNextLine()) {
            currentLine = scan.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            if (currentTokens.length == 4) {

                Item currentItem = new Item();
                currentItem.setId(Integer.parseInt(currentTokens[0]));
                currentItem.setName(currentTokens[1]);
                currentItem.setPriceInPennies(Integer.parseInt(currentTokens[3]));
                currentItem.setQuantity(Integer.parseInt(currentTokens[2]));
                inventoryItems.put(currentItem.getName(), currentItem);
            }
        }

    }

    @Override
    public Item getItem(String itemName) throws VendingPersistenceException {

        loadFile();
        return inventoryItems.get(itemName);
    }

    public boolean writeFile(List<Item> allItem) throws VendingPersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(VENDING_FILE));
        } catch (IOException e) {
            throw new VendingPersistenceException(
                    "Could not save student data.", e);
        }

        for (Item currentItem : allItem) {

            out.println(currentItem.getId() + DELIMITER
                    + currentItem.getName() + DELIMITER
                    + currentItem.getQuantity() + DELIMITER
                    + currentItem.getPriceInPennies());

            out.flush();
        }

        out.close();
        return true; // Question
    }

    public void checkFunds(BigDecimal itemCost, BigDecimal userMoney) {
        if (itemCost.compareTo(userMoney) > 0) {
            try {
                throw new InsufficentFundsException("You don't have insufficent funds");
            } catch (InsufficentFundsException ex) {
                Logger.getLogger(VendingMachineService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public Item getUpdate() throws VendingPersistenceException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateProduct(Item toUpdate) throws VendingPersistenceException {
//          itemId.setQuantity(toUpdate.getQuantity() - 1);

//          int newQty = toUpdate.getQuantity() - 1;
        inventoryItems.get(toUpdate.getName()).setQuantity(toUpdate.getQuantity());

        List<Item> allItems = new ArrayList<Item>(inventoryItems.values());

        boolean success = writeFile(allItems);

    }

}
