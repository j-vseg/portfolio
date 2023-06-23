package org.penguin.restfulApi.domain;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.penguin.restfulApi.controller.dto.UserDTO;
import org.penguin.restfulApi.domain.exception.InvalidAuthTokenException;
import org.penguin.restfulApi.repository.UserJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class AuthTokenConverter {
    @Autowired
    private UserJPA userJPA;
    private final Key signKey;

    public AuthTokenConverter(@Value("${jwt.secret}") String secret) {
        signKey = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public AuthToken toAuthToken(String json) {
        try {
            Claims claims = (Claims) Jwts.parserBuilder().setSigningKey(signKey).build().parse(json).getBody();
            UserDTO subject = UserConverter.toUserDTO(userJPA.getUserByEmail(claims.getSubject()));

            return AuthToken.builder()
                    .subject(subject)
                    .issueDate(claims.getIssuedAt())
                    .expirationDate(claims.getExpiration())
                    .build();
        } catch(ExpiredJwtException e) {
            throw new InvalidAuthTokenException();
        }
    }

    public String toJson(AuthToken authToken) {
        return Jwts.builder()
                .setSubject(authToken.getSubject().getEmail())
                .setIssuedAt(authToken.getIssueDate())
                .setExpiration(authToken.getExpirationDate())
                //.claim("role", authToken.getRole()) //TODO: uncomment when db holds user roles
                .signWith(signKey)
                .compact();
    }
}
