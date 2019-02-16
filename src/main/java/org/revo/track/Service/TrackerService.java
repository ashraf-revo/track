package org.revo.track.Service;

import org.revo.track.Domain.Tracker;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TrackerService {
    Flux<Tracker> findAll();

    Mono<Tracker> save(Tracker tracker);

    Mono<Tracker> findOne(String id);
}
