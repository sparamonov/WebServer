package accounts;

public class AccServiceController implements AccServiceControllerMBean {
    private final AccountServiceI accountService;

    public AccServiceController(AccountServiceI accountService) {
        this.accountService = accountService;
    }

    @Override
    public int getUsers() {
        return accountService.getUsersCount();
    }

    @Override
    public int getUsersLimit() {
        return accountService.getUsersLimit();
    }

    @Override
    public void setUsersLimit(int limit) {
        accountService.setUsersLimit(limit);
    }
}