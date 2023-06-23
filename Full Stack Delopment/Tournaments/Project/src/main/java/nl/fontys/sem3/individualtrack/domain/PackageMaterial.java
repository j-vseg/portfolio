package nl.fontys.sem3.individualtrack.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PackageMaterial {
    private Long id;
    private String material;

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (!(o instanceof  PackageMaterial))
            return false;

        PackageMaterial other = (PackageMaterial)o;
        return other.id == id;

    }
}
