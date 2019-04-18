package com.vendingmachine.ui;

import com.vendingmachine.dtos.Change;
import com.vendingmachine.dtos.Item;
import com.vendingmachine.services.VendingMachineService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class VendingMachineView {

    VendingMachineView service;

    public static void run(VendingMachineService service) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    UserIO io;
    public Object view;
    public BigDecimal userMoney;

    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("  Main Menu \n");
        io.print("===============\n");
        io.print("1. List items \n");
        io.print("2. Enter money \n");
        io.print("3. Vend selected item \n");
        io.print("4. return change \n");
        io.print("5. Exit \n");

        return io.readInt("Please select from the above choices. ", 1, 5);
    }

    public void displayExit() {
        io.print("EXIT PROGRAM");
    }

    public void printAllItems(List<Item> allItems) {
        allItems.forEach(individualItem -> printItem(individualItem));
        io.readString("Please Enter to continue: ");

    }

    public void enterMoney(Change totalCash) {
        io.print("\n");
        io.print(" Dollars: " + totalCash.getDollars() + "\n");
        io.print(" Quaters: " + totalCash.getQuarters() + "\n");
        io.print(" Dimes: " + totalCash.getDimes() + "\n");
        io.print(" Nickles: " + totalCash.getNickels() + "\n");
        io.print(" Pennies: " + totalCash.getPennies() + "\n");
        io.print("\n");

    }

    public int userChoice() {
        return io.readInt("Please Select an Item:\n");

    }

    public void insert() {
        io.print("Insert money into the machine first!\n");
    }

    public void printItem(Item individualItem) {
        if (individualItem != null && individualItem.getQuantity() > 0) {

            io.print("Item ID: " + individualItem.getId());
            io.print(" | ");
            io.print("Item Name: " + individualItem.getName());
            io.print(" | ");
            io.print("Item Price: " + individualItem.getPrice());
            io.print(" | ");
            io.print("Item Quantity: " + individualItem.getQuantity());
            io.print("\n\n");
        } else {
            io.print("It doesn't exist ");

        }

    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");

    }

    public void displayItemOutOfStock() {
        io.print("Item out of stock: \n ");
    }

    public void displayNoFund() {
        io.print("Sorry! You don't have enough money: \n\n");
    }

    public void displayNoMoneyEntered() {
        io.print("Unknown Command!!!");

    }

    public void displayNoSelectionMade() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void enterMoney(BigDecimal userDollar) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void displayEnterMoneyBanner() {
        io.print(" inserted money");
    }

    public void displayEnterMoneyBanner(BigDecimal userChange) {
        io.print("Inserted:");

    }

    public BigDecimal readMoney() {
        return io.readMoney("How much money do you want to insert? \n");

    }

}
