package nl.fontys.sem3.individualtrack.business.account;

import nl.fontys.sem3.individualtrack.domain.Account;

public interface CreateAccountUseCase {
    Account createAccount(Account account);
}
