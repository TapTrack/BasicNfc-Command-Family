package com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.responses;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ScanTimeoutResponseTest {

    @Test
    public void testGetCommandCode() throws Exception {
        ScanTimeoutResponse response = new ScanTimeoutResponse();
        assertEquals(response.getCommandCode(),0x03);
    }

}