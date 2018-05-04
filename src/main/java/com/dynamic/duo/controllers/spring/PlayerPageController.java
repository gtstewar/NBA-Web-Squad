package com.dynamic.duo.controllers.spring;

import com.dynamic.duo.model.persistent.Player;
import com.dynamic.duo.model.persistent.PlayerAdvancedStats;
import com.dynamic.duo.model.persistent.PlayerGeneralStats;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PlayerPageController {
    @RequestMapping(value = "/player", method = RequestMethod.GET)
    public String home( Model model, @RequestParam String playerId) {
        Long id = Long.valueOf(playerId);
        model.addAttribute("player", Player.getByID(id));
        model.addAttribute("generalStats", PlayerGeneralStats.getByID(id));
        model.addAttribute("advancedStats", PlayerAdvancedStats.getByID(id));
        return "player";
    }
}
