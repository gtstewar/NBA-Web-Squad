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
    public String home(@RequestParam(value="id", defaultValue="1") Long id , Model model) {
        Player player = Player.getByID(id);
        PlayerGeneralStats gen = PlayerGeneralStats.getByPlayerID(player.getId());
        PlayerAdvancedStats adv = PlayerAdvancedStats.getByPlayerID(player.getId());
        model.addAttribute("points", gen.getPts());
        model.addAttribute("name", player.getName());
        model.addAttribute("offrat", adv.getOff_rating() );
        return "home";
    }
}
