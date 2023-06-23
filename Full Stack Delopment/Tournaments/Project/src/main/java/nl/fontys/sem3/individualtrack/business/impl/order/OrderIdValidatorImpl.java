package nl.fontys.sem3.individualtrack.business.impl.order;

import lombok.AllArgsConstructor;
import nl.fontys.sem3.individualtrack.business.IdValidator;
import nl.fontys.sem3.individualtrack.business.exception.InvalidException;
import nl.fontys.sem3.individualtrack.domain.Order;
import nl.fontys.sem3.individualtrack.persistence.OrderRepository;
import nl.fontys.sem3.individualtrack.persistence.Repository;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderIdValidatorImpl implements IdValidator<Order> {
    private final OrderRepository repository;

    @Override
    public void ValidateId(Long orderId)  {
        if (orderId == null || !repository.existsById(orderId)) {
            throw new InvalidException("ORDER_ID_INVALID");
        }
    }
}
