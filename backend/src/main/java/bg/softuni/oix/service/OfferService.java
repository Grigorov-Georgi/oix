package bg.softuni.oix.service;

import bg.softuni.oix.exception.ObjectNotFoundException;
import bg.softuni.oix.model.entity.OfferEntity;
import bg.softuni.oix.model.entity.UserEntity;
import bg.softuni.oix.model.enums.CategoryEnum;
import bg.softuni.oix.model.user.OixUserDetails;
import bg.softuni.oix.repository.OfferRepository;
import bg.softuni.oix.service.dto.AddOfferDTO;
import bg.softuni.oix.service.mapper.AddOfferMapper;
import bg.softuni.oix.service.mapper.OfferMapper;
import bg.softuni.oix.service.mapper.OfferViewMapper;
import bg.softuni.oix.service.views.OfferView;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferService {
    private final OfferRepository offerRepository;
    private OfferViewMapper offerViewMapper;
    private AddOfferMapper addOfferMapper;
    private UserService userService;
    private LocationService locationService;
    private CategoryService categoryService;
    private OfferMapper offerMapper;

    public OfferService(OfferRepository offerRepository, OfferViewMapper offerViewMapper, AddOfferMapper addOfferMapper, UserService userService, LocationService locationService, CategoryService categoryService, OfferMapper offerMapper) {
        this.offerRepository = offerRepository;
        this.offerViewMapper = offerViewMapper;
        this.addOfferMapper = addOfferMapper;
        this.userService = userService;
        this.locationService = locationService;
        this.categoryService = categoryService;
        this.offerMapper = offerMapper;
    }

    public void save(AddOfferDTO addOfferDTO, OixUserDetails userDetails) {
        //TODO: Fix the mapper
//        OfferEntity newOffer = addOfferMapper.toEntity(addOfferDTO);
//        newOffer.setReleaseDate(LocalDate.now());

//        newOffer.setLocation(locationService.findByCity(addOfferDTO.getLocation()));
//        newOffer.setCategory(categoryService.findByName(CategoryEnum.valueOf(addOfferDTO.getCategory())));

        OfferEntity newOffer = offerMapper.addOfferDtoToOfferEntity(addOfferDTO);
        UserEntity loggedUser = userService.findById(userDetails.getId());
        newOffer.setSeller(loggedUser);

        this.offerRepository.save(newOffer);
    }

    public List<OfferView> getAllOffers() {
        return this.offerRepository.findAll().stream().map(o ->
                offerViewMapper.toDto(o))
                .collect(Collectors.toList());
    }

    public List<OfferView> getListWithLastThreeOffers() {
        List<OfferView> offersForHomePage = new ArrayList<>();

        long repoCount = this.offerRepository.count();

        for (long i = repoCount; i > repoCount - 3; i--) {
            OfferEntity offerEntity = this.offerRepository.findById(i).get();
            offersForHomePage.add(offerViewMapper.toDto(offerEntity));
        }
        return offersForHomePage;
    }

    public OfferView findById(long id) {
        OfferEntity offerEntity = this.offerRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Offer with id " + id + " not found!"));
        return offerViewMapper.toDto(offerEntity);
    }
}
