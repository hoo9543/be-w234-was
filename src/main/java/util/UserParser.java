package util;

import model.LoginData;
import model.User;

import java.util.Map;

import static util.HttpRequestUtils.parseQueryString;

public class UserParser {
    public static User getUserFrom(Map<String,String> params) {
        if (params.get("userId") == null || params.get("password") == null || params.get("name") == null || params.get("email")== null){
            throw new RuntimeException("Invalid user data");
        }
        return new User(params.get("userId"), params.get("password"), params.get("name"), params.get("email"));
    }

    public static User getUserFromRequestBody(String body){
        Map<String, String> userData = parseQueryString(body);
        return getUserFrom(userData);
    }

    public static LoginData getLoginDataFrom(String body) {
        Map<String, String> loginData = parseQueryString(body);
        if (loginData.get("userId") == null || loginData.get("password") == null){
            throw new RuntimeException("Invalid user data");
        }
        return new LoginData(loginData.get("userId"), loginData.get("password"));
    }
}
