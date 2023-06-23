package nl.fontys.sem3.individualtrack.controller.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.sem3.individualtrack.domain.Roles;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAccountRequest {
    @NotNull
    @Min(1)
    @Max(100000)
    private Long id;
    private String username;
    private String password;
    private Roles role;
    private String email;
    private String phoneNumber;
}
