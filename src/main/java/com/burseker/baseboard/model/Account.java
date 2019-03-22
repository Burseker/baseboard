package com.burseker.baseboard.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
public class Account {
    @Column(nullable = false)
    protected String name;

    @Column(nullable = false)
    protected BigDecimal balance;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(name, account.name) &&
                Objects.equals(balance, account.balance);
    }

    @Override
    public String toString() {
        return String.format("Acc name: %s, acc balance: %s", name, balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, balance);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
