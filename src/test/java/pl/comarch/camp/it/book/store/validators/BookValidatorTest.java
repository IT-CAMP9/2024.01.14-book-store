package pl.comarch.camp.it.book.store.validators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.comarch.camp.it.book.store.exceptions.BookValidationException;
import pl.comarch.camp.it.book.store.model.Book;
import pl.comarch.camp.it.book.store.model.User;

public class BookValidatorTest {

    @Test
    public void validateCorrectTitleTest() {
        String title = "Tytul";

        BookValidator.validateTitle(title);
    }

    @Test
    public void validateEmptyTitleTest() {
        final String title = "";

        Assertions.assertThrows(BookValidationException.class,
                () -> BookValidator.validateTitle(title));
    }

    @Test
    public void validateSimpleAuthorTest() {
        String author = "Janusz Kowalski";

        BookValidator.validateAuthor(author);
    }

    @Test
    public void validateTwoSurnamesAuthorTest() {
        String author = "Janusz Kowalski Malinowski";

        BookValidator.validateAuthor(author);
    }

    @Test
    public void validateMultipleAuthorsTest() {
        String author = "Janusz Kowalski, Zbyszek Malinowski";

        BookValidator.validateAuthor(author);
    }

    @Test
    public void validateMultipleAuthorsWhenOneHaveTwoSurnamesTest() {
        String author = "Janusz Kowalski, Zbyszek Malinowski-Jakis";

        BookValidator.validateAuthor(author);
    }

    @Test
    public void validateLowerCaseNameTest() {
        String author = "janusz Kowalski";

        Assertions.assertThrows(BookValidationException.class,
                () -> BookValidator.validateAuthor(author));
    }

    @Test
    public void validateLowerCaseSurnameTest() {
        String author = "Janusz kowalski";

        Assertions.assertThrows(BookValidationException.class,
                () -> BookValidator.validateAuthor(author));
    }

    @Test
    public void validateTooShortNameTest() {
        String author = "X Kowalski";

        Assertions.assertThrows(BookValidationException.class,
                () -> BookValidator.validateAuthor(author));
    }

    @Test
    public void validateCorrectIsbn() {
        String isbn = "978-83-283-9783-5";

        BookValidator.validateISBN(isbn);
    }

    @Test
    public void validateIncorrectCountryCodeTest() {
        String isbn = "123-83-283-9783-5";

        Assertions.assertThrows(BookValidationException.class,
                () -> BookValidator.validateISBN(isbn));
    }

    @Test
    public void validateCorrectPriceTest() {
        double price = 29.99;

        BookValidator.validatePrice(price);
    }

    @Test
    public void validateNegativePriceTest() {
        double price = -3.00;

        Assertions.assertThrows(BookValidationException.class,
                () -> BookValidator.validatePrice(price));
    }

    @Test
    public void validateCorrectQuantityTest() {
        int quantity = 10;

        BookValidator.validateQuantity(quantity);
    }

    @Test
    public void validateNegativeQuantityTest() {
        int quantity = -1;

        Assertions.assertThrows(BookValidationException.class,
                () -> BookValidator.validateQuantity(quantity));
    }

    @Test
    public void validateCorrectBookTest() {
        Book book = new Book(1, "Jakis tytul",
                "Janusz Kowalski", "978-83-283-9783-5",
                30.00, 5, new User());

        BookValidator.validateBook(book);
    }
}
