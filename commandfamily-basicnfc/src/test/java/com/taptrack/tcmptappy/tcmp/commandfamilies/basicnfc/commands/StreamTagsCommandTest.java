package com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.commands;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StreamTagsCommandTest {

    @Test
    public void testGetCommandCode() throws Exception {
        StreamTagsCommand command = new StreamTagsCommand();
        assertEquals(command.getCommandCode(), 0x01);
    }
}