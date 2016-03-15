package com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.commands;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StopCommandTest {

    @Test
    public void testGetCommandCode() throws Exception {
        StopCommand command = new StopCommand();
        assertEquals(command.getCommandCode(),0x00);
    }
}