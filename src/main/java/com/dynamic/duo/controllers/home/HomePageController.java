package com.dynamic.duo.controllers.home;

import com.dynamic.duo.model.persistent.DomainObject;
import com.dynamic.duo.model.persistent.Player;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomePageController {
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String home(@RequestParam(value="name", required=false, defaultValue="Not Found") String name, Model model) {
        Player player = (Player)DomainObject.getBy(Player.class, "name", name);
        model.addAttribute("name", player.getName());
        return "home";
    }
}
