package pl.comarch.camp.it.book.store;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.comarch.camp.it.book.store.configuration.TestConfiguration;
import pl.comarch.camp.it.book.store.dao.IBookDAO;
import pl.comarch.camp.it.book.store.dao.IOrderDAO;
import pl.comarch.camp.it.book.store.dao.IPositionDAO;
import pl.comarch.camp.it.book.store.dao.IUserDAO;
import pl.comarch.camp.it.book.store.model.User;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class})
@WebAppConfiguration
public class TestSuite {

    @MockBean
    public IUserDAO userDAO;
    @MockBean
    public IBookDAO bookDAO;
    @MockBean
    public IOrderDAO orderDAO;
    @MockBean
    public IPositionDAO positionDAO;

    protected Optional<User> generateFakeUser() {
        User user = new User();
        user.setId(8734);
        user.setLogin("janusz");
        user.setPassword(DigestUtils.md5Hex("janusz123"));
        user.setName("Jaskdhjgflkahjs");
        user.setSurname("Oaskjdhgfkjha");
        user.setRole(User.Role.ADMIN);
        return Optional.of(user);
    }
}
