package nl.fontys.sem3.individualtrack.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccessToken {
    private String subject;
    private Roles role;
    private Long accountId;

    @JsonIgnore
    public boolean hasRole(Roles role) {
        if (role == null) {return false;}
        if (this.role.equals(role)) {return true;}
        return  false;
    }
}
