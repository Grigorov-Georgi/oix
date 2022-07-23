package bg.softuni.oix.service;

import bg.softuni.oix.repository.OfferRepository;
import bg.softuni.oix.service.dto.OfferDto;
import bg.softuni.oix.service.mapper.OfferMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferService {
    private final OfferRepository offerRepository;
//    private OfferMapper offerMapper;

    public OfferService(OfferRepository offerRepository, OfferMapper offerMapper) {
        this.offerRepository = offerRepository;
//        this.offerMapper = offerMapper;
    }

    public List<OfferDto> findAll(){
//        return offerRepository.findAll()
//                .stream()
//                .map(offerMapper::toDto)
//                .collect(Collectors.toList());
        return null;
    }
}
