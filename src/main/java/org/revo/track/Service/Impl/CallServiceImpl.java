package org.revo.track.Service.Impl;

import org.revo.track.Domain.Call;
import org.revo.track.Repository.CallRepository;
import org.revo.track.Service.CallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class CallServiceImpl implements CallService {
    @Autowired
    private CallRepository callRepository;

    @Override
    public Flux<Call> findAll(String id) {
        return callRepository.findByTrackerId(id);
    }
}