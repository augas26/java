package com.superHero.SuperHero.SuperHeroDao;

import com.superHero.SuperHero.Module.Heroes;
import com.superHero.SuperHero.Module.Organizations;
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
public class OrganizationsDaoDB implements OrganizationsDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Organizations getOrganizationById(int id) {
        try {
            final String SELECT_ORGANIZATIONS_BY_ID = "SELECT * FROM Organizations WHERE orgid = ?";
            return jdbc.queryForObject(SELECT_ORGANIZATIONS_BY_ID, new OrganizationsMapper(), id);
            
        } catch (DataAccessException ex) {
             return null;
        }
    }

    @Override
    @Transactional
    public Organizations addOrganization(Organizations org) {
        final String INSERT_ORGANIZATIONS = "INSERT INTO Organizations(name, description, address, phone)"
                + "VALUES(?,?,?,?)";
        jdbc.update(INSERT_ORGANIZATIONS,
                org.getName(),
                org.getDescription(),
                org.getAddress(),
                org.getPhone());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        org.setId(newId);

        return org;

    }

    @Override
    @Transactional
    public void updateOrganizations(Organizations org) {
        final String UPDATE_ORGANIZATIONS = "UPDATE Organizations SET name = ?, description = ?, address = ?, phone = ? where orgId = ?";
        jdbc.update(UPDATE_ORGANIZATIONS,
                org.getName(),
                org.getDescription(),
                org.getAddress(),
                org.getPhone(),
                org.getId());
    }

    @Override
    @Transactional
    public void deleteOrganizationsById(int id) {

        final String DELETE_HeroOrganization = "DELETE FROM HeroOrganization WHERE orgId = ?";
        jdbc.update(DELETE_HeroOrganization, id);
        
        final String DELETE_ORGANIZATION = "DELETE FROM Organizations WHERE orgId = ?";
        jdbc.update(DELETE_ORGANIZATION, id);

    }

    @Override
    public List<Organizations> getAllOrganizations() {
        final String SELECT_ALL_ORGANIZATIONS = "SELECT * FROM Organizations";
        List<Organizations> Organizations = jdbc.query(SELECT_ALL_ORGANIZATIONS, new OrganizationsMapper());
         
        return Organizations;
    }


    public static final class OrganizationsMapper implements RowMapper<Organizations> {

        @Override
        public Organizations mapRow(ResultSet rs, int index) throws SQLException {
            Organizations org = new Organizations();
            org.setId(rs.getInt("orgId"));
            org.setName(rs.getString("Name"));
            org.setDescription(rs.getString("description"));
            org.setAddress(rs.getString("address"));
            org.setPhone(rs.getString("phone"));

            return org;
        }

    }

}
