package pl.virtualpairprogrammers.isbntools;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidateISBNTests {

    @Test
    public void checkAValid10DigitISBN() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("0140449116");
        assertTrue("first value", result);
        result = validator.checkISBN("0140177396");
        assertTrue("second value", result);
    }

    @Test
    public void checkAValid13DigitISBN() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("9781853260087");
        assertTrue("first value", result);
        result = validator.checkISBN("9781118749616");
        assertTrue("second value",result);
    }

    @Test
    public void tenDigitISBNNumbersEndingWithXAreValid() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("012000030X");
        assertTrue(result);
    }

    @Test
    public void checkAnInvalid10DigitISBN() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("0140449117");
        assertFalse(result);
    }

    @Test
    public void checkAnInvalid13DigitISBN() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("9781853260088");
        assertFalse(result);
    }

    @Test(expected = NumberFormatException.class)
    public void nineDigitISBNsNotAllowed() {
        ValidateISBN validate = new ValidateISBN();
        validate.checkISBN("123456789");

    }

    @Test(expected = NumberFormatException.class)
    public void lettersAreNotAllowed() {
        ValidateISBN validate = new ValidateISBN();
        validate.checkISBN("helloworld");
    }
}
