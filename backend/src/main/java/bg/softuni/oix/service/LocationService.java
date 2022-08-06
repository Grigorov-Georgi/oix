package bg.softuni.oix.service;

import bg.softuni.oix.exception.ObjectNotFoundException;
import bg.softuni.oix.model.entity.LocationEntity;
import bg.softuni.oix.repository.LocationRepository;
import bg.softuni.oix.service.dto.AddLocationDTO;
import bg.softuni.oix.service.mapper.AddLocationDtoMapper;
import bg.softuni.oix.service.mapper.LocationViewMapper;
import bg.softuni.oix.service.views.LocationView;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationService {
    private LocationRepository locationRepository;
    private LocationViewMapper locationViewMapper;
    private AddLocationDtoMapper addLocationDtoMapper;

    public LocationService(LocationRepository locationRepository, LocationViewMapper locationViewMapper, AddLocationDtoMapper addLocationDtoMapper) {
        this.locationRepository = locationRepository;
        this.locationViewMapper = locationViewMapper;
        this.addLocationDtoMapper = addLocationDtoMapper;
    }

    public List<LocationView> getAllLocations(){
        List<LocationEntity> locationEntities = locationRepository.findAll();
        return locationEntities.stream().map(l -> locationViewMapper.toDto(l)).collect(Collectors.toList());
    }

    public void save(AddLocationDTO locationDTO) {
        this.locationRepository.save(addLocationDtoMapper.toEntity(locationDTO));
    }

    public void delete(Long id) {
        this.locationRepository.deleteById(id);
    }

    public LocationEntity findByCity(String city){
        return this.locationRepository.findByCity(city).orElseThrow(() -> new ObjectNotFoundException("Location with city:" + city + "not found!"));
    }
}
