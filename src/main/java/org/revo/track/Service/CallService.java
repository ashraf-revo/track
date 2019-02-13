package org.revo.track.Service;

import org.revo.track.Domain.Call;
import org.revo.track.Domain.Tracker;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CallService {
    Flux<Call> findAll(String id);
    Mono<Call> save(Call call);

}
