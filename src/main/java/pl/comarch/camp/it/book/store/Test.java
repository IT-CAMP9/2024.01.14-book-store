package pl.comarch.camp.it.book.store;

import org.apache.commons.codec.digest.DigestUtils;
import pl.comarch.camp.it.book.store.model.Book;
import pl.comarch.camp.it.book.store.model.User;
import pl.comarch.camp.it.book.store.model.UserBuilder;

public class Test {
    public static void main(String[] args) {
        Book book = new Book();
        /*book.setId(1);
        book.setTitle("Tytul");
        book.setAuthor("Autor");
        book.setIsbn("ISBN");
        book.setPrice(100.00);
        book.setQuantity(10);*/

        System.out.println(book);

        Cos cos = new Cos();

        cos.a(4).b(5).c(6);

        System.out.println(new UserBuilder()
                .id(5)
                .login("wiesiek")
                .password("wiesiek123")
                .name("Wiesiek")
                .surname("Malinowski")
                .role(User.Role.USER)
                .build());

        System.out.println(User.builder().id(5)
                .login("wiesiek")
                .password("wiesiek123")
                .name("Wiesiek")
                .surname("Malinowski")
                .role(User.Role.USER)
                .build());
    }
}
