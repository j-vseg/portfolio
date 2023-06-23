package nl.fontys.sem3.individualtrack.business.order;

import nl.fontys.sem3.individualtrack.domain.Order;
import nl.fontys.sem3.individualtrack.domain.OrderData;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

public interface GetAllOrdersUseCase {
    List<Order> getOrders();
    List<OrderData> getStatsYearly(int year);
    List<OrderData> getStatsWeekly(int week);
    List<OrderData> getStatsDaily(Calendar day);
}
