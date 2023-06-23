package nl.fontys.sem3.individualtrack.business.account;

import nl.fontys.sem3.individualtrack.business.impl.account.AccountConverter;
import nl.fontys.sem3.individualtrack.business.impl.account.GetAllAccountsUseCaseImpl;
import nl.fontys.sem3.individualtrack.business.impl.bundle.GetAllCourseBundlesUseCaseImpl;
import nl.fontys.sem3.individualtrack.domain.Account;
import nl.fontys.sem3.individualtrack.domain.CourseBundle;
import nl.fontys.sem3.individualtrack.persistence.AccountRepository;
import nl.fontys.sem3.individualtrack.persistence.entity.AccountEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetAllAccountsUseCaseImplTest {
    private GetAllAccountsUseCaseImpl objectUnderTest;
    private AccountRepository repository;

    @BeforeEach
    public void setUp() {
        repository = mock(AccountRepository.class);
    }

    @Test
    public void GetAllAccounts_HappyFlow() {
        AccountEntity a1 = AccountEntity.builder()
                .id(1L)
                .username("test")
                .password("password")
                .build();
        AccountEntity a2 = AccountEntity.builder()
                .id(2L)
                .username("test2")
                .password("password")
                .build();

        when(repository.findAll())
                .thenReturn(List.of(a1, a2));

        objectUnderTest = new GetAllAccountsUseCaseImpl(repository);

        List<Account> result = objectUnderTest.getAllAccounts();

        List<Account> expected = new ArrayList<>();
        expected.add(AccountConverter.convert(a1));
        expected.add(AccountConverter.convert(a2));

        Assertions.assertEquals(expected, result);
    }
    @Test
    public void GetAllAccounts_WhenAccountsAreEmpty_ReturnsEmptyList() {

        when(repository.findAll())
                .thenReturn(Collections.emptyList());

        objectUnderTest = new GetAllAccountsUseCaseImpl(repository);

        List<Account> result = objectUnderTest.getAllAccounts();

        List<Account> expected = Collections.emptyList();

        Assertions.assertEquals(expected, result);
    }
}
