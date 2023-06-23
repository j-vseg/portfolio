package nl.fontys.sem3.individualtrack.business.order;

import nl.fontys.sem3.individualtrack.business.impl.bundle.GetCourseBundleUseCaseImpl;
import nl.fontys.sem3.individualtrack.business.impl.order.GetOrderUseCaseImpl;
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
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetOrderUseCaseImplTest {
    private GetOrderUseCaseImpl objectUnderTest;
    private OrderRepository repository;

    @BeforeEach
    public void setUp() {
        repository = mock(OrderRepository.class);
    }
    @Test
    void GetOrder_HappyFlow() {
        OrderEntity order = OrderEntity.builder()
                .id(1L)
                .accountEntity(AccountEntity.builder().build())
                .orderlines(Collections.emptyList())
                .build();

        when(repository.findById(1L)).thenReturn(Optional.ofNullable(order));

        objectUnderTest = new GetOrderUseCaseImpl(repository);

        Order oExpected = Order.builder()
                .id(1L)
                .account(Account.builder().build())
                .orderlines(Collections.emptyList())
                .build();
        Optional<Order> expected = Optional.ofNullable(oExpected);

        Optional<Order> result = objectUnderTest.getOrder(1L);

        Assertions.assertEquals(expected, result);
    }
    @Test
    void getOrder_ShouldReturnEmptyOptionalWhenOrderNotFound() {

        when(repository.findById(1L)).thenReturn(Optional.empty());

        objectUnderTest = new GetOrderUseCaseImpl(repository);

        Optional<Order> expected = Optional.empty();

        Optional<Order> result = objectUnderTest.getOrder(1L);

        Assertions.assertEquals(expected, result);
    }
}
