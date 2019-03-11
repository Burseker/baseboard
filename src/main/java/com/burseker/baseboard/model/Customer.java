package com.burseker.baseboard.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
//@Table(name = "customer",
//    indexes = {@Index(
//            name = "j_passport_idx",
//            columnList = "passport_id", unique = true
//    )},
//    uniqueConstraints = {@UniqueConstraint(
//            columnNames = {"cust_id", "passport_id"}
//    )}
//)
@Table(name = "customers",
    indexes = {@Index(
            name = "j_passport_idx",
            columnList = "passport_id", unique = true
    )}
)
//@Table(name = "customer")
public class Customer {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = Constants.ID_GENERATOR)
    protected Long id;

    @Column(name = "fname", nullable = false, updatable = true)
    protected String fName;

    @Column(name = "lname", nullable = false, updatable = true)
    protected String lName;

    @Column(name = "passport_id", length = 10, nullable = false)
    protected String passportId;

    @ElementCollection
    @CollectionTable(
            name = "ACCOUNTS",
            joinColumns = @JoinColumn( name = "ACC_ID"))
    @Column(name = "ACCOUNTS_COLUMN")
    protected Set<Account> accounts = new HashSet<Account>();

    @Override
    public String toString() {
        return String.format("Cust id: %d, FName: %s, LName: %s, Passport: %s", id, fName, lName, passportId);
    }

    public int getCust_id() {
        return id.intValue();
    }

//    public void setCust_id(int cust_id) {
//        this.cust_id = cust_id;
//    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getPassportId() {
        return passportId;
    }

    public void setPassportId(String passportId) {
        this.passportId = passportId;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }
}
