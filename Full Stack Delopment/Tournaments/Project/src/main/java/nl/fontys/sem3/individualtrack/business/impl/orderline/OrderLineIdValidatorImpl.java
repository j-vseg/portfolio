package nl.fontys.sem3.individualtrack.business.impl.orderline;

import lombok.AllArgsConstructor;
import nl.fontys.sem3.individualtrack.business.IdValidator;
import nl.fontys.sem3.individualtrack.business.exception.InvalidException;
import nl.fontys.sem3.individualtrack.domain.OrderLine;
import nl.fontys.sem3.individualtrack.persistence.CourseBundleRepository;
import nl.fontys.sem3.individualtrack.persistence.OrderLineRepository;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderLineIdValidatorImpl implements IdValidator<OrderLine> {
    private final OrderLineRepository repository;
    @Override
    public void ValidateId(Long orderLineId)  {
        if (orderLineId == null || !repository.existsById(orderLineId)) {
            throw new InvalidException("ORDER_LINE_ID_INVALID");
        }
    }
}
