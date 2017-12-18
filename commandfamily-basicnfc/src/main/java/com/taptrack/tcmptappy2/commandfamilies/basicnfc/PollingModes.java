package com.taptrack.tcmptappy2.commandfamilies.basicnfc;

public class PollingModes {
    /**
     * Polls for Type 1 (Jewel/Topaz) only
     */
    public static final byte MODE_TYPE1 = 0x01;
    /**
     * Polls for Type 2, 4, MIFARE Classic only
     */
    public static final byte MODE_GENERAL = 0x02;

    private PollingModes() {

    }
}
