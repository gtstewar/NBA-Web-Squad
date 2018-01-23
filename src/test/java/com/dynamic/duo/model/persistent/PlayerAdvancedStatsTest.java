package com.dynamic.duo.model.persistent;

import org.junit.jupiter.api.Test;

public class PlayerAdvancedStatsTest {

    @Test
    public void testGetByID(){
        long id = 272;
        PlayerAdvancedStats stats = PlayerAdvancedStats.getByID(id);
        assert(stats.getPlayer_name().equals("Kyrie Irving"));
        assert(stats.getTeam_ab().equals("BOS"));
    }

    @Test
    public void testGetByPlayerID(){
        long id = Player.getIDFromName("Kyrie Irving");
        PlayerAdvancedStats stats = PlayerAdvancedStats.getByPlayerID(id);
        assert(stats.getPlayer_name().equals("Kyrie Irving"));
        assert(stats.getTeam_ab().equals("BOS"));
        System.out.println(stats.toString());
    }
}
