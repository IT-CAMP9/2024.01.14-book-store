package pl.comarch.camp.it.book.store.services.impl;

import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import pl.comarch.camp.it.book.store.TestSuite;
import pl.comarch.camp.it.book.store.exceptions.InvalidCartException;
import pl.comarch.camp.it.book.store.model.Book;
import pl.comarch.camp.it.book.store.model.Position;
import pl.comarch.camp.it.book.store.model.User;
import pl.comarch.camp.it.book.store.services.IOrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderServiceTest extends TestSuite {

    @Autowired
    HttpSession httpSession;

    @Autowired
    IOrderService orderService;

    @Test
    public void confirmOrderWithNotExistingBook() {
        Optional<User> user = generateFakeUser();
        user.get().getCart().addAll(generateFakePositions());
        this.httpSession.setAttribute("user", user.get());
        Mockito.when(this.bookDAO.getById(1)).thenReturn(Optional.empty());
        Mockito.when(this.bookDAO.getById(2)).thenReturn(
                Optional.of(
                        new Book(2, "Title2", "Author2",
                "123-234-234", 40.00, 5, new User())));
        int expectedCastSize = 1;

        Assertions.assertThrows(InvalidCartException.class,
                () -> this.orderService.confirm());

        Assertions.assertEquals(expectedCastSize, user.get().getCart().size());
    }

    @Test
    public void confirmOrderWithTooBigQuantityTest() {
        Optional<User> user = generateFakeUser();
        user.get().getCart().addAll(generateFakePositions());
        this.httpSession.setAttribute("user", user.get());
        Mockito.when(this.bookDAO.getById(1)).thenReturn(
                Optional.of(
                        new Book(1, "Title", "Author",
                                "123-123-123", 30.00, 0, new User())));
        Mockito.when(this.bookDAO.getById(2)).thenReturn(
                Optional.of(
                        new Book(2, "Title2", "Author2",
                                "123-234-234", 40.00, 5, new User())));
        int expectedCastSize = 1;

        Assertions.assertThrows(InvalidCartException.class,
                () -> this.orderService.confirm());

        Assertions.assertEquals(expectedCastSize, user.get().getCart().size());
    }

    @Test
    public void successOrderConfirmationTest() {
        Optional<User> user = generateFakeUser();
        user.get().getCart().addAll(generateFakePositions());
        this.httpSession.setAttribute("user", user.get());
        Mockito.when(this.bookDAO.getById(1)).thenReturn(
                Optional.of(
                        new Book(1, "Title", "Author",
                                "123-123-123", 30.00, 5, new User())));
        Mockito.when(this.bookDAO.getById(2)).thenReturn(
                Optional.of(
                        new Book(2, "Title2", "Author2",
                                "123-234-234", 40.00, 10, new User())));
        int expectedCartSize = 0;

        this.orderService.confirm();

        Assertions.assertEquals(expectedCartSize, user.get().getCart().size());
        Mockito.verify(this.bookDAO, Mockito.times(1)).update(new Book(1, "Title", "Author",
                "123-123-123", 30.00, 0, new User()));
        Mockito.verify(this.bookDAO, Mockito.times(1)).update(new Book(2, "Title2", "Author2",
                "123-234-234", 40.00, 5, new User()));
        Mockito.verify(this.orderDAO, Mockito.times(1)).save(Mockito.any());
    }

    public List<Position> generateFakePositions() {
        List<Position> positions = new ArrayList<>();
        positions.add(new Position(0, new Book(1, "Title", "Author",
                "123-123-123", 30.00, 5, new User()), 5));
        positions.add(new Position(0, new Book(2, "Title2", "Author2",
                "123-234-234", 40.00, 5, new User()), 5));
        return positions;
    }
}
