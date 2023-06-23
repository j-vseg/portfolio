package nl.fontys.sem3.individualtrack.business.impl.orderline;

import lombok.AllArgsConstructor;
import nl.fontys.sem3.individualtrack.business.orderline.GetAllOrderLinesUseCase;
import nl.fontys.sem3.individualtrack.domain.OrderLine;
import nl.fontys.sem3.individualtrack.persistence.OrderLineRepository;
import nl.fontys.sem3.individualtrack.persistence.entity.OrderLineEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllOrderLinesUseCaseImpl implements GetAllOrderLinesUseCase {
    private final OrderLineRepository repository;
    @Override
    public List<OrderLine> getOrderLines(long orderId) {
        List<OrderLine> orderLines = findAll(orderId)
                .stream()
                .map(OrderLineConverter::convert)
                .toList();

        return orderLines;
    }
    private List<OrderLineEntity> findAll(long orderId) { return repository.findByOrderEntity(orderId); }
}
