package pl.britenet.spring.campusbritenetspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.britenet.campus.models.User;
import pl.britenet.campus.services.UserService;
import pl.britenet.spring.campusbritenetspring.model.UserCredentials;
import pl.britenet.spring.campusbritenetspring.model.UserLoginData;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
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

    public void register(User user) throws NoSuchAlgorithmException, NoSuchProviderException {
        String salt = getSalt();

        String encryptedPassword = get_SHA_256_SecurePassword(user.getPassword(), salt);

        User insertedUser = new User();
        insertedUser.setEmail(user.getEmail());
        insertedUser.setPassword(encryptedPassword);
        insertedUser.setName(user.getName());
        insertedUser.setLast_name(user.getLast_name());
        insertedUser.setAddress(user.getAddress());
        insertedUser.setPhone_number(user.getPhone_number());

        System.out.println(insertedUser.toString());
        this.userService.insertUser(insertedUser);
    }

    private static String get_SHA_256_SecurePassword(String passwordToHash,
                                                     String salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    private static String getSalt()
            throws NoSuchAlgorithmException, NoSuchProviderException
    {
        // Always use a SecureRandom generator
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");

        // Create array for salt
        byte[] salt = new byte[16];

        // Get a random salt
        sr.nextBytes(salt);

        // return salt
        return Arrays.toString(salt);
    }
}
