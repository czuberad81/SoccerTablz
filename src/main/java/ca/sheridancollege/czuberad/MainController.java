package ca.sheridancollege.czuberad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import java.lang.Long;
import java.lang.String;

@Controller
public class MainController {

    @Autowired
    DatabaseAccess da;
    ModelAndView mv;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/addTeam")
    public String add(Model model){
        model.addAttribute("team", new Team());
        return "add";
    }
    @GetMapping("/edit")
    public String edit(Model model){
        model.addAttribute("editTeam",da.getTeams());
        return "edit";
    }
    @GetMapping("/delete")
    public String delete(Model model){

        model.addAttribute("deleteTeam",da.getTeams());
        return "delete";
    }
    @GetMapping("/displayTeam")
    public String display(Model model){
        model.addAttribute("display",da.getTeams());
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
    @GetMapping("/deleteTeamById/{teamID}")
    public ModelAndView deleteTeam(@PathVariable Long teamID){
        da.deleteTeamById(teamID);
        mv = new ModelAndView("redirect:/delete","deleteTeam",da.getTeams());
        return mv;

    }

    @GetMapping("/updateTeamById/{teamID}")
    public ModelAndView updateTeam(@PathVariable Long teamID){
        Team team;
        team = da.getTeamById(teamID).get(0);
        mv = new ModelAndView("updatePage","updateTeam",da.getTeams());
        mv.addObject("team",team);
        return mv;
    }

    @PostMapping("/editTeam")
    public ModelAndView editTeam(@ModelAttribute Team team){
        da.updateTeamById(team);
        mv = new ModelAndView("redirect:/edit","team",da.getTeams());
        return mv;
    }






}
