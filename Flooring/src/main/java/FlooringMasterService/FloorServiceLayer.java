package FlooringMasterService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import FloorMasterDto.Order;
import FloorMasterDto.Product;
import FloorMasterDto.Tax;
import FlooringMasterDao.DataPersistenceException;
import FlooringMasterDao.OrderDao;
import FlooringMasterDao.OrderDaoFileImpl;
import FlooringMasterDao.ProductDao;
import FlooringMasterDao.ProductFileDaoImpl;
import FlooringMasterDao.StateTaxDao;
import FlooringMasterDao.StateTaxDaoImpl;

import java.lang.Thread.State;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class FloorServiceLayer {

    OrderDao orderDao;
    StateTaxDao stateTaxDao;
    ProductDao productDao;
    //public Order newOrder;
    public Map<String, Order> orderList;
    Map<String, Tax> taxList;
    Map<String, Product> productList;
    String mode;

    public FloorServiceLayer() {
        orderDao = new OrderDaoFileImpl();
        stateTaxDao = new StateTaxDaoImpl();
        productDao = new ProductFileDaoImpl();
        this.mode = orderDao.getMode();
        //order = orderDao.loadOrderByDate(date)();

    }

    public FloorServiceLayer(OrderDao dao, ProductDao product, StateTaxDao stateTax) {
        this.orderDao = dao;
        this.productDao = product;
        this.stateTaxDao = stateTax;
        this.mode = orderDao.getMode();
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public Order addOrder(Order newOrder) { // yes for testing

        fetchProductDetails(newOrder);

        //setting configuration :Prod/training in Order
        newOrder.setMode(this.mode);

        //OrderNumber Increment Logic
        orderList = orderDao.loadOrderByDate(newOrder.getDate());
        int orderLength = 0;
        if (orderList == null || orderList.isEmpty() == true) {
            orderLength = 0;
        } else {
            TreeMap<String, Order> sorted = new TreeMap<>();
            // Copy all data from hashMap into TreeMap 
            sorted.putAll(orderList);
            String orderNumber = sorted.lastKey();
            orderLength = Integer.parseInt(orderNumber);
        }

        orderLength++;
        newOrder.setOrderNumber(String.valueOf(orderLength));

        //Tax calculation
        try {
            calculateTax(newOrder);
        } catch (DataPersistenceException | StateTaxValidationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //Calculation part
        calculateTotal(newOrder);

        orderDao.addOrder(newOrder);
        return newOrder;
    }

    private void fetchProductDetails(Order newOrder) {
        Product product = productDao.getSingleProduct(newOrder.getProductType());
        if (product == null) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        newOrder.setCostPerSquareFoot(product.getCostPerSquareFoot());
        newOrder.setLaborCostPerSquareFoot(product.getLaborCostPerSqFt());
    }

    public Order getOrderByOrderNumberAndDate(String orderNumber, LocalDate date) { // no testing
        orderList = orderDao.loadOrderByDate(date);
        Order order = orderList.get(orderNumber.trim());
        return order;
    }

    public Order editOrder(Order existingOrder) { //no need for test
        //setting configuration :Prod/training in Order
        existingOrder.setMode(this.mode);
        //Tax calculation
        try {
            if (existingOrder.getStateAbbr() != null && existingOrder.getStateAbbr().isEmpty() != true) {
                calculateTax(existingOrder);
            }
        } catch (DataPersistenceException | StateTaxValidationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //Calculation part
        calculateTotal(existingOrder);
        orderDao.editOrder(existingOrder);
        return existingOrder;

    }

    public Order getOrderByDate(LocalDate date, String orderNumber) { // no need for test
        Order order = orderDao.getSingleOrder(date, orderNumber);
        return order;
    }

    public Map<String, Order> getOrders(LocalDate date) { //no need for test
        Map<String, Order> orderMap = orderDao.getOrders(date);
        return orderMap;
    }

    public Order removeOrder(Order existingOrder, LocalDate date) { // no need for test

        //setting configuration :Prod/training in Order
        existingOrder.setMode(this.mode);

        orderDao.removeOrder(existingOrder, date);
        return existingOrder;
    }

    public void calculateTotal(Order order) {
        if (order.getArea() != null) {
            order.setMaterialCost(order.getCostPerSquareFoot().multiply(order.getArea())
                    .setScale(2, RoundingMode.HALF_UP));
            order.setLaborCost(order.getLaborCostPerSquareFoot().multiply(order.getArea())
                    .setScale(2, RoundingMode.HALF_UP));
        }
        if (order.getArea() != null) {
            order.setTax(order.getTaxRate().divide(new BigDecimal("100.00"))
                    .multiply((order.getMaterialCost().add(order.getLaborCostPerSquareFoot())))
                    .setScale(2, RoundingMode.HALF_UP));
        }
        if (order.getStateAbbr() != null && order.getStateAbbr().isEmpty() != true) {
            order.setTotal(order.getMaterialCost().add(order.getLaborCostPerSquareFoot()).add(order.getTax()));
        }
    }

    private void calculateTax(Order order) throws DataPersistenceException,
            StateTaxValidationException {
        Tax selectedState = stateTaxDao.getStateTax(order.getStateAbbr());
        if (selectedState == null) {
            throw new StateTaxValidationException("ERROR: SWG Corp: Unknown state.");
        }
        order.setStateAbbr(selectedState.getStateAbbr());
        order.setTaxRate(selectedState.getTaxRate());
    }

    private void validateOrderData(Order order) throws OrderValidationException {

        if (order.getCustomerName().trim().isEmpty() || order.getCustomerName() == null
                || order.getStateAbbr().trim().isEmpty() || order.getStateAbbr() == null
                || order.getProductType().trim().isEmpty() || order.getProductType() == null
                || order.getArea().compareTo(BigDecimal.ZERO) == 0 || order.getArea() == null) {
            throw new OrderValidationException("Empty fields are not allowed: ");
        }
    }
}
