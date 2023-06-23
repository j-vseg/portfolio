package nl.fontys.sem3.individualtrack.business.account;

import nl.fontys.sem3.individualtrack.domain.Account;

import java.util.Optional;

public interface GetAccountByUsernameUseCase {
    Optional<Account> getAccount(String username);
}
