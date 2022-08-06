package bg.softuni.oix.service.mapper;

import bg.softuni.oix.model.entity.CategoryEntity;
import bg.softuni.oix.model.entity.LocationEntity;
import bg.softuni.oix.model.entity.OfferEntity;
import bg.softuni.oix.model.enums.CategoryEnum;
import bg.softuni.oix.service.dto.AddOfferDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-06T23:59:28+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class AddOfferMapperImpl implements AddOfferMapper {

    @Override
    public List<OfferEntity> toEntity(List<AddOfferDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<OfferEntity> list = new ArrayList<OfferEntity>( dtoList.size() );
        for ( AddOfferDTO addOfferDTO : dtoList ) {
            list.add( toEntity( addOfferDTO ) );
        }

        return list;
    }

    @Override
    public List<AddOfferDTO> toDto(List<OfferEntity> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<AddOfferDTO> list = new ArrayList<AddOfferDTO>( entityList.size() );
        for ( OfferEntity offerEntity : entityList ) {
            list.add( toDto( offerEntity ) );
        }

        return list;
    }

    @Override
    public void partialUpdate(OfferEntity entity, AddOfferDTO dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getTitle() != null ) {
            entity.setTitle( dto.getTitle() );
        }
        if ( dto.getLocation() != null ) {
            entity.setLocation( location( dto.getLocation() ) );
        }
        if ( dto.getPrice() != null ) {
            entity.setPrice( dto.getPrice() );
        }
        if ( dto.getDescription() != null ) {
            entity.setDescription( dto.getDescription() );
        }
        if ( dto.getUrlPicture() != null ) {
            entity.setUrlPicture( dto.getUrlPicture() );
        }
        if ( dto.getCategory() != null ) {
            entity.setCategory( category( dto.getCategory() ) );
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
    public AddOfferDTO toDto(OfferEntity offerEntity) {
        if ( offerEntity == null ) {
            return null;
        }

        AddOfferDTO addOfferDTO = new AddOfferDTO();

        CategoryEnum name = offerEntityCategoryName( offerEntity );
        if ( name != null ) {
            addOfferDTO.setCategory( name.name() );
        }
        addOfferDTO.setLocation( offerEntityLocationCity( offerEntity ) );
        addOfferDTO.setTitle( offerEntity.getTitle() );
        addOfferDTO.setDescription( offerEntity.getDescription() );
        addOfferDTO.setPrice( offerEntity.getPrice() );
        addOfferDTO.setUrlPicture( offerEntity.getUrlPicture() );

        return addOfferDTO;
    }

    @Override
    public OfferEntity toEntity(AddOfferDTO addOfferDTO) {
        if ( addOfferDTO == null ) {
            return null;
        }

        OfferEntity offerEntity = new OfferEntity();

        offerEntity.setCategory( addOfferDTOToCategoryEntity( addOfferDTO ) );
        offerEntity.setLocation( addOfferDTOToLocationEntity( addOfferDTO ) );
        offerEntity.setTitle( addOfferDTO.getTitle() );
        offerEntity.setPrice( addOfferDTO.getPrice() );
        offerEntity.setDescription( addOfferDTO.getDescription() );
        offerEntity.setUrlPicture( addOfferDTO.getUrlPicture() );

        return offerEntity;
    }

    private CategoryEnum offerEntityCategoryName(OfferEntity offerEntity) {
        if ( offerEntity == null ) {
            return null;
        }
        CategoryEntity category = offerEntity.getCategory();
        if ( category == null ) {
            return null;
        }
        CategoryEnum name = category.getName();
        if ( name == null ) {
            return null;
        }
        return name;
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

    protected CategoryEntity addOfferDTOToCategoryEntity(AddOfferDTO addOfferDTO) {
        if ( addOfferDTO == null ) {
            return null;
        }

        CategoryEntity categoryEntity = new CategoryEntity();

        if ( addOfferDTO.getCategory() != null ) {
            categoryEntity.setName( Enum.valueOf( CategoryEnum.class, addOfferDTO.getCategory() ) );
        }

        return categoryEntity;
    }

    protected LocationEntity addOfferDTOToLocationEntity(AddOfferDTO addOfferDTO) {
        if ( addOfferDTO == null ) {
            return null;
        }

        LocationEntity locationEntity = new LocationEntity();

        locationEntity.setCity( addOfferDTO.getLocation() );

        return locationEntity;
    }
}
