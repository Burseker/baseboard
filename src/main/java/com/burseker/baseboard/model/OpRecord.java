package com.burseker.baseboard.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "OPRECORDS")
@Entity
public class OpRecord {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false)
    protected String memo;

    @Column(nullable = false)
    protected BigDecimal summ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    @org.hibernate.annotations.CreationTimestamp
    protected Date createdOn;

    @Column(nullable = false)
    protected String srcAccount;

    @Column(nullable = false)
    protected String dstAccount;

    @Column
    protected String dstAliasName;
}
