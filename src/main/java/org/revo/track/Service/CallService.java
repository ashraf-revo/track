package org.revo.track.Service;

import org.revo.track.Domain.Call;
import reactor.core.publisher.Flux;

public interface CallService {
    Flux<Call> findAll(String id);
}
