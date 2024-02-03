package pl.comarch.camp.it.book.store.dao.memory;

import org.springframework.stereotype.Component;

@Component
public class BookIdSequence {
    private int id = 0;

    public int getId() {
        return ++id;
    }
}
