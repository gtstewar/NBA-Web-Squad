package com.dynamic.duo.controllers.home;

import com.dynamic.duo.model.persistent.DomainObject;
import com.dynamic.duo.model.persistent.Player;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomePageController {
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String home(@RequestParam(value="name", defaultValue="James Harden") String name , Model model) {
        System.out.println(name);
        Player player = (Player)Player.getWhere(Player.class, " player_name = " + "'" + name + "'"  ).get(0);
        model.addAttribute("name", player.getName());
        return "home";
    }
}
