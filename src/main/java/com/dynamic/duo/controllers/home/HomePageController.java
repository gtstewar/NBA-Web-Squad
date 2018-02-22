package com.dynamic.duo.controllers.home;

import com.dynamic.duo.model.persistent.Player;
import com.dynamic.duo.model.persistent.PlayerAdvancedStats;
import com.dynamic.duo.model.persistent.PlayerGeneralStats;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomePageController {
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home( Model model) {
        model.addAttribute("points_top5", PlayerGeneralStats.getTopItems(PlayerGeneralStats.class,"pts", 5));
        model.addAttribute("ast_top5", PlayerGeneralStats.getTopItems(PlayerGeneralStats.class, "ast", 5));
        model.addAttribute("reb_top5", PlayerGeneralStats.getTopItems(PlayerGeneralStats.class, "reb", 5));
        return "home";
    }
}
