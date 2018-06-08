package com.dynamic.duo.controllers.api

import com.dynamic.duo.model.persistent.DomainObject
import com.dynamic.duo.model.persistent.Player
import com.dynamic.duo.model.persistent.PlayerGeneralStats
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import com.google.gson.GsonBuilder
import org.springframework.web.bind.annotation.CrossOrigin

@RestController
class PlayerController {

    @CrossOrigin(origins = ["http://localhost:4200"])
    @GetMapping("api/player/{name}")
    fun getPlayerByName(@PathVariable("name") name: String): ResponseEntity<String> {
        val player: Player? = DomainObject.getBy(Player::class.java, "player_name", name) as Player? ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        val gson = GsonBuilder().setPrettyPrinting().create()
        val jsonPlayer: String = gson.toJson(player)
        return ResponseEntity(jsonPlayer, HttpStatus.OK)
    }

    @CrossOrigin(origins = ["http://localhost:4200"])
    @GetMapping("api/players/top/{attribute}/{number}")
    fun getTopPlayersByAttribute(@PathVariable("attribute") attribute: String, @PathVariable("number") numOfPlayers: Int): ResponseEntity<String> {
        val playerGeneralStats: Array<PlayerGeneralStats?>? = DomainObject.getTopItems(PlayerGeneralStats::class.java, attribute, numOfPlayers, true ) as Array<PlayerGeneralStats?>? ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        val gson = GsonBuilder().setPrettyPrinting().create()
        val jsonPlayerGeneralStats: String = gson.toJson(playerGeneralStats)
        return ResponseEntity(jsonPlayerGeneralStats, HttpStatus.OK)
    }
}
