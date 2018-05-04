package com.dynamic.duo.controllers.spring;

import com.dynamic.duo.model.persistent.PlayerGeneralStats;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PlayersPageController {
    @RequestMapping(value = "/players", method = RequestMethod.GET)
    public String home( Model model) {
        model.addAttribute("pts_top10", PlayerGeneralStats.getTopItems(PlayerGeneralStats.class,"pts", 10, true));
        model.addAttribute("ast_top10", PlayerGeneralStats.getTopItems(PlayerGeneralStats.class, "ast", 10, true));
        model.addAttribute("reb_top10", PlayerGeneralStats.getTopItems(PlayerGeneralStats.class, "reb", 10, true));
        model.addAttribute("stl_top10", PlayerGeneralStats.getTopItems(PlayerGeneralStats.class, "stl", 10, true));
        model.addAttribute("blk_top10", PlayerGeneralStats.getTopItems(PlayerGeneralStats.class, "blk", 10, true));
        model.addAttribute("tov_top10", PlayerGeneralStats.getTopItems(PlayerGeneralStats.class, "tov", 10, true));
        return "players";
    }
}
