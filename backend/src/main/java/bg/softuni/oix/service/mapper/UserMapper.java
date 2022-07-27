package bg.softuni.oix.service.mapper;

import bg.softuni.oix.model.entity.UserEntity;
import bg.softuni.oix.service.dto.UserRegistrationDto;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface UserMapper extends EntityMapper<UserRegistrationDto, UserEntity> {
    UserEntity toEntity(UserRegistrationDto userRegistrationDto);
}
