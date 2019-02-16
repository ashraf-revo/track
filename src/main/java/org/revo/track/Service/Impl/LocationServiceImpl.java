package org.revo.track.Service.Impl;

import org.revo.track.Domain.Location;
import org.revo.track.Domain.Locations;
import org.revo.track.Repository.LocationRepository;
import org.revo.track.Service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationRepository locationRepository;

    @Override
    public Flux<Location> findAll(String id) {
        return locationRepository.findByTrackerId(id);
    }

    @Override
    public Mono<Location> save(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public Mono<Locations> save(Locations locations) {
        return locationRepository.saveAll(locations.getLocations().stream().filter(it -> it.getTrackerId() != null).collect(Collectors.toList())).collectList().map(Locations::new);
    }
}
