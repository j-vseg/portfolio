package nl.fontys.sem3.individualtrack.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseBundle {
    private Long id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private List<BundlePackage> packages;
}
