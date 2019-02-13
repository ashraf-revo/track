package org.revo.track.Repository;

import org.revo.track.Domain.Location;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface LocationRepository extends ReactiveCrudRepository<Location, String> {
    Flux<Location> findByTrackerId(String id);
}
