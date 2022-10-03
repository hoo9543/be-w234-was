package webserver;

import repository.DbUserRepository;
import repository.UserRepository;
import service.UserService;
import service.UserServiceImpl;
import webserver.controller.Controller;
import webserver.controller.LoginController;
import webserver.controller.UserListController;

public class UserListControllerFactory implements ControllerFactory{
    @Override
    public Controller create() {
        UserRepository userRepository = new DbUserRepository();
        UserService userService = new UserServiceImpl(userRepository);
        return new UserListController(userService);
    }
}
