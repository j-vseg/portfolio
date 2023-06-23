package nl.fontys.sem3.individualtrack.business.impl.orderline;

import lombok.AllArgsConstructor;
import nl.fontys.sem3.individualtrack.business.IdValidator;
import nl.fontys.sem3.individualtrack.business.exception.InvalidException;
import nl.fontys.sem3.individualtrack.business.orderline.UpdateOrderLineUseCase;
import nl.fontys.sem3.individualtrack.domain.OrderLine;
import nl.fontys.sem3.individualtrack.persistence.CourseBundleRepository;
import nl.fontys.sem3.individualtrack.persistence.OrderLineRepository;
import nl.fontys.sem3.individualtrack.persistence.OrderRepository;
import nl.fontys.sem3.individualtrack.persistence.entity.OrderLineEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateOrderLineUseCaseImpl implements UpdateOrderLineUseCase {
    private final OrderLineRepository repository;
    private final CourseBundleRepository bundleRepository;
    private final OrderRepository orderRepository;
    private final IdValidator<OrderLine> idValidator;
    @Override
    public void updateOrderLine(long courseBundleId, long orderId, OrderLine orderLine) {
        Optional<OrderLine> orderLineOptional = repository.findById(orderLine.getId())
                .map(OrderLineConverter::convert);

        if (orderLineOptional.isEmpty()) {
            throw new InvalidException("ORDER_LINE_ID_INVALID");
        }

        idValidator.ValidateId(orderLine.getId());

        updateFields(orderLine, orderId, courseBundleId);
    }
    private void updateFields(OrderLine orderLine, long orderId, long courseBundleId) {
        OrderLineEntity ol = OrderLineEntity.builder()
                .id(orderLine.getId())
                .quantity(orderLine.getQuantity())
                .price(orderLine.getPrice())
                .courseBundleEntity(bundleRepository.getReferenceById(courseBundleId))
                .orderEntity(orderRepository.getReferenceById(orderId))
                .build();
        repository.save(ol);
    }
}
