package pl.comarch.camp.it.book.store.generics;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
public class Pair<T,R> {
    T x;
    R y;

    public static <T extends Collection> void metoda(T x) {
        //???
        //??
        //?
    }

    public static Pair metoda2() {
        if(true) {
            return new Pair<String, String>();
        } else {
            return new Pair<Integer, Integer>();
        }
    }
}
