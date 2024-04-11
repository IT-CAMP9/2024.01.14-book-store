package pl.comarch.camp.it.book.store.dao;

import pl.comarch.camp.it.book.store.model.Position;

import java.util.List;

public class PositionDAOStub implements IPositionDAO {
    @Override
    public List<Position> getByOrderId(int orderId) {
        return null;
    }

    @Override
    public void save(Position position, int orderId) {

    }

    @Override
    public void update(Position position) {

    }

    @Override
    public void delete(int positionId) {

    }
}
