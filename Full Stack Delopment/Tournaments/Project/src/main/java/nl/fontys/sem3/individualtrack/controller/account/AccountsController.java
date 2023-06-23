package nl.fontys.sem3.individualtrack.controller.account;

import lombok.AllArgsConstructor;
import nl.fontys.sem3.individualtrack.business.account.*;
import nl.fontys.sem3.individualtrack.configuration.security.isauthenticated.IsAuthenticated;
import nl.fontys.sem3.individualtrack.domain.*;
import nl.fontys.sem3.individualtrack.controller.account.CreateAccountRequest;
import nl.fontys.sem3.individualtrack.controller.account.CreateAccountResponse;
import nl.fontys.sem3.individualtrack.controller.account.GetAllAccountsResponse;
import nl.fontys.sem3.individualtrack.controller.account.UpdateAccountRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false" )
@RestController
@AllArgsConstructor
@RequestMapping("/accounts")
public class AccountsController {
    private final CreateAccountUseCase createAccountUseCase;
    private final GetAllAccountsUseCase getAllAccountsUseCase;
    private final GetAccountUseCase getAccountUseCase;
    private final GetAccountByUsernameUseCase getAccountByUsernameUseCase;
    private final UpdateAccountUseCase updateAccountUseCase;
    private final DeleteAccountUseCase deleteAccountUseCase;

    @PostMapping()
    public ResponseEntity<CreateAccountResponse> createAccount(@RequestBody @Valid CreateAccountRequest request) {
        Account newAccount = Account.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .role(request.getRole())
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .build();

        CreateAccountResponse response = CreateAccountResponse.builder()
                .accountId(createAccountUseCase.createAccount(newAccount).getId())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @RolesAllowed({"ROLE_Owner"})
    @IsAuthenticated
    @GetMapping()
    public ResponseEntity<GetAllAccountsResponse> getAllAccounts() {
        GetAllAccountsResponse response =  GetAllAccountsResponse.builder()
                .accounts(getAllAccountsUseCase.getAllAccounts())
                .build();
        return ResponseEntity.ok(response);
    }

   @RolesAllowed({"ROLE_Owner", "ROLE_Customer"})
    @IsAuthenticated
    @GetMapping(value = "/getById/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable long id) {
        final Optional<Account> accountOptional = getAccountUseCase.getAccount(id);
        if (accountOptional.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(accountOptional.get());
    }

    @RolesAllowed({"ROLE_Owner"})
    @IsAuthenticated
    //@GetMapping(value = "/accounts", params = {"username"} )
    @GetMapping("/getByUsername/{username}")
    public ResponseEntity<Account> getAccountByUsername(@PathVariable String username) {
        final Optional<Account> accountOptional = getAccountByUsernameUseCase.getAccount(username);
        if (accountOptional.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(accountOptional.get());
    }

    @RolesAllowed({"ROLE_Owner", "ROLE_Customer", "ROLE_Teacher"})
    @IsAuthenticated
    @PutMapping("{id}")
    public ResponseEntity<Void> updateAccount(@PathVariable(value = "id")long id, @RequestBody @Valid UpdateAccountRequest request) {
        request.setId(id);
        Account account = Account.builder()
                .id(request.getId())
                .username(request.getUsername())
                .password(request.getPassword())
                .role(request.getRole())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .build();

        updateAccountUseCase.updateAccount(account);
        return ResponseEntity.noContent().build();
    }
    @RolesAllowed({"ROLE_Owner"})
    @IsAuthenticated
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable(value = "id")long id) {
        deleteAccountUseCase.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
}
