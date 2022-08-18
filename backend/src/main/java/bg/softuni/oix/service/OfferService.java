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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
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

    public Page<OfferView> getAllOffers(Pageable pageable) {
        return this.offerRepository.findAllByBuyerIsNull(pageable).map(o -> {
            OfferView offerView = offerMapper.offerEntityToOfferView(o);
            offerView.setAdminOffer(userService.isAdmin(o.getSeller()));
            return offerView;
        });
    }

    public List<OfferView> getMyOffers(long id) {
        return this.offerRepository.findAllBySellerIdEquals(id).stream().map(offerMapper::offerEntityToOfferView)
                .collect(Collectors.toList());
    }

    public List<OfferView> getBoughtItems(long id) {
        return this.offerRepository.findAllByBuyerIdEquals(id).stream().map(offerMapper::offerEntityToOfferView)
                .collect(Collectors.toList());
    }

    public List<OfferView> getListWithRandomThreeOffers() {
        List<OfferView> offersForHomePage = new ArrayList<>();

        List<OfferEntity> allOffers = offerRepository.findAll();
        Random random = new Random();

        while (offersForHomePage.size() < 3){
            OfferView offerView = offerMapper.offerEntityToOfferView(allOffers.get(random.nextInt(allOffers.size())));
            if(offersForHomePage.isEmpty()) {
                offersForHomePage.add(offerView);
            } else {
                boolean offerAdded = false;
                for (OfferView offer : offersForHomePage) {
                    if ((long) offer.getId() == (long) offerView.getId()){
                        offerAdded = true;
                    }
                }
                if (!offerAdded){
                    offersForHomePage.add(offerView);
                }
            }
        }

        return offersForHomePage;
    }

    public Optional<OfferEntity> findOfferEntityById(long id) {
        return  this.offerRepository.findById(id);
    }

    public AddOfferDTO findDtoById(long id) {
        OfferEntity offerEntity = this.offerRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Offer with id " + id + " not found!"));
        return offerMapper.offerEntityToAddOfferDto(offerEntity);
    }

    public void deleteOffer(OfferEntity offerEntity) {
        offerRepository.deleteById(offerEntity.getId());
    }

    public void buyOffer(long id, Long userDetailsId) {
        OfferEntity wantedOffer = offerRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Offer with id " + id + " not found!"));

        UserEntity buyer = userService.findById(userDetailsId);
        wantedOffer.setBuyer(buyer);
        offerRepository.save(wantedOffer);
    }

    public List<OfferView> getAllByTitle(String title) {
        return offerRepository.findAllByTitleContainingIgnoreCase(title)
                .stream()
                .map(offerMapper::offerEntityToOfferView)
                .collect(Collectors.toList());
    }

    public Optional<OfferEntity> findById(long id) {
        return offerRepository.findById(id);
    }

    public void update(AddOfferDTO addOfferDTO) {
        OfferEntity offerEntity = offerRepository.findById(addOfferDTO.getId()).get();
        offerMapper.updateOfferEntity(offerEntity, addOfferDTO);
        offerRepository.save(offerEntity);
//        UserEntity userById = userRepository.findById(editProfileDTO.getId()).get();
//        userMapper.updateUserEntity(userById, editProfileDTO);
//        userRepository.save(userById);
    }
}
