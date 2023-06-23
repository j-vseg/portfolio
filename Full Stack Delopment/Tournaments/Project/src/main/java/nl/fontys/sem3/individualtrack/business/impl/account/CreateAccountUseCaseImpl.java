package nl.fontys.sem3.individualtrack.business.impl.account;

import lombok.AllArgsConstructor;
import nl.fontys.sem3.individualtrack.business.account.CreateAccountUseCase;
import nl.fontys.sem3.individualtrack.domain.Account;
import nl.fontys.sem3.individualtrack.persistence.AccountRepository;
import nl.fontys.sem3.individualtrack.persistence.entity.AccountEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class CreateAccountUseCaseImpl implements CreateAccountUseCase {
    private final AccountRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public Account createAccount(Account account) {
        AccountEntity savedAccount = saveNewAccount(account);

        return AccountConverter.convert(savedAccount);
    }
    private AccountEntity saveNewAccount(Account account) {
        String encodedPassword = passwordEncoder.encode(account.getPassword());
        AccountEntity newAccount = AccountEntity.builder()
                .username(account.getUsername())
                .password(encodedPassword)
                .role(account.getRole())
                .phoneNumber(account.getPhoneNumber())
                .email(account.getEmail())
                .build();
        return repository.save(newAccount);
    }

}
