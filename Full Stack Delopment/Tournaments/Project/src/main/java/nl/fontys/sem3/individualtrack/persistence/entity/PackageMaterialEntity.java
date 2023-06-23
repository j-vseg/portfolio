package nl.fontys.sem3.individualtrack.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.sem3.individualtrack.domain.BundlePackage;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "packagematerial")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PackageMaterialEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Length(min = 2, max = 125)
    @Column(name = "material")
    private String material;

    @ManyToOne(optional = false)
    @JoinColumn(name = "package_Id", nullable = false)
    private BundlePackageEntity bundlePackageEntity;

}
