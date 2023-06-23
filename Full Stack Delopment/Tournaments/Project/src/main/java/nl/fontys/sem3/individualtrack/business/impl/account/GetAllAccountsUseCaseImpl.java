package nl.fontys.sem3.individualtrack.business.impl.account;

import lombok.AllArgsConstructor;
import nl.fontys.sem3.individualtrack.business.account.GetAllAccountsUseCase;
import nl.fontys.sem3.individualtrack.domain.Account;
import nl.fontys.sem3.individualtrack.persistence.AccountRepository;
import nl.fontys.sem3.individualtrack.persistence.entity.AccountEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllAccountsUseCaseImpl implements GetAllAccountsUseCase {
    private AccountRepository repository;
    @Override
    public List<Account> getAllAccounts() {
        List<Account> accounts = findAll()
                .stream()
                .map(AccountConverter::convert)
                .toList();
        return accounts;
    }
    private List<AccountEntity> findAll() {
        return repository.findAll();
    }
}
