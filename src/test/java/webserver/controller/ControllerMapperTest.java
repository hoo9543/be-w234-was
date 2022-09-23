package webserver.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import webserver.http.HttpMethod;

public class ControllerMapperTest {

    @Test
    @DisplayName("httpMethod 와 path fmf xhdgo Controller를 잘 가져오는지 확인")
    void ControllerTest(){
        ControllerMapper controllerMapper = new ControllerMapper();

        Controller postUserSaveController = controllerMapper.getController(HttpMethod.POST,"/user/create");
        Controller loginController = controllerMapper.getController(HttpMethod.POST,"/user/login");
        Controller defaultController = controllerMapper.getController(HttpMethod.GET,"/user/filePath");

        Assertions.assertThat(postUserSaveController.getClass()).isEqualTo(PostUserSaveController.class);
        Assertions.assertThat(loginController.getClass()).isEqualTo(LoginController.class);
        Assertions.assertThat(defaultController.getClass()).isEqualTo(DefaultController.class);
    }
}
