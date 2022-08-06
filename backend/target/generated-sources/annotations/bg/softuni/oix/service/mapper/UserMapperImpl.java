package bg.softuni.oix.service.mapper;

import bg.softuni.oix.model.entity.UserEntity;
import bg.softuni.oix.service.dto.UserRegistrationDto;
import bg.softuni.oix.service.views.UserView;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-07T00:41:08+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserEntity userRegisterDtoToUserEntity(UserRegistrationDto userRegistrationDto) {
        if ( userRegistrationDto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setEmail( userRegistrationDto.getEmail() );
        userEntity.setFirstName( userRegistrationDto.getFirstName() );
        userEntity.setLastName( userRegistrationDto.getLastName() );
        userEntity.setPassword( userRegistrationDto.getPassword() );

        return userEntity;
    }

    @Override
    public UserView userEntityToUserView(UserEntity userEntity) {
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
