package org.revo.track.Service.Impl;

import org.revo.track.Domain.User;
import org.revo.track.Repository.UserRepository;
import org.revo.track.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<String> currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() != null && authentication.getPrincipal() instanceof User)
            return Optional.of(((User) authentication.getPrincipal()).getId());
        return Optional.empty();
    }

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
