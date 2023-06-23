package nl.fontys.sem3.individualtrack.business.bundle;

import nl.fontys.sem3.individualtrack.business.exception.InvalidException;
import nl.fontys.sem3.individualtrack.business.impl.account.AccountIdValidatorImpl;
import nl.fontys.sem3.individualtrack.business.impl.bundle.CourseBundleIdValidatorImpl;
import nl.fontys.sem3.individualtrack.domain.Account;
import nl.fontys.sem3.individualtrack.persistence.CourseBundleRepository;
import nl.fontys.sem3.individualtrack.persistence.entity.CourseBundleEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.fail;

public class CourseBundleIdValidatorImplTest {
    private CourseBundleIdValidatorImpl objectUnderTest;
    private CourseBundleRepository repository;

    @BeforeEach
    public void setUp() {
        repository = mock(CourseBundleRepository.class);
        objectUnderTest = new CourseBundleIdValidatorImpl(repository);

    }

    @Test
    public void invalidateIdThrowsInvalidCourseBundleException_SavedCourseBundlesAreNull() {
        Assertions.assertThrows(InvalidException.class, () -> objectUnderTest.ValidateId(null));
    }

    @Test
    public void invalidateIdThrowsInvalidCourseBundleException_BundleIdDoesntExists() {
        Assertions.assertThrows(InvalidException.class, () -> objectUnderTest.ValidateId(9999L));
    }

    @Test
    public void invalidateIdThrowsInvalidCourseBundleException_HappyFlow() {
        objectUnderTest = new CourseBundleIdValidatorImpl(repository);
        when(repository.existsById(1L)).thenReturn(true);

        try {
            objectUnderTest.ValidateId(1L);
        }
        catch(Exception e) {
            fail("Should not have thrown any exception");
        }
    }
}
