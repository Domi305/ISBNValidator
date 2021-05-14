package pl.virtualpairprogrammers.isbntools;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

public class ExampleTest {

    @Test
    public void exampleTest() {
        //fail("Not yet implemented");
    }

    @Test
    public void checkAValidISBN() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("0140449116");
        assertTrue("first value", result);
        result = validator.checkISBN("0140177396");
        assertTrue("second value", result);
    }

    @Test
    public void checkAnInvalidISBN() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("0140449117");
        assertFalse(result);
    }
}
