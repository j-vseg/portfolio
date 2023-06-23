package nl.fontys.sem3.individualtrack.controller.teacher;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTeacherRequest {
    //@NotNull
    //@Min(0)
    //private Long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotNull
    @Min(0)
    private int age;
    private String education;
    @NotBlank
    private String description;
}
