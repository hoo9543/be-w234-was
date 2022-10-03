package webserver;

import repository.BoardRepository;
import repository.DbBoardRepository;
import repository.DbUserRepository;
import repository.UserRepository;
import service.BoardService;
import service.BoardServiceImpl;
import service.UserService;
import service.UserServiceImpl;
import webserver.controller.Controller;
import webserver.controller.PostUserSaveController;

public class PostUserSaveControllerFactory implements ControllerFactory{
    @Override
    public Controller create() {
        UserRepository userRepository = new DbUserRepository();
        UserService userService = new UserServiceImpl(userRepository);
        return new PostUserSaveController(userService);
    }
}
