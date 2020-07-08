package com.happysathya.klarna;

public class Challenge {
  public static String numberToOrdinal(Integer number) {
    if (number == 0)
      return "0";

    switch (mod(number, 100)) {
      case 11:
      case 12:
      case 13:
        return String.format("%dth", number);
      default:
        switch (mod(number, 10)) {
          case 1:
            return String.format("%dst", number);
          case 2:
            return String.format("%dnd", number);
          case 3:
            return String.format("%drd", number);
          default:
            return String.format("%dth", number);
        }
    }
  }

  private static int mod(Integer number, Integer modBy) {
    return number % modBy;
  }
}
