package nl.fontys.sem3.individualtrack.business.impl.orderline;

import lombok.AllArgsConstructor;
import nl.fontys.sem3.individualtrack.business.orderline.GetOrderLineUseCase;
import nl.fontys.sem3.individualtrack.domain.OrderLine;
import nl.fontys.sem3.individualtrack.persistence.OrderLineRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetOrderLineUseCaseImpl implements GetOrderLineUseCase {
    private final OrderLineRepository repository;
    @Override
    public Optional<OrderLine> getOrderLine(long id) {
        return repository.findById(id)
                .map(OrderLineConverter::convert);
    }
}
