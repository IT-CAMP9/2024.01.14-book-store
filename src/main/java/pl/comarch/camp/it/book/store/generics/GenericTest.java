package pl.comarch.camp.it.book.store.generics;

import pl.comarch.camp.it.book.store.model.Book;
import pl.comarch.camp.it.book.store.model.User;
import pl.comarch.camp.it.book.store.model.dto.rest.ResponseListDTO;

import java.util.Collection;

public class GenericTest {
    public static void main(String[] args) {
        ResponseListDTO<Book> responseListDTO = new ResponseListDTO<>();
        responseListDTO.getElements().add(new Book());


        ResponseListDTO<User> responseListDTO2 = new ResponseListDTO<>();
        responseListDTO2.getElements().add(new User());

        Pair<String,Integer> pair1 = new Pair<>();
        pair1.setX("abc");
        pair1.setY(5);

        Pair<Book, User> pair2 = new Pair<>();
        pair2.setX(new Book());
        pair2.setY(new User());

        //Pair.metoda(new Object());

        Pair<? super Collection,?> x = Pair.metoda2();
    }
}
