package nl.fontys.sem3.individualtrack.persistence.entity;

import lombok.*;
import nl.fontys.sem3.individualtrack.domain.CourseBundle;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "orderline")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(1)
    @Column(name = "quantity")
    private int quantity;

    @NotNull
    @Min(0)
    @Column(name = "price")
    private double price;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity orderEntity;

    @ManyToOne
    @JoinColumn(name = "bundle_id", nullable = false)
    private CourseBundleEntity courseBundleEntity;
}
