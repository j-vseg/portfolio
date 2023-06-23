package nl.fontys.sem3.individualtrack.controller.order;

import lombok.AllArgsConstructor;
import nl.fontys.sem3.individualtrack.business.order.*;
import nl.fontys.sem3.individualtrack.domain.Order;
import nl.fontys.sem3.individualtrack.controller.order.CreateOrderRequest;
import nl.fontys.sem3.individualtrack.controller.order.CreateOrderResponse;
import nl.fontys.sem3.individualtrack.controller.order.GetAllOrderResponse;
import nl.fontys.sem3.individualtrack.controller.order.UpdateOrderRequest;
import nl.fontys.sem3.individualtrack.domain.OrderData;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.IsoFields;
import java.time.temporal.WeekFields;
import java.util.*;

@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false" )
@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final CreateOrderUseCase createOrderUseCase;
    private final GetAllOrdersUseCase getAllOrdersUseCase;
    private final GetOrderUseCase getOrderUseCase;
    private final UpdateOrderUseCase updateOrderUseCase;
    private final DeleteOrderUseCase deleteOrderUseCase;

    @RolesAllowed({"ROLE_Owner", "ROLE_Customer"})
    @PostMapping()
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody @Valid CreateOrderRequest request) {
        CreateOrderResponse response = CreateOrderResponse.builder()
                .orderId(createOrderUseCase.createOrder(request.getAccountId(), request.getOrdered()).getId())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @RolesAllowed({"ROLE_Owner", "ROLE_Teacher"})
    @GetMapping
    public ResponseEntity<GetAllOrderResponse> getAllOrders() {
        GetAllOrderResponse response = GetAllOrderResponse.builder()
                .orders(getAllOrdersUseCase.getOrders(/*request*/))
                .build();

        return ResponseEntity.ok(response);
    }
    @RolesAllowed({"ROLE_Owner", "ROLE_Teacher"})
    @GetMapping("/stats")
    public ResponseEntity<GetAllOrderResponse> getAllOrdersStats(@RequestParam(required = false, value = "yearly") @DateTimeFormat(pattern = "dd-MM-yyyy") Calendar year,
                                                                 @RequestParam(required = false, value = "quarter") @DateTimeFormat(pattern = "dd-MM-yyyy") Calendar quarter,
                                                                 @RequestParam(required = false, value = "weekly") @DateTimeFormat(pattern = "dd-MM-yyyy") Calendar week,
                                                                 @RequestParam(required = false, value = "daily") @DateTimeFormat(pattern = "dd-MM-yyyy") Calendar day) {
        List<OrderData> data = new ArrayList<>();

        if (year != null) { data = getAllOrdersUseCase.getStatsYearly(year.get(Calendar.YEAR)); }
        else if (week != null) {
            data = getAllOrdersUseCase.getStatsWeekly(week.get(Calendar.WEEK_OF_YEAR));
        }
        else if (day != null) { data = getAllOrdersUseCase.getStatsDaily(day); }

        GetAllOrderResponse response = GetAllOrderResponse.builder()
                .orderData(data)
                .build();

        return ResponseEntity.ok(response);
    }
    @RolesAllowed({"ROLE_Owner", "ROLE_Teacher", "ROLE_Customer"})
    @GetMapping("{id}")
    public ResponseEntity<Order> getOrder(@PathVariable(value = "id") final long id) {
        final Optional<Order> orderOptional = getOrderUseCase.getOrder(id);
        if (orderOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(orderOptional.get());
    }
    @RolesAllowed({"ROLE_Owner", "ROLE_Teacher", "ROLE_Customer"})
    @PutMapping("{id}")
    public ResponseEntity<Void> updateOrder(@PathVariable("id") long id,
                                                   @RequestBody @Valid UpdateOrderRequest request) {
        request.setId(id);
        updateOrderUseCase.updateOrder(request.getId(), request.getAccountId(), request.getOrdered());
        return ResponseEntity.noContent().build();
    }
    @RolesAllowed({"ROLE_Owner"})
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable int id) {
        deleteOrderUseCase.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
