package FlooringMasterUI;

import FloorMasterDto.Order;
import FlooringMasterController.FloorController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class FlooringView {

    public UserIO io;
    public static final String DELIMITER = "\t";

    public FlooringView(UserIO io) {
        this.io = io;
    }

    public int printMenuGetSelectin() {
        io.print("\nSWG Corporation");
        io.print("   Main Menu");
        io.print("==================");
        io.print("1. Display Orders");
        io.print("2. Add an Order");
        io.print("3. Edit an Order");
        io.print("4. Remove an Order");
        io.print("5. Exit");

        return io.readInt("Select from the above choices:", 1, 5);
    }

    public LocalDate getOrderDateForDisplay() {
        String date = io.readString("Please enter order date");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        //Convert
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate;

    }

    public Order getOrderInfo() {
        String customerName = io.readString("Please enter customer name: ");
        String stateAbbr = io.readString("Please enter state abbreviation:");
        String date = io.readString("Please enter order date");
        String productType = io.readString("Please enter productType");
        String area = io.readString("Please enter area");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        //convert String to LocalDate
        LocalDate localDate = LocalDate.parse(date, formatter);

        Order currentOrder = new Order();

        currentOrder.setCustomerName(customerName);
        currentOrder.setStateAbbr(stateAbbr);
        currentOrder.setDate(localDate);
        currentOrder.setProductType(productType);
        currentOrder.setArea(new BigDecimal(area));
        return currentOrder;

    }

    public void displayOrderList(Map<String, Order> orderMap) {
        if (orderMap == null) {
            io.print("File is empty");
        } else if (orderMap.size() == 0) {
            io.print("No order found for given date");
        } else {
            Iterator it = orderMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Order> pair = (Entry<String, Order>) it.next();
                Order currentOrder = pair.getValue();
                io.print(currentOrder.getOrderNumber() + DELIMITER
                        + currentOrder.getCustomerName() + DELIMITER
                        + currentOrder.getStateAbbr() + DELIMITER
                        + currentOrder.getTaxRate() + DELIMITER
                        + currentOrder.getProductType() + DELIMITER
                        + currentOrder.getArea() + DELIMITER
                        + currentOrder.getCostPerSquareFoot() + DELIMITER
                        + currentOrder.getLaborCostPerSquareFoot() + DELIMITER
                        + currentOrder.getMaterialCost() + DELIMITER
                        + currentOrder.getlaborCost() + DELIMITER
                        + currentOrder.getTax() + DELIMITER
                        + currentOrder.getTotal());
                io.print("\n");
            }
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayMessageForExistingOrder() {
        io.print("Existing order details are: ");
    }

    public String displayUserInputForRemove() {
        io.print("Are you sure you want to remove order. ");
        String input = io.readString("Please enter Y/N.");
        return input;

    }

    public void displayContinue() {
        io.readString("Please enter to continue. ");
    }

    public void displayAddOrdersBanner() {
        io.print("===Display Order===");
    }

    public void displaySuccessfullyAddedBanner() {
        io.print("Successfully added\n");
    }

    public void displayEditOrderBanner() {
        io.print("Edit Order");
    }

    public void displayNoSuchOrderToUser() {
        io.print("No such order exists.");
    }

    public void displaySuccessfullyEditedBanner() {
        io.print("Successfully Edited\n");
    }

    public void displaySuccessfullyRemovedBanner() {
        io.print("Successfully Removed\n");
    }

    public Order getOrderInfoForEdit(Order currentOrder) {
        io.print("Enter below details to edit an order");
        String customerName = io.readString(" Update customer name Or press 'Enter' to keep the current name: ");
        String stateAbbr = io.readString("Please enter state abbreviation:");
        String productType = io.readString("Please enter productType:");
        String area = io.readString("Please enter area:");

        if (customerName != null && customerName != "" && customerName.length() != 0) {
            currentOrder.setCustomerName(customerName);
        }
        if (stateAbbr != null && stateAbbr.isEmpty() != true && stateAbbr != "" && stateAbbr.length() != 0) {
            currentOrder.setStateAbbr(stateAbbr);
        }
        if (productType != null && productType != "" && productType.length() != 0) {
            currentOrder.setProductType(productType);
        }
        if (area != null && area.isEmpty() != true && area != " " && Integer.parseInt(area) != 0) {
            currentOrder.setArea(new BigDecimal(area));
        }
        return currentOrder;

    }

    public Order getOrderByDate() {
        String orderNumber = io.readString("Please enter Order Number: ");
        String date = io.readString("Please enter order date as shown 'MM-dd-yyyy' ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        //convert String to LocalDate
        LocalDate localDate = LocalDate.parse(date, formatter);

        Order currentOrder = new Order();
        currentOrder.setOrderNumber(orderNumber);
        currentOrder.setDate(localDate);
        return currentOrder;

    }

    public void displayValidDataMsg() {
        io.print("Order not added.");
    }

    public void displayRemoveOrderBanner() {
        io.print("===Remove Order===");
    }

    public void displayExitBanner() {
        io.print("Thank you. Good Bye!!!");
    }

    public void displayOrderByDateBanner(LocalDate selectedDate) {
        io.print("===Display Order===");

    }

    public String displayUserInputForAdd() {
        io.print("===Are you sure you want to add:");
        String input = io.readString("Please enter Y/N.");
        return input;
    }

    public String displayUserInputForEditOrder() {
        io.print("===Are you sure you want to edit:");
        String input = io.readString("Please enter Y/N.");
        return input;

    }

    public String displayUserInputForAddOrder() {
        io.print("===Are you sure you want to add:");
        String input = io.readString("Please enter Y/N.");
        return input;
    }

    public void displaySuccessfullyAddBanner() {
        io.print("Successfully Edited");
    }

}
