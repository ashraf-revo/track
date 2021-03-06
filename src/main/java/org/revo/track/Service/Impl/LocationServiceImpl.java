package org.revo.track.Service.Impl;

import org.revo.track.Domain.Location;
import org.revo.track.Domain.Locations;
import org.revo.track.Repository.LocationRepository;
import org.revo.track.Service.LocationService;
import org.revo.track.Util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
        return locationRepository.findByTrackerIdOrderByDateDesc(id, PageRequest.of(0, 100));
    }

    @Override
    public Mono<Location> save(Location location) {
        return locationRepository.findTopByOrderByDateDesc().defaultIfEmpty(new Location())
                .filter(it -> it.getLat() == null || it.getLng() == null || (it.getLat() != null && !it.getLat().equals(location.getLat())) || (it.getLng() != null && !it.getLng().equals(location.getLng())) || (it.getLat() != null && it.getLng() != null && Util.getDistanceFromLatLonInKm(it.getLat(), it.getLng(), location.getLat(), location.getLng()) * 1000 > 20))
                .flatMap(it -> locationRepository.save(location)).defaultIfEmpty(new Location());
    }

    @Override
    public Mono<Locations> save(Locations locations) {
        return locationRepository.saveAll(locations.getLocations().stream().filter(it -> it.getTrackerId() != null).collect(Collectors.toList())).collectList().map(Locations::new);
    }
}
