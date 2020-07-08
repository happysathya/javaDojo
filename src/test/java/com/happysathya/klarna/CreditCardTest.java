package com.happysathya.klarna;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreditCardTest {

  @Test
  void maskify() {
    assertEquals("5###########0694", CreditCard.maskify("5512103073210694"));
    assertEquals("54321", CreditCard.maskify("54321"));
    assertEquals("4###########5616", CreditCard.maskify("4556364607935616"));
    assertEquals("4###-####-####-5616", CreditCard.maskify("4556-3646-0793-5616"));
    assertEquals("6######5616", CreditCard.maskify("64607935616"));
    assertEquals("ABCD-EFGH-IJKLM-NOPQ", CreditCard.maskify("ABCD-EFGH-IJKLM-NOPQ"));
    assertEquals("A#######BCDEFG89HI", CreditCard.maskify("A1234567BCDEFG89HI"));
    assertEquals("12345", CreditCard.maskify("12345"));
    assertEquals("", CreditCard.maskify(""));
    assertEquals("Skippy", CreditCard.maskify("Skippy"));
  }
}