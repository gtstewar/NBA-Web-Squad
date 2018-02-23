package com.dynamic.duo.controllers.home;

import com.dynamic.duo.model.persistent.PlayerGeneralStats;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PlayerPageController {
    @RequestMapping(value = "/players", method = RequestMethod.GET)
    public String home( Model model) {
        model.addAttribute("pts_top10", PlayerGeneralStats.getTopItems(PlayerGeneralStats.class,"pts", 10));
        model.addAttribute("ast_top10", PlayerGeneralStats.getTopItems(PlayerGeneralStats.class, "ast", 10));
        model.addAttribute("reb_top10", PlayerGeneralStats.getTopItems(PlayerGeneralStats.class, "reb", 10));
        model.addAttribute("stl_top10", PlayerGeneralStats.getTopItems(PlayerGeneralStats.class, "stl", 10));
        model.addAttribute("blk_top10", PlayerGeneralStats.getTopItems(PlayerGeneralStats.class, "blk", 10));
        model.addAttribute("tov_top10", PlayerGeneralStats.getTopItems(PlayerGeneralStats.class, "tov", 10));
        return "players";
    }
}
