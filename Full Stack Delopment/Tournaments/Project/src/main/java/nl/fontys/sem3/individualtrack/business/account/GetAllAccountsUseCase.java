package nl.fontys.sem3.individualtrack.business.account;

import nl.fontys.sem3.individualtrack.domain.Account;

import java.util.List;

public interface GetAllAccountsUseCase {
    List<Account> getAllAccounts();
}
