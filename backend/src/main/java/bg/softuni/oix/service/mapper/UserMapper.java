package bg.softuni.oix.service.mapper;

import bg.softuni.oix.model.entity.UserEntity;
import bg.softuni.oix.service.dto.UserRegistrationDto;
import bg.softuni.oix.service.views.UserView;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface UserMapper{
    UserEntity userRegisterDtoToUserEntity(UserRegistrationDto userRegistrationDto);

    UserView userEntityToUserView(UserEntity userEntity);
}
