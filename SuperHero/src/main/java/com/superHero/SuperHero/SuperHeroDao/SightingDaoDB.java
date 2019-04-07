package com.superHero.SuperHero.SuperHeroDao;

import com.superHero.SuperHero.Module.Heroes;
import com.superHero.SuperHero.Module.Locations;
import com.superHero.SuperHero.Module.Organizations;
import com.superHero.SuperHero.Module.Sighting;
import com.superHero.SuperHero.SuperHeroDao.HeroDaoDB.HeroMapper;
import com.superHero.SuperHero.SuperHeroDao.LocationDaoDB.LocationsMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.tools.DocumentationTool.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SightingDaoDB implements SightingDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<Sighting> getAllSighting() {
        final String SELECT_ALL_SIGHTINGS = "SELECT * FROM Sighting";
        
        List<Sighting> sights = jdbc.query(SELECT_ALL_SIGHTINGS, new SightingMapper());
        associateHeroSightAndLocation(sights);
        return sights;

    }
    
     private void associateHeroSightAndLocation(List<Sighting> sights) {
         for(Sighting sight: sights){
           sight.setLocation(getLocationForSights(sight.getId()));
           sight.setHeroes(getHeroesForSighting(sight.getId()));
         }
    }

    @Override
    @Transactional
    public Sighting addSighting(Sighting sight) {
        final String INSERT_SIGHTING = "INSERT INTO Sighting(locId, date)"
                + "VALUES(?,?)";
        jdbc.update(INSERT_SIGHTING,
                sight.getLocation().getId(),
                java.sql.Date.valueOf(sight.getDate()));

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        sight.setId(newId);
        insertSightingHeroes(sight);
        return sight;
    }
    
     private void insertSightingHeroes(Sighting sight) {
         final String INSERT_SIGHTING_HEROES = "INSERT INTO "
                + "HeroSight(heroId, sighId) VALUES(?,?)"; 
        
        for(Heroes hero : sight.getHeroes()){ // attention
            jdbc.update(INSERT_SIGHTING_HEROES,
                    hero.getId(),
                    sight.getId());
    }
     
    }

    @Override
    public void updateSighting(Sighting sight) {
        final String UPDATE_SIGHTING = "UPDATE Sighting SET locId = ?, date = ?"
                + "WHERE sightId = ?";
        jdbc.update(UPDATE_SIGHTING,
                sight.getLocation().getId(),
                java.sql.Date.valueOf(sight.getDate()),
                sight.getId());
    }

    @Override
    @Transactional
    public void deleteSighting(Sighting sight) {
        final String DELETE_SIGHT_HERO = "DELETE FROM HeroSight WHERE sighId = ?";
        jdbc.update(DELETE_SIGHT_HERO, sight.getId());

        final String DELETE_SIGHTING = "DELETE FROM Sighting WHERE sighId = ?";
        jdbc.update(DELETE_SIGHTING, sight.getId());

    }

    @Override
    public Sighting getSightingById(int id) {
        try {
            final String SELECT_SIGHTING_BY_ID = "SELECT * FROM Sighting WHERE sighId = ?";
            Sighting sight = jdbc.queryForObject(SELECT_SIGHTING_BY_ID, new SightingMapper(), id);
            sight.setLocation(getLocationForSights(id));
            sight.setHeroes(getHeroesForSighting(id));
            return sight;

        } catch (DataAccessException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private Locations getLocationForSights(int id) {
        final String SELECT_SIGHTING_FOR_LOCATION = "SELECT l. * FROM Location l "
                + "JOIN Sighting h ON h.locId = l.locId WHERE h.sighId = ?";
        return jdbc.queryForObject(SELECT_SIGHTING_FOR_LOCATION, new LocationsMapper(), id);
    }

    private List<Heroes> getHeroesForSighting(int id) {
        final String SELECT_HEROES_FOR_SIGHTING = "SELECT h.* FROM SuperHero h "
                + "JOIN HeroSight sh ON sh.heroId = h.heroId WHERE sh.sighId = ?";
        return jdbc.query(SELECT_HEROES_FOR_SIGHTING, new HeroMapper(), id);
    }

    public static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int index) throws SQLException {
            Sighting sight = new Sighting();
            sight.setId(rs.getInt("sighId"));
            sight.setDate(rs.getDate("date").toLocalDate());

            return sight;
        }
    }

}
