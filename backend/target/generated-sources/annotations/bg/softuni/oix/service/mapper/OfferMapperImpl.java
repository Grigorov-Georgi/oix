package bg.softuni.oix.service.mapper;

import bg.softuni.oix.model.entity.OfferEntity;
import bg.softuni.oix.service.dto.OfferDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-01T21:38:13+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class OfferMapperImpl implements OfferMapper {

    @Override
    public OfferEntity toEntity(OfferDto dto) {
        if ( dto == null ) {
            return null;
        }

        OfferEntity offerEntity = new OfferEntity();

        return offerEntity;
    }

    @Override
    public OfferDto toDto(OfferEntity entity) {
        if ( entity == null ) {
            return null;
        }

        OfferDto offerDto = new OfferDto();

        return offerDto;
    }

    @Override
    public List<OfferEntity> toEntity(List<OfferDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<OfferEntity> list = new ArrayList<OfferEntity>( dtoList.size() );
        for ( OfferDto offerDto : dtoList ) {
            list.add( toEntity( offerDto ) );
        }

        return list;
    }

    @Override
    public List<OfferDto> toDto(List<OfferEntity> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<OfferDto> list = new ArrayList<OfferDto>( entityList.size() );
        for ( OfferEntity offerEntity : entityList ) {
            list.add( toDto( offerEntity ) );
        }

        return list;
    }

    @Override
    public void partialUpdate(OfferEntity entity, OfferDto dto) {
        if ( dto == null ) {
            return;
        }
    }
}
