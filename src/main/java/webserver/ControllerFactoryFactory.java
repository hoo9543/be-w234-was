package webserver;

public class ControllerFactoryFactory {
    public static ControllerFactory getControllerFactory(String type){
        switch(type){
            case "user_save": return new PostUserSaveControllerFactory();
            case "login": return new LoginControllerFactory();
            case "user_list": return new UserListControllerFactory();
            case "board_save": return new BoardSaveControllerFactory();
            case "board_list": return new BoardListControllerFactory();
            case "front": return new FrontControllerFactory();
        }
        throw new IllegalArgumentException("");
    }
}
