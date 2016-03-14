package com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.commands;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GetBasicNfcLibraryVersionCommandTest {

    @Test
    public void testGetCommandCode() throws Exception {
        GetBasicNfcLibraryVersionCommand tcmpMessage = new GetBasicNfcLibraryVersionCommand();
        assertEquals(tcmpMessage.getCommandCode(),(byte) 0xFF);
    }
}