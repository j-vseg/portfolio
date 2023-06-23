package nl.fontys.sem3.individualtrack.business.impl.teacher;

import lombok.AllArgsConstructor;
import nl.fontys.sem3.individualtrack.business.teacher.GetAllTeachersUseCase;
import nl.fontys.sem3.individualtrack.controller.teacher.GetAllTeachersResponse;
import nl.fontys.sem3.individualtrack.domain.Teacher;
import nl.fontys.sem3.individualtrack.persistence.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllTeachersUseCaseImpl implements GetAllTeachersUseCase {
    private final Repository<Teacher> repository;
    @Override
    public GetAllTeachersResponse getTeachers() {
        List<Teacher> teachers = repository.findAll();
        return GetAllTeachersResponse.builder()
                .teachers(teachers)
                .build();
    }
}
