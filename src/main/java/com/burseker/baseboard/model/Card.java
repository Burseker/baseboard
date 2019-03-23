package com.burseker.baseboard.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "CARDS")
@Entity
public class Card {
    @Id
    @GeneratedValue(generator = "accountKeyGenerator")
    @org.hibernate.annotations.GenericGenerator(
            name = "accountKeyGenerator",
            strategy = "foreign",
            parameters =
                @org.hibernate.annotations.Parameter(
                        name = "property", value = "account"
                )
    )
    protected Long id;

    @OneToOne(optional = false)
    @PrimaryKeyJoinColumn
    protected Account account;

    @Column(length = 20, nullable = false, updatable = false)
    protected String name;

    @Column(length = 6, nullable = false, updatable = false, unique = true)
    protected String pin;

    //    protected Timestamp expiredDate;


    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
//                ", account=" + account +
                ", name='" + name + '\'' +
                ", pin='" + pin + '\'' +
                '}';
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

}
