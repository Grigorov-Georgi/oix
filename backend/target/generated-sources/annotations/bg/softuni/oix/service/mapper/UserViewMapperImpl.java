package bg.softuni.oix.service.mapper;

import bg.softuni.oix.model.entity.CategoryEntity;
import bg.softuni.oix.model.entity.LocationEntity;
import bg.softuni.oix.model.entity.UserEntity;
import bg.softuni.oix.service.views.UserView;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-06T16:01:14+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class UserViewMapperImpl implements UserViewMapper {

    @Override
    public UserEntity toEntity(UserView dto) {
        if ( dto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId( dto.getId() );
        userEntity.setFirstName( dto.getFirstName() );
        userEntity.setLastName( dto.getLastName() );

        return userEntity;
    }

    @Override
    public List<UserEntity> toEntity(List<UserView> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<UserEntity> list = new ArrayList<UserEntity>( dtoList.size() );
        for ( UserView userView : dtoList ) {
            list.add( toEntity( userView ) );
        }

        return list;
    }

    @Override
    public List<UserView> toDto(List<UserEntity> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<UserView> list = new ArrayList<UserView>( entityList.size() );
        for ( UserEntity userEntity : entityList ) {
            list.add( toDto( userEntity ) );
        }

        return list;
    }

    @Override
    public void partialUpdate(UserEntity entity, UserView dto) {
        if ( dto == null ) {
            return;
        }

        entity.setId( dto.getId() );
        if ( dto.getFirstName() != null ) {
            entity.setFirstName( dto.getFirstName() );
        }
        if ( dto.getLastName() != null ) {
            entity.setLastName( dto.getLastName() );
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
    public UserView toDto(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserView userView = new UserView();

        userView.setId( userEntity.getId() );
        userView.setFirstName( userEntity.getFirstName() );
        userView.setLastName( userEntity.getLastName() );

        return userView;
    }
}
