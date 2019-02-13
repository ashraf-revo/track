package org.revo.track.Config;

import org.revo.track.Service.CallService;
import org.revo.track.Service.LocationService;
import org.revo.track.Service.TrackerService;
import org.revo.track.Service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Util {
    @Bean
    public CommandLineRunner runner(Env env, UserService userService, LocationService locationService, CallService callService, TrackerService trackerService) {
        return args -> {
            if (env.getUsers().size() > 0)
                userService.count().filter(it -> it == 0).flatMapMany(it -> userService.save(env.getUsers())).subscribe();

        };
    }
}
