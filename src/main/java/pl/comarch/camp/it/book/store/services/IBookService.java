package pl.comarch.camp.it.book.store.services;

import pl.comarch.camp.it.book.store.model.Book;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    void save(Book book);
    Optional<Book> getById(int id);
    void update(int id, Book book);
    List<Book> getByPattern(String pattern);
    List<Book> getAll();
}