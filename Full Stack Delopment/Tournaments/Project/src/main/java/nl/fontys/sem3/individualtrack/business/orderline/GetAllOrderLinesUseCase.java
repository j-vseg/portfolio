package nl.fontys.sem3.individualtrack.business.orderline;

import nl.fontys.sem3.individualtrack.domain.OrderLine;

import java.util.List;

public interface GetAllOrderLinesUseCase {
    List<OrderLine> getOrderLines(long orderId);
}
