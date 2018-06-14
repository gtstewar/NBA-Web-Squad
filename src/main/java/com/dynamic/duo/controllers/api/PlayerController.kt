package com.dynamic.duo.controllers.api

import com.dynamic.duo.model.persistent.DomainObject
import com.dynamic.duo.model.persistent.Player
import com.dynamic.duo.model.persistent.PlayerGeneralStats
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import com.google.gson.GsonBuilder
import org.springframework.web.bind.annotation.*
import java.util.*

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
    @GetMapping("api/players/gen/top/{attribute}/{number}")
    fun getTopPlayersByAttribute(@PathVariable("attribute") attribute: String, @PathVariable("number") numOfPlayers: Int): ResponseEntity<String> {
        val playerGeneralStats: ArrayList<PlayerGeneralStats?>? = PlayerGeneralStats.getTopItems(attribute, numOfPlayers, true ) as ArrayList<PlayerGeneralStats?>? ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        val gson = GsonBuilder().setPrettyPrinting().create()
        val jsonPlayerGeneralStats: String = gson.toJson(playerGeneralStats)
        return ResponseEntity(jsonPlayerGeneralStats, HttpStatus.OK)
    }

    @CrossOrigin(origins = ["http://localhost:4200"])
    @GetMapping("api/players/gen/home/atts")
    fun getTopPlayersByListOfAttributes(@RequestParam("attributes") atts: String): ResponseEntity<String>{
        val scanner = Scanner(atts)
        scanner.useDelimiter(",")
        var att: String
        var attLists :ArrayList<ArrayList<PlayerGeneralStats?>?> = ArrayList()
        while(scanner.hasNext()) {
            att = scanner.next()
            val playerGeneralStats: ArrayList<PlayerGeneralStats?>? = PlayerGeneralStats.getTopItems(att, 5, true ) as ArrayList<PlayerGeneralStats?>? ?: return ResponseEntity(HttpStatus.NOT_FOUND)
            attLists.add(playerGeneralStats)
        }
        val gson = GsonBuilder().setPrettyPrinting().create()
        val jsonPlayerGeneralStats: String = gson.toJson(attLists)
        return ResponseEntity(jsonPlayerGeneralStats, HttpStatus.OK)
    }

    @CrossOrigin(origins = ["http://localhost:4200"])
    @GetMapping("api/players/gen/all")
    fun getAllGeneralStats(): ResponseEntity<String> {
        val playerGeneralStats: ArrayList<PlayerGeneralStats?>? = DomainObject.getAllItems(PlayerGeneralStats::class.java) as ArrayList<PlayerGeneralStats?>? ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        val gson = GsonBuilder().setPrettyPrinting().create()
        val jsonPlayerGeneralStats: String = gson.toJson(playerGeneralStats)
        return ResponseEntity(jsonPlayerGeneralStats, HttpStatus.OK)
    }


}
