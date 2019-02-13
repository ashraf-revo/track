package org.revo.track.Repository;

import org.revo.track.Domain.Call;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface CallRepository extends ReactiveCrudRepository<Call, String> {
    Flux<Call> findByTrackerId(String id);
}
