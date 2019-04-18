/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vendingmachine.services;

import com.vendingmachine.daos.VendingDao;
import com.vendingmachine.daos.vendingDaoImpl;
import com.vendingmachine.dtos.Change;
import com.vendingmachine.dtos.Item;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author abdisamadugas
 */
public class VendingMachineServiceTest {

//    VendingDao dao = new vendingDaoImpl("items.txt");
//    VendingMachineService service = new VendingMachineService(dao);
    VendingMachineService service;

    public VendingMachineServiceTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("vendingMachineService", VendingMachineService.class);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetAllItems() throws Exception {
        List<Item> items = service.getAllItems();
        assertEquals(4, items.size());
    }

    @Test
    public void testGetChange() throws Exception {
        Item item = new Item(4, "water", 2, 80);
        BigDecimal userMoney = BigDecimal.valueOf(2);
        Change change = service.getChange(item, 200);
        assertEquals(change.getDollars(), 1);
        assertEquals(change.getDimes(), 2);

    }

    @Test
    public void testCheckInventory() {
        Item checkInventory = new Item(3, "Pepsi", 5, 100);
        try {
            service.checkInventory(checkInventory);
        } catch (NoInventoryException ex) {

        }
    }

    @Test
    public void testCheckFunds() throws Exception {
        Item checkItem = new Item(3, "Pepsi", 50, 10);
        BigDecimal enterMoney = BigDecimal.valueOf(1);

        try {
            service.checkFunds(checkItem.getPrice(), enterMoney);
//            fail();
        } catch (InsufficentFundsException e) {

        }

    }

    @Test
    public void testUpdateProduct() throws Exception {
        Item updateItem = new Item(1, "Water", 5, 1);
        updateItem.setQuantity(0);
        assertEquals(0, updateItem.getQuantity());
    }

}
