/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlooringMasterDao;

import FloorMasterDto.Order;
import FloorMasterDto.Tax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
public class OrderDaoFileImplTest {

    public OrderDao dao;

    public OrderDaoFileImplTest() {

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
//        List<Order> allOrders = dao.getOrders();
//        for (Order order : allOrders){
//            dao.removeOrder(order.getOrderNumumber());

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getOrder method, of class OrderDaoFileImpl.
     */
    @Test
    public void testGetOrder() {
    }

    /**
     * Test of setOrder method, of class OrderDaoFileImpl.
     */
    @Test
    public void testSetOrder() {
    }

    /**
     * Test of getTaxList method, of class OrderDaoFileImpl.
     */
    @Test
    public void testGetTaxList() {
    }

    /**
     * Test of setTaxList method, of class OrderDaoFileImpl.
     */
    @Test
    public void testSetTax() {
        Tax taxData = new Tax();
        taxData.setStateAbbr("OH");
        taxData.setTaxRate(new BigDecimal(6.25));

//        dao.setStateAbbr(taxData.getStateAbbr());
        assertEquals("OH", taxData.getStateAbbr());
    }

    /**
     * Test of getProductList method, of class OrderDaoFileImpl.
     */
    @Test
    public void testGetProductList() {
    }

    /**
     * Test of setProductList method, of class OrderDaoFileImpl.
     */
    @Test
    public void testSetProductList() {
    }

    /**
     * Test of loadOrderByDate method, of class OrderDaoFileImpl.
     */
    @Test
    public void testLoadOrderByDate() {
    }

    /**
     * Test of getOrders method, of class OrderDaoFileImpl.
     */
    @Test
    public void testGetOrders() {
    }

    /**
     * Test of getSingleOrder method, of class OrderDaoFileImpl.
     */
    @Test
    public void testGetSingleOrder() {
    }

    /**
     * Test of addOrder method, of class OrderDaoFileImpl.
     */
    @Test
    public void testAddOrder() {
        dao = new OrderDaoFileImpl();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MM-dd-yyyy");

        Order order = new Order("2");
        order.setDate(LocalDate.parse("03-01-2019", df));
        order.setCustomerName("William");
        order.setStateAbbr("MN");
        order.setTaxRate(new BigDecimal(5.75));
        order.setProductType("Carpet");
        order.setArea(new BigDecimal(200));
        order.setCostPerSquareFoot(new BigDecimal(2.25));
        order.setLaborCostPerSquareFoot(new BigDecimal(2.10));
        order.setMaterialCost(new BigDecimal(20));
        order.setLaborCost(new BigDecimal(20));
        order.setTax(new BigDecimal(1.25));
        order.setTotal(new BigDecimal(100));

        dao.addOrder(order);
        assertNotNull(order);
    }

    /**
     * Test of editOrder method, of class OrderDaoFileImpl.
     */
    @Test
    public void testEditOrder() {
        dao = new OrderDaoFileImpl();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        Order order = new Order("Prod");
        order.setOrderNumber("1");
        order.setDate(LocalDate.parse("03-01-2019", df));
        order.setCustomerName("Lee2");
        order.setStateAbbr("OH");
        order.setTaxRate(new BigDecimal(6.25));
        order.setProductType("Carpet");
        order.setArea(new BigDecimal(300));
        order.setCostPerSquareFoot(new BigDecimal(2.25));
        order.setLaborCostPerSquareFoot(new BigDecimal(2.10));
        order.setMaterialCost(new BigDecimal(675.00));
        order.setLaborCost(new BigDecimal(2.10));
        order.setTax(new BigDecimal(42.32));
        order.setTotal(new BigDecimal(719.42));

        dao.editOrder(order);
        assertEquals("Lee2", order.getCustomerName());
    }

    @Test
    public void testRemoveOrder() {
        dao = new OrderDaoFileImpl();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        Order order1 = new Order("Prod");
        order1.setDate(LocalDate.parse("03-01-2019", df));
        order1.setOrderNumber("10");
        order1.setCustomerName("jama");
        order1.setStateAbbr("MN");
        order1.setTaxRate(new BigDecimal(5.75));
        order1.setProductType("Wood");
        order1.setArea(new BigDecimal(500));
        order1.setCostPerSquareFoot(new BigDecimal(5.15));
        order1.setLaborCostPerSquareFoot(new BigDecimal(4.75));
        order1.setMaterialCost(new BigDecimal(2575.00));
        order1.setLaborCost(new BigDecimal(4.75));
        order1.setTax(new BigDecimal(148.34));
        order1.setTotal(new BigDecimal(2728.09));

        dao.addOrder(order1);
        int length = dao.getOrders(LocalDate.parse("03-01-2019", df)).size();
        dao.removeOrder(order1, LocalDate.parse("03-01-2019", df));
        assertEquals(length - 1, dao.getOrders(LocalDate.parse("03-01-2019", df)).size());

    }

    /**
     * Test of writeToFile method, of class OrderDaoFileImpl.
     */
    @Test
    public void testWriteToFile() {
    }

}
