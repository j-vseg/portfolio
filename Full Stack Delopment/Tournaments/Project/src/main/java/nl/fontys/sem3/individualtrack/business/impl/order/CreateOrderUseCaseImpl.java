package nl.fontys.sem3.individualtrack.business.impl.order;

import lombok.AllArgsConstructor;
import nl.fontys.sem3.individualtrack.business.order.CreateOrderUseCase;
import nl.fontys.sem3.individualtrack.domain.Order;
import nl.fontys.sem3.individualtrack.persistence.AccountRepository;
import nl.fontys.sem3.individualtrack.persistence.OrderRepository;
import nl.fontys.sem3.individualtrack.persistence.entity.OrderEntity;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

@Service
@AllArgsConstructor
public class CreateOrderUseCaseImpl implements CreateOrderUseCase {
    private final OrderRepository repository;
    private final AccountRepository accountRepository;
    @Override
    public Order createOrder(long accountId, Calendar ordered) {
        OrderEntity savedOrder = saveNewOrder(accountId, ordered);
        return OrderConverter.convert(savedOrder);
    }
    private OrderEntity saveNewOrder(long accountId, Calendar ordered) {
        OrderEntity newOrder = OrderEntity.builder()
                .accountEntity(accountRepository.getReferenceById(accountId))
                .orderlines(Collections.emptyList())
                .ordered(ordered)
                .build();
        return repository.save(newOrder);
    }
}
