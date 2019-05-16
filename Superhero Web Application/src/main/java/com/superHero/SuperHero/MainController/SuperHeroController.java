package com.superHero.SuperHero.MainController;

import com.superHero.SuperHero.Module.Heroes;
import com.superHero.SuperHero.Module.Organizations;
import com.superHero.SuperHero.Module.Sighting;
import com.superHero.SuperHero.SuperHeroDao.HeroesDao;
import com.superHero.SuperHero.SuperHeroDao.LocationsDao;
import com.superHero.SuperHero.SuperHeroDao.OrganizationsDao;
import com.superHero.SuperHero.SuperHeroDao.SightingDao;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SuperHeroController {

    @Autowired
    HeroesDao heroesDao;

    @Autowired
    OrganizationsDao organizationsDao;

    @Autowired
    LocationsDao locationsDao;

    @Autowired
    SightingDao sightingDao;

    @GetMapping("heroes")
    public String displayHeroes(Model model) {
        List<Heroes> heroes = heroesDao.getAllHeroes();
        List<Organizations> organization = organizationsDao.getAllOrganizations();
        List<Sighting> sight = sightingDao.getAllSighting();
        
        model.addAttribute("heroes", heroes);
        model.addAttribute("organizations", organization);
        model.addAttribute("sights", sight);
        return "SuperHero";
    }

    @PostMapping("addHero")
    public String addHero(HttpServletRequest request) {

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String superPower = request.getParameter("superPower");
        
        Heroes hero = new Heroes();
        hero.setName(name);
        hero.setDescription(description);
        hero.setSuperPower(superPower);

        heroesDao.addHero(hero);
        
//        hero.setOrganizations(organizationsDao.getOrganizationById(Integer.parseInt("Id")));
        
//        String[] sightIds = request.getParameterValues("sightIds");
//        String[] orgId = request.getParameterValues("orgId");
//        
//        
//        List<Sighting> sight = new ArrayList<>();
//        for(String sighting : sightIds){
//            sight.add(sightingDao.getSightingById(Integer.parseInt(sighting)));
//        }
        
//        hero.setId(id);

        return "redirect:/heroes";
    }
    
    

    @GetMapping("editHero")
    public String editHero(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Heroes hero = heroesDao.getHeroById(id);

        List<Organizations> org = organizationsDao.getAllOrganizations();
        List<Sighting> sight = sightingDao.getAllSighting();

        model.addAttribute("heroes", hero);
        model.addAttribute("organizations", org);
        model.addAttribute("sights", sight);

        return "editHero";
    }

    @PostMapping("editHero")
    public String performEditHero(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Heroes hero = heroesDao.getHeroById(id);

        hero.setName(request.getParameter("name"));
        hero.setDescription(request.getParameter("description"));
        hero.setSuperPower(request.getParameter("superPower"));

        heroesDao.updateHero(hero);

        return "redirect:/heroes";
    }

    @GetMapping("deleteHero")
    public String deleteHero(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        heroesDao.deleteHeroById(id);

        return "redirect:/heroes";
    }

}
