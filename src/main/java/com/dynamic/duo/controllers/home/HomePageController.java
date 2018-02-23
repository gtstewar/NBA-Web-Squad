package com.dynamic.duo.controllers.home;

import com.dynamic.duo.model.persistent.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomePageController {
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home( Model model) {
        model.addAttribute("points_top5", PlayerGeneralStats.getTopItems(PlayerGeneralStats.class,"pts", 5, true));
        model.addAttribute("ast_top5", PlayerGeneralStats.getTopItems(PlayerGeneralStats.class, "ast", 5, true));
        model.addAttribute("reb_top5", PlayerGeneralStats.getTopItems(PlayerGeneralStats.class, "reb", 5, true));
        model.addAttribute("stl_top5", PlayerGeneralStats.getTopItems(PlayerGeneralStats.class, "stl", 5, true));
        model.addAttribute("blk_top5", PlayerGeneralStats.getTopItems(PlayerGeneralStats.class, "blk", 5, true));
        model.addAttribute("tov_top5", PlayerGeneralStats.getTopItems(PlayerGeneralStats.class, "tov", 5, true));
        model.addAttribute("team_pts_top5", TeamGeneralStats.getTopItems(TeamGeneralStats.class, "pts", 5, true));
        model.addAttribute("team_ast_top5", TeamGeneralStats.getTopItems(TeamGeneralStats.class, "ast", 5, true));
        model.addAttribute("team_reb_top5", TeamGeneralStats.getTopItems(TeamGeneralStats.class, "reb", 5, true));
        model.addAttribute("team_stl_top5", TeamGeneralStats.getTopItems(TeamGeneralStats.class, "ast", 5, true));
        return "home";
    }
}
