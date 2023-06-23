package nl.fontys.sem3.individualtrack.business.impl.material;

import lombok.AllArgsConstructor;
import nl.fontys.sem3.individualtrack.business.IdValidator;
import nl.fontys.sem3.individualtrack.business.exception.InvalidException;
import nl.fontys.sem3.individualtrack.domain.PackageMaterial;
import nl.fontys.sem3.individualtrack.persistence.PackageMaterialRepository;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PackageMaterialIdValidatorImpl implements IdValidator<PackageMaterial> {
    private final PackageMaterialRepository repository;
    @Override
    public void ValidateId(Long materialId) {
        if (materialId == null || !repository.existsById(materialId)) {
            throw new InvalidException("MATERIAL_ID_INVALID");
        }
    }
}
