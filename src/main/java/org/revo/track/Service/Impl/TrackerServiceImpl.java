package org.revo.track.Service.Impl;

import com.mongodb.client.result.UpdateResult;
import org.revo.track.Domain.Tracker;
import org.revo.track.Repository.TrackerRepository;
import org.revo.track.Service.TrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class TrackerServiceImpl implements TrackerService {
    @Autowired
    private TrackerRepository trackerRepository;
    @Autowired
    private ReactiveMongoOperations reactiveMongoOperations;

    @Override
    public Flux<Tracker> findAll() {
        return trackerRepository.findAll();
    }

    @Override
    public Mono<Tracker> save(Tracker tracker) {
        return trackerRepository.save(tracker);
    }

    @Override
    public Mono<Tracker> findOne(String id) {
        return trackerRepository.findById(id);
    }

    @Override
    public Mono<UpdateResult> update(String id, Date lastUpdateCall, Date lastUpdateLocation) {
        Criteria where = Criteria.where("id").is(id);
        Update update = new Update();

        if (lastUpdateCall != null) {
            update.set("lastUpdateCall", lastUpdateCall);
            where.and("lastUpdateCall").lt(lastUpdateCall);
        }
        if (lastUpdateLocation != null) {
            update.set("lastUpdateLocation", lastUpdateLocation);
            where.and("lastUpdateLocation").lt(lastUpdateLocation);
        }
        return reactiveMongoOperations.updateFirst(new Query(where), update, Tracker.class);
    }
}
