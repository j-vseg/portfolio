package nl.fontys.sem3.individualtrack.business.order;

import nl.fontys.sem3.individualtrack.business.impl.account.DeleteAccountUseCaseImpl;
import nl.fontys.sem3.individualtrack.business.impl.bundle.DeleteCourseBundleUseCaseImpl;
import nl.fontys.sem3.individualtrack.business.impl.order.DeleteOrderUseCaseImpl;
import nl.fontys.sem3.individualtrack.persistence.OrderRepository;
import nl.fontys.sem3.individualtrack.persistence.entity.OrderEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.util.AssertionErrors.fail;

public class DeleteOrderUseCaseImplTest {
    private DeleteOrderUseCaseImpl objectUnderTest;
    private OrderRepository repository;

    @BeforeEach
    public void setUp() {
        repository = mock(OrderRepository.class);
    }

    @Test
    void deleteBundleSuccessfully() {
        objectUnderTest = new DeleteOrderUseCaseImpl(repository);

        OrderEntity bundleEntity = OrderEntity.builder()
                .id(1L)
                .orderlines(Collections.emptyList())
                .build();
        repository.save(bundleEntity);

        doNothing().when(repository).deleteById(1L);

        try {
            objectUnderTest.deleteOrder(1L);
        }
        catch(Exception e) {
            fail("Should not have thrown any exception");
        }
    }
    @Test
    void deleteOrder_WhenIdIsInvalid_NothingHappens() {
        objectUnderTest = new DeleteOrderUseCaseImpl(repository);

        try {
            objectUnderTest.deleteOrder(99L);
        }
        catch(Exception e) {
            fail("Should not have thrown any exception");
        }
    }
}
