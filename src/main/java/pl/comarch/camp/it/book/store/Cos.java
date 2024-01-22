package pl.comarch.camp.it.book.store;

import lombok.*;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Accessors(fluent = true)
public class Cos {
    private int a;
    private int b;
    private int c;
}
