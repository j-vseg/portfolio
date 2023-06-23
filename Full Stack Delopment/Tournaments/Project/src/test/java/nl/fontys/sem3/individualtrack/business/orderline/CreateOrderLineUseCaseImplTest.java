package nl.fontys.sem3.individualtrack.business.orderline;

import nl.fontys.sem3.individualtrack.business.impl.orderline.CreateOrderLineUseCaseImpl;
import nl.fontys.sem3.individualtrack.domain.CourseBundle;
import nl.fontys.sem3.individualtrack.domain.OrderLine;
import nl.fontys.sem3.individualtrack.persistence.CourseBundleRepository;
import nl.fontys.sem3.individualtrack.persistence.OrderLineRepository;
import nl.fontys.sem3.individualtrack.persistence.OrderRepository;
import nl.fontys.sem3.individualtrack.persistence.entity.AccountEntity;
import nl.fontys.sem3.individualtrack.persistence.entity.CourseBundleEntity;
import nl.fontys.sem3.individualtrack.persistence.entity.OrderEntity;
import nl.fontys.sem3.individualtrack.persistence.entity.OrderLineEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CreateOrderLineUseCaseImplTest {
    private OrderLineRepository repository;
    private OrderRepository orderRepository;
    private CourseBundleRepository bundleRepository;
    private CreateOrderLineUseCaseImpl objectUnderTest;
    @BeforeEach
    private void setUp() {
        repository = mock(OrderLineRepository.class);
        setUpOrderLines();
    }
    private void setUpOrderLines() {
        bundleRepository.save(CourseBundleEntity.builder()
                .name("Bundle A")
                .description("Description")
                .price(500)
                .quantity(5)
                .packages(Collections.emptyList())
                .build());

        orderRepository.save(OrderEntity.builder()
                .accountEntity(AccountEntity.builder()
                        .id(1L)
                        .username("test")
                        .password("password")
                        .build())
                .build());
    }

    // Todo: Fix test, not sure how to handle it
    /*@Test
    void createOrderLineSuccessfully() {
        OrderLineEntity orderLineEntity = OrderLineEntity.builder()
                .id(1L)
                .courseBundleEntity(CourseBundleEntity.builder()
                        .id(1L)
                        .name("Bundle A")
                        .description("Description")
                        .price(500)
                        .quantity(5)
                        .packages(Collections.emptyList())
                        .build())
                .quantity(5)
                .price(2500)
                .orderEntity(OrderEntity.builder()
                        .id(1L)
                        .accountEntity(AccountEntity.builder()
                                .id(1L)
                                .username("test")
                                .password("password")
                                .build())
                        .build())
                .build();


        when(repository.save(orderLineEntity)).thenReturn(orderLineEntity);

        objectUnderTest = new CreateOrderLineUseCaseImpl(repository, orderRepository, bundleRepository);

        OrderLine orderLine = OrderLine.builder()
                .courseBundle(CourseBundle.builder()
                        .id(1L)
                        .name("Bundle A")
                        .description("Description")
                        .price(500)
                        .quantity(5)
                        .packages(Collections.emptyList())
                        .build())
                .quantity(5)
                .price(2500)
                .build();
        OrderLine result = objectUnderTest.createOrderLine(1L, 1L, orderLine);


        Assertions.assertEquals(1L, result.getId());
    }*/
}
