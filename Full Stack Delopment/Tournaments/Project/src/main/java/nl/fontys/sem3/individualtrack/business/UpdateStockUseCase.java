package nl.fontys.sem3.individualtrack.business;

import nl.fontys.sem3.individualtrack.domain.CourseBundle;
import nl.fontys.sem3.individualtrack.domain.StockUpdate;

import java.util.List;

public interface UpdateStockUseCase {
    List<CourseBundle> updateStock(StockUpdate update);
}
