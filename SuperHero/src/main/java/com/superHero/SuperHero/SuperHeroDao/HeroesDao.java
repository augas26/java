/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.superHero.SuperHero.SuperHeroDao;

import com.superHero.SuperHero.Module.Heroes;
import java.util.List;

/**
 *
 * @author abdisamadugas
 */
public interface HeroesDao {

    Heroes getHeroById(int id);

    List<Heroes> getAllHeroes();

    Heroes addHero(Heroes hero);

    void updateHero(Heroes hero);

    void deleteHeroById(int id);

}
