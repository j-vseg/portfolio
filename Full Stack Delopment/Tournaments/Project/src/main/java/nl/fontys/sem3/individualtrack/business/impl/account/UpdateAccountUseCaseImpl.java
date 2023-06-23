package nl.fontys.sem3.individualtrack.business.impl.account;

import lombok.AllArgsConstructor;
import nl.fontys.sem3.individualtrack.business.IdValidator;
import nl.fontys.sem3.individualtrack.business.account.UpdateAccountUseCase;
import nl.fontys.sem3.individualtrack.business.exception.InvalidException;
import nl.fontys.sem3.individualtrack.domain.Account;
import nl.fontys.sem3.individualtrack.persistence.AccountRepository;
import nl.fontys.sem3.individualtrack.persistence.entity.AccountEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateAccountUseCaseImpl implements UpdateAccountUseCase {
    private final AccountRepository repository;
    private final IdValidator<Account> idValidator;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void updateAccount(Account account) {

        if (account == null)
            throw new InvalidException("ACCOUNT_ID_INVALID");

        idValidator.ValidateId(account.getId());

        updateFields(account);
    }
    private void updateFields(Account account) {
        String encodedPassword = passwordEncoder.encode(account.getPassword());
        AccountEntity a = AccountEntity.builder()
                .id(account.getId())
                .username(account.getUsername())
                .password(encodedPassword)
                .role(account.getRole())
                .phoneNumber(account.getPhoneNumber())
                .email(account.getEmail())
                .build();

        repository.save(a);
    }
}
