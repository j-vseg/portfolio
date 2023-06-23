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
public class BundlePackage {
    private Long id;
    private String name;
    private String description;
    private double price;
    private List<PackageMaterial> materials;

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (!(o instanceof  BundlePackage))
            return false;

        BundlePackage other = (BundlePackage)o;
        return other.id == id;

    }
}
