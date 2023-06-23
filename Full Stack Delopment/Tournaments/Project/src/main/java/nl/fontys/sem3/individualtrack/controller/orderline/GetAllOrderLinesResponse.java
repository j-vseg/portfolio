package nl.fontys.sem3.individualtrack.controller.orderline;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.sem3.individualtrack.domain.OrderLine;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllOrderLinesResponse {
    private List<OrderLine> orderlines;
}
