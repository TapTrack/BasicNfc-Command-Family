package com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.commands;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ScanNdefCommandTest {

    @Test
    public void testGetCommandCode() throws Exception {
        ScanNdefCommand command = new ScanNdefCommand();
        assertEquals(command.getCommandCode(),0x04);
    }
}