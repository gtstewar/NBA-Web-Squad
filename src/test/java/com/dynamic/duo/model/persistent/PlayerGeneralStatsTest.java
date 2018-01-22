package com.dynamic.duo.model.persistent;

import org.junit.jupiter.api.Test;

public class PlayerGeneralStatsTest {

    @Test
    public void testGetByID(){
        long id = 283;
        PlayerGeneralStats stats = PlayerGeneralStats.getByID(id);
        assert(stats.getAge() == 25 );
        assert(stats.getPlayer_name().equals("Kyrie Irving"));
        assert(stats.getTeam_ab().equals("BOS"));
    }

    @Test
    public void testGetByPlayerID(){
        long id = Player.getIDFromName("Kyrie Irving");
        PlayerGeneralStats stats = PlayerGeneralStats.getByPlayerID(id);
        assert(stats.getPlayer_name().equals("Kyrie Irving") );
        assert(stats.getAge() == 25 );
        assert(stats.getTeam_ab().equals("BOS") );
    }
}
