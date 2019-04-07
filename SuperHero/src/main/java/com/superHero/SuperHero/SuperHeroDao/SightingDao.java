/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.superHero.SuperHero.SuperHeroDao;

import com.superHero.SuperHero.Module.Heroes;
import com.superHero.SuperHero.Module.Locations;
import com.superHero.SuperHero.Module.Sighting;
import java.util.List;

/**
 *
 * @author abdisamadugas
 */
public interface SightingDao {
    
    Sighting getSightingById(int id);

   List<Sighting> getAllSighting();

    Sighting addSighting(Sighting sight);

    void updateSighting(Sighting sight);

    void deleteSighting(Sighting sight);

}
