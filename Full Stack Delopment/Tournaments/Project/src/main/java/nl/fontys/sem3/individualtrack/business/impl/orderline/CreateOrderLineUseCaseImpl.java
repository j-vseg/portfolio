package nl.fontys.sem3.individualtrack.business.impl.orderline;

import lombok.AllArgsConstructor;
import nl.fontys.sem3.individualtrack.business.orderline.CreateOrderLineUseCase;
import nl.fontys.sem3.individualtrack.domain.OrderLine;
import nl.fontys.sem3.individualtrack.persistence.CourseBundleRepository;
import nl.fontys.sem3.individualtrack.persistence.OrderLineRepository;
import nl.fontys.sem3.individualtrack.persistence.OrderRepository;
import nl.fontys.sem3.individualtrack.persistence.entity.OrderLineEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateOrderLineUseCaseImpl implements CreateOrderLineUseCase {
    private final OrderLineRepository repository;
    private final OrderRepository orderRepository;
    private final CourseBundleRepository bundleRepository;

    @Override
    public OrderLine createOrderLine(long orderId, long courseBundleId, OrderLine orderLine) {
        OrderLineEntity savedOrderLine = saveNewOrderLine(orderId, courseBundleId, orderLine);
        return OrderLineConverter.convert(savedOrderLine);
    }
    private OrderLineEntity saveNewOrderLine(long orderId, long courseBundleId, OrderLine entity) {
        OrderLineEntity newOrderLine = OrderLineEntity.builder()
                .quantity(entity.getQuantity())
                .price(entity.getPrice())
                .courseBundleEntity(bundleRepository.getReferenceById(courseBundleId))
                .orderEntity(orderRepository.getReferenceById(orderId))
                .build();
        repository.save(newOrderLine);
        return newOrderLine;
    }
}
