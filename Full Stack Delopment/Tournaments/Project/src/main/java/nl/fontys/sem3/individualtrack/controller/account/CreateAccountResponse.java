package nl.fontys.sem3.individualtrack.controller.account;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateAccountResponse {
    private long accountId;
}
