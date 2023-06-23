package org.penguin.restfulApi.controller;

import lombok.RequiredArgsConstructor;

import org.penguin.restfulApi.controller.responses.GetUserResponse;
import org.penguin.restfulApi.controller.responses.PostUserResponse;
import org.penguin.restfulApi.controller.responses.PutUserResponse;
import org.penguin.restfulApi.controller.dto.UserDTO;
import org.penguin.restfulApi.domain.exception.BadRequestException;
import org.penguin.restfulApi.domain.exception.NotFoundException;
import org.penguin.restfulApi.domain.exception.UnauthorizedException;
import org.penguin.restfulApi.repository.entity.User;
import org.penguin.restfulApi.domain.*;
import org.penguin.restfulApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    AuthToken authToken;

    @CrossOrigin
    @GetMapping
    public ResponseEntity<GetUserResponse> get(
            @RequestParam(name = "email", required = false) Optional<String> email,
            @RequestParam(name = "id", required = false) Optional<Long> id,
            @RequestParam(name = "self", required = false) Optional<Boolean> self
    ) {
        UserDTO authUser = authToken.getSubject();
        UserDTO user = null;

        if(email.isPresent()) {
            user = userService.getUserByEmail(email.get());
        } else if(id.isPresent()) {
            user = userService.getUserById(id.get());
        } else if(self.isPresent() && authUser != null) {
            user = userService.getUserById(authUser.getId());
        } else {
            throw new BadRequestException();
        }

        if(user == null) throw new NotFoundException();

        if(!new UserAuthorizationRule(user, authUser).check())
            throw new UnauthorizedException();

        GetUserResponse response = GetUserResponse.builder()
                .user(user)
                .build();
        return ResponseEntity.ok(response);
    }

    @CrossOrigin
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostUserResponse> post(
            @RequestBody User user) {
        User newUser = User.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .email(user.getEmail())
                .build();
        PostUserResponse response = PostUserResponse.builder()
                .user(userService.saveAndFlush(newUser))
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @CrossOrigin
    @DeleteMapping("{id}")
    public ResponseEntity delete(
            @PathVariable(value = "id") long id) {
        UserDTO authUser = authToken.getSubject();
        UserDTO expectedUser = userService.getUserById(id);

        if(!new UserAuthorizationRule(expectedUser, authUser).check())
            throw new UnauthorizedException();

        if(userService.deleteByUserId(id))
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @CrossOrigin
    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity put(
            @PathVariable(value = "id") long id,
            @RequestBody User user) {
        UserDTO authUser = authToken.getSubject();
        UserDTO expectedUser = userService.getUserById(id);

        if(!new UserAuthorizationRule(expectedUser, authUser).check())
            throw new UnauthorizedException();

        PutUserResponse response = PutUserResponse.builder()
                .user(userService.saveAndFlush(user))
                .build();
        return ResponseEntity.ok(response);
    }
}
