package pl.comarch.camp.it.book.store.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order implements Cloneable {
    private int id;
    private LocalDateTime date;
    private Status status;
    private double total;
    private User user;
    private final Set<Position> positions = new HashSet<>();

    public String getFormattedDate() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return this.date.format(dateTimeFormatter);
    }

    public enum Status {
        NEW,
        PAID,
        SENT,
        DONE
    }

    @Override
    public Order clone() {
        Order order = new Order();
        order.setId(this.id);
        order.setTotal(this.total);
        order.setStatus(this.status);
        order.setUser(this.user.clone());
        order.getPositions().addAll(this.positions.stream().map(Position::clone).toList());

        return order;
    }
}
