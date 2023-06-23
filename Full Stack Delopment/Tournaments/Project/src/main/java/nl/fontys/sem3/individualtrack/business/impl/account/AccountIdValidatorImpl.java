package nl.fontys.sem3.individualtrack.business.impl.account;

import lombok.AllArgsConstructor;
import nl.fontys.sem3.individualtrack.business.IdValidator;
import nl.fontys.sem3.individualtrack.business.exception.InvalidException;
import nl.fontys.sem3.individualtrack.domain.Account;
import nl.fontys.sem3.individualtrack.persistence.AccountRepository;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AccountIdValidatorImpl implements IdValidator<Account> {
    private AccountRepository repository;

    @Override
    public void ValidateId(Long accountId) throws InvalidException {
        if (accountId == null || !repository.existsById(accountId)) {
            throw new InvalidException("ACCOUNT_ID_INVALID");
        }
    }
}
