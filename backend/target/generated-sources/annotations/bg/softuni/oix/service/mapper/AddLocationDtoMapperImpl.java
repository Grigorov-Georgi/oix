package bg.softuni.oix.service.mapper;

import bg.softuni.oix.model.entity.CategoryEntity;
import bg.softuni.oix.model.entity.LocationEntity;
import bg.softuni.oix.service.dto.AddLocationDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-06T16:01:15+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class AddLocationDtoMapperImpl implements AddLocationDtoMapper {

    @Override
    public AddLocationDTO toDto(LocationEntity entity) {
        if ( entity == null ) {
            return null;
        }

        AddLocationDTO addLocationDTO = new AddLocationDTO();

        addLocationDTO.setCity( entity.getCity() );

        return addLocationDTO;
    }

    @Override
    public List<LocationEntity> toEntity(List<AddLocationDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<LocationEntity> list = new ArrayList<LocationEntity>( dtoList.size() );
        for ( AddLocationDTO addLocationDTO : dtoList ) {
            list.add( toEntity( addLocationDTO ) );
        }

        return list;
    }

    @Override
    public List<AddLocationDTO> toDto(List<LocationEntity> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<AddLocationDTO> list = new ArrayList<AddLocationDTO>( entityList.size() );
        for ( LocationEntity locationEntity : entityList ) {
            list.add( toDto( locationEntity ) );
        }

        return list;
    }

    @Override
    public void partialUpdate(LocationEntity entity, AddLocationDTO dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getCity() != null ) {
            entity.setCity( dto.getCity() );
        }
    }

    @Override
    public LocationEntity location(String value) {
        if ( value == null ) {
            return null;
        }

        LocationEntity locationEntity = new LocationEntity();

        return locationEntity;
    }

    @Override
    public CategoryEntity category(String value) {
        if ( value == null ) {
            return null;
        }

        CategoryEntity categoryEntity = new CategoryEntity();

        return categoryEntity;
    }

    @Override
    public LocationEntity toEntity(AddLocationDTO addLocationDTO) {
        if ( addLocationDTO == null ) {
            return null;
        }

        LocationEntity locationEntity = new LocationEntity();

        locationEntity.setCity( addLocationDTO.getCity() );

        return locationEntity;
    }
}
