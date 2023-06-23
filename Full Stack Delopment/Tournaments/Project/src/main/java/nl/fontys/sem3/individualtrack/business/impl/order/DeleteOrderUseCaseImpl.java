package nl.fontys.sem3.individualtrack.business.impl.order;

import lombok.AllArgsConstructor;
import nl.fontys.sem3.individualtrack.business.order.DeleteOrderUseCase;
import nl.fontys.sem3.individualtrack.domain.Order;
import nl.fontys.sem3.individualtrack.persistence.OrderRepository;
import nl.fontys.sem3.individualtrack.persistence.Repository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteOrderUseCaseImpl implements DeleteOrderUseCase {
    private final OrderRepository repository;

    @Override
    public void deleteOrder(long id) {
        this.repository.deleteById(id);
    }
}
