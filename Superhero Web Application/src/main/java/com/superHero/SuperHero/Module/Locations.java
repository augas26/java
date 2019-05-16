package com.superHero.SuperHero.Module;

import java.time.LocalDate;
import java.util.Objects;

public class Locations {

    private int id;
    private String name;
    private String description;
    private String address;
    private double Latitude;
    private double longtitude;

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double Latitude) {
        this.Latitude = Latitude;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.id;
        hash = 17 * hash + Objects.hashCode(this.name);
        hash = 17 * hash + Objects.hashCode(this.description);
        hash = 17 * hash + Objects.hashCode(this.address);
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.Latitude) ^ (Double.doubleToLongBits(this.Latitude) >>> 32));
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.longtitude) ^ (Double.doubleToLongBits(this.longtitude) >>> 32));
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
        final Locations other = (Locations) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.Latitude) != Double.doubleToLongBits(other.Latitude)) {
            return false;
        }
        if (Double.doubleToLongBits(this.longtitude) != Double.doubleToLongBits(other.longtitude)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        return true;
    }

}