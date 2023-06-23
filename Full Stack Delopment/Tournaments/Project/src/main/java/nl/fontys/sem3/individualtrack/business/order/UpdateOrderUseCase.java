package nl.fontys.sem3.individualtrack.business.order;

import java.util.Calendar;
import java.util.Date;

public interface UpdateOrderUseCase {
    void updateOrder(Long orderId, Long accountId, Calendar ordered);
}
