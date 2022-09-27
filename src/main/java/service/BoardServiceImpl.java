package service;

import model.Board;
import repository.BoardRepository;

import java.util.Collection;

public class BoardServiceImpl implements BoardService{

    private BoardRepository boardRepository;
    public BoardServiceImpl(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }

    @Override
    public void save(Board board) {
        boardRepository.save(board);
    }

    @Override
    public Collection<Board> findAll() {
        return boardRepository.findAll();
    }
}
