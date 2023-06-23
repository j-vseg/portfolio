package nl.fontys.sem3.individualtrack.controller.login;

import lombok.RequiredArgsConstructor;
import nl.fontys.sem3.individualtrack.business.LoginUseCase;
import nl.fontys.sem3.individualtrack.controller.login.LoginRequest;
import nl.fontys.sem3.individualtrack.controller.login.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {
    private final LoginUseCase loginUseCase;

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        LoginResponse loginResponse = LoginResponse.builder()
                .accessToken(loginUseCase.login(loginRequest.getUsername(), loginRequest.getPassword()))
                .build();

        return ResponseEntity.ok(loginResponse);
    }
}
