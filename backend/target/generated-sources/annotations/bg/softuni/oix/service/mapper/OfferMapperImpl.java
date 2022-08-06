package bg.softuni.oix.service.mapper;

import bg.softuni.oix.model.entity.LocationEntity;
import bg.softuni.oix.model.entity.OfferEntity;
import bg.softuni.oix.model.enums.CategoryEnum;
import bg.softuni.oix.service.CategoryService;
import bg.softuni.oix.service.LocationService;
import bg.softuni.oix.service.dto.AddOfferDTO;
import bg.softuni.oix.service.views.OfferView;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-07T00:33:43+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class OfferMapperImpl implements OfferMapper {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private LocationService locationService;

    @Override
    public OfferEntity addOfferDtoToOfferEntity(AddOfferDTO addOfferDTO) {
        if ( addOfferDTO == null ) {
            return null;
        }

        OfferEntity offerEntity = new OfferEntity();

        setReleaseDate( offerEntity );

        if ( addOfferDTO.getCategory() != null ) {
            offerEntity.setCategory( categoryService.findByName( Enum.valueOf( CategoryEnum.class, addOfferDTO.getCategory() ) ) );
        }
        offerEntity.setLocation( locationService.findByCity( addOfferDTO.getLocation() ) );
        offerEntity.setTitle( addOfferDTO.getTitle() );
        offerEntity.setPrice( addOfferDTO.getPrice() );
        offerEntity.setDescription( addOfferDTO.getDescription() );
        offerEntity.setUrlPicture( addOfferDTO.getUrlPicture() );

        return offerEntity;
    }

    @Override
    public OfferView offerEntityToOfferView(OfferEntity offerEntity) {
        if ( offerEntity == null ) {
            return null;
        }

        OfferView offerView = new OfferView();

        offerView.setLocation( offerEntityLocationCity( offerEntity ) );
        offerView.setTitle( offerEntity.getTitle() );
        offerView.setPrice( offerEntity.getPrice() );
        offerView.setDescription( offerEntity.getDescription() );
        offerView.setId( offerEntity.getId() );
        offerView.setUrlPicture( offerEntity.getUrlPicture() );

        return offerView;
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
}
