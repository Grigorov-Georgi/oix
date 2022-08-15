package bg.softuni.oix.web;

import bg.softuni.oix.repository.OfferRepository;
import bg.softuni.oix.service.mapper.OfferMapper;
import bg.softuni.oix.service.views.OfferView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/offers")
public class RestOfferController {
    private OfferRepository offerRepository;
    private OfferMapper offerMapper;

    public RestOfferController(OfferRepository offerRepository, OfferMapper offerMapper) {
        this.offerRepository = offerRepository;
        this.offerMapper = offerMapper;
    }

//    @GetMapping("/search")
//    public List<OfferView> searchOffers(@RequestParam String title){
//        return offerRepository.findAllByTitle(title)
//                .stream()
//                .map(offerMapper::offerEntityToOfferView)
//                .collect(Collectors.toList());
//    }
}
