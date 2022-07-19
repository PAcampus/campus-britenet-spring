package pl.britenet.spring.campusbritenetspring.model;

public class UserLoginData {

    private final int userId;
    private final String userToken;

    public UserLoginData(int userId, String userToken) {
        this.userId = userId;
        this.userToken = userToken;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserToken() {
        return userToken;
    }
}
