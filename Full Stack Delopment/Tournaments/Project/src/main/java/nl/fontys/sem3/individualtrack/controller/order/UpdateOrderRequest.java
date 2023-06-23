package nl.fontys.sem3.individualtrack.controller.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.sem3.individualtrack.domain.Account;
import nl.fontys.sem3.individualtrack.domain.OrderLine;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderRequest {
    @NotNull
    @Min(1)
    @Max(100000)
    private Long id;
    private Calendar ordered;
    private List<OrderLine> orderlines;
    private Long accountId;
}
