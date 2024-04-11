package pl.comarch.camp.it.book.store.services;

import pl.comarch.camp.it.book.store.model.Position;

import java.util.Optional;

public interface IPositionService {
    Optional<Position> getById(int id);
}
