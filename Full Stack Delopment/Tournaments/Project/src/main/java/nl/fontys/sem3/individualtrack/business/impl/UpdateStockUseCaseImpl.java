package nl.fontys.sem3.individualtrack.business.impl;

import lombok.RequiredArgsConstructor;
import nl.fontys.sem3.individualtrack.business.UpdateStockUseCase;
import nl.fontys.sem3.individualtrack.business.exception.InvalidException;
import nl.fontys.sem3.individualtrack.business.impl.bundle.CourseBundleConverter;
import nl.fontys.sem3.individualtrack.domain.CourseBundle;
import nl.fontys.sem3.individualtrack.domain.StockUpdate;
import nl.fontys.sem3.individualtrack.persistence.CourseBundleRepository;
import nl.fontys.sem3.individualtrack.persistence.entity.CourseBundleEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateStockUseCaseImpl implements UpdateStockUseCase {
    private final CourseBundleRepository repository;
    @Override
    public List<CourseBundle> updateStock(StockUpdate update) {
        Optional<CourseBundleEntity> savedBundle = repository.findById(update.getBundleId());

        if (savedBundle.isEmpty()) { throw new InvalidException("COURSE_BUNDLE_ID_INVALID"); }

        updateFields(savedBundle.get(), update);

        return repository.findAll()
                .stream()
                .map(CourseBundleConverter::convert)
                .toList();
    }
    private void updateFields(CourseBundleEntity entity, StockUpdate update) {
        CourseBundleEntity courseBundle = CourseBundleEntity.builder()
                .id(update.getBundleId())
                .name(entity.getName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .quantity(update.getQuantity())
                .build();
        repository.save(courseBundle);
    }
}
