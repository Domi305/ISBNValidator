package pl.virtualpairprogrammers;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import pl.virtualpairprogrammers.isbntools.Book;
import pl.virtualpairprogrammers.isbntools.ExternalISBNDataService;
import pl.virtualpairprogrammers.isbntools.StockManager;

import static org.junit.Assert.*;

public class StockManagementTests {

    ExternalISBNDataService testWebService;
    StockManager stockManager;
    ExternalISBNDataService testDatabaseService;

    @Before
    public void setup() {
        testWebService = mock(ExternalISBNDataService.class);
        stockManager = new StockManager();
        stockManager.setWebService(testWebService);
        testDatabaseService = mock(ExternalISBNDataService.class);
        stockManager.setDatabaseService(testDatabaseService);
    }

    @Test
    public void testCanGetACorrectLocatorCode() {
        when(testWebService.lookup(anyString())).thenReturn(new Book("0135792738", "CCNA 200-301 Official Cert Guide, Volume 1", "W. Odom"));
        when(testDatabaseService.lookup(anyString())).thenReturn(null);

        String isbn = "0135792738";
        String locatorCode = stockManager.getLocatorCode(isbn);
        assertEquals("2738W7", locatorCode); //last 4 digits of isbn number + first letter of author name + number of words in the title
    }

    @Test
    public void databaseIsUsedIfDataIsPresent() {

        when(testDatabaseService.lookup("0135792738"))
                .thenReturn(new Book("0135792738", "CCNA", "Odom"));

        String isbn = "0135792738";
        String locatorCode = stockManager.getLocatorCode(isbn);

        verify(testDatabaseService, times(1)).lookup("0135792738");
        verify(testWebService, never()).lookup(anyString());
    }

    @Test
    public void webServiceIsUsedIfDataIsNotPresentInDatabase() {

        when(testDatabaseService.lookup("0135792738"))
                .thenReturn(null);

        when(testWebService.lookup("0135792738"))
                .thenReturn(new Book("0135792738", "CCNA", "Odom"));

        String isbn = "0135792738";
        String locatorCode = stockManager.getLocatorCode(isbn);

        verify(testDatabaseService, times(1)).lookup("0135792738");
        verify(testWebService).lookup("0135792738");
    }

}