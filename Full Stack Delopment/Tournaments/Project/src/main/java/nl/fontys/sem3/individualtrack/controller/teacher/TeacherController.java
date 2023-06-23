package nl.fontys.sem3.individualtrack.controller.teacher;

import lombok.AllArgsConstructor;
import nl.fontys.sem3.individualtrack.business.teacher.*;
import nl.fontys.sem3.individualtrack.domain.*;
import nl.fontys.sem3.individualtrack.controller.teacher.CreateTeacherRequest;
import nl.fontys.sem3.individualtrack.controller.teacher.CreateTeacherResponse;
import nl.fontys.sem3.individualtrack.controller.teacher.GetAllTeachersResponse;
import nl.fontys.sem3.individualtrack.controller.teacher.UpdateTeacherRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value = "/teachers")
@AllArgsConstructor
public class TeacherController {
    private final CreateTeacherUseCase createTeacherUseCase;
    private final GetAllTeachersUseCase getAllTeachersUseCase;
    private final GetTeacherUseCase getTeacherUseCase;
    private final UpdateTeacherUseCase updateTeacherUseCase;
    private final DeleteTeacherUseCase deleteTeacherUseCase;

    @PostMapping()
    public ResponseEntity<CreateTeacherResponse> createTeacher(@RequestBody @Valid CreateTeacherRequest request) {
        CreateTeacherResponse response = createTeacherUseCase.createTeacher(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping()
    public ResponseEntity<GetAllTeachersResponse> getAllTeachers() {
        return ResponseEntity.ok(getAllTeachersUseCase.getTeachers());
    }

    @GetMapping("{id}")
    public ResponseEntity<Teacher> getTeacher(@PathVariable(value = "id")long id) {
        final Optional<Teacher> teacherOptional = getTeacherUseCase.getTeacher(id);
        if (teacherOptional.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(teacherOptional.get());
    }
    @PutMapping("{id}")
    public ResponseEntity<Void> updateTeacher(@PathVariable(value = "id") long id,
                                              @RequestBody @Valid UpdateTeacherRequest request) {
        request.setId(id);
        updateTeacherUseCase.updateTeacher(request);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable int id) {
        deleteTeacherUseCase.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }
}
