package com.vendingmachine.dtos;

import java.math.BigDecimal;
import java.util.List;

public class VendingMachine {

    private List<Item> toSell;

    private int totalPenniesEntered = 0;

    public void enterDollar() {
        totalPenniesEntered += 100;
    }

    public void enterQuarter() {
        totalPenniesEntered += 25;
    }

    public void enterDime() {
        totalPenniesEntered += 10;
    }

    public void enterNickel() {
        totalPenniesEntered += 5;
    }

    public void enterPenny() {
        totalPenniesEntered += 1;
    }

    public void enterMoney(BigDecimal toEnter) {

        int justEntered = toEnter.multiply(new BigDecimal(100)).intValue();
        totalPenniesEntered += justEntered;
    }

    public Change returnChange() {

        Change toReturn = new Change(totalPenniesEntered);

        totalPenniesEntered = 0;

        return toReturn;

    }

    /**
     * @return the toSell
     */
    public List<Item> getToSell() {
        return toSell;
    }

    /**
     * @return the penniesEntered
     */
    public int getTotalPenniesEntered() {
        return totalPenniesEntered;
    }

    /**
     * @param toSell the toSell to set
     */
    public void setToSell(List<Item> toSell) {
        this.toSell = toSell;
    }

}
