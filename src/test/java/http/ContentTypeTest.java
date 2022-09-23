package http;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import webserver.http.ContentType;

import static webserver.http.ContentType.getContentTypeFromPath;

public class ContentTypeTest {

    @Test
    @DisplayName("path의 확장자를 확인해서 ContentType return 하는 getContentTypePath 함수 확인")
    void contentTypeReturnTest(){
        String cssPath = "/user.css";
        String htmlPath = "/user.html";
        String plainText = "/user.txt";

        Assertions.assertThat(getContentTypeFromPath(cssPath)).isEqualTo(ContentType.CSS);
        Assertions.assertThat(getContentTypeFromPath(htmlPath)).isEqualTo(ContentType.HTML);
        Assertions.assertThat(getContentTypeFromPath(plainText)).isEqualTo(ContentType.PLAIN_TEXT);

    }

}
