package cinema.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @GetMapping
    public String welcome(Authentication authentication) {
        return String.format("Welcome, %s!", authentication.getName());
    }
}
