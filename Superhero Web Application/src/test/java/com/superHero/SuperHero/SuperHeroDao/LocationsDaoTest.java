/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.superHero.SuperHero.SuperHeroDao;

import com.superHero.SuperHero.Module.Heroes;
import com.superHero.SuperHero.Module.Locations;
import com.superHero.SuperHero.Module.Organizations;
import java.time.LocalDate;
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

@RunWith(SpringRunner.class)
@SpringBootTest
public class LocationsDaoTest {

    @Autowired
    HeroesDao heroesDao;

    @Autowired
    OrganizationsDao organizationsDao;

    @Autowired
    LocationsDao locationsDao;

    @Autowired
    SightingDao sightingDao;

    public LocationsDaoTest() {
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
     * Test of getLocationsById method, of class LocationsDao.
     */
    @Test
    public void testGetLocationsById() {
        Locations loc = new Locations();
        loc.setName("First ");
        loc.setDescription("Discription");
        loc.setAddress("Address");
        loc.setLatitude(20.0);
        loc.setLongtitude(0.0);
        loc = locationsDao.addLocations(loc);

        Locations fromDao = locationsDao.getLocationsById(loc.getId());

        assertEquals(loc, fromDao);

    }

    /**
     * Test of getAllLocations method, of class LocationsDao.
     */
    @Test
    public void testGetAllLocations() {
        Locations loc1 = new Locations();
        loc1.setName("name ");
        loc1.setDescription("Discription");
        loc1.setAddress("Address");
        loc1.setLatitude(0.0);
        loc1.setLongtitude(0.0);
        loc1 = locationsDao.addLocations(loc1);

        Locations loc2 = new Locations();
        loc2.setName("name ");
        loc2.setDescription("Description");
        loc2.setAddress("Address");
        loc2.setLatitude(0.0);
        loc2.setLongtitude(0.0);
        loc2 = locationsDao.addLocations(loc2);

        List<Locations> loc = locationsDao.getAllLocations();

        assertEquals(2, loc.size());
        assertTrue(loc.contains(loc1));
        assertTrue(loc.contains(loc2));

    }

    /**
     * Test of addLocations method, of class LocationsDao.
     */
    @Test
    public void testAddLocations() {
        Locations loc1 = new Locations();
        loc1.setName("name ");
        loc1.setDescription("Discription");
        loc1.setAddress("Address");
        loc1.setLatitude(0.0);
        loc1.setLongtitude(0.0);
        loc1 = locationsDao.addLocations(loc1);

        Locations fromDao = locationsDao.getLocationsById(loc1.getId());
        assertEquals(loc1, fromDao);
    }

    /**
     * Test of updateLocations method, of class LocationsDao.
     */
    @Test
    public void testUpdateLocations() {
        Locations loc1 = new Locations();
        loc1.setName("name ");
        loc1.setDescription("Discription");
        loc1.setAddress("Address");
        loc1.setLatitude(0.0);
        loc1.setLongtitude(0.0);
        loc1 = locationsDao.addLocations(loc1);

        Locations fromDao = locationsDao.getLocationsById(loc1.getId());
        assertEquals(loc1, fromDao);

        loc1.setName("New Location");
        locationsDao.updateLocations(loc1);
        assertNotEquals(loc1, fromDao);

        fromDao = locationsDao.getLocationsById(loc1.getId());
        assertEquals(loc1, fromDao);
    }

    /**
     * Test of deleteLocationsById method, of class LocationsDao.
     */
    @Test
    public void testDeleteLocationsById() {

        Locations loc1 = new Locations();
        loc1.setName("name ");
        loc1.setDescription("Discription");
        loc1.setAddress("Address");
        loc1.setLatitude(0.0);
        loc1.setLongtitude(0.0);
        loc1 = locationsDao.addLocations(loc1);

        Locations loc2 = new Locations();
        loc2.setName("name ");
        loc2.setDescription("Discription");
        loc2.setAddress("Address");
        loc2.setLatitude(0.0);
        loc2.setLongtitude(0.0);
        loc2 = locationsDao.addLocations(loc1);

        List<Locations> Locations = new ArrayList<>();
        Locations.add(loc2);

        Locations fromDao = locationsDao.getLocationsById(loc1.getId());
        assertEquals(loc1, fromDao);

        locationsDao.deleteLocationsById(loc1.getId());

        fromDao = locationsDao.getLocationsById(loc2.getId());
        assertNull(fromDao);
    }

}
