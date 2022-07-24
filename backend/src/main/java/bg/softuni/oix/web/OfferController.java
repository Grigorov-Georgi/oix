package bg.softuni.oix.web;

import bg.softuni.oix.repository.OfferRepository;
import bg.softuni.oix.service.OfferService;
import bg.softuni.oix.service.dto.OfferDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OfferController {
    private OfferService offerService;

    public OfferController( OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/offers")
    public String getAllOffers(){
        return "offers";
    }
}
