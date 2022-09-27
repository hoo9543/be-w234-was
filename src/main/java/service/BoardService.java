package service;

import model.Board;

import java.util.Collection;

public interface BoardService {
    public void save(Board board);
    public Collection<Board> findAll();
}
