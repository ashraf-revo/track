package org.revo.track.Service.Impl;

import org.revo.track.Domain.Call;
import org.revo.track.Domain.Calls;
import org.revo.track.Repository.CallRepository;
import org.revo.track.Service.CallService;
import org.revo.track.Service.TrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.stream.Collectors;

@Service
public class CallServiceImpl implements CallService {
    @Autowired
    private CallRepository callRepository;
    @Autowired
    private TrackerService trackerService;

    @Override
    public Flux<Call> findAll(String id) {
        return callRepository.findByTrackerId(id);
    }

    @Override
    public Mono<Call> save(Call call) {
        return callRepository.save(call).flatMap(it -> trackerService.update(it.getTrackerId(), it.getDate(), null).flatMap(updateResult -> Mono.just(it)));
    }

    @Override
    public Mono<Calls> save(Calls calls) {
        return callRepository.saveAll(calls.getCalls().stream().filter(it -> it.getTrackerId() != null)
                .sorted(Comparator.comparing(Call::getDate))
                .collect(Collectors.toList())).collectList().map(Calls::new)
                .flatMap(calls1 -> trackerService.update(calls1.getCalls().get(0).getTrackerId(), calls1.getCalls().get(0).getDate(), null).flatMap(it -> Mono.just(calls1)));
    }
}
