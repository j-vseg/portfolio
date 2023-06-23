package nl.fontys.sem3.individualtrack.business.impl.order;

import nl.fontys.sem3.individualtrack.business.impl.account.AccountConverter;
import nl.fontys.sem3.individualtrack.business.impl.orderline.OrderLineConverter;
import nl.fontys.sem3.individualtrack.domain.Order;
import nl.fontys.sem3.individualtrack.domain.OrderLine;
import nl.fontys.sem3.individualtrack.persistence.entity.OrderEntity;
import nl.fontys.sem3.individualtrack.persistence.entity.OrderLineEntity;

public class OrderConverter {
    public static Order convert(OrderEntity entity) {
        return  Order.builder()
                .id(entity.getId())
                .orderlines(entity.getOrderlines()
                        .stream()
                        .map(OrderLineConverter::convert)
                        .toList())
                .account(AccountConverter.convert(entity.getAccountEntity()))
                .ordered(entity.getOrdered())
                .build();
    }
    public static OrderEntity convert(Order order) {
        return OrderEntity.builder()
                .id(order.getId())
                .orderlines(order.getOrderlines()
                        .stream()
                        .map(OrderLineConverter::convert)
                        .toList())
                .accountEntity(AccountConverter.convert(order.getAccount()))
                .ordered(order.getOrdered())
                .build();
    }
}
