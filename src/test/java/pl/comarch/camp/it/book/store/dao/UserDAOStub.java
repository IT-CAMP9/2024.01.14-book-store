package pl.comarch.camp.it.book.store.dao;

import pl.comarch.camp.it.book.store.model.User;

import java.util.List;
import java.util.Optional;

public class UserDAOStub implements IUserDAO {
    @Override
    public Optional<User> getById(int id) {
        if(id == 1) {
            User user = new User();
            user.setId(1);
            user.setName("Janusz");
            user.setSurname("Kowalski");
            user.setPassword("kh45g6k3jh45g6jkh34");
            user.setRole(User.Role.ADMIN);
            user.setLogin("janusz");

            return Optional.of(user);
        }

        return Optional.empty();
    }

    @Override
    public Optional<User> getByLogin(String login) {
        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public void save(User user) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(User user) {

    }
}
