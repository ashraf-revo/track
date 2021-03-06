package org.revo.track;

import org.revo.track.Config.Env;
import org.revo.track.Domain.*;
import org.revo.track.Service.CallService;
import org.revo.track.Service.LocationService;
import org.revo.track.Service.TrackerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.Objects;

import static org.revo.track.Domain.Role.Paths.ADMIN_PATH;
import static org.revo.track.Domain.Role.Paths.USER_PATH;
import static org.springframework.security.core.context.ReactiveSecurityContextHolder.getContext;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@EnableConfigurationProperties(Env.class)
@SpringBootApplication
public class TrackApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrackApplication.class, args);
    }

    private final RequestPredicate base =
            GET("/").
                    or(GET("/home")).
                    or(GET("/login")).
                    or(GET("/track/{id}"));

    @Bean
    public RouterFunction<ServerResponse> function(CallService callService, LocationService locationService, TrackerService trackerService, @Value("classpath:/static/index.html") final Resource indexHtml) {
        return
                route(GET(ADMIN_PATH + "/location/{id}"), req -> ok().body(locationService.findAll(req.pathVariable("id")), Location.class))
                        .andRoute(GET(ADMIN_PATH + "/call/{id}"), req -> ok().body(callService.findAll(req.pathVariable("id")), Call.class))
                        .andRoute(GET(ADMIN_PATH + "/tracker"), req -> ok().body(trackerService.findAll(), Tracker.class))
                        .andRoute(POST(USER_PATH + "/location"), req -> ok().body(req.bodyToMono(Location.class).flatMap(locationService::save), Location.class))
                        .andRoute(POST(USER_PATH + "/locations"), req -> ok().body(req.bodyToMono(Locations.class).flatMap(locationService::save), Locations.class))
                        .andRoute(POST(USER_PATH + "/call"), req -> ok().body(req.bodyToMono(Call.class).flatMap(callService::save), Call.class))
                        .andRoute(POST(USER_PATH + "/calls"), req -> ok().body(req.bodyToMono(Calls.class).flatMap(callService::save), Calls.class))
                        .andRoute(GET(USER_PATH + "/tracker/{id}"), req -> ok().body(trackerService.findOne(req.pathVariable("id")), Tracker.class))
                        .andRoute(POST(USER_PATH + "/tracker"), req -> ok().body(req.bodyToMono(Tracker.class).flatMap(trackerService::save), Tracker.class))
                        .andRoute(base, request -> ok().contentType(MediaType.TEXT_HTML).syncBody(indexHtml))
                        .andRoute(GET("/auth/user"), req -> ok().body(getContext().map(SecurityContext::getAuthentication).filter(Objects::nonNull).map(Authentication::getPrincipal).cast(User.class), User.class));
    }
}

