package FlooringMasterDao;

import FloorMasterDto.Order;
import FloorMasterDto.Product;
import FloorMasterDto.Tax;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class OrderDaoFileImpl implements OrderDao {

    public static final String Product_file = "Products.txt";
    public static final String Taxes_file = "Taxes.txt";
    public static final String Training_file = "Mode.txt";
    public static final String DELIMITER = ",";
    public static final String FILE_MODE = "File_mode.txt";
    private Map<String, Order> order = new HashMap<String, Order>();

    Map<String, Tax> taxList;
    Map<String, Product> productList;

    public OrderDaoFileImpl() {

    }

    public Map<String, Order> getOrder() {
        return order;
    }

    public void setOrder(Map<String, Order> order) {
        this.order = order;
    }

    public Map<String, Tax> getTaxList() {
        return taxList;
    }

    public void setTaxList(Map<String, Tax> taxList) {
        this.taxList = taxList;
    }

    public Map<String, Product> getProductList() {
        return productList;
    }

    public void setProductList(Map<String, Product> productList) {
        this.productList = productList;
    }

    public Map<String, Order> loadOrderByDate(LocalDate date) {
        Map<String, Order> order = new HashMap<String, Order>();
        try {
            int month = date.getMonth().getValue();
            int day = date.getDayOfMonth();
            int year = date.getYear();
            String fileName = "Orders_" + month + day + year + ".txt";
            //System.out.println("FileName is" + fileName);
            File ItemFile = new File(fileName);

            if (!ItemFile.exists()) {
                System.out.println("No order exist for given date"); // throw exception
                order = new HashMap<String, Order>();
                return order;
            }

            Scanner scan = new Scanner(
                    new BufferedReader(
                            new FileReader(ItemFile)));

            while (scan.hasNextLine()) {
// All
                String line = scan.nextLine();
                String[] cells = line.split(",");
                Order ord = new Order();
                if (cells != null && cells.length >= 2) {
                    ord.setOrderNumber(cells[0]);
                    ord.setCustomerName(cells[1]);
                    ord.setStateAbbr(cells[2]);
                    ord.setTaxRate(new BigDecimal(cells[3]));
                    ord.setProductType(cells[4]);
                    ord.setArea(new BigDecimal(cells[5]));
                    ord.setCostPerSquareFoot(new BigDecimal(cells[6]));
                    ord.setLaborCost(new BigDecimal(cells[7]));
                    ord.setMaterialCost(new BigDecimal(cells[8]));
                    ord.setLaborCostPerSquareFoot(new BigDecimal(cells[9]));
                    if (cells.length > 10 && cells[10] != null) {
                        ord.setTax(new BigDecimal(cells[10]));
                    }
                    if (cells.length > 11 && cells[11] != null) {
                        ord.setTotal(new BigDecimal(cells[11]));
                    }
                    ord.setDate(date);                //Add setters for other fields
                    order.put(cells[0], ord);
                } else {
                    throw new FileNotFoundException("File is empty.");
                }
            }
        } catch (FileNotFoundException ex) {
            order = new HashMap<String, Order>();
        } catch (IOException ex) {
            order = new HashMap<String, Order>();
        }

        return order;

    }

    @Override
    public Map<String, Order> getOrders(LocalDate date) {
        return loadOrderByDate(date);

    }

    @Override
    public Order getSingleOrder(LocalDate dateInfo, String ordNumber) {
        Map<String, Order> map = loadOrderByDate(dateInfo);
        Order orderToBeSearched = null;
        if (map != null) {
            Iterator it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Order> pair = (Entry<String, Order>) it.next();
                if (pair.getKey().equals(ordNumber)) {
                    orderToBeSearched = pair.getValue();
                    break;
                }
            }
        }
        return orderToBeSearched;
    }

    public String getMode() {
        Scanner newScanner = null;

        try {
            newScanner = new Scanner(new BufferedReader(new FileReader(FILE_MODE)));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String currentMode;
        currentMode = newScanner.nextLine();
        newScanner.close();
        System.out.println("Mode is " + currentMode);
        return currentMode;

    }

    @Override
    public Order addOrder(Order ord) {
        order = loadOrderByDate(ord.getDate());
        order.put(ord.getOrderNumber(), ord);
        if (ord.getMode().equalsIgnoreCase("Prod")) {
            writeToFile(ord.getDate());
        }
        return ord;
    }

    @Override
    public Order editOrder(Order newOrder) {
        order = loadOrderByDate(newOrder.getDate());
        order.put(newOrder.getOrderNumber(), newOrder);
        if (newOrder.getMode().equalsIgnoreCase("Prod")) {
            writeToFile(newOrder.getDate());
        }
        return newOrder;
    }

    @Override
    public Order removeOrder(Order ord, LocalDate date) {
        Order removedOrder = null;
        order = loadOrderByDate(date);
        if (order != null) {
            Iterator it = order.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Order> pair = (Entry<String, Order>) it.next();
                if (pair.getKey().equals(ord.getOrderNumber())) {
                    removedOrder = order.remove(ord.getOrderNumber());
                    break;
                }
            }
        }
        if (ord.getMode().equalsIgnoreCase("Prod")) {
            writeToFile(date); // get order by date
        }
        return removedOrder;
    }

    public boolean writeToFile(LocalDate date) {
        PrintWriter out = null;
        int month = date.getMonth().getValue();
        int day = date.getDayOfMonth();
        int year = date.getYear();
        String fileName = "Orders_" + month + day + year + ".txt";

        try {
            out = new PrintWriter(new FileWriter(fileName));
        } catch (IOException e) {
            System.out.println("Unable to write to file");// no sysout
//            throw new DataPersistenceException("Failed to write to msg");
        }
        if (order.size() > 0) {
            Iterator it = order.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Order> pair = (Entry<String, Order>) it.next();
                Order currentOrder = pair.getValue();
                out.println(currentOrder.getOrderNumber() + DELIMITER
                        + currentOrder.getCustomerName() + DELIMITER
                        + currentOrder.getStateAbbr() + DELIMITER
                        + currentOrder.getTaxRate() + DELIMITER
                        + currentOrder.getProductType() + DELIMITER
                        + currentOrder.getArea() + DELIMITER
                        + currentOrder.getCostPerSquareFoot() + DELIMITER
                        + currentOrder.getLaborCostPerSquareFoot() + DELIMITER
                        + currentOrder.getMaterialCost() + DELIMITER
                        + currentOrder.getLaborCostPerSquareFoot() + DELIMITER
                        + currentOrder.getTax() + DELIMITER
                        + currentOrder.getTotal());
            }
        } else {
            out.println("");
        }

        out.flush();
        out.close();
        return true; // 
    }

}
