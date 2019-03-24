package com.burseker.baseboard.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Table(name = "ACCOUNTS")
@Entity
public class Account {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUST_ID", nullable = false)
    protected Customer customer;

    @Column(nullable = false)
    protected String name;

    @Column(nullable = false)
    protected BigDecimal balance;

    @OneToOne(
            mappedBy = "account",
            cascade = CascadeType.PERSIST
    )
    protected Card card;

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
        return String.format("Acc id: %s, Acc name: %s, acc balance: %s", id, name, balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, balance);
    }

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
