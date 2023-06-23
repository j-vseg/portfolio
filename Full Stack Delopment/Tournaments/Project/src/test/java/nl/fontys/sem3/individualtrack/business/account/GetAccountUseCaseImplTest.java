package nl.fontys.sem3.individualtrack.business.account;

import nl.fontys.sem3.individualtrack.business.impl.account.GetAccountUseCaseImpl;
import nl.fontys.sem3.individualtrack.business.impl.bundle.GetCourseBundleUseCaseImpl;
import nl.fontys.sem3.individualtrack.domain.AccessToken;
import nl.fontys.sem3.individualtrack.domain.Account;
import nl.fontys.sem3.individualtrack.domain.CourseBundle;
import nl.fontys.sem3.individualtrack.domain.Roles;
import nl.fontys.sem3.individualtrack.persistence.AccountRepository;
import nl.fontys.sem3.individualtrack.persistence.entity.AccountEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetAccountUseCaseImplTest {
    private GetAccountUseCaseImpl objectUnderTest;
    private AccountRepository repository;

    @BeforeEach
    public void setUp() {
        repository = mock(AccountRepository.class);
    }
    @Test
    void GetAccount_HappyFlow() {
        long id = 1;
        AccountEntity account = AccountEntity.builder()
                .id(id)
                .username("test")
                .password("password")
                .role(Roles.Customer)
                .build();

        when(repository.findById(id)).thenReturn(Optional.ofNullable(account));

        AccessToken accessToken = AccessToken.builder()
                .accountId(1L)
                .role(Roles.Customer)
                .build();
        objectUnderTest = new GetAccountUseCaseImpl(repository, accessToken);

        Account aExpected = Account.builder()
                .id(id)
                .username("test")
                .password("password")
                .role(Roles.Customer)
                .build();

        Optional<Account> expected = Optional.ofNullable(aExpected);

        Optional<Account> result = objectUnderTest.getAccount(id);


        Assertions.assertEquals(expected, result);
    }
    @Test
    void getAccount_ShouldReturnEmptyOptionalWhenAccountNotFound() {

        when(repository.findById(1L)).thenReturn(Optional.empty());

        AccessToken accessToken = AccessToken.builder()
                .accountId(1L)
                .role(Roles.Customer)
                .build();
        objectUnderTest = new GetAccountUseCaseImpl(repository, accessToken);

        Optional<Account> expected = Optional.empty();

        Optional<Account> result = objectUnderTest.getAccount(1L);

        Assertions.assertEquals(expected, result);
    }
}
