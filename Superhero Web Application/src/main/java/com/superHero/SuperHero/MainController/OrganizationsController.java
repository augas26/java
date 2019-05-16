package com.superHero.SuperHero.MainController;

import com.superHero.SuperHero.Module.Heroes;
import com.superHero.SuperHero.Module.Locations;
import com.superHero.SuperHero.Module.Organizations;
import com.superHero.SuperHero.SuperHeroDao.HeroesDao;
import com.superHero.SuperHero.SuperHeroDao.LocationsDao;
import com.superHero.SuperHero.SuperHeroDao.OrganizationsDao;
import com.superHero.SuperHero.SuperHeroDao.SightingDao;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrganizationsController {

    @Autowired
    HeroesDao heroesDao;

    @Autowired
    OrganizationsDao organizationsDao;

    @Autowired
    LocationsDao locationsDao;

    @Autowired
    SightingDao sightingDao;

    @GetMapping("organizations")
    public String displayOrganization(Model model) {
        List<Organizations> organization = organizationsDao.getAllOrganizations();
        List<Heroes> heroes = heroesDao.getAllHeroes();
        List<Locations> locations = locationsDao.getAllLocations();
        model.addAttribute("organization", organization);
        model.addAttribute("heroes", heroes);
        model.addAttribute("locations", locations);
        return "organizations";
    }

    @PostMapping("addOrganization")
    public String addOrganization(HttpServletRequest request) {

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");

        Organizations org = new Organizations();
        org.setName(name);
        org.setDescription(description);
        org.setAddress(address);
        org.setPhone(phone);

        organizationsDao.addOrganization(org);

        return "redirect:/organizations";
    }

    @GetMapping("organizationDetail")
    public String organizationDetail(Integer id, Model model) {
        Organizations organization = organizationsDao.getOrganizationById(id);
        model.addAttribute("organization", organization);
        return "organization";
    }

    @GetMapping("editOrganization")
    public String editHero(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));

        Organizations organization = organizationsDao.getOrganizationById(id);
        model.addAttribute("organization", organization);

        List<Locations> location = locationsDao.getAllLocations();
        model.addAttribute("location", location);

        List<Heroes> heroes = heroesDao.getAllHeroes();
        model.addAttribute("heroes", heroes);

        return "editOrganization";
    }

    @PostMapping("editOrganization")
    public String performEditOrganization(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));

        Organizations org = organizationsDao.getOrganizationById(id);
        org.setName(request.getParameter("name"));
        org.setDescription(request.getParameter("description"));
        org.setAddress(request.getParameter("address"));
        org.setPhone(request.getParameter("phone"));

        organizationsDao.updateOrganizations(org);

        return "redirect:/organizations";
    }

    @GetMapping("deleteOrganization")
    public String deleteOrganization(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        organizationsDao.deleteOrganizationsById(id);

        return "redirect:/organizations";
    }

}
