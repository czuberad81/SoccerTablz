package ca.sheridancollege.czuberad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.swing.text.html.Option;
import java.lang.Long;
import java.lang.String;
import java.util.Optional;

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
        model.addAttribute("display",da.getTeams());
        return "edit";
    }
    @GetMapping("/delete")
    public String delete(Model model) {
        model.addAttribute("display", da.getTeams());
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
        mv = new ModelAndView("redirect:/delete","display",da.getTeams());
        return mv;

    }

    @GetMapping("/updateTeamById/{teamID}")
    public ModelAndView updateTeam(@PathVariable Long teamID){
        Team team;
        team = da.getTeamById(teamID).get(0);
        mv = new ModelAndView("updatePage","display",da.getTeams());
        mv.addObject("team",team);
        return mv;
    }

    @PostMapping("/editTeam")
    public ModelAndView editTeam(@ModelAttribute Team team){
        da.updateTeamById(team);
        mv = new ModelAndView("redirect:/edit","display",da.getTeams());
        return mv;
    }
    @GetMapping("/search")
    public ModelAndView searchDB(@RequestParam String searchedString){
        if(searchedString != null){
            mv = new ModelAndView("/delete","display",da.getTeamBySearch(searchedString));
        }
        else{
            mv = new ModelAndView("/delete","display",da.getTeams());
        }
        return mv;
    }
    @GetMapping("/sortTable")
    public ModelAndView sortDB(@RequestParam String option){
        if(option.equals("points")){
            mv = new ModelAndView("/display","display",da.getTeamsByPoints());
        }
        else if(option.equals("name")){
            mv = new ModelAndView("/display","display",da.getTeamsByName());
        }
        else if(option.equals("con")){
            mv = new ModelAndView("/display","display",da.getTeamsByCon());
        }
        return mv;
    }

}
