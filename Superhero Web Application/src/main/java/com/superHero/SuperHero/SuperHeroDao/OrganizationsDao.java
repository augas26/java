/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.superHero.SuperHero.SuperHeroDao;

import com.superHero.SuperHero.Module.Heroes;
import com.superHero.SuperHero.Module.Organizations;
import java.util.List;

/**
 *
 * @author abdisamadugas
 */
public interface OrganizationsDao {
    
    List<Organizations> getAllOrganizations();

    Organizations getOrganizationById(int id);

    Organizations addOrganization(Organizations org);

    void updateOrganizations(Organizations org);

    void deleteOrganizationsById(int id);

}
