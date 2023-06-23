package nl.fontys.sem3.individualtrack.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.sem3.individualtrack.persistence.entity.CourseBundleEntity;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "bundlepackage")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BundlePackageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "bundlePackageEntity", cascade = {CascadeType.ALL})
    private List<PackageMaterialEntity> materials;

    @ManyToOne
    @JoinColumn(name = "bundle_Id", nullable = false)
    private CourseBundleEntity courseBundleEntity;
}
