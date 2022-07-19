package pl.britenet.spring.campusbritenetspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.britenet.campus.models.User;
import pl.britenet.campus.services.UserService;
import pl.britenet.spring.campusbritenetspring.model.UserCredentials;
import pl.britenet.spring.campusbritenetspring.model.UserLoginData;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthenticationService {
    private final Map<String, Integer> activeTokens;
    private final UserService userService;

    @Autowired
    public AuthenticationService(UserService userService) {
        this.activeTokens = new HashMap<>();
        this.userService = userService;
    }

    public UserLoginData login(UserCredentials userCredentials) {
        Optional<User> oUser = this.userService.getUser(userCredentials.getEmail(), userCredentials.getPassword());
        if (oUser.isPresent()) {
            int userId = oUser.get().getId();
            String userToken = UUID.randomUUID().toString();
            this.activeTokens.put(userToken, userId);
            return new UserLoginData(userId, userToken);
        }
        else {
            throw new IllegalStateException("Invalid credentials.");
        }
    }

    public boolean isLogged(String userToken) {
        return this.activeTokens.containsKey(userToken);
    }

    public void register(UserCredentials userCredentials) {
        User user = new User();
        user.setEmail(userCredentials.getEmail());
        user.setPassword(userCredentials.getPassword());
        this.userService.insertUser(user);
    }
}
