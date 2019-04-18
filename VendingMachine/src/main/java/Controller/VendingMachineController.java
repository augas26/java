package Controller;

import com.vendingmachine.daos.VendingDao;
import com.vendingmachine.dtos.Change;
import com.vendingmachine.dtos.Item;
import com.vendingmachine.services.InsufficentFundsException;
import com.vendingmachine.services.NoInventoryException;

import com.vendingmachine.services.VendingMachineService;
import com.vendingmachine.daos.VendingPersistenceException;
import com.vendingmachine.ui.UserIO;
import com.vendingmachine.ui.UserIOImpl;
import com.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VendingMachineController {

    VendingMachineView view;
    VendingMachineService service;
    private UserIO io;
    private BigDecimal userMoney = new BigDecimal(0);

    public VendingMachineController(VendingMachineView v) {
        view = v;
        service = new VendingMachineService();
    }

    public void run() { // never throws exception
        boolean keepGoing = true;
        int menuSelection = 0;
        while (keepGoing) {

            try {
                menuSelection = view.printMenuAndGetSelection();

                switch (menuSelection) {
                    case 1:
                        listOfItems();
                        break;
                    case 2:
                        enterMoney();
                        break;
                    case 3:
                        vendItem();
                        break;
                    case 4:
                        returnChange();
                        break;
                    case 5:
                        exit();
                        return;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        view.displayUnknownCommandBanner();
                }
            } catch (VendingPersistenceException ex) {
                view.displayNoFund();

            } catch (NoInventoryException ex) {
                view.displayItemOutOfStock();

            }

        }
        view.displayExitBanner();

    }

    private void getmenuSelection() {
        view.printMenuAndGetSelection();
    }

    public List<Item> listOfItems() {
        List<Item> getAllItems = service.getAllItems();
        view.printAllItems(getAllItems);
        return getAllItems;
    }

    public void enterMoney() {
        BigDecimal userChange = view.readMoney();
        service.enterMoney(userChange);
        view.displayEnterMoneyBanner(userChange);
        this.userMoney = userChange;
        Change totalMoney = new Change(userMoney.intValue());
        view.enterMoney(totalMoney);
    }

    public void vendItem() throws VendingPersistenceException, NoInventoryException {
        if (userMoney == null) {
            view.insert();
            return;
        }
        MathContext mc = new MathContext(2);//For precision
        listOfItems();
        List<Item> allItems = service.getAllItems();
        Item choice = null;
        boolean validIP = false;
        while (!validIP) {

            int userChoice = view.userChoice();

            for (Item item : allItems) {
                if (item.getId() == userChoice) {
                    choice = item;
                    validIP = true;
                    break;
                }
            }
        }
        boolean isSufficeintFundAvailable = false;
        if (choice != null) {
            service.checkInventory(choice);

            try {
                isSufficeintFundAvailable = service.checkFunds(choice.getPrice(), userMoney);
            } catch (InsufficentFundsException e) {
                throw new VendingPersistenceException("No item");

            }
            try {
                if (isSufficeintFundAvailable) {
                    userMoney = userMoney.subtract(choice.getPrice(), mc);

                    service.updateProduct(choice, allItems);
                }
            } catch (VendingPersistenceException ex) {
                throw new VendingPersistenceException("No update", ex);

            }

        }
    }

    public void returnChange(String userSelection) {

    }

    private void returnChange() {
        Change totalMoney = new Change(userMoney.intValue());
        view.enterMoney(totalMoney);

    }

    private void exit() {
        view.displayExitBanner();

    }

}
