package bg.softuni.oix.service.mapper;

import bg.softuni.oix.model.entity.CategoryEntity;
import bg.softuni.oix.model.entity.LocationEntity;
import bg.softuni.oix.service.views.LocationView;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-06T23:59:27+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class LocationViewMapperImpl implements LocationViewMapper {

    @Override
    public LocationEntity toEntity(LocationView dto) {
        if ( dto == null ) {
            return null;
        }

        LocationEntity locationEntity = new LocationEntity();

        locationEntity.setId( dto.getId() );
        locationEntity.setCity( dto.getCity() );

        return locationEntity;
    }

    @Override
    public List<LocationEntity> toEntity(List<LocationView> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<LocationEntity> list = new ArrayList<LocationEntity>( dtoList.size() );
        for ( LocationView locationView : dtoList ) {
            list.add( toEntity( locationView ) );
        }

        return list;
    }

    @Override
    public List<LocationView> toDto(List<LocationEntity> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<LocationView> list = new ArrayList<LocationView>( entityList.size() );
        for ( LocationEntity locationEntity : entityList ) {
            list.add( toDto( locationEntity ) );
        }

        return list;
    }

    @Override
    public void partialUpdate(LocationEntity entity, LocationView dto) {
        if ( dto == null ) {
            return;
        }

        entity.setId( dto.getId() );
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
    public LocationView toDto(LocationEntity locationEntity) {
        if ( locationEntity == null ) {
            return null;
        }

        LocationView locationView = new LocationView();

        locationView.setId( locationEntity.getId() );
        locationView.setCity( locationEntity.getCity() );

        return locationView;
    }
}
