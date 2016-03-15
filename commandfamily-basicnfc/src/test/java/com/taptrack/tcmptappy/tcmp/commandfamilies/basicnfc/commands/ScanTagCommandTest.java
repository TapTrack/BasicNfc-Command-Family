package com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.commands;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ScanTagCommandTest {

    @Test
    public void testGetCommandCode() throws Exception {
        ScanTagCommand command = new ScanTagCommand();
        assertEquals(command.getCommandCode(),0x02);
    }
}