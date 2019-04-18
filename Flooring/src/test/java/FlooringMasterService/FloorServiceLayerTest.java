/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlooringMasterService;

import FloorMasterDto.Order;
import FlooringMasterController.FloorController;
import FlooringMasterDao.OrderDaoFileImpl;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
public class FloorServiceLayerTest {

    public FloorServiceLayer service;

    public FloorServiceLayerTest() {

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
    public void testGetOrderDao() {
    }

    @Test
    public void testSetOrderDao() {
    }

    @Test
    public void testAddOrder() {
        service = new FloorServiceLayer();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MM-dd-yyyy");

        Order order = new Order("2");
        order.setDate(LocalDate.parse("03-01-2019", df));
        order.setCustomerName("William");
        order.setStateAbbr("MN");
        order.setTaxRate(new BigDecimal("5.75"));
        order.setProductType("Carpet");
        order.setArea(new BigDecimal("200"));
        order.setCostPerSquareFoot(new BigDecimal("2.25"));
        order.setLaborCostPerSquareFoot(new BigDecimal("2.10"));
        order.setMaterialCost(new BigDecimal("20"));
        order.setLaborCost(new BigDecimal("20"));
        order.setTax(new BigDecimal("1.25"));
        order.setTotal(new BigDecimal("100"));

        service.addOrder(order);
        assertNotNull(order);
    }

    @Test
    public void testEditOrder() {
        service = new FloorServiceLayer();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        Order order = new Order("Prod");
        order.setOrderNumber("1");
        order.setDate(LocalDate.parse("03-01-2019", df));
        order.setCustomerName("John");
        order.setStateAbbr("OH");
        order.setTaxRate(new BigDecimal("6.25"));
        order.setProductType("Carpet");
        order.setArea(new BigDecimal("300"));
        order.setCostPerSquareFoot(new BigDecimal("2.25"));
        order.setLaborCostPerSquareFoot(new BigDecimal("2.10"));
        order.setMaterialCost(new BigDecimal("675.00"));
        order.setLaborCost(new BigDecimal("2.10"));
        order.setTax(new BigDecimal("42.32"));
        order.setTotal(new BigDecimal("719.42"));

        service.editOrder(order);
        assertEquals("John", order.getCustomerName());
    }

    @Test
    public void testCalculateTotal() {
        service = new FloorServiceLayer();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        Order order = new Order("Prod");
        order.setOrderNumber("1");
        order.setDate(LocalDate.parse("03-01-2019", df));
        order.setCustomerName("John");
        order.setStateAbbr("OH");
        order.setTaxRate(new BigDecimal("6.25"));
        order.setProductType("Carpet");
        order.setArea(new BigDecimal("300"));
        order.setCostPerSquareFoot(new BigDecimal("5.15"));
        order.setLaborCostPerSquareFoot(new BigDecimal("2.10"));
        order.setMaterialCost(new BigDecimal("675.00"));
        order.setLaborCost(new BigDecimal("2.10"));
        order.setTax(new BigDecimal("42.32"));
        //order.setTotal(new BigDecimal("719.42"));

        service.calculateTotal(order);

        assertNotNull(order.getTotal());
    }

}
