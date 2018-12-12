package tests.servlets;

import org.mockito.Mockito;

import accounts.AccountService;
import accounts.AccountServiceI;

public class AdminServletTest {
    private AccountServiceI accountService = Mockito.mock(AccountService.class);
}