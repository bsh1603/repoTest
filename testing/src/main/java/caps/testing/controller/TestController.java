package caps.testing.controller;

import caps.testing.domain.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class TestController {

    @GetMapping("/api/home")
    public User setUser() {
        User user = new User();
        user.setId(1);
        user.setUsername("김");
        return user;
    }
}
