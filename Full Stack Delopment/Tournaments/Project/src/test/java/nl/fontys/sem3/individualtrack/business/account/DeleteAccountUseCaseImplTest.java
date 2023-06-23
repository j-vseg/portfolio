package nl.fontys.sem3.individualtrack.business.account;

import nl.fontys.sem3.individualtrack.business.impl.account.DeleteAccountUseCaseImpl;
import nl.fontys.sem3.individualtrack.business.impl.bundle.DeleteCourseBundleUseCaseImpl;
import nl.fontys.sem3.individualtrack.persistence.AccountRepository;
import nl.fontys.sem3.individualtrack.persistence.entity.AccountEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.util.AssertionErrors.fail;

public class DeleteAccountUseCaseImplTest {
    private DeleteAccountUseCaseImpl objectUnderTest;
    private AccountRepository repository;

    @BeforeEach
    public void setUp() {
        repository = mock(AccountRepository.class);
    }

    @Test
    void deleteAccountSuccessfully() {
        objectUnderTest = new DeleteAccountUseCaseImpl(repository);

        AccountEntity accountEntity = AccountEntity.builder()
                .id(1L)
                .username("test")
                .password("password")
                .build();
        repository.save(accountEntity);

        doNothing().when(repository).deleteById(1L);

        try {
            objectUnderTest.deleteAccount(1L);
        }
        catch(Exception e) {
            fail("Should not have thrown any exception");
        }
    }
    @Test
    void deleteAccount_WhenIdIsInvalid_NothingHappens() {
        objectUnderTest = new DeleteAccountUseCaseImpl(repository);

        try {
            objectUnderTest.deleteAccount(99L);
        }
        catch(Exception e) {
            fail("Should not have thrown any exception");
        }
    }
}
