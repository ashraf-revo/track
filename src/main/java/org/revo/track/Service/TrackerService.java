package org.revo.track.Service;

import com.mongodb.client.result.UpdateResult;
import org.revo.track.Domain.Tracker;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface TrackerService {
    Flux<Tracker> findAll();

    Mono<Tracker> save(Tracker tracker);

    Mono<Tracker> findOne(String id);

    Mono<UpdateResult> update(String id, Date lastUpdateCall, Date lastUpdateLocation);
}
