package org.penguin.restfulApi.configuration;

import org.penguin.restfulApi.domain.AuthToken;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;

@Configuration
public class AuthTokenProvider {
    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public AuthToken provide() {
        SecurityContext context = SecurityContextHolder.getContext();

        if(
            context.getAuthentication() == null ||
            !(context.getAuthentication().getPrincipal() instanceof AuthToken)
        ) return AuthToken.builder().build();

        return (AuthToken)context.getAuthentication().getPrincipal();
    }
}
