package com.dynamic.duo.controllers.home;

import com.dynamic.duo.model.persistent.Player;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomePageController {
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String home(@RequestParam(value="id", defaultValue="1") Long id , Model model) {
        Player player = Player.getByID(id);
        long idret = Player.getIDFromName(player.getName());
        System.out.println(idret);
        model.addAttribute("name", player.getName());
        return "home";
    }
}
