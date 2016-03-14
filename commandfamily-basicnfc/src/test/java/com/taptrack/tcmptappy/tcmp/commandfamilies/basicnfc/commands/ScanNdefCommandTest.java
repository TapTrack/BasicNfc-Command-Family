package com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.commands;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ScanNdefCommandTest {
    Random random = new Random();

    @Test
    public void testFromPayload() throws Exception {
        for(int i = 0; i < 5; i++) {
            byte[] payload = new byte[1];
            random.nextBytes(payload);
            ScanNdefCommand command = ScanNdefCommand.fromPayload(payload);
            assertEquals(command.getTimeout(),payload[0]);
        }
    }

    @Test
    public void testGetPayload() throws Exception {
        for(int i = 0; i < 5; i++) {
            byte[] timeout = new byte[1];
            random.nextBytes(timeout);
            ScanNdefCommand command = new ScanNdefCommand(timeout[0]);
            assertArrayEquals(command.getPayload(), timeout);
        }
    }

    @Test
    public void testGetCommandCode() throws Exception {
        ScanNdefCommand command = new ScanNdefCommand();
        assertEquals(command.getCommandCode(),0x04);
    }
}