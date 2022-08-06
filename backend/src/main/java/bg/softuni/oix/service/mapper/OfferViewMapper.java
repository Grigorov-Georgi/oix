package bg.softuni.oix.service.mapper;

import bg.softuni.oix.model.entity.LocationEntity;
import bg.softuni.oix.model.entity.OfferEntity;
import bg.softuni.oix.service.views.OfferView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring"
)
public interface OfferViewMapper extends EntityMapper<OfferView, OfferEntity> {
    @Mapping(target = "location", source = "location.city")
    OfferView toDto(OfferEntity offerEntity);

    @Mapping(target = "location.city", source = "location")
    OfferEntity toEntity(OfferView offerView);
}
