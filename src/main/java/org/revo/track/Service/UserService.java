package org.revo.track.Service;

import org.revo.track.Domain.User;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<String> currentUser();

    Mono<Long> count();

    Flux<User> save(List<User> users);

    Flux<User> findAll();

    Mono<User> findByUsername(String username);
}
