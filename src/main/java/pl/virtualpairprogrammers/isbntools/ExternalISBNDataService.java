package pl.virtualpairprogrammers.isbntools;

public interface ExternalISBNDataService {
    Book lookup(String isbn);
}
