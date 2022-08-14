package bg.softuni.oix.service.mapper;

import bg.softuni.oix.model.entity.LocationEntity;
import bg.softuni.oix.service.dto.AddLocationDTO;
import bg.softuni.oix.service.views.LocationView;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-13T22:17:24+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class LocationMapperImpl implements LocationMapper {

    @Override
    public LocationEntity addLocationDtoToEntity(AddLocationDTO addLocationDTO) {
        if ( addLocationDTO == null ) {
            return null;
        }

        LocationEntity locationEntity = new LocationEntity();

        locationEntity.setCity( addLocationDTO.getCity() );

        return locationEntity;
    }

    @Override
    public LocationView locationEntityToLocationView(LocationEntity locationEntity) {
        if ( locationEntity == null ) {
            return null;
        }

        LocationView locationView = new LocationView();

        locationView.setId( locationEntity.getId() );
        locationView.setCity( locationEntity.getCity() );

        return locationView;
    }
}
