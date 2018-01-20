package com.dynamic.duo.controllers.api;

import com.dynamic.duo.model.persistent.DomainObject;
import com.dynamic.duo.model.persistent.Player;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {

    @GetMapping("/player/{name}")
    public ResponseEntity getPlayerByName(@PathVariable("name") String name) {
        Player player = (Player)DomainObject.getBy(Player.class, "player_name", name);
        return ResponseEntity.ok().body(player);
    }
}
