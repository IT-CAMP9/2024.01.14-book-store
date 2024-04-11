package pl.comarch.camp.it.book.store.model.dto.rest;

import lombok.*;
import pl.comarch.camp.it.book.store.dao.IBookDAO;
import pl.comarch.camp.it.book.store.exceptions.BookNotExistException;
import pl.comarch.camp.it.book.store.model.Position;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PositionDTO {
    private int id;
    private String book;
    private int quantity;

    public PositionDTO(Position position) {
        this.id = position.getId();
        this.book = "http://localhost:8080/api/v1/book/" + position.getBook().getId();
        this.quantity = position.getQuantity();
    }
}
