package bg.softuni.oix.service.mapper;

import bg.softuni.oix.model.entity.LocationEntity;
import bg.softuni.oix.service.dto.AddLocationDTO;
import bg.softuni.oix.service.views.LocationView;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface AddLocationDtoMapper extends EntityMapper<AddLocationDTO, LocationEntity>{
    LocationEntity toEntity(AddLocationDTO addLocationDTO);
}
