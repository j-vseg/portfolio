package nl.fontys.sem3.individualtrack.persistence.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.sem3.individualtrack.domain.BundlePackage;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Entity
@Table(name = "coursebundle")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseBundleEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Length(min = 2, max = 75)
    @Column(name = "name")
    private String name;

    @NotBlank
    @Length(min = 2)
    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;

    @Column(name = "quantity")
    private int quantity;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "courseBundleEntity", cascade = {CascadeType.ALL})
    private List<BundlePackageEntity> packages;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "courseBundleEntity", cascade = {CascadeType.ALL})
    private List<OrderLineEntity> orderlines;
}
