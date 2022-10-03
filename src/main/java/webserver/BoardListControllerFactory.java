package webserver;

import repository.BoardRepository;
import repository.DbBoardRepository;
import service.BoardService;
import service.BoardServiceImpl;
import webserver.controller.BoardListController;
import webserver.controller.BoardSaveController;
import webserver.controller.Controller;

public class BoardListControllerFactory implements ControllerFactory{
    @Override
    public Controller create() {
        BoardRepository boardRepository = new DbBoardRepository();
        BoardService boardService = new BoardServiceImpl(boardRepository);
        return new BoardListController(boardService);
    }
}
