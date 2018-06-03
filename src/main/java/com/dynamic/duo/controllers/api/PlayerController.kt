package com.dynamic.duo.controllers.api

import com.dynamic.duo.model.persistent.DomainObject
import com.dynamic.duo.model.persistent.Player
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class PlayerController {

    @GetMapping("/player/{name}")
    fun getPlayerByName(@PathVariable("name") name: String): ResponseEntity<Player?> {
        val player: Player? = DomainObject.getBy(Player::class.java, "player_name", name) as Player? ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        return ResponseEntity(player, HttpStatus.OK)
    }

    @GetMapping("/players/top/{attribute}/{number}")
    fun getTopPlayersByAttribute(@PathVariable("attribute") attribute: String, @PathVariable("number") numOfPlayers: Int): ResponseEntity<Array<Player?>?> {
        val players: Array<Player?>? = DomainObject.getTopItems(Player::class.java, attribute, numOfPlayers, true ) as Array<Player?>? ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        return ResponseEntity(players, HttpStatus.OK)
    }
}
