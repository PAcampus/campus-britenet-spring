package pl.britenet.spring.campusbritenetspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.britenet.campus.models.User;
import pl.britenet.spring.campusbritenetspring.model.UserCredentials;
import pl.britenet.spring.campusbritenetspring.model.UserLoginData;
import pl.britenet.spring.campusbritenetspring.service.AuthenticationService;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final AuthenticationService authenticationService;

    @Autowired
    public LoginController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public UserLoginData logInUser(@RequestBody UserCredentials userCredentials) {
        return this.authenticationService.login(userCredentials);
    }

    @PostMapping("/logout")
    public void logOutUser() {

    }

    @PostMapping("/register")
    public void registerUser(@RequestBody User user) throws NoSuchAlgorithmException, NoSuchProviderException {
        this.authenticationService.register(user);
    }
}
