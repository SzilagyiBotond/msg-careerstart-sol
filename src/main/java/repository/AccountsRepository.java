package repository;

import domain.AccountModel;

public class AccountsRepository {
    public static final InMemoryDatabase<AccountModel> INSTANCE = new InMemoryDatabase<>();
}
