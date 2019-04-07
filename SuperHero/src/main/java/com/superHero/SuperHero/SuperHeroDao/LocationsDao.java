/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.superHero.SuperHero.SuperHeroDao;

import com.superHero.SuperHero.Module.Locations;
import java.util.List;

/**
 *
 * @author abdisamadugas
 */
public interface LocationsDao {

    Locations getLocationsById(int id);
    
    List<Locations> getAllLocations();
    
    Locations addLocations(Locations loc);
    
    void updateLocations(Locations location);

    void deleteLocationsById(int id);

}
