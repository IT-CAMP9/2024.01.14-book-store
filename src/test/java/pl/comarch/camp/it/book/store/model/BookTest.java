package pl.comarch.camp.it.book.store.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BookTest {

    @Test
    public void cloneTest() {
        User user = new User();
        Book book = new Book();
        book.setId(10);
        book.setTitle("Tytul");
        book.setAuthor("Autor");
        book.setIsbn("123-123-123-123");
        book.setQuantity(10);
        book.setPrice(30.00);
        book.setCreator(user);

        Book clone = book.clone();

        Assertions.assertEquals(book.getId(), clone.getId());
        Assertions.assertEquals(book.getTitle(), clone.getTitle());
        Assertions.assertEquals(book.getAuthor(), clone.getAuthor());
        Assertions.assertEquals(book.getIsbn(), clone.getIsbn());
        Assertions.assertEquals(book.getQuantity(), clone.getQuantity());
        Assertions.assertEquals(book.getPrice(), clone.getPrice());
        Assertions.assertSame(book.getCreator(), clone.getCreator());
        Assertions.assertNotSame(book, clone);
    }
}
