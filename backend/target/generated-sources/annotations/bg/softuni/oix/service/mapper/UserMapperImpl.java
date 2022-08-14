package bg.softuni.oix.service.mapper;

import bg.softuni.oix.model.entity.UserEntity;
import bg.softuni.oix.service.dto.EditProfileDTO;
import bg.softuni.oix.service.dto.UserRegistrationDto;
import bg.softuni.oix.service.views.UserView;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-13T22:27:12+0300",
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

    @Override
    public EditProfileDTO userEntityToEditProfileDTO(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        EditProfileDTO editProfileDTO = new EditProfileDTO();

        editProfileDTO.setId( userEntity.getId() );
        editProfileDTO.setFirstName( userEntity.getFirstName() );
        editProfileDTO.setLastName( userEntity.getLastName() );
        editProfileDTO.setEmail( userEntity.getEmail() );

        return editProfileDTO;
    }

    @Override
    public void updateUserEntity(UserEntity userEntity, EditProfileDTO editProfileDTO) {
        if ( editProfileDTO == null ) {
            return;
        }

        userEntity.setId( editProfileDTO.getId() );
        if ( editProfileDTO.getEmail() != null ) {
            userEntity.setEmail( editProfileDTO.getEmail() );
        }
        if ( editProfileDTO.getFirstName() != null ) {
            userEntity.setFirstName( editProfileDTO.getFirstName() );
        }
        if ( editProfileDTO.getLastName() != null ) {
            userEntity.setLastName( editProfileDTO.getLastName() );
        }
    }
}
