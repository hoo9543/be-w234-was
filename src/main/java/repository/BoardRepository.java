package repository;

import model.Board;

import javax.persistence.*;
import java.util.Collection;

public interface BoardRepository {

    public void save(Board board);
    public Collection<Board> findAll();
}
