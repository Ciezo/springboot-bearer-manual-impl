package api.inkdown.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import api.inkdown.keys.*;

@RestController
public class DefaultController {

    private final static String SECURE_KEY = SecureKeys.getApiKeys().trim();

    @GetMapping("/")
    public boolean root() {
        return true;
    }

    @GetMapping("/api")
    public boolean dir() {
        return true;
    }

    @GetMapping("/api/inkdown")
    public boolean api() {
        return true;
    }

    @GetMapping("/api/inkdown/test-auth-header")
    public boolean test(@RequestHeader("Authorization") String auth) {
        String authKey = auth.replace("Bearer", "").trim();
        if (SECURE_KEY.equals(authKey)) {
            return true;
        } else {
            return false;
        }
    }

}
