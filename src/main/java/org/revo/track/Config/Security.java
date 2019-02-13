package org.revo.track.Config;

import org.revo.track.Service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.csrf.CookieServerCsrfTokenRepository;
import org.springframework.security.web.server.csrf.CsrfToken;
import reactor.core.publisher.Mono;

import static org.revo.track.Domain.Role.ADMIN;
import static org.revo.track.Domain.Role.Paths.ADMIN_PATH;
import static org.revo.track.Domain.Role.Paths.USER_PATH;

@EnableWebFluxSecurity
public class Security {
    @Bean
    public SecurityWebFilterChain webFilterChain(ServerHttpSecurity http) {
        http
                .exceptionHandling()

                .authenticationEntryPoint((exchange, e) -> {
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getAttributeOrDefault(CsrfToken.class.getName(), Mono.empty()).flatMap(it -> exchange.getResponse().setComplete());
                })
                .and()
                .authorizeExchange()
                .pathMatchers("/auth/user").authenticated()
                .pathMatchers(ADMIN_PATH, ADMIN_PATH + "/*/**").hasRole(ADMIN.getRole())
                .pathMatchers(USER_PATH, USER_PATH + "/*/**").permitAll()
                .anyExchange().permitAll()
                .and().csrf()
                .csrfTokenRepository(CookieServerCsrfTokenRepository.withHttpOnlyFalse())

                .and().formLogin()
                .loginPage("/login")
                .authenticationFailureHandler((exchange, e) -> {
                    exchange.getExchange().getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return Mono.empty();
                })
                .authenticationSuccessHandler((webFilterExchange, authentication) -> {
                    webFilterExchange.getExchange().getResponse().setStatusCode(HttpStatus.OK);
                    return Mono.empty();
                })
                .and()
                .logout()
                .logoutUrl("/signout")
                .logoutSuccessHandler((exchange, authentication) -> {
                    exchange.getExchange().getResponse().setStatusCode(HttpStatus.OK);
                    return Mono.empty();
                });
        return http.build();
    }

    @Bean
    public ReactiveUserDetailsService userDetailsService(UserService userService) {
        return s -> userService.findByUsername(s).cast(UserDetails.class);
    }

    @Bean
    public PasswordEncoder encoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuditorAware<String> aware(UserService userService) {
        return userService::currentUser;
    }
}
