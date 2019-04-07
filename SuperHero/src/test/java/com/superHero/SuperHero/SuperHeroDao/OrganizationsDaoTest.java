package com.superHero.SuperHero.SuperHeroDao;

import com.superHero.SuperHero.Module.Heroes;
import com.superHero.SuperHero.Module.Organizations;
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

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrganizationsDaoTest {

    @Autowired
    HeroesDao heroesDao;

    @Autowired
    OrganizationsDao organizationsDao;

    @Autowired
    LocationsDao locationsDao;

    @Autowired
    SightingDao sightingDao;

    public OrganizationsDaoTest() {
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
            organizationsDao.deleteOrganizationsById(org.getId());

        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetAllOrganizations() {

        Organizations org = new Organizations();
        org.setName("New Org");
        org.setDescription("Software");
        org.setAddress("OnAir");
        org.setPhone("56567");
        org = organizationsDao.addOrganization(org);

        Organizations org1 = new Organizations();
        org1.setName("First Org ");
        org1.setDescription("SpiderMan 2nd");
        org1.setAddress("OnAir 2nd2");
        org1.setPhone("56761");
        org1 = organizationsDao.addOrganization(org1);

        List<Organizations> organizations = organizationsDao.getAllOrganizations();

        assertEquals(2, organizations.size());
        assertTrue(organizations.contains(org));
        assertTrue(organizations.contains(org1));
    }

    @Test
    public void testGetOrganizationById() {
        Organizations org = new Organizations();
        org.setName("New Org");
        org.setDescription("Software");
        org.setAddress("OnAir");
        org.setPhone("56567");
        org = organizationsDao.addOrganization(org);

        Organizations fromDao = organizationsDao.getOrganizationById(org.getId());
        assertEquals(org, fromDao);
    }

    @Test
    public void testAddOrganization() {
        Organizations org = new Organizations();
        org.setName("new Org");
        org.setDescription("Guild");
        org.setAddress("1267 kopra");
        org.setPhone("56567");
        org = organizationsDao.addOrganization(org);

        Organizations fromDao = organizationsDao.getOrganizationById(org.getId());

        assertEquals(org, fromDao);

    }

    @Test
    public void testUpdateOrganizations() {
        Organizations org = new Organizations();
        org.setName("New Org");
        org.setDescription("Software");
        org.setAddress("OnAir");
        org.setPhone("56567");
        org = organizationsDao.addOrganization(org);

        Organizations fromDao = organizationsDao.getOrganizationById(org.getId());
        assertEquals(org, fromDao);

        org.setName("New One");
        organizationsDao.updateOrganizations(org);

        assertNotEquals(org, fromDao);

        fromDao = organizationsDao.getOrganizationById(org.getId());

        assertEquals(org, fromDao);

    }

    @Test
    public void testDeleteOrganizationsById() {
        Organizations org = new Organizations();
        org.setName("name");
        org.setDescription("Software");
        org.setAddress("OnAir");
        org.setPhone("56567");
        org = organizationsDao.addOrganization(org);

        Organizations org1 = new Organizations();
        org1.setName("Test ");
        org1.setDescription("Cool");
        org1.setAddress("Test");
        org1.setPhone("Test");
        org = organizationsDao.addOrganization(org1);

        List<Organizations> organizations = new ArrayList<>();
        organizations.add(org);

        Organizations fromDao = organizationsDao.getOrganizationById(org.getId());
        assertEquals(org, fromDao);

        organizationsDao.deleteOrganizationsById(org.getId());

        fromDao = organizationsDao.getOrganizationById(org1.getId());
        assertNull(fromDao);
    }

}
