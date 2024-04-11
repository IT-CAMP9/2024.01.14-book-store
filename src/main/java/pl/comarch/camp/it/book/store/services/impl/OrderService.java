package pl.comarch.camp.it.book.store.services.impl;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.comarch.camp.it.book.store.dao.IBookDAO;
import pl.comarch.camp.it.book.store.dao.IOrderDAO;
import pl.comarch.camp.it.book.store.dao.IUserDAO;
import pl.comarch.camp.it.book.store.exceptions.InvalidCartException;
import pl.comarch.camp.it.book.store.model.Book;
import pl.comarch.camp.it.book.store.model.Order;
import pl.comarch.camp.it.book.store.model.Position;
import pl.comarch.camp.it.book.store.model.User;
import pl.comarch.camp.it.book.store.services.IOrderService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {

    @Autowired
    HttpSession httpSession;

    private final IBookDAO bookDAO;

    private final IOrderDAO orderDAO;

    private final IUserDAO userDAO;

    public OrderService(IBookDAO bookDAO, IOrderDAO orderDAO, IUserDAO userDAO) {
        this.bookDAO = bookDAO;
        this.orderDAO = orderDAO;
        this.userDAO = userDAO;
    }

    @Override
    public void confirm() {
        final User user = (User) this.httpSession.getAttribute("user");
        List<Position> incorrectPositions = user.getCart().stream()
                .filter(position -> {
                    Optional<Book> bookFromDbBox =
                            this.bookDAO.getById(position.getBook().getId());
                    return bookFromDbBox.isEmpty() ||
                            bookFromDbBox.get().getQuantity() < position.getQuantity();
                })
                .toList();
        incorrectPositions.forEach(user.getCart()::remove);

        if(incorrectPositions.size() > 0) {
            throw new InvalidCartException();
        }

        user.getCart().forEach(p -> {
                    Optional<Book> bookFromDb = this.bookDAO.getById(p.getBook().getId());
                    bookFromDb.get().setQuantity(bookFromDb.get().getQuantity() - p.getQuantity());
                    this.bookDAO.update(bookFromDb.get());
                    p.setBook(bookFromDb.get());
                });

        Order order = new Order();
        order.setUser(user);
        order.setTotal(user.total());
        order.setStatus(Order.Status.NEW);
        order.getPositions().addAll(user.getCart());
        order.setDate(LocalDateTime.now());

        this.orderDAO.save(order);

        user.getCart().clear();
    }

    @Override
    public List<Order> getCurrentUserOrders() {
        User user = (User) this.httpSession.getAttribute("user");
        return this.orderDAO.getByUserId(user.getId());
    }

    @Override
    public Optional<Order> getById(int id) {
        return this.orderDAO.getById(id);
    }
}
