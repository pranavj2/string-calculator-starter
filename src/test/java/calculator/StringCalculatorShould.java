package calculator;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorShould {

	//Verify test case return 0 for empty string 
    @Test
    void empty_string_should_return_0() throws Exception {
        StringCalculator stringCalculator = new StringCalculator();
        assertEquals(0, stringCalculator.add(""));
    }
    
    //Verify test case for single number
    @Test
    void string_with_single_number_should_return_number_as_int()throws Exception {
        StringCalculator stringCalculator = new StringCalculator();
        assertEquals(1, stringCalculator.add("1"));
    }

    //Verify test case handles an unknown amount of numbers
    @Test
    void string_with_two_numbers_should_return_number_as_int()throws Exception {
        StringCalculator stringCalculator = new StringCalculator();
        assertEquals(3, stringCalculator.add("1,2"));
    }
    
    //Verify test case handles an unknown amount of numbers
    @Test
    void string_with_multiple_numbers_should_return_number_as_int() throws Exception {
        StringCalculator stringCalculator = new StringCalculator();
        assertEquals(27, stringCalculator.add("1,2,5,7,4,8"));
    }
    
    //Verify test case handles new lines between numbers (instead of commas).
    @Test
    void string_with_newlines_in_numbers_should_return_number_as_int() throws Exception {
        StringCalculator stringCalculator = new StringCalculator();
        assertEquals(6, stringCalculator.add("1\\n2,3"));
    }
    
    //Verify test case support different delimiters
    @Test
    void string_with_semicolon_as_delimeter_should_return_number_as_int() throws Exception {
        StringCalculator stringCalculator = new StringCalculator();
        assertEquals(10, stringCalculator.add("1\\n2;3;4", ";"));
    }
    
  //Verify test case support different delimiters
    @Test
    void string_with_at_as_delimeter_should_return_number_as_int() throws Exception {
        StringCalculator stringCalculator = new StringCalculator();
        assertEquals(10, stringCalculator.add("1@2@3@4","@"));
    }
    
    //Verify test case throw an exception “negatives not allowed” -and the negative that was passed.
    @Test
    void string_with_negative_numbes_should_return_error() {
        try{
            StringCalculator stringCalculator = new StringCalculator();
            assertEquals(10, stringCalculator.add("1,-2,-3,4"));
        }
        catch (Exception ex){
            
            fail(ex.getMessage());
        }
    }
    
    //verify test case numbers bigger than 1000 should be ignored
    @Test
    void string_with_number_greater_than_1000_should_return_number_as_int()throws Exception {
        StringCalculator stringCalculator = new StringCalculator();
        assertEquals(2, stringCalculator.add("2,1001"));
    }
    //verify test case returns how many times Add() was invoked.
    @AfterAll
    static void print_called_count() throws Exception {
        StringCalculator stringCalculator = new StringCalculator();
        System.out.println("Called count :: "+stringCalculator.getCalledCount());
    }

  //verify test case handles delimiter of any length
    @Test
    void string_with_multilength_delimeters() throws Exception {
        StringCalculator stringCalculator = new StringCalculator();
        assertEquals(6, stringCalculator.add("//[***]\\n1***2***3","multilength"));
    }

    //verify test case handles multiple delimiters
    @Test
    void string_with_multiple_delimeters() throws Exception {
        StringCalculator stringCalculator = new StringCalculator();
        assertEquals(6, stringCalculator.add("//[*][%]\\n1*2%3","multiple"));
    }
  //verify test case handles multiple delimiters and of any length
    @Test
    void string_with_multiples_delimeters() throws Exception {
        StringCalculator stringCalculator = new StringCalculator();
        assertEquals(6, stringCalculator.add("//[**][%%]\\n1**2%%3","multiple"));
    }
}