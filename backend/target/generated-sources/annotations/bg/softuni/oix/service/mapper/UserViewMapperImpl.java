package bg.softuni.oix.service.mapper;

import bg.softuni.oix.model.entity.UserEntity;
import bg.softuni.oix.service.views.UserView;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-01T21:08:47+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class UserViewMapperImpl implements UserViewMapper {

    @Override
    public UserEntity toEntity(UserView arg0) {
        if ( arg0 == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId( arg0.getId() );
        userEntity.setFirstName( arg0.getFirstName() );
        userEntity.setLastName( arg0.getLastName() );

        return userEntity;
    }

    @Override
    public List<UserEntity> toEntity(List<UserView> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<UserEntity> list = new ArrayList<UserEntity>( arg0.size() );
        for ( UserView userView : arg0 ) {
            list.add( toEntity( userView ) );
        }

        return list;
    }

    @Override
    public List<UserView> toDto(List<UserEntity> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<UserView> list = new ArrayList<UserView>( arg0.size() );
        for ( UserEntity userEntity : arg0 ) {
            list.add( toDto( userEntity ) );
        }

        return list;
    }

    @Override
    public void partialUpdate(UserEntity arg0, UserView arg1) {
        if ( arg1 == null ) {
            return;
        }

        arg0.setId( arg1.getId() );
        if ( arg1.getFirstName() != null ) {
            arg0.setFirstName( arg1.getFirstName() );
        }
        if ( arg1.getLastName() != null ) {
            arg0.setLastName( arg1.getLastName() );
        }
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
