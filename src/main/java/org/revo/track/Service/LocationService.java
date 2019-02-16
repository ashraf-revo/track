package org.revo.track.Service;

import org.revo.track.Domain.Location;
import org.revo.track.Domain.Locations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LocationService {
    Flux<Location> findAll(String id);

    Mono<Location> save(Location location);

    Mono<Locations> save(Locations locations);
}
