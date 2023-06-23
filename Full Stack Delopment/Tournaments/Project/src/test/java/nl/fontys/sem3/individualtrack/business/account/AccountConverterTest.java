package nl.fontys.sem3.individualtrack.business.account;

import nl.fontys.sem3.individualtrack.business.impl.account.AccountConverter;
import nl.fontys.sem3.individualtrack.domain.Account;
import nl.fontys.sem3.individualtrack.domain.CourseBundle;
import nl.fontys.sem3.individualtrack.persistence.AccountRepository;
import nl.fontys.sem3.individualtrack.persistence.AccountRepository;
import nl.fontys.sem3.individualtrack.persistence.entity.AccountEntity;
import nl.fontys.sem3.individualtrack.persistence.entity.AccountEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class AccountConverterTest {
    private AccountRepository repository;
    private AccountConverter objectUnderTest;

    @BeforeEach
    private void setUp() {
        objectUnderTest = new AccountConverter();
    }

    @Test
    void ConvertAccountToEntitySuccessfully() {
        AccountEntity accountEntity = AccountEntity.builder()
                .id(1L)
                .username("test")
                .password("password")
                .build();

        Account account = Account.builder()
                .id(1L)
                .username("test")
                .password("password")
                .build();

        AccountEntity result = AccountConverter.convert(account);

        Assertions.assertEquals(accountEntity.getId(), result.getId());
        Assertions.assertEquals(accountEntity.getUsername(), result.getUsername());
        Assertions.assertEquals(accountEntity.getPassword(), result.getPassword());
    }
    @Test
    void ConvertAccountToEntity_WhenEntityIsNull_ThrowsException() {
        Assertions.assertThrows(NullPointerException.class, () -> objectUnderTest.convert((Account) null));
    }
    @Test
    void ConvertToEntityToAccount() {
        objectUnderTest = new AccountConverter();
        AccountEntity accountEntity = AccountEntity.builder()
                .id(1L)
                .username("test")
                .password("password")
                .build();

        Account account = Account.builder()
                .id(1L)
                .username("test")
                .password("password")
                .build();

        Account result = AccountConverter.convert(accountEntity);

        Assertions.assertEquals(account.getId(), result.getId());
        Assertions.assertEquals(account.getUsername(), result.getUsername());
        Assertions.assertEquals(account.getPassword(), result.getPassword());
    }
    @Test
    void ConvertEntityToAccount_WhenEntityIsNull_ThrowsException() {
        Assertions.assertThrows(NullPointerException.class, () -> objectUnderTest.convert((AccountEntity) null));
    }
}
