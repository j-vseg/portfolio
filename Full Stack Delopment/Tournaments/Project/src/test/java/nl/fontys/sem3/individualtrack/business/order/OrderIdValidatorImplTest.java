package nl.fontys.sem3.individualtrack.business.order;

import nl.fontys.sem3.individualtrack.business.exception.InvalidException;
import nl.fontys.sem3.individualtrack.business.impl.bundle.CourseBundleIdValidatorImpl;
import nl.fontys.sem3.individualtrack.business.impl.order.OrderIdValidatorImpl;
import nl.fontys.sem3.individualtrack.persistence.CourseBundleRepository;
import nl.fontys.sem3.individualtrack.persistence.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.fail;

public class OrderIdValidatorImplTest {
    private OrderIdValidatorImpl objectUnderTest;
    private OrderRepository repository;

    @BeforeEach
    public void setUp() {
        repository = mock(OrderRepository.class);
        objectUnderTest = new OrderIdValidatorImpl(repository);

    }

    @Test
    public void invalidateIdThrowsInvalidException_SavedOrderAreNull() {
        Assertions.assertThrows(InvalidException.class, () -> objectUnderTest.ValidateId(null));
    }

    @Test
    public void invalidateIdThrowsInvalidException_OrderIdDoesntExists() {
        Assertions.assertThrows(InvalidException.class, () -> objectUnderTest.ValidateId(9999L));
    }

    @Test
    public void invalidateIdThrowsInvalidException_HappyFlow() {
        objectUnderTest = new OrderIdValidatorImpl(repository);

        when(repository.existsById(1L)).thenReturn(true);

        try {
            objectUnderTest.ValidateId(1L);
        }
        catch(Exception e) {
            fail("Should not have thrown any exception");
        }
    }
}
