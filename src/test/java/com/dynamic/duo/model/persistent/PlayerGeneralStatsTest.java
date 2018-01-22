package com.dynamic.duo.model.persistent;

import org.junit.jupiter.api.Test;

public class PlayerGeneralStatsTest {

    @Test
    public void testGetByID(){
        long id = 233;
        PlayerGeneralStats stats = PlayerGeneralStats.getByID(id);

        assert(stats.getAge() == 25 );
        System.out.println(stats.getPlayer_name());
    }
}
