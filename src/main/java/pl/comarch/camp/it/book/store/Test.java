package pl.comarch.camp.it.book.store;

import org.apache.commons.codec.digest.DigestUtils;

public class Test {
    public static void main(String[] args) {
        System.out.println(DigestUtils.md5Hex("janusz123"));
    }
}
