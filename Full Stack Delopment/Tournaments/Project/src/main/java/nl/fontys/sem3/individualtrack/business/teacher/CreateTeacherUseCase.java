package nl.fontys.sem3.individualtrack.business.teacher;

import nl.fontys.sem3.individualtrack.controller.teacher.CreateTeacherRequest;
import nl.fontys.sem3.individualtrack.controller.teacher.CreateTeacherResponse;

public interface CreateTeacherUseCase {
    CreateTeacherResponse createTeacher(CreateTeacherRequest request);
}
