package pl.comarch.camp.it.book.store.model.dto.rest;

import lombok.*;
import pl.comarch.camp.it.book.store.dao.IBookDAO;
import pl.comarch.camp.it.book.store.dao.IUserDAO;
import pl.comarch.camp.it.book.store.exceptions.UserNotExistException;
import pl.comarch.camp.it.book.store.model.Order;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDTO {
    private int id;
    private LocalDateTime date;
    private Order.Status status;
    private double total;
    private String user;
    private final Set<String> positions = new HashSet<>();

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.date = order.getDate();
        this.status = order.getStatus();
        this.total = order.getTotal();
        this.user = "http://localhost:8080/api/v1/user/" + order.getUser().getId();
        this.positions.addAll(order.getPositions().stream()
                .map(position -> "http://localhost:8080/api/v1/position/" + position.getId())
                .toList());
    }
}
