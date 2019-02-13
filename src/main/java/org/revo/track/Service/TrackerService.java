package org.revo.track.Service;

import org.revo.track.Domain.Tracker;
import org.revo.track.Domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TrackerService {
    Flux<Tracker> findAll();

    Mono<Tracker> save(Tracker tracker);
}
