package nl.fontys.sem3.individualtrack.business.order;

import nl.fontys.sem3.individualtrack.domain.Order;

import java.util.Map;
import java.util.Optional;

public interface GetOrderUseCase {
    Optional<Order> getOrder(long id);

}
