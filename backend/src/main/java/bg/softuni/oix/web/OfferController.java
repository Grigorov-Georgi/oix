package bg.softuni.oix.web;

import bg.softuni.oix.repository.OfferRepository;
import bg.softuni.oix.service.OfferService;
import bg.softuni.oix.service.dto.AddOfferDTO;
import bg.softuni.oix.service.dto.OfferDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/offers")
public class OfferController {
//    private OfferService offerService;
//
//    public OfferController( OfferService offerService) {
//        this.offerService = offerService;
//    }

    @GetMapping
    public String allOffers(){
        return "offers";
    }

    @GetMapping("/add")
    public String addOffer(Model model){
        if (!model.containsAttribute("addOfferModel")){
            model.addAttribute("addOfferModel", new AddOfferDTO());
        }

        return "add-offer";
    }

}
