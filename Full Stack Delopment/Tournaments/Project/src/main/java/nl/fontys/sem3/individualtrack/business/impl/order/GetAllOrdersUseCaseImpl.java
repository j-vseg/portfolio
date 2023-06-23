package nl.fontys.sem3.individualtrack.business.impl.order;

import lombok.AllArgsConstructor;
import nl.fontys.sem3.individualtrack.business.order.GetAllOrdersUseCase;
import nl.fontys.sem3.individualtrack.domain.Order;
import nl.fontys.sem3.individualtrack.domain.OrderData;
import nl.fontys.sem3.individualtrack.persistence.OrderRepository;
import nl.fontys.sem3.individualtrack.persistence.entity.OrderEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class GetAllOrdersUseCaseImpl implements GetAllOrdersUseCase {
    private final OrderRepository repository;

    @Override
    public List<Order> getOrders() {
        List<Order> orders = findAll()
                .stream()
                .map(OrderConverter::convert)
                .toList();
        
        return orders;
    }
    @Override
    public List<OrderData> getStatsYearly(int year) {
        List<Order> orders = getOrders();
        List<OrderData> data = new ArrayList<>();

        for (int i = 6; i >= 0; i--) {
            data.add(new OrderData(year-i, 0));
        }

        for (Order order : orders) {
            if (order.getOrdered().get(Calendar.YEAR) <= year && order.getOrdered().get(Calendar.YEAR) >= year-6) {
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).getYear() == order.getOrdered().get(Calendar.YEAR)) {
                        int count = data.get(i).getCount();
                        count++;
                        data.set(i, new OrderData(order.getOrdered().get(Calendar.YEAR), count));
                    }
                    //else { data.add(new OrderData(order.getOrdered().get(Calendar.YEAR), 1)); }
                }
            }
        }

        return data;
    }

    @Override
    public List<OrderData> getStatsWeekly(int week) {
        List<Order> orders = getOrders();
        List<OrderData> data = new ArrayList<>();

        for (int i = 6; i >= 0; i--) {
            Calendar cal = new GregorianCalendar();
            cal.set(Calendar.YEAR-1, 12, 31);
            int weeksThisYear = cal.getActualMaximum(Calendar.WEEK_OF_YEAR);
            if (week-i < 1 || week-i > weeksThisYear) {
                int weeknumber = weeksThisYear-i;
                if (weeknumber == 53) { weeknumber = 52; }
                //i = 6 - i;
                data.add(new OrderData(weeknumber, 0));
            }
            else {
                int weeknumber = week-i;
                if (weeknumber == 53) { weeknumber = 52; }
                data.add(new OrderData(weeknumber, 0));
            }
        }


        for (Order order : orders) {
            int weeknumber = order.getOrdered().get(Calendar.WEEK_OF_YEAR);
            if (weeknumber == 53) { weeknumber = 52; }
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).getYear() == weeknumber) {
                    int count = data.get(i).getCount();
                    count++;
                    data.set(i, new OrderData(weeknumber, count));
                }
                //else { data.add(new OrderData(order.getOrdered().get(Calendar.YEAR), 1)); }
            }
        }

        return data;
    }

    @Override
    public List<OrderData> getStatsDaily(Calendar day) { // Todo: Fix return valid information
        List<Order> orders = getOrders();
        List<OrderData> data = new ArrayList<>();

        for (int i = 6; i >= 0; i--) {
            Calendar cal = new GregorianCalendar();
            if (day.MONTH-1 < 0) { cal.set(day.YEAR-1, day.MONTH-1, 21); }
            else { cal.set(day.YEAR-1, Calendar.DECEMBER, 21); }
            int daysAMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            int date = day.DAY_OF_MONTH;
            int test = day.DAY_OF_MONTH-i;
            if (day.DAY_OF_MONTH-i < 1 || day.DAY_OF_MONTH-i > daysAMonth) {
                data.add(new OrderData(daysAMonth-i, 0));
            }
            else { data.add(new OrderData(day.DAY_OF_MONTH-i, 0)); }
        }

        for (Order order : orders) {
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).getYear() == order.getOrdered().get(Calendar.DAY_OF_MONTH)) {
                        // Should check for month as well
                        int count = data.get(i).getCount();
                        count++;
                        data.set(i, new OrderData(order.getOrdered().get(Calendar.DAY_OF_MONTH), count));
                    }
                    //else { data.add(new OrderData(order.getOrdered().get(Calendar.YEAR), 1)); }
                }
            }

        return data;
    }

    private List<OrderEntity> findAll() {
        return repository.findAll();
    }
}
