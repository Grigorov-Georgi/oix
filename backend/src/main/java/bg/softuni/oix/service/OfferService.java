package bg.softuni.oix.service;

import bg.softuni.oix.model.entity.LocationEntity;
import bg.softuni.oix.model.entity.OfferEntity;
import bg.softuni.oix.repository.LocationRepository;
import bg.softuni.oix.repository.OfferRepository;
import bg.softuni.oix.service.dto.AddOfferDTO;
import bg.softuni.oix.service.dto.OfferDto;
import bg.softuni.oix.service.mapper.OfferMapper;
import bg.softuni.oix.service.mapper.OfferViewMapper;
import bg.softuni.oix.service.views.OfferView;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfferService {
    private final OfferRepository offerRepository;
    private LocationRepository locationRepository;
    private OfferViewMapper offerViewMapper;

    public OfferService(OfferRepository offerRepository, OfferMapper offerMapper, LocationRepository locationRepository, OfferViewMapper offerViewMapper) {
        this.offerRepository = offerRepository;
        this.locationRepository = locationRepository;
        this.offerViewMapper = offerViewMapper;
    }

    public List<OfferDto> findAll() {
//        return offerRepository.findAll()
//                .stream()
//                .map(offerMapper::toDto)
//                .collect(Collectors.toList());
        return null;
    }

    public void save(AddOfferDTO addOfferDTO) {
        OfferEntity offerEntity = new OfferEntity();
        offerEntity.setTitle(addOfferDTO.getTitle());
        Optional<LocationEntity> optLocation = this.locationRepository.findByCity(addOfferDTO.getLocation());
        offerEntity.setLocation(optLocation.orElse(null));
        offerEntity.setDescription(addOfferDTO.getDescription());
        offerEntity.setPrice(addOfferDTO.getPrice());
        offerEntity.setReleaseDate(LocalDate.now());

        this.offerRepository.save(offerEntity);
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
}
