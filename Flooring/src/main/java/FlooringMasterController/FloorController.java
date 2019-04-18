package FlooringMasterController;

import FloorMasterDto.Order;
import FlooringMasterService.FloorServiceLayer;
import FlooringMasterUI.FlooringView;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FloorController {

    FloorServiceLayer service;
    FlooringView view;

    public FloorController(FloorServiceLayer service, FlooringView view) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;

        while (keepGoing) {

            menuSelection = view.printMenuGetSelectin();

            switch (menuSelection) {
                case 1:
                    displayOrderByDate();
                    break;
                case 2:
                    addOrder();
                    break;
                case 3:
                    editOrder();
                    break;
                case 4:
                    removeOrder();
                    break;
                case 5:
                    keepGoing = false;
                    break;
                default:
                    unknowCommand();

            }
        }
        exitMessage();
    }

    private void displayOrderByDate() {
        LocalDate date = view.getOrderDateForDisplay();
        view.displayOrderByDateBanner(date);
        Map<String, Order> orders = service.getOrders(date);
        view.displayOrderList(orders);
    }

    private void addOrder() {
        view.displayAddOrdersBanner();
        Order newOrder = view.getOrderInfo();
//        service.addOrder(newOrder);
        if (view.displayUserInputForAddOrder().trim().equalsIgnoreCase("Y")) {
            service.addOrder(newOrder);
            view.displaySuccessfullyAddedBanner();

        } else {
            view.displayValidDataMsg();

        }
    }

    private void editOrder() {
        view.displayEditOrderBanner();
        Order newOrder = view.getOrderByDate();
        Order existingOrder = service.getOrderByOrderNumberAndDate(newOrder.getOrderNumber(), newOrder.getDate());
        Order editedOrder = null;
        Map<String, Order> orders = new HashMap<>();
        if (existingOrder != null) {
            orders.put(existingOrder.getOrderNumber(), existingOrder);
            view.displayMessageForExistingOrder();
            view.displayOrderList(orders);

            editedOrder = view.getOrderInfoForEdit(existingOrder);

        } else {
            view.displayNoSuchOrderToUser();
        }
        if (editedOrder != null) {
            service.editOrder(editedOrder);
        }
        view.displaySuccessfullyEditedBanner();

    }

    private void removeOrder() {
        view.displayRemoveOrderBanner();
        while (true) {
            Order editedOrder = view.getOrderByDate();
            Order existingOrder = service.getOrderByOrderNumberAndDate(editedOrder.getOrderNumber(), editedOrder.getDate());
            Map<String, Order> orders = new HashMap<>();
            if (existingOrder != null) {
                orders.put(existingOrder.getOrderNumber(), existingOrder);
                view.displayMessageForExistingOrder();
                view.displayOrderList(orders);
                if (view.displayUserInputForRemove().trim().equalsIgnoreCase("Y")) {
                    service.removeOrder(existingOrder, editedOrder.getDate());
                    view.displaySuccessfullyRemovedBanner();
                }
                break;
            } else {
                view.displayValidDataMsg();
            }
        }

    }

    private void unknowCommand() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

}
