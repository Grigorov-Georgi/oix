package bg.softuni.oix.service;

import bg.softuni.oix.exception.ObjectNotFoundException;
import bg.softuni.oix.model.entity.OfferEntity;
import bg.softuni.oix.model.entity.UserEntity;
import bg.softuni.oix.model.user.OixUserDetails;
import bg.softuni.oix.repository.OfferRepository;
import bg.softuni.oix.repository.UserRepository;
import bg.softuni.oix.service.dto.AddOfferDTO;
import bg.softuni.oix.service.dto.OfferDto;
import bg.softuni.oix.service.mapper.OfferMapper;
import bg.softuni.oix.service.views.OfferView;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfferService {
    private final OfferRepository offerRepository;
    private final UserService userService;
    private final OfferMapper offerMapper;

    public OfferService(OfferRepository offerRepository, UserService userService, OfferMapper offerMapper) {
        this.offerRepository = offerRepository;
        this.userService = userService;
        this.offerMapper = offerMapper;
    }

    public void save(AddOfferDTO addOfferDTO, OixUserDetails userDetails) {
        OfferEntity newOffer = offerMapper.addOfferDtoToOfferEntity(addOfferDTO);

        UserEntity loggedUser = userService.findById(userDetails.getId());
        newOffer.setSeller(loggedUser);

        this.offerRepository.save(newOffer);
    }

    public List<OfferView> getAllOffers() {
        return this.offerRepository.findAllByBuyerIsNull().stream().map(offerMapper::offerEntityToOfferView)
                .collect(Collectors.toList());
    }

    public List<OfferView> getListWithLastThreeOffers() {
        List<OfferView> offersForHomePage = new ArrayList<>();

        long repoCount = this.offerRepository.count();

        for (long id = repoCount; id > repoCount - 3; id--) {
            OfferEntity offerEntity = this.offerRepository.findById(id)
                    .orElseThrow(() -> new ObjectNotFoundException("Offer not found!"));
            offersForHomePage.add(offerMapper.offerEntityToOfferView(offerEntity));
        }
        return offersForHomePage;
    }

    public OfferView findViewById(long id) {
        OfferEntity offerEntity = this.offerRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Offer with id " + id + " not found!"));
        return offerMapper.offerEntityToOfferView(offerEntity);
    }

    public AddOfferDTO findDtoById(long id) {
        OfferEntity offerEntity = this.offerRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Offer with id " + id + " not found!"));
        return offerMapper.offerEntityToAddOfferDto(offerEntity);
    }

    public void deleteOffer(long id) {
        Optional<OfferEntity> byId = offerRepository.findById(id);
        offerRepository.deleteById(id);
    }

    public void buyOffer(long id, Long userDetailsId) {
        OfferEntity wantedOffer = offerRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Offer with id " + id + " not found!"));

        UserEntity buyer = userService.findById(userDetailsId);
        wantedOffer.setBuyer(buyer);
        offerRepository.save(wantedOffer);
    }
}
