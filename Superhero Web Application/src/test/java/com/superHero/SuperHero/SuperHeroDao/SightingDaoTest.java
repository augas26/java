/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.superHero.SuperHero.SuperHeroDao;

import com.superHero.SuperHero.Module.Heroes;
import com.superHero.SuperHero.Module.Locations;
import com.superHero.SuperHero.Module.Organizations;
import com.superHero.SuperHero.Module.Sighting;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import javax.tools.DocumentationTool;
import javax.tools.DocumentationTool.Location;
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
public class SightingDaoTest {

    @Autowired
    HeroesDao heroesDao;

    @Autowired
    OrganizationsDao organizationsDao;

    @Autowired
    LocationsDao locationsDao;

    @Autowired
    SightingDao sightingDao;

    public SightingDaoTest() {
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

    @Test
    public void testGetSightingById() {
        Locations loc1 = new Locations();
        loc1.setName("name");
        loc1.setDescription("Discription");
        loc1.setAddress("Address");
        loc1.setLatitude(0.0);
        loc1.setLongtitude(0.0);
        loc1 = locationsDao.addLocations(loc1);

        Sighting sight = new Sighting();
        sight.setLocation(loc1);
        sight.setDate(LocalDate.of(2000, Month.APRIL, 20));
        sight = sightingDao.addSighting(sight);

        Sighting fromDao = sightingDao.getSightingById(sight.getId());
        assertEquals(sight, fromDao);
//        assertEquals(sight.getId(), fromDao.getLocation().getName());

    }

    @Test
    public void testGetAllSighting() {

        Locations loc1 = new Locations();
        loc1.setName("name");
        loc1.setDescription("Discription");
        loc1.setAddress("Address");
        loc1.setLatitude(0.0);
        loc1.setLongtitude(0.0);
        loc1 = locationsDao.addLocations(loc1);

        Locations loc2 = new Locations();
        loc2.setName("name");
        loc2.setDescription("Discription");
        loc2.setAddress("Address");
        loc2.setLatitude(0.0);
        loc2.setLongtitude(0.0);
        loc2 = locationsDao.addLocations(loc2);

        Sighting sight = new Sighting();
        sight.setLocation(loc1);
        sight.setDate(LocalDate.of(2000, Month.APRIL, 20));
        sight = sightingDao.addSighting(sight);

        Sighting sight1 = new Sighting();
        sight1.setLocation(loc1);
        sight1.setDate(LocalDate.of(2000, Month.APRIL, 20));
        sight1 = sightingDao.addSighting(sight1);

        List<Locations> locations = locationsDao.getAllLocations();
        List<Sighting> sights = sightingDao.getAllSighting();

        assertEquals(2, locations.size());
        assertTrue(locations.contains(loc1));
        assertTrue(locations.contains(loc2));

        assertEquals(2, sights.size());
        assertTrue(sights.contains(sight));
        assertTrue(sights.contains(sight1));

    }

    @Test
    public void testAddSighting() {
        Locations loc1 = new Locations();
        loc1.setName("name");
        loc1.setDescription("Discription");
        loc1.setAddress("Address");
        loc1.setLatitude(0.0);
        loc1.setLongtitude(0.0);
        loc1 = locationsDao.addLocations(loc1);

        Heroes hero = new Heroes();
        hero.setName("First ");
        hero.setDescription("SpiderMan1");
        hero.setSuperPower("OnAir");
        hero = heroesDao.addHero(hero);

        List<Heroes> hero1 = new ArrayList<>();
        hero1.add(hero);

        Sighting sight = new Sighting();
        sight.setLocation(loc1);
        sight.setDate(LocalDate.of(2000, Month.APRIL, 20));
        sight = sightingDao.addSighting(sight);

        Sighting fromDao = sightingDao.getSightingById(sight.getId());
        assertEquals(sight, fromDao);

    }

    @Test
    public void testUpdateSighting() {

        Locations loc1 = new Locations();
        loc1.setName("name");
        loc1.setDescription("Discription");
        loc1.setAddress("Address");
        loc1.setLatitude(0.0);
        loc1.setLongtitude(0.0);
        loc1 = locationsDao.addLocations(loc1);

        Sighting sight = new Sighting();
        sight.setLocation(loc1);
        sight.setDate(LocalDate.of(2000, Month.APRIL, 20));
        sight = sightingDao.addSighting(sight);

        sight.setDate(LocalDate.of(2001, Month.APRIL, 20));
        sight = sightingDao.addSighting(sight);

        Sighting fromDao = sightingDao.getSightingById(sight.getId());

        assertEquals(sight, fromDao);

    }

    @Test
    public void testDeleteSighting() {

        Locations loc1 = new Locations();
        loc1.setName("name");
        loc1.setDescription("Discription");
        loc1.setAddress("Address");
        loc1.setLatitude(0.0);
        loc1.setLongtitude(0.0);
        loc1 = locationsDao.addLocations(loc1);

        Heroes hero = new Heroes();
        hero.setName("First ");
        hero.setDescription("SpiderMan1");
        hero.setSuperPower("OnAir");
        hero = heroesDao.addHero(hero);

        List<Heroes> hero1 = new ArrayList<>();
        hero1.add(hero);

        Sighting sight = new Sighting();
        sight.setLocation(loc1);
        sight.setDate(LocalDate.of(2000, Month.APRIL, 20));
        sight.setHeroes(hero1);
        sight = sightingDao.addSighting(sight);

        Sighting fromDao = sightingDao.getSightingById(sight.getId());
        assertEquals(sight, fromDao);

        sightingDao.deleteSighting(sight);

        fromDao = sightingDao.getSightingById(sight.getId());
        assertNull(fromDao);

    }

}
