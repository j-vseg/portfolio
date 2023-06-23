package nl.fontys.sem3.individualtrack.business.impl.account;

import nl.fontys.sem3.individualtrack.domain.Account;
import nl.fontys.sem3.individualtrack.persistence.entity.AccountEntity;

public class AccountConverter {
    public AccountConverter() {}
    public static Account convert(AccountEntity entity) {
        return Account.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .role(entity.getRole())
                .phoneNumber(entity.getPhoneNumber())
                .email(entity.getEmail())
                .build();
    }
    public static AccountEntity convert(Account account) {
        return AccountEntity.builder()
                .id(account.getId())
                .username(account.getUsername())
                .password(account.getPassword())
                .role(account.getRole())
                .phoneNumber(account.getPhoneNumber())
                .email(account.getEmail())
                .build();
    }
}
