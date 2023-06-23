package org.penguin.restfulApi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.penguin.restfulApi.controller.dto.UserDTO;
import org.penguin.restfulApi.repository.entity.User;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class AuthToken {
    private UserDTO subject;
    private Date issueDate;
    private Date expirationDate;

    public AuthToken(User user) {
        Instant now = Instant.now();

        this.subject = UserConverter.toUserDTO(user);
        this.issueDate = Date.from(now);
        this.expirationDate = Date.from(now.plus(30, ChronoUnit.MINUTES));
    }
}
