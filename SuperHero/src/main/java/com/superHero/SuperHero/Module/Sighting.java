package com.superHero.SuperHero.Module;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sighting {

    private int id;
    private List<Heroes> heroes = new ArrayList<>();
    private Locations location;
    private LocalDate date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Heroes> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<Heroes> heroes) {
        this.heroes = heroes;
    }

    public Locations getLocation() {
        return location;
    }

    public void setLocation(Locations location) {
        this.location = location;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
        hash = 37 * hash + Objects.hashCode(this.heroes);
        hash = 37 * hash + Objects.hashCode(this.location);
        hash = 37 * hash + Objects.hashCode(this.date);
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
        final Sighting other = (Sighting) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.heroes, other.heroes)) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }


}
