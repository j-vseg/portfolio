package nl.fontys.sem3.individualtrack.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderLine {
    private Long id;
    private int quantity;
    private double price;
    private CourseBundle courseBundle;
}
