package nl.fontys.sem3.individualtrack.business.impl.order;

import lombok.AllArgsConstructor;
import nl.fontys.sem3.individualtrack.business.IdValidator;
import nl.fontys.sem3.individualtrack.business.exception.InvalidException;
import nl.fontys.sem3.individualtrack.business.order.UpdateOrderUseCase;
import nl.fontys.sem3.individualtrack.domain.Order;
import nl.fontys.sem3.individualtrack.persistence.AccountRepository;
import nl.fontys.sem3.individualtrack.persistence.OrderRepository;
import nl.fontys.sem3.individualtrack.persistence.entity.OrderEntity;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateOrderUseCaseImpl implements UpdateOrderUseCase {
    private final OrderRepository repository;
    private final AccountRepository accountRepository;
    private final IdValidator<Order> idValidator;

    @Override
    public void updateOrder(Long orderId, Long accountId, Calendar ordered) {
        Optional<Order> orderOptional = repository.findById(orderId)
                .map(OrderConverter::convert);

        if (orderOptional.isEmpty()) {
            throw new InvalidException("ORDER_ID_INVALID");
        }

        idValidator.ValidateId(orderOptional.get().getId());

        updateFields(orderOptional.get(), accountId, ordered);
    }

    private void updateFields(Order order, Long accountId, Calendar ordered) {
        OrderEntity o = OrderEntity.builder()
                .id(order.getId())
                .accountEntity(accountRepository.getReferenceById(accountId))
                .ordered(ordered)
                .build();

        repository.save(o);
    }
}
