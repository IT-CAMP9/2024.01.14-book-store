package pl.comarch.camp.it.book.store.services.impl;

import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import pl.comarch.camp.it.book.store.TestSuite;
import pl.comarch.camp.it.book.store.model.Book;
import pl.comarch.camp.it.book.store.model.User;
import pl.comarch.camp.it.book.store.model.dto.rest.BookDTO;
import pl.comarch.camp.it.book.store.services.IBookService;

import java.util.Optional;

public class BookServiceTest extends TestSuite {

    @Autowired
    HttpSession httpSession;

    @Autowired
    IBookService bookService;

    @Test
    public void saveBookTest() {
        Book book = new Book();
        book.setTitle("Adkajshkjasd");
        book.setAuthor("Aasdf Afasd");
        book.setIsbn("23-23-32-23-21");
        book.setQuantity(345);
        book.setPrice(234.234);
        book.setCreator(null);
        book.setId(0);
        Optional<User> user = generateFakeUser();
        this.httpSession.setAttribute("user", user.get());

        this.bookService.save(book);

        Assertions.assertSame(user.get(), book.getCreator());
        Mockito.verify(this.bookDAO, Mockito.times(1)).save(book);
    }

    @Test
    public void updateBookTest() {
        int updateBookId = 7;
        Book book = new Book();
        book.setTitle("Adkajshkjasd");
        book.setAuthor("Aasdf Afasd");
        book.setIsbn("23-23-32-23-21");
        book.setQuantity(345);
        book.setPrice(234.234);
        book.setCreator(null);
        book.setId(0);
        Optional<User> user = generateFakeUser();
        this.httpSession.setAttribute("user", user.get());

        this.bookService.update(updateBookId, book);

        Assertions.assertSame(user.get(), book.getCreator());
        Assertions.assertEquals(updateBookId, book.getId());
        Mockito.verify(this.bookDAO, Mockito.times(1)).update(book);
    }

    @Test
    public void updateBookDTOTest() {
        int updateBookId = 7;
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(0);
        bookDTO.setTitle("Alkajsdfk");
        bookDTO.setAuthor("Aasdfa Asdfgasdf");
        bookDTO.setIsbn("342-34-342-324");
        bookDTO.setPrice(345.23);
        bookDTO.setQuantity(4536);
        bookDTO.setCreatorId(5);
        Optional<User> user = generateFakeUser();
        user.get().setId(5);
        Mockito.when(this.userDAO.getById(5)).thenReturn(user);
        Book expected = new Book();
        expected.setId(updateBookId);
        expected.setAuthor("Aasdfa Asdfgasdf");
        expected.setTitle("Alkajsdfk");
        expected.setIsbn("342-34-342-324");
        expected.setQuantity(4536);
        expected.setCreator(user.get());
        expected.setPrice(345.23);

        BookDTO actual = this.bookService.update(updateBookId, bookDTO);

        Mockito.verify(this.bookDAO, Mockito.times(1)).persist(expected);
        bookDTO.setId(7);
        Assertions.assertNotSame(bookDTO, actual);
        Assertions.assertEquals(bookDTO, actual);
    }
}
