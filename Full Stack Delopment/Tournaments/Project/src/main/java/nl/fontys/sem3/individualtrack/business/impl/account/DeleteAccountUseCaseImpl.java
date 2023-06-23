package nl.fontys.sem3.individualtrack.business.impl.account;

import lombok.AllArgsConstructor;
import nl.fontys.sem3.individualtrack.business.account.DeleteAccountUseCase;
import nl.fontys.sem3.individualtrack.domain.Account;
import nl.fontys.sem3.individualtrack.persistence.AccountRepository;
import nl.fontys.sem3.individualtrack.persistence.Repository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteAccountUseCaseImpl implements DeleteAccountUseCase {
    private AccountRepository repository;
    @Override
    public void deleteAccount(long id) {
        this.repository.deleteById(id);
    }
}
