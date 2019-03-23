package com.burseker.baseboard;

import org.junit.Test;

import java.math.BigDecimal;

public class OtherTests {

    @Test
    public void otherTests() {
        BigDecimal bigDecimal1 = new BigDecimal(120);
        BigDecimal bigDecimal2 = new BigDecimal(0);
        BigDecimal bigDecimal3 = new BigDecimal(0);

        System.out.println(bigDecimal1);
        System.out.println(bigDecimal2);
        System.out.println(bigDecimal3);

        System.out.println("------------------------------------");
        bigDecimal2 = bigDecimal2.add(bigDecimal1);
        System.out.println(bigDecimal1);
        System.out.println(bigDecimal2);
        System.out.println(bigDecimal3);

        System.out.println("------------------------------------");
        bigDecimal2 = bigDecimal2.add(bigDecimal1);
        System.out.println(bigDecimal1);
        System.out.println(bigDecimal2);
        System.out.println(bigDecimal3);

        System.out.println("------------------------------------");
    }
}
