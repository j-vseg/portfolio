package nl.fontys.sem3.individualtrack.business.account;

import nl.fontys.sem3.individualtrack.business.impl.account.AccountConverter;
import nl.fontys.sem3.individualtrack.business.impl.account.CreateAccountUseCaseImpl;
import nl.fontys.sem3.individualtrack.business.impl.bundle.CourseBundleConverter;
import nl.fontys.sem3.individualtrack.domain.Account;
import nl.fontys.sem3.individualtrack.domain.CourseBundle;
import nl.fontys.sem3.individualtrack.domain.Roles;
import nl.fontys.sem3.individualtrack.persistence.AccountRepository;
import nl.fontys.sem3.individualtrack.persistence.AccountRepository;
import nl.fontys.sem3.individualtrack.persistence.entity.AccountEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CreateAccountUseCaseImplTest {
    private AccountRepository repository;
    private PasswordEncoder passwordEncoder;
    private CreateAccountUseCaseImpl objectUnderTest;
    @BeforeEach
    private void setUp() {
        repository = mock(AccountRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
    }

    @Test
    void createAccountSuccessfully() {
        AccountEntity account = AccountEntity.builder()
                .username("test")
                .password("password")
                .role(Roles.Customer)
                .email("janne@vanseggelen.net")
                .build();

        AccountEntity returnAccount = AccountEntity.builder()
                .id(1L)
                .username("test")
                .password("password")
                .role(Roles.Customer)
                .email("janne@vanseggelen.net")
                .build();

        when(repository.save(account)).thenReturn(returnAccount);

        objectUnderTest = new CreateAccountUseCaseImpl(repository, passwordEncoder);

        Account accountTest = Account.builder()
                .username("test")
                .password("password")
                .role(Roles.Customer)
                .email("janne@vanseggelen.net")
                .build();

        Account result = objectUnderTest.createAccount(accountTest);

        Assertions.assertEquals(returnAccount.getId(), result.getId());
    }
    @Test
    void createAccount_WhenFailsToConvert_ThrowsNullPointerException() {
        Account account = Account.builder()
                .username("test")
                .password("password")
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> objectUnderTest.createAccount(account));
    }
}
