package com.happysathya.klarna;

import java.util.Stack;

public class Calc {

  public double evaluate(String expr) {
    if (expr.trim().isEmpty()) return 0;

    Stack<Double> stack = new Stack<>();
    String[] expressionArray = expr.trim().split(" ");

    for (String part : expressionArray) {
      switch (part) {
        case "+":
          stack.push(stack.pop() + stack.pop());
          break;
        case "-":
          {
            Double lastElement = stack.pop();
            Double lastButOneElement = stack.pop();
            stack.push(lastButOneElement - lastElement);
          }
          break;
        case "*":
          stack.push(stack.pop() * stack.pop());
          break;
        case "/":
          {
            Double lastElement = stack.pop();
            Double lastButOneElement = stack.pop();
            stack.push(lastButOneElement / lastElement);
          }
          break;
        default:
          stack.push(Double.valueOf(part));
      }
    }
    return stack.pop();
  }
}
