package pl.comarch.camp.it.book.store.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "pl.comarch.camp.it.book.store.controllers",
        "pl.comarch.camp.it.book.store.services"
})
public class TestConfiguration {
}
