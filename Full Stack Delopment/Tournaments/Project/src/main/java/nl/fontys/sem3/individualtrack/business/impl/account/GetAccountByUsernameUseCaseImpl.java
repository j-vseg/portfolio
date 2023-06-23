package nl.fontys.sem3.individualtrack.business.impl.account;

import lombok.AllArgsConstructor;
import nl.fontys.sem3.individualtrack.business.account.GetAccountByUsernameUseCase;
import nl.fontys.sem3.individualtrack.domain.Account;
import nl.fontys.sem3.individualtrack.persistence.AccountRepository;
import nl.fontys.sem3.individualtrack.persistence.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetAccountByUsernameUseCaseImpl implements GetAccountByUsernameUseCase {
    private final AccountRepository repository;
    @Override
    public Optional<Account> getAccount(String username) {
        return repository.findByUsername(username)
                .map(AccountConverter::convert);
    }
}
