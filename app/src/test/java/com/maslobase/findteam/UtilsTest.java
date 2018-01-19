package com.maslobase.findteam;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Inessa on 19.01.2018.
 */
public class UtilsTest {

    @Test
    public void testSteamId64ToSteamId32() {
        final String steamId64 = "76561198029398909";
        assertEquals("69133181", Utils.steamId64ToSteamId32(steamId64));
    }
}
