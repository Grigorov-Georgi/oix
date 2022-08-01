package bg.softuni.oix.service.mapper;

import bg.softuni.oix.model.entity.OfferEntity;
import bg.softuni.oix.service.dto.OfferDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring"
)
public interface OfferMapper extends EntityMapper<OfferDto, OfferEntity>{
//    @Mapping(target = "sellerName", source = "seller.firstName")
//    @Mapping(target = "city", source = "location.city")
//    @Mapping(target = "categoryName", source = "category")
//    @Mapping(target = "releaseDate", source = "releaseDate")
//    @Mapping(target = "description", source = "description")
//    OfferDto toDto(OfferEntity offerEntity);

//    @Mapping(target = "seller.firstName", source = "sellerName")
//    @Mapping(target = "location.city", source = "city")
//    OfferEntity toEntity(OfferDto offerDto);
}
