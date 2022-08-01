package bg.softuni.oix.service.mapper;

import bg.softuni.oix.model.entity.LocationEntity;
import bg.softuni.oix.model.entity.OfferEntity;
import bg.softuni.oix.service.views.OfferView;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-01T21:38:12+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class OfferViewMapperImpl implements OfferViewMapper {

    @Override
    public List<OfferEntity> toEntity(List<OfferView> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<OfferEntity> list = new ArrayList<OfferEntity>( dtoList.size() );
        for ( OfferView offerView : dtoList ) {
            list.add( toEntity( offerView ) );
        }

        return list;
    }

    @Override
    public List<OfferView> toDto(List<OfferEntity> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<OfferView> list = new ArrayList<OfferView>( entityList.size() );
        for ( OfferEntity offerEntity : entityList ) {
            list.add( toDto( offerEntity ) );
        }

        return list;
    }

    @Override
    public void partialUpdate(OfferEntity entity, OfferView dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        if ( dto.getTitle() != null ) {
            entity.setTitle( dto.getTitle() );
        }
        if ( dto.getLocation() != null ) {
            entity.setLocation( map( dto.getLocation() ) );
        }
        if ( dto.getPrice() != null ) {
            entity.setPrice( dto.getPrice() );
        }
        if ( dto.getDescription() != null ) {
            entity.setDescription( dto.getDescription() );
        }
    }

    @Override
    public OfferView toDto(OfferEntity offerEntity) {
        if ( offerEntity == null ) {
            return null;
        }

        OfferView offerView = new OfferView();

        offerView.setLocation( offerEntityLocationCity( offerEntity ) );
        offerView.setTitle( offerEntity.getTitle() );
        offerView.setPrice( offerEntity.getPrice() );
        offerView.setDescription( offerEntity.getDescription() );
        offerView.setId( offerEntity.getId() );

        return offerView;
    }

    @Override
    public OfferEntity toEntity(OfferView offerView) {
        if ( offerView == null ) {
            return null;
        }

        OfferEntity offerEntity = new OfferEntity();

        offerEntity.setLocation( offerViewToLocationEntity( offerView ) );
        if ( offerView.getId() != null ) {
            offerEntity.setId( offerView.getId() );
        }
        offerEntity.setTitle( offerView.getTitle() );
        offerEntity.setPrice( offerView.getPrice() );
        offerEntity.setDescription( offerView.getDescription() );

        return offerEntity;
    }

    @Override
    public LocationEntity map(String value) {
        if ( value == null ) {
            return null;
        }

        LocationEntity locationEntity = new LocationEntity();

        return locationEntity;
    }

    private String offerEntityLocationCity(OfferEntity offerEntity) {
        if ( offerEntity == null ) {
            return null;
        }
        LocationEntity location = offerEntity.getLocation();
        if ( location == null ) {
            return null;
        }
        String city = location.getCity();
        if ( city == null ) {
            return null;
        }
        return city;
    }

    protected LocationEntity offerViewToLocationEntity(OfferView offerView) {
        if ( offerView == null ) {
            return null;
        }

        LocationEntity locationEntity = new LocationEntity();

        locationEntity.setCity( offerView.getLocation() );

        return locationEntity;
    }
}
