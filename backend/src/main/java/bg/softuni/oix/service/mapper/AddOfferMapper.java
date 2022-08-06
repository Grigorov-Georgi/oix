package bg.softuni.oix.service.mapper;

import bg.softuni.oix.model.entity.OfferEntity;
import bg.softuni.oix.service.CategoryService;
import bg.softuni.oix.service.LocationService;
import bg.softuni.oix.service.dto.AddOfferDTO;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(
        componentModel = "spring"
//        uses = {LocationService.class, CategoryService.class}
)
public interface AddOfferMapper extends EntityMapper<AddOfferDTO, OfferEntity> {
    @Mapping(target = "category", source = "category.name")
    @Mapping(target = "location", source = "location.city")
//    @Mapping(target = "category", ignore = true)
//    @Mapping(target = "location", ignore = true)
    AddOfferDTO toDto(OfferEntity offerEntity);

    @Mapping(target = "category.name", source = "category")
    @Mapping(target = "location.city", source = "location")
//    @Mapping(target = "category", ignore = true)
//    @Mapping(target = "location", ignore = true)
    OfferEntity toEntity(AddOfferDTO addOfferDTO);

}
