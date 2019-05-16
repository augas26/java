package com.superHero.SuperHero.MainController;

import com.superHero.SuperHero.Module.Heroes;
import com.superHero.SuperHero.Module.Locations;
import com.superHero.SuperHero.SuperHeroDao.HeroesDao;
import com.superHero.SuperHero.SuperHeroDao.LocationsDao;
import com.superHero.SuperHero.SuperHeroDao.OrganizationsDao;
import com.superHero.SuperHero.SuperHeroDao.SightingDao;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.tools.DocumentationTool.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LocationController {

    @Autowired
    HeroesDao heroesDao;

    @Autowired
    OrganizationsDao organizationsDao;

    @Autowired
    LocationsDao locationsDao;

    @Autowired
    SightingDao sightingDao;

    @GetMapping("location")
    public String displayLocation(Model model) { //Ok
        List<Locations> loc = locationsDao.getAllLocations();

        model.addAttribute("location", loc);
        return "location";
    }

    @PostMapping("addLocation")
    public String addLocation(HttpServletRequest request) {

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String address = request.getParameter("address");
        String latitude = request.getParameter("latitude");
        String longtitude = request.getParameter("longtitude");

        Locations loc = new Locations();
        loc.setName(name);
        loc.setDescription(description);
        loc.setAddress(address);
        loc.setLatitude(Double.parseDouble(latitude));
        loc.setLongtitude(Double.parseDouble(longtitude));

        locationsDao.addLocations(loc);

        return "redirect:/location";
    }

    @GetMapping("editLocation")
    public String editLocation(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));

        Locations loc = locationsDao.getLocationsById(id);
        model.addAttribute("location", loc);
        return "editLocation";
    }

    @PostMapping("editLocation")
    public String performEditLocation(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Locations loc = locationsDao.getLocationsById(id);
        
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String address = request.getParameter("address");
        String latitude = request.getParameter("latitude");
        String longtitude = request.getParameter("longtitude");
        
        
        loc.setName(name);
        loc.setDescription(description);
        loc.setAddress(address);
        loc.setLatitude(Double.parseDouble(latitude));
        loc.setLongtitude(Double.parseDouble(longtitude));



        
        locationsDao.updateLocations(loc);

        return "redirect:/location";
    }

    @GetMapping("deleteLocation")
    public String deleteLocation(HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("id"));
        locationsDao.deleteLocationsById(id);

        return "redirect:/location";
    }

}
