package nl.fontys.sem3.individualtrack.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.sem3.individualtrack.domain.Account;
import nl.fontys.sem3.individualtrack.domain.OrderLine;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "\"order\"")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "ordered")
    private Calendar ordered;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "orderEntity", cascade = CascadeType.ALL)
    private List<OrderLineEntity> orderlines;

    @ManyToOne
    @JoinColumn(name = "account_Id", nullable = false)
    private AccountEntity accountEntity;

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof OrderEntity)) { return false; }
        OrderEntity other = (OrderEntity) obj;
        return other.id == id;
    }
}
