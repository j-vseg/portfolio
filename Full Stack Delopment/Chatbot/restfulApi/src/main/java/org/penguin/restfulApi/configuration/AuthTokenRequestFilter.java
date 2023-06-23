package org.penguin.restfulApi.configuration;

import org.penguin.restfulApi.domain.AuthToken;
import org.penguin.restfulApi.domain.AuthTokenConverter;
import org.penguin.restfulApi.domain.exception.InvalidAuthTokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthTokenRequestFilter extends OncePerRequestFilter {
    @Autowired
    private AuthTokenConverter authTokenConverter;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String json = request.getHeader("Authorization");

        if(json == null) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            AuthToken authToken = authTokenConverter.toAuthToken(json);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(authToken, null);
            SecurityContextHolder.getContext().setAuthentication(auth);
        } catch (InvalidAuthTokenException e) {
            logger.info("Received invalid token. Continuing without token");
        }

        filterChain.doFilter(request, response);
    }
}
