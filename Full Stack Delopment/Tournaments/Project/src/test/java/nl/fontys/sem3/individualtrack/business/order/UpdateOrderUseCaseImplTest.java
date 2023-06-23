package nl.fontys.sem3.individualtrack.business.order;

import nl.fontys.sem3.individualtrack.business.IdValidator;
import nl.fontys.sem3.individualtrack.business.exception.InvalidException;
import nl.fontys.sem3.individualtrack.business.impl.bundle.CourseBundleIdValidatorImpl;
import nl.fontys.sem3.individualtrack.business.impl.bundle.UpdateCourseBundleUseCaseImpl;
import nl.fontys.sem3.individualtrack.business.impl.order.OrderConverter;
import nl.fontys.sem3.individualtrack.business.impl.order.OrderIdValidatorImpl;
import nl.fontys.sem3.individualtrack.business.impl.order.UpdateOrderUseCaseImpl;
import nl.fontys.sem3.individualtrack.domain.Account;
import nl.fontys.sem3.individualtrack.domain.CourseBundle;
import nl.fontys.sem3.individualtrack.domain.Order;
import nl.fontys.sem3.individualtrack.persistence.AccountRepository;
import nl.fontys.sem3.individualtrack.persistence.OrderRepository;
import nl.fontys.sem3.individualtrack.persistence.entity.AccountEntity;
import nl.fontys.sem3.individualtrack.persistence.entity.OrderEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.fail;

public class UpdateOrderUseCaseImplTest {
    private OrderRepository repository;
    private UpdateOrderUseCaseImpl objectUnderTest;

    @BeforeEach
    private void setUp() {
        repository = mock(OrderRepository.class);
    }

    @Test
    void UpdateOrderButOrderIsNotValid() {
        IdValidator<Order> idValidator = mock(IdValidator.class);
        OrderRepository orderRepository = mock(OrderRepository.class);
        AccountRepository accountRepository = mock(AccountRepository.class);
        objectUnderTest = new UpdateOrderUseCaseImpl(orderRepository, accountRepository, idValidator);
        Assertions.assertThrows(InvalidException.class, () -> objectUnderTest.updateOrder(2L, 2L, new GregorianCalendar()));
    }
    @Test
    void updateCourseBundle_WhenIdInvalid_ThrowInvalidException() {
        IdValidator<Order> idValidator = new OrderIdValidatorImpl(repository);
        AccountRepository accountRepository = mock(AccountRepository.class);
        objectUnderTest = new UpdateOrderUseCaseImpl(repository, accountRepository, idValidator);

        Assertions.assertThrows(InvalidException.class, () -> objectUnderTest.updateOrder(1L, 1L, new GregorianCalendar()));
    }
    @Test
    void updateOrderHappyFlow() {
        OrderEntity order = OrderEntity.builder()
                .id(1L)
                .accountEntity(AccountEntity.builder().build())
                .orderlines(Collections.emptyList())
                .build();
        repository.save(order);

        Order testOrder = Order.builder()
                .id(1L)
                .account(Account.builder()
                        .id(1L)
                        .build())
                .orderlines(Collections.emptyList())
                .build();

        when(repository.findById(1L)).thenReturn(Optional.of(order));
        when(repository.save(OrderConverter.convert(testOrder)))
                .thenReturn(OrderConverter.convert(testOrder));
        when(repository.existsById(1L)).thenReturn(true);

        IdValidator<Order> idValidator = new OrderIdValidatorImpl(repository);
        AccountRepository accountRepository = mock(AccountRepository.class);
        objectUnderTest = new UpdateOrderUseCaseImpl(repository, accountRepository, idValidator);

        try {
            Calendar cal = new GregorianCalendar();
            objectUnderTest.updateOrder(1L, 1L, cal);
        }
        catch(Exception e) {
            fail("Should not have thrown any exception");
        }
    }
}
