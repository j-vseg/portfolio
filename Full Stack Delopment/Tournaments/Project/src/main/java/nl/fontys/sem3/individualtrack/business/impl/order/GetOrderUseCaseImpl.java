package nl.fontys.sem3.individualtrack.business.impl.order;

import lombok.AllArgsConstructor;
import nl.fontys.sem3.individualtrack.business.order.GetOrderUseCase;
import nl.fontys.sem3.individualtrack.domain.Order;
import nl.fontys.sem3.individualtrack.persistence.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetOrderUseCaseImpl implements GetOrderUseCase {
    private final OrderRepository repository;

    @Override
    public Optional<Order> getOrder(long id) {
        return repository.findById(id)
                .map(OrderConverter::convert);
    }
}
