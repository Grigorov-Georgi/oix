package bg.softuni.oix.service.mapper;

import bg.softuni.oix.model.entity.CategoryEntity;
import bg.softuni.oix.model.entity.LocationEntity;
import bg.softuni.oix.model.entity.UserEntity;
import bg.softuni.oix.service.dto.UserRegistrationDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-06T16:01:15+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserRegistrationDto toDto(UserEntity entity) {
        if ( entity == null ) {
            return null;
        }

        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();

        userRegistrationDto.setEmail( entity.getEmail() );
        userRegistrationDto.setFirstName( entity.getFirstName() );
        userRegistrationDto.setLastName( entity.getLastName() );
        userRegistrationDto.setPassword( entity.getPassword() );

        return userRegistrationDto;
    }

    @Override
    public List<UserEntity> toEntity(List<UserRegistrationDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<UserEntity> list = new ArrayList<UserEntity>( dtoList.size() );
        for ( UserRegistrationDto userRegistrationDto : dtoList ) {
            list.add( toEntity( userRegistrationDto ) );
        }

        return list;
    }

    @Override
    public List<UserRegistrationDto> toDto(List<UserEntity> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<UserRegistrationDto> list = new ArrayList<UserRegistrationDto>( entityList.size() );
        for ( UserEntity userEntity : entityList ) {
            list.add( toDto( userEntity ) );
        }

        return list;
    }

    @Override
    public void partialUpdate(UserEntity entity, UserRegistrationDto dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getEmail() != null ) {
            entity.setEmail( dto.getEmail() );
        }
        if ( dto.getFirstName() != null ) {
            entity.setFirstName( dto.getFirstName() );
        }
        if ( dto.getLastName() != null ) {
            entity.setLastName( dto.getLastName() );
        }
        if ( dto.getPassword() != null ) {
            entity.setPassword( dto.getPassword() );
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
    public UserEntity toEntity(UserRegistrationDto userRegistrationDto) {
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
}
