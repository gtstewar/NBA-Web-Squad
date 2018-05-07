package com.dynamic.duo.controllers.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TeamPageController {
    @RequestMapping(value = "/teams", method = RequestMethod.GET)
    public String home( Model model) {
        return "teams";
    }
}
