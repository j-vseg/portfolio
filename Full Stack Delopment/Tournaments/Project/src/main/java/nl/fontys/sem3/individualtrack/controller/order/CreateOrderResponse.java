package nl.fontys.sem3.individualtrack.controller.order;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateOrderResponse {
    private Long orderId;
}
