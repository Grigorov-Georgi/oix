package bg.softuni.oix.service.mapper;

import bg.softuni.oix.model.entity.OfferEntity;
import bg.softuni.oix.model.enums.CategoryEnum;
import bg.softuni.oix.model.user.OixUserDetails;
import bg.softuni.oix.service.CategoryService;
import bg.softuni.oix.service.LocationService;
import bg.softuni.oix.service.UserService;
import bg.softuni.oix.service.dto.AddOfferDTO;
import bg.softuni.oix.service.views.OfferView;
import org.mapstruct.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.time.LocalDate;

@Mapper(componentModel = "spring",
        uses = {CategoryService.class, LocationService.class})
public interface OfferMapper {
    @Mapping(target = "category", source = "category")
    @Mapping(target = "location", source = "location")
    OfferEntity addOfferDtoToOfferEntity(AddOfferDTO addOfferDTO);

    @Mapping(target = "location", source = "location.city")
    OfferView offerEntityToOfferView(OfferEntity offerEntity);

    @BeforeMapping
    default void  setReleaseDate(@MappingTarget OfferEntity offerEntity){
        offerEntity.setReleaseDate(LocalDate.now());
    }

    @AfterMapping
    default void setAdditionalFields(@MappingTarget OfferEntity offerEntity,
                                     CategoryService categoryService,
                                     LocationService locationService,
                                     AddOfferDTO addOfferDTO){
        offerEntity.setCategory(categoryService.findByName(CategoryEnum.valueOf(addOfferDTO.getCategory())));
        offerEntity.setLocation(locationService.findByCity(addOfferDTO.getLocation()));
    }
}
