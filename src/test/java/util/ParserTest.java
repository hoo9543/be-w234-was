package util;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static util.Parsing.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
public class ParsingTest {

    @Test
    @DisplayName("구분자로 String을 정확히 2개의 String으로 구분하는 getStringPairSplitedByDelimiter 함수 Test ")
    void parsingErrorTest(){
        //Assertions.assertThrows(NotFoundUrlException.class,()->urlFromRequestLine("get"));
        assertThatThrownBy(()-> StringDivideAndCheckNum("GET /index.html http/1.1"," ",2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("");
    }



}
