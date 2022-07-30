package bg.softuni.oix.service;

import bg.softuni.oix.model.entity.LocationEntity;
import bg.softuni.oix.repository.LocationRepository;
import bg.softuni.oix.service.dto.AddLocationDTO;
import bg.softuni.oix.service.views.LocationView;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationService {
    private LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<LocationView> getAllLocations(){
        List<LocationEntity> locationEntities = locationRepository.findAll();
        return locationEntities.stream().map(l -> new LocationView(l.getId(), l.getCity())).collect(Collectors.toList());
    }

    public void save(AddLocationDTO locationDTO) {
        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setCity(locationDTO.getCity());

        this.locationRepository.save(locationEntity);
    }

    public void delete(Long id) {
        this.locationRepository.deleteById(id);
    }
}
