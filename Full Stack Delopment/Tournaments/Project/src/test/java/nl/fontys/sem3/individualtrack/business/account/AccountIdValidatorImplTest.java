package nl.fontys.sem3.individualtrack.business.account;

import nl.fontys.sem3.individualtrack.business.IdValidator;
import nl.fontys.sem3.individualtrack.business.exception.InvalidException;
import nl.fontys.sem3.individualtrack.business.impl.account.AccountIdValidatorImpl;
import nl.fontys.sem3.individualtrack.domain.Account;
import nl.fontys.sem3.individualtrack.persistence.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.fail;

public class AccountIdValidatorImplTest {
    private AccountIdValidatorImpl objectUnderTest;
    private AccountRepository repository;

    @BeforeEach
    public void setUp() {
        repository = mock(AccountRepository.class);
        objectUnderTest = new AccountIdValidatorImpl(repository);
    }

    @Test
    public void invalidateIdThrowsInvalidAccountException_SavedAccountsAreNull() {
        Assertions.assertThrows(InvalidException.class, () -> objectUnderTest.ValidateId(null));
    }

    @Test
    public void invalidateIdThrowsInvalidAccountException_BundleIdDoesntExists() {
        Assertions.assertThrows(InvalidException.class, () -> objectUnderTest.ValidateId(9999L));
    }

    @Test
    public void invalidateIdThrowsInvalidAccountException_HappyFlow() {
        when(repository.existsById(1L)).thenReturn(true);

        try {
            objectUnderTest.ValidateId(1L);
        }
        catch(Exception e) {
            fail("Should not have thrown any exception");
        }
    }
}
