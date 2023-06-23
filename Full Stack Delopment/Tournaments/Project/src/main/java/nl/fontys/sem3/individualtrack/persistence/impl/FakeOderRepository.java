package nl.fontys.sem3.individualtrack.persistence.impl;

import nl.fontys.sem3.individualtrack.domain.*;
import nl.fontys.sem3.individualtrack.persistence.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class FakeOderRepository implements Repository<Order> {
    private static long NEXT_ID = 1;
    private final List<Order> savedOrders;

    public FakeOderRepository() {
        savedOrders = new ArrayList<>();
        List<OrderLine> ols = new ArrayList<>();
        long id = 1;
        long idol = 1;
        long ida = 1;
        long olid =1;
        ols.add(new OrderLine(olid++, 2, 2.50, new CourseBundle(id++, "Bundle A", "Desciption", 50.00, 1, null)));
        ols.add(new OrderLine(olid++, 2, 2.50, new CourseBundle(id++, "Bundle A", "Desciption", 50.00, 2, null)));
        Order o1 = Order.builder()
                .id(idol)
                .orderlines(ols)
                .account(new Account(ida++, "janne", "pass", Roles.Customer,"email", "123"))
                .build();
        save(o1);
        save(o1);
    }

    @Override
    public boolean existsById(long id) {
        return this.savedOrders
                .stream()
                .anyMatch(Order -> Order.getId().equals(id));
    }

    @Override
    public Order save(Order order) {
        if (order.getId() == null) {
            order.setId(NEXT_ID);
            NEXT_ID++;
            this.savedOrders.add(order);
        }
        return order;
    }

    @Override
    public void deleteById(long orderId) {
        this.savedOrders.removeIf(Order -> Order.getId().equals(orderId));
    }

    @Override
    public List<Order> findAll() {
        return Collections.unmodifiableList((this.savedOrders));
    }

    @Override
    public Optional<Order> findById(long orderId) {
        return this.savedOrders.stream()
                .filter(Order -> Order.getId().equals(orderId))
                .findFirst();
    }
}
