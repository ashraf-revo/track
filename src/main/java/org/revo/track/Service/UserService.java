package org.revo.track.Service;

import org.revo.track.Domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface UserService {

    Mono<Long> count();

    Mono<User> save(User user);

    Flux<User> save(List<User> users);

    Flux<User> findAll();

    Mono<User> findByUsername(String username);
}
