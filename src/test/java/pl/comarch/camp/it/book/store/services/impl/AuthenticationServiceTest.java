package pl.comarch.camp.it.book.store.services.impl;

import jakarta.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import pl.comarch.camp.it.book.store.TestSuite;
import pl.comarch.camp.it.book.store.model.User;
import pl.comarch.camp.it.book.store.model.dto.RegisterUserDTO;
import pl.comarch.camp.it.book.store.services.IAuthenticationService;

import java.util.Optional;

public class AuthenticationServiceTest extends TestSuite {

    @Autowired
    HttpSession httpSession;

    @Autowired
    IAuthenticationService authenticationService;

    @Test
    public void successLoginTest() {
        String login = "janusz";
        String password = "janusz123";
        Optional<User> user = generateFakeUser();
        Mockito.when(this.userDAO.getByLogin(Mockito.any())).thenReturn(user);
        this.httpSession.removeAttribute("user");

        this.authenticationService.login(login, password);

        Assertions.assertNotNull(this.httpSession.getAttribute("user"));
        Assertions.assertSame(user.get(), this.httpSession.getAttribute("user"));
        Assertions.assertNull(user.get().getPassword());
    }

    @Test
    public void incorrectPasswordLoginTest() {
        String login = "janusz";
        String password = "zlehaslo";
        Optional<User> user = generateFakeUser();
        Mockito.when(this.userDAO.getByLogin(Mockito.any())).thenReturn(user);
        this.httpSession.removeAttribute("user");

        this.authenticationService.login(login, password);

        Assertions.assertNull(this.httpSession.getAttribute("user"));
    }

    @Test
    public void logoutTest() {
        Optional<User> user = generateFakeUser();
        this.httpSession.setAttribute("user", user.get());

        this.authenticationService.logout();

        Assertions.assertNull(this.httpSession.getAttribute("user"));
    }

    @Test
    public void registerTest() {
        RegisterUserDTO registerUserDTO = new RegisterUserDTO();
        registerUserDTO.setLogin("janusz");
        registerUserDTO.setName("Janusz");
        registerUserDTO.setSurname("Kowalski");
        registerUserDTO.setPassword("janusz123");
        registerUserDTO.setPassword2("janusz123");
        User user = new User();
        user.setLogin("janusz");
        user.setName("Janusz");
        user.setSurname("Kowalski");
        user.setPassword(DigestUtils.md5Hex("janusz123"));
        user.setRole(User.Role.USER);

        this.authenticationService.register(registerUserDTO);

        Mockito.verify(this.userDAO, Mockito.times(1)).save(user);
    }

}
