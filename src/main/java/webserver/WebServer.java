package webserver;

import java.net.ServerSocket;
import java.net.Socket;

import exception.BadCredentialsException;
import exception.DuplicatedUserIdException;
import exception.NoAuthorizationException;
import exception.NoSuchUserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import repository.BoardRepository;
import repository.DbBoardRepository;
import repository.DbUserRepository;
import repository.UserRepository;
import service.BoardService;
import service.BoardServiceImpl;
import service.UserService;
import service.UserServiceImpl;
import webserver.controller.*;
import webserver.exceptionResponseFactory.DuplicatedUserExceptionResponseFactory;
import webserver.exceptionResponseFactory.ExceptionHandler;
import webserver.exceptionResponseFactory.LoginFailureExceptionResponseFactory;
import webserver.exceptionResponseFactory.NoAuthorizationExceptionResponseFactory;
import webserver.http.Constants;
import webserver.http.HttpMethod;

@SpringBootApplication
public class WebServer {
    private static final Logger logger = LoggerFactory.getLogger(WebServer.class);
    private static final int DEFAULT_PORT = 8080;

    public static void main(String args[]) throws Exception {
        int port = 0;
        if (args == null || args.length == 0) {
            port = DEFAULT_PORT;
        } else {
            port = Integer.parseInt(args[0]);
        }
        FrontController frontController = CreateFrontController();
        // 서버소켓을 생성한다. 웹서버는 기본적으로 8080번 포트를 사용한다.
        try (ServerSocket listenSocket = new ServerSocket(port)) {
            logger.info("Web Application Server started {} port.", port);
            // 클라이언트가 연결될때까지 대기한다.
            Socket connection;
            while ((connection = listenSocket.accept()) != null) {
                Thread thread = new Thread(new RequestHandler(connection,frontController));
                thread.start();
            }
        }

    }

    private static FrontController CreateFrontController() {
        UserRepository userRepository = new DbUserRepository();
        BoardRepository boardRepository = new DbBoardRepository();

        UserService userService = new UserServiceImpl(userRepository);
        BoardService boardService = new BoardServiceImpl(boardRepository);

        ControllerMapper controllerMapper = new ControllerMapper();
        setControllers(userService, boardService, controllerMapper);

        ExceptionHandler exceptionHandler = new ExceptionHandler();
        setExceptionResponseFactory(exceptionHandler);

        return new FrontController(controllerMapper, exceptionHandler);

    }

    private static void setExceptionResponseFactory(ExceptionHandler exceptionHandler) {
        exceptionHandler.addExceptionResponseFactory(DuplicatedUserIdException.class, new DuplicatedUserExceptionResponseFactory());
        exceptionHandler.addExceptionResponseFactory(BadCredentialsException.class, new LoginFailureExceptionResponseFactory());
        exceptionHandler.addExceptionResponseFactory(NoSuchUserException.class, new LoginFailureExceptionResponseFactory());
        exceptionHandler.addExceptionResponseFactory(NoAuthorizationException.class, new NoAuthorizationExceptionResponseFactory());
    }

    private static void setControllers(UserService userService, BoardService boardService, ControllerMapper controllerMapper) {
        controllerMapper.addController(HttpMethod.POST, Constants.USER_CREATE_PATH,new PostUserSaveController(userService));
        controllerMapper.addController(HttpMethod.POST,Constants.USER_LOGIN_PATH,new LoginController(userService));
        controllerMapper.addController(HttpMethod.GET,Constants.USER_LIST_PATH,new UserListController(userService));
        controllerMapper.addController(HttpMethod.POST,Constants.BOARD_CREATE_PATH,new BoardSaveController(boardService));
        controllerMapper.addController(HttpMethod.GET,Constants.BOARD_LIST_PATH,new BoardListController(boardService));
    }

}
