package nl.fontys.sem3.individualtrack.business.account;

import nl.fontys.sem3.individualtrack.business.IdValidator;
import nl.fontys.sem3.individualtrack.business.exception.InvalidException;
import nl.fontys.sem3.individualtrack.business.impl.account.AccountConverter;
import nl.fontys.sem3.individualtrack.business.impl.account.AccountIdValidatorImpl;
import nl.fontys.sem3.individualtrack.business.impl.account.UpdateAccountUseCaseImpl;
import nl.fontys.sem3.individualtrack.business.impl.bundle.CourseBundleIdValidatorImpl;
import nl.fontys.sem3.individualtrack.business.impl.bundle.UpdateCourseBundleUseCaseImpl;
import nl.fontys.sem3.individualtrack.domain.Account;
import nl.fontys.sem3.individualtrack.domain.CourseBundle;
import nl.fontys.sem3.individualtrack.persistence.AccountRepository;
import nl.fontys.sem3.individualtrack.persistence.entity.AccountEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.fail;

public class UpdateAccountUseCaseImplTest {
    private AccountRepository repository;
    private UpdateAccountUseCaseImpl objectUnderTest;
    //private IdValidator<Account> idValidator;

    @BeforeEach
    private void setUp() {
        repository = mock(AccountRepository.class);
        //idValidator = new AccountIdValidatorImpl(repository);
    }

    @Test
    void UpdateAccountButAccountIsNull() {
        IdValidator<Account> idValidator = mock(IdValidator.class);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        objectUnderTest = new UpdateAccountUseCaseImpl(repository, idValidator, passwordEncoder);
        Assertions.assertThrows(InvalidException.class, () -> objectUnderTest.updateAccount(null));
    }

    @Test
    void updateAccount_WhenIdInvalid_ThrowInvalidException() {
        IdValidator<Account> idValidator = new AccountIdValidatorImpl(repository);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        objectUnderTest = new UpdateAccountUseCaseImpl(repository, idValidator, passwordEncoder);

        Account account = Account.builder()
                .id(1L)
                .username("test")
                .password("password")
                .build();

        Assertions.assertThrows(InvalidException.class, () -> objectUnderTest.updateAccount(account));
    }

    @Test
    void updateAccountHappyFlow() {
        AccountEntity accountEntity = AccountEntity.builder()
                .id(1L)
                .username("test")
                .password("password")
                .build();
        repository.save(accountEntity);

        Account testAccount = Account.builder()
                .id(1L)
                .username("test")
                .password("password")
                .build();


        when(repository.save(AccountConverter.convert(testAccount)))
                .thenReturn(AccountConverter.convert(testAccount));
        when(repository.existsById(1L)).thenReturn(true);

        IdValidator<Account> idValidator = new AccountIdValidatorImpl(repository);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        objectUnderTest = new UpdateAccountUseCaseImpl(repository, idValidator, passwordEncoder);

        try {
            objectUnderTest.updateAccount(testAccount);
        }
        catch(Exception e) {
            fail("Should not have thrown any exception");
        }
    }
}
