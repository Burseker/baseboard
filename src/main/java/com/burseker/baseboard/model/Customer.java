package com.burseker.baseboard.model;

import javax.persistence.*;

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
@Table(name = "customer",
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
    private int cust_id;

    @Column(name = "fname", nullable = false, updatable = true)
    private String fName;

    @Column(name = "lname", nullable = false, updatable = true)
    private String lName;

    @Column(name = "passport_id", length = 10, nullable = false)
    private String passportId;

    @Override
    public String toString() {
        return String.format("Cust id: %d, FName: %s, LName: %s, Passport: %s", cust_id, fName, lName, passportId);
    }

    public int getCust_id() {
        return cust_id;
    }

    public void setCust_id(int cust_id) {
        this.cust_id = cust_id;
    }

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
}
