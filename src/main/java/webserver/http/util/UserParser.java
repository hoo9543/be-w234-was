package webserver.http.util;

import model.Board;
import model.LoginData;
import model.User;

import java.util.Collection;
import java.util.Map;

public class UserParser {
    public static User getUserFrom(Map<String,String> params) {
        if (params.get("userId") == null || params.get("password") == null || params.get("name") == null || params.get("email")== null){
            throw new RuntimeException("Invalid user data");
        }
        return new User(params.get("userId"), params.get("password"), params.get("name"), params.get("email"));
    }

    public static User getUserFromRequestBody(String body){
        Map<String, String> userData = HttpRequestUtils.parseQueryString(body);
        return getUserFrom(userData);
    }

    public static LoginData getLoginDataFrom(String body) {
        Map<String, String> loginData = HttpRequestUtils.parseQueryString(body);
        if (loginData.get("userId") == null || loginData.get("password") == null){
            throw new RuntimeException("Invalid user data");
        }
        return new LoginData(loginData.get("userId"), loginData.get("password"));
    }

    public static User getUserFromUserString(String userString){
        String str = userString.replaceAll("[\\[|\\]\\,]","");
        Map<String,String> params = HttpRequestUtils.parseValues(str," ");
        return getUserFrom(params);
    }

    public static String getUserStringFrom(Collection<User> users) {
        StringBuilder stringBuilder = new StringBuilder();
        users.forEach(user -> stringBuilder.append(user.toString()).append("\r\n"));
        return stringBuilder.toString();
    }

    public static Board getBoardFromString(String str){
        Map<String, String> params = HttpRequestUtils.parseQueryString(str);
        return getBoardFrom(params);
    }

    public static Board getBoardFrom(Map<String,String> params) {
        if (params.get("writer") == null || params.get("title") == null || params.get("contents") == null){
            throw new RuntimeException("Invalid board data");
        }
        return new Board(params.get("writer"), params.get("title"), params.get("contents"));
    }
}
