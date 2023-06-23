package nl.fontys.sem3.individualtrack.controller.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.sem3.individualtrack.domain.Order;
import nl.fontys.sem3.individualtrack.domain.OrderData;

import java.util.List;
import java.util.Map;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllOrderResponse {
    private List<Order> orders;
    private List<OrderData> orderData;
}
