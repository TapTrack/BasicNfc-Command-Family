package com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.commands;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StreamNdefCommandTest {


    @Test
    public void testGetCommandCode() throws Exception {
        StreamNdefCommand command = new StreamNdefCommand();
        assertEquals(command.getCommandCode(),0x03);
    }
}