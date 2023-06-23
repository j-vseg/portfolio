package nl.fontys.sem3.individualtrack.persistence.entity;

import lombok.*;
import nl.fontys.sem3.individualtrack.domain.Roles;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "\"account\"")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Length(min = 2, max = 25)
    @Column(name = "username")
    private String username;

    @NotBlank
    @Length(min = 2, max = 100)
    @Column(name = "password")
    private String password;

    @NotNull
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Roles role;

    @Length(min = 2, max = 25)
    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Length(min = 2, max = 50)
    @Column(name = "email")
    private String email;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "accountEntity", cascade = {CascadeType.ALL})
    private List<OrderEntity> orders;

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AccountEntity)) { return false; }
        AccountEntity other = (AccountEntity) obj;
        return other.id == id;
    }
}
