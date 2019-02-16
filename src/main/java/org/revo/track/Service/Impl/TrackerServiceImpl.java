package org.revo.track.Service.Impl;

import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
        log.info("org.revo.****" + id + "      " + lastUpdateCall + "        " + lastUpdateLocation);

        Criteria criteria = Criteria.where("id").is(id);
        Update update = new Update();

        if (lastUpdateCall != null) {
            update.set("lastUpdateCall", lastUpdateCall);
            criteria.orOperator(Criteria.where("lastUpdateCall").gte(lastUpdateCall), Criteria.where("lastUpdateCall").exists(false));
        }
        if (lastUpdateLocation != null) {
            update.set("lastUpdateLocation", lastUpdateLocation);
            criteria.orOperator(Criteria.where("lastUpdateLocation").gte(lastUpdateLocation), Criteria.where("lastUpdateLocation").exists(false));
        }
        log.info("org.revo.****" + new Query(criteria).toString());
        return reactiveMongoOperations.updateFirst(new Query(criteria), update, Tracker.class);
    }
}
