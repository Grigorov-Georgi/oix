package bg.softuni.oix.web;

import bg.softuni.oix.service.OfferService;
import bg.softuni.oix.service.views.OfferView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    private OfferService offerService;

    public HomeController(OfferService offerService) {
        this.offerService = offerService;
    }

    @ModelAttribute("firstThreeOffers")
    public List<OfferView> initThreeOffers(){
        return new ArrayList<>();
    }

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("firstThreeOffers", this.offerService.getListWithLastThreeOffers());
        return "index";
    }

    @GetMapping("/about")
    public String about(){
        return "about-page";
    }
}
