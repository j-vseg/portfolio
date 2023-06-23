package nl.fontys.sem3.individualtrack.business.order;

import nl.fontys.sem3.individualtrack.business.impl.bundle.GetAllCourseBundlesUseCaseImpl;
import nl.fontys.sem3.individualtrack.business.impl.order.GetAllOrdersUseCaseImpl;
import nl.fontys.sem3.individualtrack.business.impl.order.OrderConverter;
import nl.fontys.sem3.individualtrack.domain.CourseBundle;
import nl.fontys.sem3.individualtrack.domain.Order;
import nl.fontys.sem3.individualtrack.persistence.OrderRepository;
import nl.fontys.sem3.individualtrack.persistence.entity.AccountEntity;
import nl.fontys.sem3.individualtrack.persistence.entity.OrderEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetAllOrdersUseCaseImplTest {
    private GetAllOrdersUseCaseImpl objectUnderTest;
    private OrderRepository repository;

    @BeforeEach
    public void setUp() {
        repository = mock(OrderRepository.class);
    }

    @Test
    public void GetAllOrders_HappyFlow() {
        OrderEntity o1 = OrderEntity.builder()
                .id(1L)
                .accountEntity(AccountEntity.builder().build())
                .orderlines(Collections.emptyList())
                .build();
        OrderEntity o2 = OrderEntity.builder()
                .id(2L)
                .accountEntity(AccountEntity.builder().build())
                .orderlines(Collections.emptyList())
                .build();

        when(repository.findAll())
                .thenReturn(List.of(o1, o2));

        objectUnderTest = new GetAllOrdersUseCaseImpl(repository);

        List<Order> result = objectUnderTest.getOrders();

        List<Order> expected = new ArrayList<>();
        expected.add(OrderConverter.convert(o1));
        expected.add(OrderConverter.convert(o2));

        Assertions.assertEquals(expected, result);
    }
    @Test
    public void GetAllOrders_WhenOrdersAreEmpty_ReturnsEmptyList() {
        when(repository.findAll())
                .thenReturn(Collections.emptyList());

        objectUnderTest = new GetAllOrdersUseCaseImpl(repository);

        List<Order> result = objectUnderTest.getOrders();

        List<Order> expected = Collections.emptyList();

        Assertions.assertEquals(expected, result);
    }
}
