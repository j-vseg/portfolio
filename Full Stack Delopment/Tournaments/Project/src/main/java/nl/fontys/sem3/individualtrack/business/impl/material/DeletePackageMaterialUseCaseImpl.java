package nl.fontys.sem3.individualtrack.business.impl.material;

import lombok.AllArgsConstructor;
import nl.fontys.sem3.individualtrack.business.material.DeletePackageMaterialUseCase;
import nl.fontys.sem3.individualtrack.persistence.PackageMaterialRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeletePackageMaterialUseCaseImpl implements DeletePackageMaterialUseCase {
    private final PackageMaterialRepository repository;
    @Override
    public void deleteMaterial(long id) {
        repository.deleteById(id);
    }
}
