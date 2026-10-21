package artisteja.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller 
public class UserContoller {

    @RequestMapping(value="/login")
    public String login() {
        return "login";
    }
    
}
