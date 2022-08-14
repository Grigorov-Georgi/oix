package bg.softuni.oix.service.mapper;

import bg.softuni.oix.model.entity.UserEntity;
import bg.softuni.oix.service.dto.EditProfileDTO;
import bg.softuni.oix.service.dto.UserRegistrationDto;
import bg.softuni.oix.service.views.UserView;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring"
)
public interface UserMapper{
    UserEntity userRegisterDtoToUserEntity(UserRegistrationDto userRegistrationDto);

    UserView userEntityToUserView(UserEntity userEntity);

    EditProfileDTO userEntityToEditProfileDTO(UserEntity userEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserEntity(@MappingTarget UserEntity userEntity, EditProfileDTO editProfileDTO);
}
