package com.vendingmachine.dtos;

import java.math.BigDecimal;
import java.util.Objects;

public class Item {

    private int itemId;
    private String itemName;
    private int Quantity;
    private int priceInPennies;

    public Item() {
    }

    public Item(int itemId, String itemName, int Quantity, int priceInPennies) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.Quantity = Quantity;
        this.priceInPennies = priceInPennies;
    }

    public int getId() {
        return itemId;
    }

    public void setId(int id) {
        this.itemId = id;
    }

    public String getName() {
        return itemName;
    }

    public void setName(String name) {
        this.itemName = name;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        this.Quantity = quantity;
    }

    public int getPriceInPennies() {
        return priceInPennies;
    }

    public BigDecimal getPrice() {
        return new BigDecimal(priceInPennies);
    }

    public void setPriceInPennies(int priceInPennies) {
        this.priceInPennies = priceInPennies;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.itemId;
        hash = 67 * hash + Objects.hashCode(this.itemName);
        hash = 67 * hash + this.Quantity;
        hash = 67 * hash + this.priceInPennies;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (this.itemId != other.itemId) {
            return false;
        }
        if (this.Quantity != other.Quantity) {
            return false;
        }
        if (this.priceInPennies != other.priceInPennies) {
            return false;
        }
        if (!Objects.equals(this.itemName, other.itemName)) {
            return false;
        }
        return true;
    }

}
