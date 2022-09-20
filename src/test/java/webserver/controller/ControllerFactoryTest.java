package webserver.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import webserver.controller.*;
import webserver.http.HttpMethod;

public class ControllerFactoryTest {

    @Test
    @DisplayName("httpMethod 와 path fmf xhdgo Controller를 잘 가져오는지 확인")
    void ControllerTest(){
        ControllerFactory controllerFactory = new ControllerFactory();

        Controller userSaveController = controllerFactory.getController(HttpMethod.GET,"/user/create");
        Controller postUserSaveController = controllerFactory.getController(HttpMethod.POST,"/user/create");
        Controller loginController = controllerFactory.getController(HttpMethod.POST,"/user/login");
        Controller defaultController = controllerFactory.getController(HttpMethod.GET,"/user/filePath");

        Assertions.assertThat(userSaveController.getClass()).isEqualTo(UserSaveController.class);
        Assertions.assertThat(postUserSaveController.getClass()).isEqualTo(PostUserSaveController.class);
        Assertions.assertThat(loginController.getClass()).isEqualTo(LoginController.class);
        Assertions.assertThat(defaultController.getClass()).isEqualTo(DefaultController.class);
    }
}
