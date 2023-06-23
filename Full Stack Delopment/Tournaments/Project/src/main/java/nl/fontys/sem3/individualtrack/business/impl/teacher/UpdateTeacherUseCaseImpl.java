package nl.fontys.sem3.individualtrack.business.impl.teacher;

import lombok.AllArgsConstructor;
import nl.fontys.sem3.individualtrack.business.IdValidator;
import nl.fontys.sem3.individualtrack.business.exception.InvalidException;
import nl.fontys.sem3.individualtrack.business.teacher.UpdateTeacherUseCase;
import nl.fontys.sem3.individualtrack.domain.Teacher;
import nl.fontys.sem3.individualtrack.controller.teacher.UpdateTeacherRequest;
import nl.fontys.sem3.individualtrack.persistence.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateTeacherUseCaseImpl implements UpdateTeacherUseCase {
    private final Repository<Teacher> repository;
    private final IdValidator<Teacher> idValidator;
    @Override
    public void updateTeacher(UpdateTeacherRequest request) {
        Optional<Teacher> teachers = repository.findById(request.getId());
        if (teachers.isEmpty())
            throw new InvalidException("TEACHER_ID_INVALID");

        idValidator.ValidateId(request.getId());

        Teacher teacher = teachers.get();
        updateFields(request, teacher);
    }
    private void updateFields(UpdateTeacherRequest request, Teacher teacher) {
        teacher.setFirstName(request.getFirstName());
        teacher.setLastName(request.getLastName());
        teacher.setAge(request.getAge());
        teacher.setEducation(request.getEducation());
        teacher.setDescription(request.getDescription());
        repository.save(teacher);
    }
}
