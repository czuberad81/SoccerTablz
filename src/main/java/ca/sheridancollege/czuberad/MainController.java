package ca.sheridancollege.czuberad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @Autowired
    DatabaseAccess da;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/addTeam")
    public String add(Model model){
        model.addAttribute("team", new Team());
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

    @PostMapping("/insertTeam")
    public String insertTeam(@ModelAttribute Team team){
        da.insertTeam(team.getTeamName(),team.getContinent(),team.getPlayed(),team.getWon(),team.getDrawn(),team.getLost());
        return "add";

    }



}
