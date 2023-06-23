package nl.fontys.sem3.individualtrack.controller.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.sem3.individualtrack.domain.Account;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAllAccountsResponse {
    private List<Account> accounts;
}
