package org.revo.track.Service.Impl;

import org.revo.track.Domain.Tracker;
import org.revo.track.Repository.TrackerRepository;
import org.revo.track.Service.TrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TrackerServiceImpl implements TrackerService {
    @Autowired
    private TrackerRepository trackerRepository;

    @Override
    public Flux<Tracker> findAll() {
        return trackerRepository.findAll();
    }

    @Override
    public Mono<Tracker> save(Tracker tracker) {
        return trackerRepository.save(tracker);
    }
}
