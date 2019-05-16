/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.superHero.SuperHero.SuperHeroDao;

import com.superHero.SuperHero.Module.Heroes;
import com.superHero.SuperHero.Module.Locations;
import com.superHero.SuperHero.Module.Organizations;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author abdisamadugas
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HeroDaoDBTest {

    @Autowired
    HeroesDao heroesDao;

    @Autowired
    OrganizationsDao organizationsDao;

    @Autowired
    LocationsDao locationsDao;

    @Autowired
    SightingDao sightingDao;

    public HeroDaoDBTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        List<Heroes> heroes = heroesDao.getAllHeroes();
        for (Heroes hero : heroes) {
            heroesDao.deleteHeroById(hero.getId());
        }

        List<Locations> locations = locationsDao.getAllLocations();
        for (Locations loc : locations) {
            locationsDao.deleteLocationsById(loc.getId());

        }

        List<Organizations> organizations = organizationsDao.getAllOrganizations();
        for (Organizations org : organizations) {
            organizationsDao.getOrganizationById(org.getId());

        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getHeroById method, of class HeroDaoDB.
     */
    @Test
    public void testGetHeroById() {
        Heroes hero = new Heroes();
        hero.setName("First ");
        hero.setDescription("SpiderMan1");
        hero.setSuperPower("OnAir");
        hero = heroesDao.addHero(hero);

        Heroes fromDao = heroesDao.getHeroById(hero.getId());

        assertEquals(hero, fromDao);

    }

    /**
     * Test of getAllHeroes method, of class HeroDaoDB.
     */
    @Test
    public void testGetAllHeroes() {
        Heroes hero = new Heroes();
        hero.setName("First ");
        hero.setDescription("SpiderMan1");
        hero.setSuperPower("OnAir");
        hero = heroesDao.addHero(hero);

        Heroes hero2 = new Heroes();
        hero2.setName("Second ");
        hero2.setDescription("SpiderMan 2nd");
        hero2.setSuperPower("OnAir 2nd2");
        hero2 = heroesDao.addHero(hero2);

        List<Heroes> heroes = heroesDao.getAllHeroes();

        assertEquals(2, heroes.size());
        assertTrue(heroes.contains(hero));
        assertTrue(heroes.contains(hero2));

    }

    /**
     * Test of addHero method, of class HeroDaoDB.
     */
    @Test
    public void testAddAndGetHero() {
        Heroes hero = new Heroes();
        hero.setName("Hero three");
        hero.setDescription("Superman");
        hero.setSuperPower("laser eyes");
        hero = heroesDao.addHero(hero);

        Heroes fromDao = heroesDao.getHeroById(hero.getId());

        assertEquals(hero, fromDao);

    }

    /**
     * Test of updateHero method, of class HeroDaoDB.
     */
    @Test
    public void testUpdateHero() {
        Heroes hero = new Heroes();
        hero.setName("Hero four");
        hero.setDescription("Hockmash");
        hero.setSuperPower("Smash");
        hero = heroesDao.addHero(hero);

        Heroes fromDao = heroesDao.getHeroById(hero.getId());
        assertEquals(hero, fromDao);

        hero.setName("New Hero");
        heroesDao.updateHero(hero);

        assertNotEquals(hero, fromDao);

        fromDao = heroesDao.getHeroById(hero.getId());

        assertEquals(hero, fromDao);
    }

    /**
     * Test of deleteHeroById method, of class HeroDaoDB.
     */
    @Test
    public void testDeleteHeroById() {
        Heroes hero = new Heroes();
        hero.setName("Hero three");
        hero.setDescription("Superman");
        hero.setSuperPower("laser eyes");
        hero = heroesDao.addHero(hero);

        Heroes hero4 = new Heroes();
        hero4.setName("Hero three");
        hero4.setDescription("Superman");
        hero4.setSuperPower("laser eyes");
        hero4 = heroesDao.addHero(hero4);

        List<Heroes> heroes = new ArrayList<>();
        heroes.add(hero4);

        Heroes fromDao = heroesDao.getHeroById(hero.getId());
        assertEquals(hero, fromDao);

        heroesDao.deleteHeroById(hero.getId());

        fromDao = heroesDao.getHeroById(hero.getId());
        assertNull(fromDao);
    }
}
