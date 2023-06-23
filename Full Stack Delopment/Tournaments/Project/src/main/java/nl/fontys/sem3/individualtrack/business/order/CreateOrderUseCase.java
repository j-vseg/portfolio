package nl.fontys.sem3.individualtrack.business.order;

import nl.fontys.sem3.individualtrack.domain.Order;

import java.util.Calendar;
import java.util.Date;

public interface CreateOrderUseCase {
    Order createOrder(long accountId, Calendar ordered);
}
