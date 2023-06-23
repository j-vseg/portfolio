package nl.fontys.sem3.individualtrack.business.impl.orderline;

import lombok.AllArgsConstructor;
import nl.fontys.sem3.individualtrack.business.orderline.DeleteOrderLineUseCase;
import nl.fontys.sem3.individualtrack.persistence.OrderLineRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteOrderLineUseCaseImpl implements DeleteOrderLineUseCase {
    private final OrderLineRepository repository;
    @Override
    public void deleteOrderLine(long id) {
        this.repository.deleteById(id);
    }
}
