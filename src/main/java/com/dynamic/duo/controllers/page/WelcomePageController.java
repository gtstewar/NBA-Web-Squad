package com.dynamic.duo.controllers.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WelcomePageController {
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }
}
