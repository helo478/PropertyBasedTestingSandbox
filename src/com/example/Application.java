package com.example;

import java.util.function.*;

public class Application {

    public static void main(String[] args) {
        new Application().start();
    }
    
    private void start() {
        for (int i = 0; i < 100; ++i) {
            System.out.println(calculate(i) + "\n");
        }
    }

    public String calculate(int number) {
        
        Function<Integer, String> fn = number % 3 == 0 ?
            i -> i % 5 == 0 ? "FizzBuzz" : "Fizz":
            i -> i % 5 == 0 ? "Buzz" : String.valueOf(i);
       
        return fn.apply(number);
    }

}
