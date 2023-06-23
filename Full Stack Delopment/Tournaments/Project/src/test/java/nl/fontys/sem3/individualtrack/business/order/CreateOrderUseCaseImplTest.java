package nl.fontys.sem3.individualtrack.business.order;

import nl.fontys.sem3.individualtrack.business.impl.bundle.CourseBundleConverter;
import nl.fontys.sem3.individualtrack.business.impl.order.CreateOrderUseCaseImpl;
import nl.fontys.sem3.individualtrack.business.impl.order.OrderConverter;
import nl.fontys.sem3.individualtrack.domain.Account;
import nl.fontys.sem3.individualtrack.domain.CourseBundle;
import nl.fontys.sem3.individualtrack.domain.Order;
import nl.fontys.sem3.individualtrack.persistence.AccountRepository;
import nl.fontys.sem3.individualtrack.persistence.OrderRepository;
import nl.fontys.sem3.individualtrack.persistence.entity.AccountEntity;
import nl.fontys.sem3.individualtrack.persistence.entity.CourseBundleEntity;
import nl.fontys.sem3.individualtrack.persistence.entity.OrderEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.GregorianCalendar;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CreateOrderUseCaseImplTest {
    private OrderRepository repository;
    private CreateOrderUseCaseImpl objectUnderTest;
    @BeforeEach
    private void setUp() {
        repository = mock(OrderRepository.class);
    }

    @Test
    void createOrderSuccessfully() {
        OrderEntity order = OrderEntity.builder()
                .accountEntity(AccountEntity.builder()
                        .id(1L)
                        .build())
                .orderlines(Collections.emptyList())
                .build();

        OrderEntity returnOrder = OrderEntity.builder()
                .id(1L)
                .accountEntity(AccountEntity.builder()
                        .id(1L)
                        .build())
                .orderlines(Collections.emptyList())
                .build();

        AccountRepository accountRepository = mock(AccountRepository.class);
        objectUnderTest = new CreateOrderUseCaseImpl(repository, accountRepository);

        when(repository.save(order)).thenReturn(returnOrder);
        when(accountRepository.getReferenceById(1L)).thenReturn(AccountEntity.builder()
                .id(1L)
                .build());

        Order actualOrder = objectUnderTest.createOrder(1L, new GregorianCalendar());

        Assertions.assertEquals(returnOrder.getId(), actualOrder.getId());
        Assertions.assertEquals(returnOrder.getAccountEntity().getId(), actualOrder.getAccount().getId());
    }
    @Test
    void createOrder_WhenFailsToConvert_ThrowsNullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> objectUnderTest.createOrder(1L, new GregorianCalendar()));
    }
    @Test
    void createOrder_WhenConvertObjectToEntitySuccessfully() {
        OrderEntity orderEntity = OrderEntity.builder()
                .id(1L)
                .accountEntity(AccountEntity.builder()
                        .id(1L)
                        .build())
                .orderlines(Collections.emptyList())
                .build();

        Order result = OrderConverter.convert(orderEntity);

        Assertions.assertEquals(orderEntity.getId(), result.getId());
        Assertions.assertEquals(orderEntity.getAccountEntity().getId(), result.getAccount().getId());
        Assertions.assertEquals(orderEntity.getOrderlines(), result.getOrderlines());
    }
}
