package rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bo.yang
 */
@RestController
public class RestTest {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
