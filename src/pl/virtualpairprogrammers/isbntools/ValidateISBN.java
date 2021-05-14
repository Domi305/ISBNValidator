package pl.virtualpairprogrammers.isbntools;

public class ValidateISBN {

    public boolean checkISBN(String isbn) {
/*        if (isbn == "0140449116") {
            return true;
        }
        return false;*/

        int total = 0;

        for (int i = 0; i < 10; i++) {
            total = total + isbn.charAt(i) * (10 - i);
        }

        if (total % 11 == 0) {
            return true;
        } else {
            return false;
        }
    }
}
