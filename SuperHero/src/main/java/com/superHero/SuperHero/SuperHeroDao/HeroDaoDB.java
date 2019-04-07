package com.superHero.SuperHero.SuperHeroDao;

import com.superHero.SuperHero.Module.Heroes;
import com.superHero.SuperHero.Module.Organizations;
import com.superHero.SuperHero.Module.Sighting;
import com.superHero.SuperHero.SuperHeroDao.OrganizationsDaoDB.OrganizationsMapper;
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
public class HeroDaoDB implements HeroesDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Heroes getHeroById(int id) {

        try {
            final String SELECT_HERO_BY_ID = "SELECT * FROM SuperHero WHERE heroid = ?";
            return jdbc.queryForObject(SELECT_HERO_BY_ID, new HeroMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Heroes> getAllHeroes() {
        final String SELECT_ALL_Heroes = "SELECT * FROM SuperHero";
        return jdbc.query(SELECT_ALL_Heroes, new HeroMapper());
    }

    @Override
    @Transactional
    public Heroes addHero(Heroes hero) {
        final String INSERT_HERO = "INSERT INTO SuperHero(name, description, superPower)" + "Values(?, ?, ?)";
        jdbc.update(INSERT_HERO,
                hero.getName(),
                hero.getDescription(),
                hero.getSuperPower());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        hero.setId(newId);
        insertSuperHeroOrganizations(hero);
        return hero;
    }
       private void insertSuperHeroOrganizations(Heroes hero) {
           final String INSERT_SUPERHERO_ORGANIZATION = "INSERT INTO "
                + "HeroOrganization(heroId, orgId) VALUES(?,?)"; 
        
        for(Organizations org : hero.getOrganizations()){ // attention
            jdbc.update(INSERT_SUPERHERO_ORGANIZATION,
                    hero.getId(),
                    org.getId());
        }
    }
    
    @Override
    @Transactional
    public void updateHero(Heroes hero) {
        final String UPDATE_HERO = "UPDATE SuperHero SET name = ?, description = ?, SuperPower = ? "
                + "WHERE heroid = ?";
        jdbc.update(UPDATE_HERO,
                
                hero.getName(),
                hero.getDescription(),
                hero.getSuperPower(),
                hero.getId());

    }

    @Override
    public void deleteHeroById(int id) {
        final String DELETE_HEROES_SIGHT = "DELETE FROM HeroSight WHERE heroId = ?";
        jdbc.update(DELETE_HEROES_SIGHT, id);
              
        
        final String DELETE_SIGHTING = "DELETE FROM HeroOrganization WHERE heroId = ?";
        jdbc.update(DELETE_SIGHTING, id);
        
         final String DELETE_HERO = "DELETE FROM SuperHero WHERE heroId = ?";
        jdbc.update(DELETE_HERO, id);
        
        
    }


    public static final class HeroMapper implements RowMapper<Heroes> {

        @Override
        public Heroes mapRow(ResultSet rs, int i) throws SQLException {
            Heroes hero = new Heroes();
            hero.setId(rs.getInt("heroid"));
            hero.setName(rs.getString("Name"));
            hero.setDescription(rs.getString("Description"));
            hero.setSuperPower(rs.getString("SuperPower"));

            return hero;
        }
    }

}
