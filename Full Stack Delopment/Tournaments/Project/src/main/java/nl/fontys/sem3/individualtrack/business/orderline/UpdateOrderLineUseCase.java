package nl.fontys.sem3.individualtrack.business.orderline;

import nl.fontys.sem3.individualtrack.domain.OrderLine;

public interface UpdateOrderLineUseCase {
    void updateOrderLine(long courseBundleId, long orderId, OrderLine orderLine);
}
