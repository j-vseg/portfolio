package nl.fontys.sem3.individualtrack.business.orderline;

import nl.fontys.sem3.individualtrack.domain.OrderLine;

public interface CreateOrderLineUseCase {
    OrderLine createOrderLine(long orderId, long courseBundleId, OrderLine orderLine);
}
