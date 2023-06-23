package nl.fontys.sem3.individualtrack.business.order;

import nl.fontys.sem3.individualtrack.business.impl.order.OrderConverter;
import nl.fontys.sem3.individualtrack.domain.Account;
import nl.fontys.sem3.individualtrack.domain.CourseBundle;
import nl.fontys.sem3.individualtrack.domain.Order;
import nl.fontys.sem3.individualtrack.persistence.OrderRepository;
import nl.fontys.sem3.individualtrack.persistence.entity.AccountEntity;
import nl.fontys.sem3.individualtrack.persistence.entity.OrderEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderConverterTest {
    private OrderRepository repository;
    private OrderConverter objectUnderTest;

    @BeforeEach
    private void setUp() {
        repository = mock(OrderRepository.class);
        objectUnderTest = new OrderConverter();
    }
    @Test
    void ConvertOrderToEntitySuccessfully() {
        OrderEntity orderEntity = OrderEntity.builder()
                .id(1L)
                .accountEntity(AccountEntity.builder().build())
                .orderlines(Collections.emptyList())
                .build();

        Order order = Order.builder()
                .id(1L)
                .account(Account.builder().build())
                .orderlines(Collections.emptyList())
                .build();

        OrderEntity result = objectUnderTest.convert(order);

        Assertions.assertEquals(orderEntity.getId(), result.getId());
        Assertions.assertEquals(orderEntity.getAccountEntity().getId(), result.getAccountEntity().getId());
        Assertions.assertIterableEquals(orderEntity.getOrderlines(), result.getOrderlines());
    }
    @Test
    void ConvertOrderToEntity_WhenEntityIsNull_ThrowsException() {
        Assertions.assertThrows(NullPointerException.class, () -> objectUnderTest.convert((Order) null));
    }
    @Test
    void ConvertToEntityToOrder() {
        OrderEntity orderEntity = OrderEntity.builder()
                .id(1L)
                .accountEntity(AccountEntity.builder().build())
                .orderlines(Collections.emptyList())
                .build();

        Order order = Order.builder()
                .id(1L)
                .account(Account.builder().build())
                .orderlines(Collections.emptyList())
                .build();


        Order result = objectUnderTest.convert(orderEntity);

        Assertions.assertEquals(order.getId(), result.getId());
        Assertions.assertEquals(order.getAccount().getId(), result.getAccount().getId());
        Assertions.assertIterableEquals(order.getOrderlines(), result.getOrderlines());
    }
    @Test
    void ConvertEntityToOrder_WhenEntityIsNull_ThrowsException() {
        Assertions.assertThrows(NullPointerException.class, () -> objectUnderTest.convert((OrderEntity) null));
    }
}
