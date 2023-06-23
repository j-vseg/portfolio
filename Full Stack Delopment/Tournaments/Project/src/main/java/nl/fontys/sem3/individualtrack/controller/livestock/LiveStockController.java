package nl.fontys.sem3.individualtrack.controller.livestock;

import lombok.AllArgsConstructor;
import nl.fontys.sem3.individualtrack.business.UpdateStockUseCase;
import nl.fontys.sem3.individualtrack.domain.StockUpdate;
import nl.fontys.sem3.individualtrack.domain.UpdateMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

@RestController
@AllArgsConstructor
public class LiveStockController {
    private final UpdateStockUseCase updateStockUseCase;

    @MessageMapping("/update-stock")
    @SendTo("/topic/live-stock")
    public UpdateMessage updateStock(StockUpdate stockUpdate) throws Exception {
        //Thread.sleep(1000); // simulated delay
        return new UpdateMessage(updateStockUseCase.updateStock(stockUpdate));
    }
}
