package com.example;

import org.junit.*;

import net.jqwik.api.*;

public class FizzBuzzTest {
    
    private Application sut = new Application();
    
    @Before
    public void setUp() {
        sut = new Application();
    }
    
    @Test
    public void testCalculate_0_shouldNotError() {
        
        final int input = 0;
        sut.calculate(input);
    }
    
    @Provide
    public Arbitrary<Integer> divisibleBy3() {
        return Arbitraries.integers().filter(i -> i % 3 == 0);
    }
    
    @Property(tries = 10)
    boolean testCalculate_divisibleBy3_shouldReturnFizz(@ForAll("divisibleBy3") int input) {
        
        final String actual = sut.calculate(input);
        if (actual == null) return false;
        return actual.startsWith("Fizz");
    }
    
    @Provide
    public Arbitrary<Integer> divisibleBy5() {
        return Arbitraries.integers().filter(i -> i % 5 == 0);
    }
    
    @Property
    boolean testCalculate_divisibleBy5_shouldReturnBuzz(@ForAll("divisibleBy5") int input) {
        
        final String actual = sut.calculate(input);
        if (actual == null) return false;
        return actual.endsWith("Buzz");
    }
    
    @Provide
    public Arbitrary<Integer> divisibleBy3And5() {
        return Arbitraries.integers().filter(i -> i % 3 == 0 && i % 5 == 0);
    }
    
    @Property
    boolean testCalculate_divisibleBy3And5_shouldReturnFizzBuzz(@ForAll("divisibleBy3And5") int input) {
        
        final String expected = "FizzBuzz";
        final String actual = sut.calculate(input);
        return expected.equals(actual);
    }
    
    @Provide
    public Arbitrary<Integer> divisibleByNeither3Nor5() {
        return Arbitraries.integers().filter(i -> i % 3 != 0 && i % 5 != 0);
    }
    
    @Property
    boolean testCalculate_divisibleByNeither3Nor5_shouldReturnNumber(@ForAll("divisibleByNeither3Nor5") int input) {
        
        final String expected =  String.valueOf(input);
        final String actual = sut.calculate(input);
        return expected.equals(actual);
    }

}
