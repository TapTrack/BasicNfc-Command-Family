package com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.responses;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BasicNfcErrorResponseTest {

    @Test
    public void testGetCommandCode() throws Exception {
        BasicNfcErrorResponse response = new BasicNfcErrorResponse();
        assertEquals(response.getCommandCode(),0x7F);
    }
}