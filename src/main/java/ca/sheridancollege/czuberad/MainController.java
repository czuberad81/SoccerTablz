package ca.sheridancollege.czuberad;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/addTeam")
    public String add(){
        return "add";
    }
    @GetMapping("/editTeam")
    public String edit(){
        return "edit";
    }
    @GetMapping("/deleteTeam")
    public String delete(){
        return "delete";
    }
    @GetMapping("/displayTeam")
    public String display(){
        return "display";
    }
    @GetMapping("/backHome")
    public String backHome(){
        return "home";
    }

}
