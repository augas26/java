package com.superHero.SuperHero.SuperHeroDao;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import com.superHero.SuperHero.Module.Heroes;
import com.superHero.SuperHero.Module.Locations;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class LocationDaoDB implements LocationsDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Locations getLocationsById(int id) {

        try {
            final String GET_LOCATION_BY_ID = "SELECT * FROM Location WHERE locId = ?";
            return jdbc.queryForObject(GET_LOCATION_BY_ID, new LocationsMapper(), id);
        } catch (DataAccessException ex) {

            return null;
        }
    }

    @Override
    public List<Locations> getAllLocations() {

        final String GET_ALL_LOCATIONS = "SELECT * FROM Location";
        return jdbc.query(GET_ALL_LOCATIONS, new LocationsMapper());
    }

    @Override
    public void updateLocations(Locations location) {
        final String UPDATE_LOCATION = "UPDATE Location SET name = ?, description = ?, address = ?, latitude = ?, longtitude = ? "
                + "WHERE locid = ?";
        jdbc.update(UPDATE_LOCATION,
                location.getName(),
                location.getDescription(),
                location.getAddress(),
                location.getLatitude(),
                location.getLongtitude(),
                location.getId());
               

    }


    @Override
    @Transactional
    public void deleteLocationsById(int id) {
        
        final String DELETE_HERO_SIGHTING = "DELETE hs.* FROM HeroSight hs "
                + "JOIN Sighting h ON hs.heroId = hs.sighId WHERE h.locId = ?";
        jdbc.update(DELETE_HERO_SIGHTING, id);
        
        final String DELETE_SIGHTING = "DELETE FROM Sighting WHERE locId = ?";
        jdbc.update(DELETE_SIGHTING, id);
        
        final String DELETE_LOCATION = "DELETE FROM Location WHERE locId = ?";
        jdbc.update(DELETE_LOCATION, id);
         
    }

    @Override
    @Transactional
    public Locations addLocations(Locations loc) {
        final String INSERT_LOCATION = "INSERT INTO Location(name, description, address, latitude, longtitude)"
                + "VALUES(?,?,?,?,?)";
        jdbc.update(INSERT_LOCATION,
                loc.getName(),
                loc.getDescription(),
                loc.getAddress(),
                loc.getLatitude(),
                loc.getLongtitude());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        loc.setId(newId);
        return loc;
    }

    public static final class LocationsMapper implements RowMapper<Locations> {

        @Override
        public Locations mapRow(ResultSet rs, int i) throws SQLException {
            Locations loc = new Locations();
            loc.setId(rs.getInt("locId"));
            loc.setName(rs.getString("name"));
            loc.setDescription(rs.getString("Description"));
            loc.setAddress(rs.getString("Address"));
            loc.setLatitude(rs.getDouble("latitude"));
            loc.setLongtitude(rs.getDouble("longtitude"));
            
            

            return loc;
        }
    }

}
