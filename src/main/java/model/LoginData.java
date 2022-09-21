package model;

public class LoginData {
    private String userId;
    private String password;

    public LoginData(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
    public String getUserId() {
        return userId;
    }
    public String getPassword() {
        return password;
    }
}
