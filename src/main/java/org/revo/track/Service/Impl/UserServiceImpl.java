package org.revo.track.Service.Impl;

import org.revo.track.Domain.User;
import org.revo.track.Repository.UserRepository;
import org.revo.track.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Mono<Long> count() {
        return userRepository.count();
    }

    @Override
    public Flux<User> save(List<User> users) {
        return userRepository.saveAll(users);
    }

    @Override
    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Mono<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


}
