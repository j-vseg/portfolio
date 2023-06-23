package nl.fontys.sem3.individualtrack.business.impl.teacher;

import lombok.AllArgsConstructor;
import nl.fontys.sem3.individualtrack.business.teacher.CreateTeacherUseCase;
import nl.fontys.sem3.individualtrack.controller.teacher.CreateTeacherRequest;
import nl.fontys.sem3.individualtrack.controller.teacher.CreateTeacherResponse;
import nl.fontys.sem3.individualtrack.domain.Teacher;
import nl.fontys.sem3.individualtrack.persistence.Repository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateTeacherUseCaseImpl implements CreateTeacherUseCase {
    private final Repository<Teacher> repository;
    @Override
    public CreateTeacherResponse createTeacher(CreateTeacherRequest request) {
        Teacher savedTeacher = saveNewTeacher(request);
        return CreateTeacherResponse.builder()
                .teacherId(savedTeacher.getId())
                .build();
    }
    private Teacher saveNewTeacher(CreateTeacherRequest request) {
        Teacher newTeacher = Teacher.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .age(request.getAge())
                .education(request.getEducation())
                .description(request.getDescription())
                .build();
        return repository.save(newTeacher);
    }
}
