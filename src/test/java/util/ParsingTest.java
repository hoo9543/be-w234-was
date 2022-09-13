package util;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static util.Parsing.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
public class ParsingTest {

    @Test
    @DisplayName("URL을 찾을 수 없는 경우 error NotFoundUrlException 발생")
    void parsingErrorTest(){
        //Assertions.assertThrows(NotFoundUrlException.class,()->urlFromRequestLine("get"));
        assertThatThrownBy(()-> getUrlFromRequestLine("get"))
                .isInstanceOf(NotFoundUrlException.class)
                .hasMessage("Can not find URL");
    }

    @Test
    @DisplayName("URL parsing 확인")
    void parsingTest(){
        assertThat(getUrlFromRequestLine("get /index.html http")).isEqualTo("/index.html");
    }
}
