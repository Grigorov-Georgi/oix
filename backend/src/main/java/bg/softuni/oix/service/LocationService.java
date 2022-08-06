package bg.softuni.oix.service;

import bg.softuni.oix.exception.ObjectNotFoundException;
import bg.softuni.oix.model.entity.LocationEntity;
import bg.softuni.oix.repository.LocationRepository;
import bg.softuni.oix.service.dto.AddLocationDTO;
import bg.softuni.oix.service.mapper.LocationMapper;
import bg.softuni.oix.service.views.LocationView;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationService {
    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;

    public LocationService(LocationRepository locationRepository,LocationMapper locationMapper) {
        this.locationRepository = locationRepository;
        this.locationMapper = locationMapper;
    }

    public List<LocationView> getAllLocations(){
        List<LocationEntity> locationEntities = locationRepository.findAll();
        return locationEntities.stream().map(locationMapper::locationEntityToLocationView).collect(Collectors.toList());
    }

    public void save(AddLocationDTO locationDTO) {
        this.locationRepository.save(locationMapper.addLocationDtoToEntity(locationDTO));
    }

    public void delete(Long id) {
        this.locationRepository.deleteById(id);
    }

    public LocationEntity findByCity(String city){
        return this.locationRepository.findByCity(city).orElseThrow(() -> new ObjectNotFoundException("Location with city:" + city + "not found!"));
    }
}
