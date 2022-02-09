package com.kiyotakeshi.junitPlayground.oreilly.scratch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

public class AssertTest {

    private class InsufficientFundsException extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public InsufficientFundsException(String message) {
        }
    }

    class Account {
        int balance;
        String name;

        public Account(String name) {
            this.name = name;
        }

        void deposit(int dollars) {
            balance += dollars;
        }

        void withdraw(int dollars) {
            if (balance < dollars) {
                throw new InsufficientFundsException("balance only " + balance);
            }
            balance -= dollars;
        }

        public int getBalance() {
            return balance;
        }

        public String getName() {
            return name;
        }

        public boolean hasPositiveBalance() {
            return balance > 0;
        }
    }

    class Customer {
        List<Account> accounts = new ArrayList<>();

        void add(Account account) {
            accounts.add(account);
        }

        Iterator<Account> getAccounts() {
            return accounts.iterator();
        }
    }

    private Account account;

    @BeforeEach
    void setUp() {
        // インスタンス変数として参照を保持
        account = new Account("Account name");
    }

    @Test
    void hasPositiveBalance() {
        // set up(arrange)
        account.deposit(50);

        // exercise(act)
        // verify(assert)
        Assertions.assertTrue(account.hasPositiveBalance());
        assertThat(account.getBalance() > 0).isTrue();
    }

    @Test
    void depositIncreasesBalance() {
        // set up(arrange)
        int initialBalance = account.getBalance();
        account.deposit(100);

        // exercise(act)
        // verify(assert)
        Assertions.assertTrue(account.getBalance() > initialBalance);
        assertThat(account.getBalance()).isEqualTo(100);
    }

    @Test
    void assertAccountName() {
        // verify(assert)
        Assertions.assertTrue(account.getName().startsWith("Account"));
        assertThat(account.getName())
                .startsWith("Account")
                .endsWith("name")
                .isNotEqualTo("Account name2")
                .isNotNull()
                .isOfAnyClassIn(String.class);
    }

    @Test
    void items() {
        // set up(arrange)
        ArrayList<String> names = new ArrayList<>();
        names.add("mike");
        names.add("popcorn");
        names.add("yamada");

        // verify(assert)
        // @see https://assertj.github.io/doc/#overview-what-is-assertj
        assertThat(names).contains("mike");
        assertThat(names).containsAnyOf("mike", "popcorn", "jackson");
    }

    @Test
    void location() {
        // verify(assert)
        assertThat(2.32 * 3).isCloseTo(6.96,within(0.05));
    }
}
