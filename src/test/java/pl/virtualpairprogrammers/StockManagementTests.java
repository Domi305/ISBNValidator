package pl.virtualpairprogrammers;

import static org.mockito.Mockito.*;

import org.junit.Test;
import pl.virtualpairprogrammers.isbntools.Book;
import pl.virtualpairprogrammers.isbntools.ExternalISBNDataService;
import pl.virtualpairprogrammers.isbntools.StockManager;

import static org.junit.Assert.*;

public class StockManagementTests {

    @Test
    public void testCanGetACorrectLocatorCode() {

        ExternalISBNDataService testWebService = new ExternalISBNDataService() {

            @Override
            public Book lookup(String isbn) {
                return new Book(isbn, "CCNA 200-301 Official Cert Guide, Volume 1", "W. Odom");
            }
        };

        ExternalISBNDataService testDatabaseService = new ExternalISBNDataService() {
            @Override
            public Book lookup(String isbn) {
                return null;
            }
        };

        StockManager stockManager = new StockManager();
        stockManager.setWebService(testWebService);
        stockManager.setDatabaseService(testDatabaseService);
        String isbn = "0135792738";
        String locatorCode = stockManager.getLocatorCode(isbn);
        assertEquals("2738W7", locatorCode); //last 4 digits of isbn number + first letter of author name + number of words in the title
    }

    @Test
    public void databaseIsUsedIfDataIsPresent() {
        ExternalISBNDataService databaseService = mock(ExternalISBNDataService.class);
        ExternalISBNDataService webService = mock(ExternalISBNDataService.class);

        when(databaseService.lookup("0135792738"))
                .thenReturn(new Book("0135792738", "CCNA", "Odom"));

        StockManager stockManager = new StockManager();
        stockManager.setWebService(webService);
        stockManager.setDatabaseService(databaseService);

        String isbn = "0135792738";
        String locatorCode = stockManager.getLocatorCode(isbn);
        verify(databaseService, times(1)).lookup("0135792738");
        verify(webService, never()).lookup(anyString());
    }

    @Test
    public void webServiceIsUsedIfDataIsNotPresentInDatabase() {
        ExternalISBNDataService databaseService = mock(ExternalISBNDataService.class);
        ExternalISBNDataService webService = mock(ExternalISBNDataService.class);

        when(databaseService.lookup("0135792738"))
                .thenReturn(null);

        when(webService.lookup("0135792738"))
                .thenReturn(new Book("0135792738", "CCNA", "Odom"));

        StockManager stockManager = new StockManager();
        stockManager.setWebService(webService);
        stockManager.setDatabaseService(databaseService);

        String isbn = "0135792738";
        String locatorCode = stockManager.getLocatorCode(isbn);

        verify(databaseService, times(1)).lookup("0135792738");
        verify(webService).lookup("0135792738");
    }

}