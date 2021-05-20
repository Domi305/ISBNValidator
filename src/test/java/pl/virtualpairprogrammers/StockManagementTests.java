package pl.virtualpairprogrammers;

import org.junit.Test;
import pl.virtualpairprogrammers.isbntools.Book;
import pl.virtualpairprogrammers.isbntools.ExternalISBNDataService;
import pl.virtualpairprogrammers.isbntools.StockManager;

import static org.junit.Assert.*;

public class StockManagementTests {

    @Test
    public void testCanGetACorrectLocatorCode() {
        ExternalISBNDataService testService = new ExternalISBNDataService() {

            @Override
            public Book lookup(String isbn) {
                return new Book(isbn, "CCNA 200-301 Official Cert Guide, Volume 1", "W. Odom");
            }
        };

        StockManager stockManager = new StockManager();
        stockManager.setService(testService);
        String isbn = "0135792738";
        String locatorCode = stockManager.getLocatorCode(isbn);
        assertEquals("2738W7", locatorCode); //last 4 digits of isbn number + first letter of author name + number of words in the title
    }

}