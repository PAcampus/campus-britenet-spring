package pl.britenet.spring.campusbritenetspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.britenet.campus.models.User;
import pl.britenet.campus.services.UserService;
import pl.britenet.spring.campusbritenetspring.model.UserCredentials;
import pl.britenet.spring.campusbritenetspring.model.UserLoginData;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class AuthenticationService {
    private final Map<String, Integer> activeTokens;
    private final UserService userService;

    @Autowired
    public AuthenticationService(UserService userService) {
        this.activeTokens = new HashMap<>();
        this.userService = userService;
    }

    public UserLoginData login(UserCredentials userCredentials) throws NoSuchAlgorithmException {
        Optional<User> oUser = this.userService.getUser(userCredentials.getEmail(), encryptPassword(userCredentials.getPassword()));
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

    public int getUserId(String token) {
        return this.activeTokens.get(token);
    }

    public void register(User user) throws NoSuchAlgorithmException {

        String encryptedPassword = encryptPassword(user.getPassword());

        User insertedUser = new User();
        insertedUser.setEmail(user.getEmail());
        insertedUser.setPassword(encryptedPassword);
        insertedUser.setName(user.getName());
        insertedUser.setLast_name(user.getLast_name());
        insertedUser.setAddress(user.getAddress());
        insertedUser.setPhone_number(user.getPhone_number());
        this.userService.insertUser(insertedUser);
    }

    public String encryptPassword(String passwordToEncrypt) throws NoSuchAlgorithmException {
        String generatedPassword = null;
        try
        {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Add password bytes to digest
            md.update(passwordToEncrypt.getBytes());

            // Get the hash's bytes
            byte[] bytes = md.digest();

            // This bytes[] has bytes in decimal format. Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            // Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
}
