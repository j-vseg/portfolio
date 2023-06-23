package nl.fontys.sem3.individualtrack.business.orderline;

import nl.fontys.sem3.individualtrack.domain.OrderLine;

import java.util.Optional;

public interface GetOrderLineUseCase {
    Optional<OrderLine> getOrderLine(long id);
}
