package bg.softuni.oix.web;

import bg.softuni.oix.exception.ObjectNotFoundException;
import bg.softuni.oix.model.entity.OfferEntity;
import bg.softuni.oix.service.LocationService;
import bg.softuni.oix.service.OfferService;
import bg.softuni.oix.service.dto.AddOfferDTO;
import bg.softuni.oix.service.views.LocationView;
import bg.softuni.oix.service.views.OfferView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/offers")
public class OfferController {
    private OfferService offerService;
    private LocationService locationService;

    public OfferController(OfferService offerService, LocationService locationService) {
        this.offerService = offerService;
        this.locationService = locationService;
    }

    @ModelAttribute("addOfferDTO")
    public AddOfferDTO initAddOfferDTO() {
        return new AddOfferDTO();
    }

    @GetMapping
    public String allOffers(Model model) {
        model.addAttribute("offers", this.offerService.getAllOffers());
        return "offers";
    }

    @GetMapping("/add")
    public String addOfferPage(Model model) {
        model.addAttribute("locations", locationService.getAllLocations());
        return "add-offer";
    }

    @PostMapping("/add")
    public String addOffer(@Valid AddOfferDTO addOfferDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addOfferDTO", addOfferDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addOfferDTO",
                    bindingResult);
            return "redirect:/offers/add";
        }
        this.offerService.save(addOfferDTO);
        return "redirect:/offers";
    }

    @GetMapping("/details/{id}")
    public String getOfferDetails(@PathVariable("id") long id, Model model) {
        OfferView offerView = offerService.findById(id);

        model.addAttribute("offer", offerView);

        return "offer-details";
    }

}
