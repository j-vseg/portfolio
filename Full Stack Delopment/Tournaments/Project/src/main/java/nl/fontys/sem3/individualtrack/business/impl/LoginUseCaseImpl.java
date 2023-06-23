package nl.fontys.sem3.individualtrack.business.impl;

import lombok.RequiredArgsConstructor;
import nl.fontys.sem3.individualtrack.business.AccessTokenEncoder;
import nl.fontys.sem3.individualtrack.business.LoginUseCase;
import nl.fontys.sem3.individualtrack.business.exception.InvalidCredentialsException;
import nl.fontys.sem3.individualtrack.domain.AccessToken;
import nl.fontys.sem3.individualtrack.persistence.AccountRepository;
import nl.fontys.sem3.individualtrack.persistence.entity.AccountEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginUseCaseImpl implements LoginUseCase {
    private final AccountRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenEncoder accessTokenEncoder;

    @Override
    public String login(String username, String password) {
        Optional<AccountEntity> account = repository.findByUsername(username);
        if (account.isEmpty()) {
            throw new InvalidCredentialsException();
        }

        if (!matchesPassword(password, account.get().getPassword())) {
            throw new InvalidCredentialsException();
        }

        String accessToken = generateAccessToken(account.get());
        return accessToken;
    }

    private boolean matchesPassword(String rawPassword, String encodedPassword) {
        //String encodedRawPassword = passwordEncoder.encode(rawPassword);
        if (passwordEncoder.matches(rawPassword, encodedPassword)) { return true; }
        return false;
    }

    private String generateAccessToken(AccountEntity account) {
        //Long accountId = account != null ? account.getId() : null;

        return accessTokenEncoder.encode(
                AccessToken.builder()
                        .subject(account.getUsername())
                        .accountId(account.getId())
                        .role(account.getRole())
                        .build());
    }

}

