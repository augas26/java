package com.superHero.SuperHero.MainController;

import com.superHero.SuperHero.Module.Heroes;
import com.superHero.SuperHero.Module.Locations;
import com.superHero.SuperHero.Module.Organizations;
import com.superHero.SuperHero.Module.Sighting;
import com.superHero.SuperHero.SuperHeroDao.HeroesDao;
import com.superHero.SuperHero.SuperHeroDao.LocationsDao;
import com.superHero.SuperHero.SuperHeroDao.OrganizationsDao;
import com.superHero.SuperHero.SuperHeroDao.SightingDao;
import com.superHero.SuperHero.SuperHeroDao.SightingDaoDB;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SightingController {
    
    @Autowired
    HeroesDao heroesDao;
    
    @Autowired
    OrganizationsDao organizationsDao;
    
    @Autowired
    LocationsDao locationsDao;
    
    @Autowired
    SightingDao sightingDao;
    
    @GetMapping("sighting")
    public String displaySighting(Model model) {
        
        List<Sighting> sight = sightingDao.getAllSighting();
        model.addAttribute("sight", sight);
        
        List<Heroes> heroes = heroesDao.getAllHeroes();
        model.addAttribute("heroes", heroes);
        
        List<Locations> location = locationsDao.getAllLocations();
        model.addAttribute("locations", location);
        
        return "sighting";
    }
    
    @PostMapping("addSighting")
    public String addSighting(HttpServletRequest request) {
        String location = request.getParameter("location");
        String date = request.getParameter("date");
        
        Sighting sight = new Sighting();
//        sight.setLocation(location);
        sight.setLocation(locationsDao.getLocationsById(Integer.parseInt(location)));
        sight.setDate(LocalDate.parse(date));
        
        sightingDao.addSighting(sight);
        return "redirect:/sighting";
    }
    
    @GetMapping("editSighting")
    public String editSighting(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        
        Sighting sight = sightingDao.getSightingById(id);
        model.addAttribute("sight", sight);
        
        List<Locations> location = locationsDao.getAllLocations();
        model.addAttribute("locations", location);
        
        List<Heroes> heroes = heroesDao.getAllHeroes();
        model.addAttribute("heroes", heroes);
        
        return "editSighting";
    }
    
    @PostMapping("editSighting")
    public String editSighting(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        
        String location = request.getParameter("location");
        String date = request.getParameter("date");
        
        Sighting sight = sightingDao.getSightingById(id);
        sight.setLocation(locationsDao.getLocationsById(Integer.parseInt(location)));
        sight.setDate(LocalDate.parse(date));
        
        sightingDao.updateSighting(sight);
        
        return "sighting";
    }
    
    
    @GetMapping("deleteSighting")
    public String deleteSighting(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        
        Sighting sight = sightingDao.getSightingById(id);
        sightingDao.deleteSighting(sight);
        
        return "redirect:/sighting";
    }
    
    @GetMapping("/")
    public String getLastSighting( Model model) {
      
        
         List<Sighting> sight = sightingDao.getLastSighting();
         model.addAttribute("sight", sight);
         
        
       
        
        return "index";
    }
    
}
