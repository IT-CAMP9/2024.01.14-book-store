package pl.comarch.camp.it.book.store.services.impl;

import org.springframework.stereotype.Service;
import pl.comarch.camp.it.book.store.dao.IBookDAO;
import pl.comarch.camp.it.book.store.model.Book;
import pl.comarch.camp.it.book.store.services.IBookService;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService {

    private final IBookDAO bookDAO;

    public BookService(IBookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public void save(Book book) {
        this.bookDAO.save(book);
    }

    @Override
    public Optional<Book> getById(int id) {
        return this.bookDAO.getById(id);
    }

    @Override
    public void update(int id, Book book) {
        book.setId(id);
        this.bookDAO.update(book);
    }

    @Override
    public List<Book> getByPattern(String pattern) {
        return this.bookDAO.getByPattern(pattern);
    }

    @Override
    public List<Book> getAll() {
        return this.bookDAO.getAll();
    }
}
