/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vendingmachine.daos;

import com.vendingmachine.dtos.Item;
import com.vendingmachine.services.NoInventoryException;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author abdisamadugas
 */
public class VendingDaoTest {

    private VendingDao dao = new vendingDaoImpl("items.txt");

    public VendingDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {
        List<Item> itemList = dao.getAllItems();
        for (Item item : itemList) {
            dao.getAllItems();
        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetAllItems() throws Exception {
        assertEquals(4, dao.getAllItems().size());

    }


    @Test
    public void testGetUpdate() throws Exception {
        Item updateItem = new Item(1, "Water", 5, 80);
        updateItem.setQuantity(2);
        assertEquals(2, updateItem.getQuantity());

    }

    @Test
    public void testGetInventoryItems() throws VendingPersistenceException {
        Item item = dao.getItem("Coke");

        assertEquals(1, item.getId());
        assertEquals("Coke", item.getName());
        assertEquals(36, item.getQuantity());
        assertEquals(75, item.getPriceInPennies());
    }

}
