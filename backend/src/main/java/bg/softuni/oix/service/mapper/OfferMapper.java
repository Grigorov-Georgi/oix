package bg.softuni.oix.service.mapper;

import bg.softuni.oix.model.entity.OfferEntity;
import bg.softuni.oix.model.enums.CategoryEnum;
import bg.softuni.oix.service.CategoryService;
import bg.softuni.oix.service.LocationService;
import bg.softuni.oix.service.UserService;
import bg.softuni.oix.service.dto.AddOfferDTO;
import bg.softuni.oix.service.views.OfferView;
import org.mapstruct.*;

import java.time.LocalDate;

@Mapper(componentModel = "spring",
        uses = {CategoryService.class,
                LocationService.class,
                UserService.class,
                CommentMapper.class})
public interface OfferMapper {
    @Mapping(target = "category", source = "category")
    @Mapping(target = "location", source = "location")
    OfferEntity addOfferDtoToOfferEntity(AddOfferDTO addOfferDTO);

    @Mapping(target = "category", source = "category.name")
    @Mapping(target = "location", source = "location.city")
    AddOfferDTO offerEntityToAddOfferDto(OfferEntity offerEntity);

    @Mapping(target = "location", source = "location.city")
    @Mapping(target = "category", source = "category.name")
    @Mapping(target = "comments", source = "comments")
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

    @AfterMapping
    default void setAdditionalFieldsForView(@MappingTarget OfferView offerView,
                                     OfferEntity offerEntity){
        offerView.setCategory(offerEntity.getCategory().getName().name());
        offerView.setLocation(offerEntity.getLocation().getCity());
        String fullName = offerEntity.getSeller().getFirstName() + " " + offerEntity.getSeller().getLastName();
        offerView.setSellerFullName(fullName);
        offerView.setSellerId(offerEntity.getSeller().getId());
    }
}
