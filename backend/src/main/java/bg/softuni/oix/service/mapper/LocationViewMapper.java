package bg.softuni.oix.service.mapper;

import bg.softuni.oix.model.entity.LocationEntity;
import bg.softuni.oix.model.entity.UserEntity;
import bg.softuni.oix.service.dto.UserRegistrationDto;
import bg.softuni.oix.service.views.LocationView;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface LocationViewMapper extends EntityMapper<LocationView, LocationEntity>{
    LocationView toDto(LocationEntity locationEntity);
}
