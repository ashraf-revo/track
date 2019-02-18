package org.revo.track.Repository;

import org.revo.track.Domain.Location;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LocationRepository extends ReactiveCrudRepository<Location, String> {
    Mono<Location> findTopByOrderByDateDesc();

    Flux<Location> findByTrackerId(String id);

    Flux<Location> findByTrackerIdOrderByDateDesc(String id, PageRequest request);
}
