package nl.fontys.sem3.individualtrack.controller.orderline;

import lombok.AllArgsConstructor;
import nl.fontys.sem3.individualtrack.business.orderline.*;
import nl.fontys.sem3.individualtrack.domain.OrderLine;
import nl.fontys.sem3.individualtrack.controller.orderline.CreateOrderLineRequest;
import nl.fontys.sem3.individualtrack.controller.orderline.CreateOrderLineResponse;
import nl.fontys.sem3.individualtrack.controller.orderline.GetAllOrderLinesResponse;
import nl.fontys.sem3.individualtrack.controller.orderline.UpdateOrderLineRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false" )
@RestController
@RequestMapping(value = "/orders/{orderId}/orderlines") //, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
@AllArgsConstructor
public class OrderLineController {
    private final CreateOrderLineUseCase createOrderLineUseCase;
    private final GetAllOrderLinesUseCase getAllOrderLinesUseCase;
    private final GetOrderLineUseCase getOrderLineUseCase;
    private final UpdateOrderLineUseCase updateOrderLineUseCase;
    private final DeleteOrderLineUseCase deleteOrderLineUseCase;

    @PostMapping()
    public ResponseEntity<CreateOrderLineResponse> createOrderLine(@PathVariable(value = "orderId") long orderId, @RequestBody @Valid CreateOrderLineRequest request) {
        OrderLine orderLine = OrderLine.builder()
                .quantity(request.getQuantity())
                .price(request.getPrice())
                .build();

        CreateOrderLineResponse response = CreateOrderLineResponse.builder()
                .orderId(createOrderLineUseCase.createOrderLine(orderId, request.getCourseBundle(), orderLine).getId())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @RolesAllowed({"ROLE_Owner", "ROLE_Customer", "ROLE_Teacher"})
    @GetMapping
    public ResponseEntity<GetAllOrderLinesResponse> getAllOrderLines(@PathVariable(value = "orderId") long orderId) {
        GetAllOrderLinesResponse response = GetAllOrderLinesResponse.builder()
                .orderlines(getAllOrderLinesUseCase.getOrderLines(orderId))
                .build();

        return ResponseEntity.ok(response);
    }
    @RolesAllowed({"ROLE_Owner", "ROLE_Customer", "ROLE_Teacher"})
    @GetMapping("{id}")
    public ResponseEntity<OrderLine> getOrderLine(@PathVariable(value = "id") final long id) {
        final Optional<OrderLine> orderLineOptional = getOrderLineUseCase.getOrderLine(id);
        if (orderLineOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(orderLineOptional.get());
    }
    @RolesAllowed({"ROLE_Owner", "ROLE_Customer", "ROLE_Teacher"})
    @PutMapping("{id}")
    public ResponseEntity<Void> updateOrderLine(@PathVariable(value = "orderId") long orderId, @PathVariable("id") long id,
                                                   @RequestBody @Valid UpdateOrderLineRequest request) {
        request.setId(id);
        OrderLine ol = OrderLine.builder()
                .id(request.getId())
                .quantity(request.getQuantity())
                .price(request.getPrice())
                .build();
        updateOrderLineUseCase.updateOrderLine(request.getCourseBundleId(), orderId, ol);
        return ResponseEntity.noContent().build();
    }
    @RolesAllowed({"ROLE_Owner", "ROLE_Customer", "ROLE_Teacher"})
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteOrderLine(@PathVariable int id) {
        deleteOrderLineUseCase.deleteOrderLine(id);
        return ResponseEntity.noContent().build();
    }
}
