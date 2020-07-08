package com.happysathya.klarna;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class CreditCard {

  public static String maskify(String creditCardNumber) {
    return Optional.of(creditCardNumber)
        .map(String::trim)
        .filter(s -> s.length() >= 6)
        .map(CreditCard::mask)
        .orElse(creditCardNumber);
  }

  private static String mask(String validatedCreditCardNumber) {
    String regex = "^(.{1})(.*)(.{4})$";
    Matcher matcher = Pattern.compile(regex).matcher(validatedCreditCardNumber);
    if (matcher.matches()) {
      return String.format(
          "%s%s%s", matcher.group(1), matcher.group(2).replaceAll("[0-9]", "#"), matcher.group(3));
    }
    return validatedCreditCardNumber;
  }
}
