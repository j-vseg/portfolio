package nl.fontys.sem3.individualtrack.business.impl.account;

import lombok.AllArgsConstructor;
import nl.fontys.sem3.individualtrack.business.account.GetAccountUseCase;
import nl.fontys.sem3.individualtrack.business.exception.UnauthorizedDataAccessException;
import nl.fontys.sem3.individualtrack.domain.AccessToken;
import nl.fontys.sem3.individualtrack.domain.Account;
import nl.fontys.sem3.individualtrack.domain.Roles;
import nl.fontys.sem3.individualtrack.persistence.AccountRepository;
import nl.fontys.sem3.individualtrack.persistence.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetAccountUseCaseImpl implements GetAccountUseCase {
    private final AccountRepository repository;
    private AccessToken requestAccessToken;
    @Override
    public Optional<Account> getAccount(long id) {
        if (!requestAccessToken.hasRole(Roles.Owner)) {
            if (requestAccessToken.getAccountId() != id) {
                throw new UnauthorizedDataAccessException("ACCOUNT_ID_NOT_FROM_LOGGED_IN_USER");
            }
        }
        return repository.findById(id)
                .map(AccountConverter::convert);
    }
}
