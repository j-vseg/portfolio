package nl.fontys.sem3.individualtrack.business.impl.material;

import lombok.AllArgsConstructor;
import nl.fontys.sem3.individualtrack.business.material.GetPackageMaterialUseCase;
import nl.fontys.sem3.individualtrack.domain.PackageMaterial;
import nl.fontys.sem3.individualtrack.persistence.PackageMaterialRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetPackageMaterialUseCaseImpl implements GetPackageMaterialUseCase {
    private final PackageMaterialRepository repository;
    @Override
    public Optional<PackageMaterial> getMaterial(long id) {
        return repository.findById(id)
                .map(PackageMaterialConverter::convert);
    }
}
