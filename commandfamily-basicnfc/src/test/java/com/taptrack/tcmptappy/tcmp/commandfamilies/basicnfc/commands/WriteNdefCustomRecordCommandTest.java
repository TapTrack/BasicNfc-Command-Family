package com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.commands;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class WriteNdefCustomRecordCommandTest {
    Random random = new Random();

    private byte[] generatePayload(byte timeout, byte lockFlag, byte[] content) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(timeout);
        byteArrayOutputStream.write(lockFlag);
        try {
            byteArrayOutputStream.write(content);
        } catch (IOException ignored) {
            //this should be impossible
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Test
    public void testParsePayload() throws Exception {
        byte timeout = 0x03;
        byte lockflag = 0x01;
        byte[] content = new byte[15];
        random.nextBytes(content);
        byte[] payload = generatePayload(timeout,lockflag,content);

        WriteNdefCustomRecordCommand command = new WriteNdefCustomRecordCommand();
        command.parsePayload(payload);
        assertEquals(command.getTimeout(), timeout);
        assertEquals(command.getLockflag(), lockflag);
        assertEquals(command.willLock(),(lockflag == 0x01));
        assertArrayEquals(command.getContent(),content);
    }

    @Test
    public void testGetPayload() throws Exception {
        byte timeout = 0x03;
        byte lockflag = 0x01;
        byte[] content = new byte[15];
        random.nextBytes(content);
        byte[] payload = generatePayload(timeout,lockflag,content);

        WriteNdefCustomRecordCommand command =
                new WriteNdefCustomRecordCommand(timeout,lockflag,content);
        assertArrayEquals(payload,command.getPayload());
    }

    @Test
    public void testGetCommandCode() throws Exception {
        WriteNdefCustomRecordCommand command = new WriteNdefCustomRecordCommand();
        assertEquals(command.getCommandCode(),0x07);
    }
}