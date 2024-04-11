package pl.comarch.camp.it.book.store.validators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.comarch.camp.it.book.store.exceptions.UserValidationException;
import pl.comarch.camp.it.book.store.model.dto.RegisterUserDTO;

public class UserValidatorTest {

    @Test
    public void validateCorrectLoginTest() {
        String login = "janusz";

        UserValidator.validateLogin(login);
    }

    @Test
    public void validateLoginWithSpecialSignTest() {
        String login = "janusz$";

        Assertions.assertThrows(UserValidationException.class,
                () -> UserValidator.validateLogin(login));
    }

    @Test
    public void validateTooShortLoginTest() {
        String login = "jan";

        Assertions.assertThrows(UserValidationException.class,
                () -> UserValidator.validateLogin(login));
    }

    @Test
    public void validateCorrectNameTest() {
        String name = "Janusz";

        UserValidator.validateName(name);
    }

    @Test
    public void validateTooShortNameTest() {
        String name = "Ja";

        Assertions.assertThrows(UserValidationException.class,
                () -> UserValidator.validateName(name));
    }

    @Test
    public void validateStartLowerCaseNameTest() {
        String name = "janusz";

        Assertions.assertThrows(UserValidationException.class,
                () -> UserValidator.validateName(name));
    }

    @Test
    public void validateCorrectSurnameTest() {
        String surname = "Kowalski";

        UserValidator.validateSurname(surname);
    }

    @Test
    public void validateCorrectTwoSectionSurnameTest() {
        String surname = "Kowalski-Malinowski";

        UserValidator.validateSurname(surname);
    }

    @Test
    public void validateTooShortSurnameTest() {
        String surname = "K";

        Assertions.assertThrows(UserValidationException.class,
                () -> UserValidator.validateSurname(surname));
    }

    @Test
    public void validateLowerCaseSurnameTest() {
        String surname = "kowalski";

        Assertions.assertThrows(UserValidationException.class,
                () -> UserValidator.validateSurname(surname));
    }

    @Test
    public void validateCorrectPasswordTest() {
        String password = "janusz123";

        UserValidator.validatePassword(password);
    }

    @Test
    public void validateCorrectPasswordWithDigitInMiddleTest() {
        String password = "jan12usz";

        UserValidator.validatePassword(password);
    }

    @Test
    public void validateTooShortPasswordTest() {
        String password = "ja12";

        Assertions.assertThrows(UserValidationException.class,
                () -> UserValidator.validatePassword(password));
    }

    @Test
    public void validatePasswordWithoutDigitTest() {
        String password = "janusz";

        Assertions.assertThrows(UserValidationException.class,
                () -> UserValidator.validatePassword(password));
    }

    @Test
    public void validatePasswordEqualityTest() {
        String pass1 = "janusz123";
        String pass2 = "janusz123";

        UserValidator.validatePasswordsEquality(pass1, pass2);
    }

    @Test
    public void validatePasswordEqualityWhenNotEqualTest() {
        String pass1 = "janusz123";
        String pass2 = "janusz12";

        Assertions.assertThrows(UserValidationException.class,
                () -> UserValidator.validatePasswordsEquality(pass1, pass2));
    }

    @Test
    public void validateCorrectRegisterDTOTest() {
        RegisterUserDTO registerUserDTO = new RegisterUserDTO();
        registerUserDTO.setLogin("janusz");
        registerUserDTO.setName("Janusz");
        registerUserDTO.setSurname("Kowalski");
        registerUserDTO.setPassword("janusz123");
        registerUserDTO.setPassword2("janusz123");

        UserValidator.validateUserDto(registerUserDTO);
    }
}
