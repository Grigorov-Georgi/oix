package bg.softuni.oix.web;

import bg.softuni.oix.service.LocationService;
import bg.softuni.oix.service.dto.AddLocationDTO;
import bg.softuni.oix.service.views.LocationView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private LocationService locationService;

    public AdminController(LocationService locationService) {
        this.locationService = locationService;
    }

    @ModelAttribute("addLocationModel")
    public AddLocationDTO initLocationModel(){
        return new AddLocationDTO();
    }

    @ModelAttribute("locations")
    public List<LocationView> initLocations(){
        return new ArrayList<>();
    }

    @GetMapping
    public String adminPanel(Model model){
        model.addAttribute("locations", locationService.getAllLocations());
        return "admin-panel";
    }

    @PostMapping("/location/add")
    public String addLocation(@Valid AddLocationDTO locationDTO){
        this.locationService.save(locationDTO);
        return "redirect:/admin";
    }

    @GetMapping("/location/delete")
    public String deleteLocation(@RequestParam Long id){
        this.locationService.delete(id);
        return "redirect:/admin";
    }


}
