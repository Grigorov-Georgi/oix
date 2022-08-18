package bg.softuni.oix.service.mapper;

import bg.softuni.oix.model.entity.CategoryEntity;
import bg.softuni.oix.model.entity.CommentEntity;
import bg.softuni.oix.model.entity.LocationEntity;
import bg.softuni.oix.model.entity.OfferEntity;
import bg.softuni.oix.model.enums.CategoryEnum;
import bg.softuni.oix.service.CategoryService;
import bg.softuni.oix.service.LocationService;
import bg.softuni.oix.service.dto.AddOfferDTO;
import bg.softuni.oix.service.views.CommentView;
import bg.softuni.oix.service.views.OfferView;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-18T10:27:20+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class OfferMapperImpl implements OfferMapper {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private CommentMapper commentMapper;

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
        offerEntity.setId( addOfferDTO.getId() );
        offerEntity.setTitle( addOfferDTO.getTitle() );
        offerEntity.setPrice( addOfferDTO.getPrice() );
        offerEntity.setDescription( addOfferDTO.getDescription() );
        offerEntity.setUrlPicture( addOfferDTO.getUrlPicture() );

        return offerEntity;
    }

    @Override
    public AddOfferDTO offerEntityToAddOfferDto(OfferEntity offerEntity) {
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
        addOfferDTO.setId( offerEntity.getId() );

        return addOfferDTO;
    }

    @Override
    public OfferView offerEntityToOfferView(OfferEntity offerEntity) {
        if ( offerEntity == null ) {
            return null;
        }

        OfferView offerView = new OfferView();

        offerView.setLocation( offerEntityLocationCity( offerEntity ) );
        CategoryEnum name = offerEntityCategoryName( offerEntity );
        if ( name != null ) {
            offerView.setCategory( name.name() );
        }
        offerView.setComments( commentEntityListToCommentViewList( offerEntity.getComments() ) );
        offerView.setTitle( offerEntity.getTitle() );
        offerView.setPrice( offerEntity.getPrice() );
        offerView.setDescription( offerEntity.getDescription() );
        offerView.setId( offerEntity.getId() );
        offerView.setUrlPicture( offerEntity.getUrlPicture() );

        setAdditionalFieldsForView( offerView, offerEntity );

        return offerView;
    }

    @Override
    public void updateOfferEntity(OfferEntity offerEntity, AddOfferDTO addOfferDTO) {
        if ( addOfferDTO == null ) {
            return;
        }

        setReleaseDate( offerEntity );

        offerEntity.setId( addOfferDTO.getId() );
        if ( addOfferDTO.getTitle() != null ) {
            offerEntity.setTitle( addOfferDTO.getTitle() );
        }
        if ( addOfferDTO.getLocation() != null ) {
            offerEntity.setLocation( locationService.findByCity( addOfferDTO.getLocation() ) );
        }
        if ( addOfferDTO.getPrice() != null ) {
            offerEntity.setPrice( addOfferDTO.getPrice() );
        }
        if ( addOfferDTO.getDescription() != null ) {
            offerEntity.setDescription( addOfferDTO.getDescription() );
        }
        if ( addOfferDTO.getUrlPicture() != null ) {
            offerEntity.setUrlPicture( addOfferDTO.getUrlPicture() );
        }
        if ( addOfferDTO.getCategory() != null ) {
            offerEntity.setCategory( categoryService.findByName( Enum.valueOf( CategoryEnum.class, addOfferDTO.getCategory() ) ) );
        }
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

    protected List<CommentView> commentEntityListToCommentViewList(List<CommentEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<CommentView> list1 = new ArrayList<CommentView>( list.size() );
        for ( CommentEntity commentEntity : list ) {
            list1.add( commentMapper.commentEntityToCommentView( commentEntity ) );
        }

        return list1;
    }
}
