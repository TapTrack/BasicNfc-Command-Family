package com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.commands;

import com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.AbstractBasicNfcMessage;

/**
 * Command to instruct the Tappy to scan for NDEF-formatted tags.
 * If the Tappy detects a valid tag, it will stop scanning.
 *
 * Timeout values of 0 correspond to indefinite scanning
 */
public class ScanNdefCommand extends AbstractBasicNfcMessage {
    public static final byte COMMAND_CODE = (byte)0x04;
    protected byte mTimeout;

    public ScanNdefCommand() {
        mTimeout = (byte) 0x00;
    }

    public ScanNdefCommand(byte timeout) {
        mTimeout = timeout;
    }

    public static ScanNdefCommand fromPayload(byte[] payload) {
        if(payload.length > 0)
            return new ScanNdefCommand(payload[0]);
        else
            return new ScanNdefCommand((byte) 0x00);
    }

    public void setTimeout(byte timeout) {
        mTimeout = timeout;
    }

    public byte getTimeout() {
        return mTimeout;
    }

    @Override
    public byte[] getPayload() {
        return new byte[]{mTimeout};
    }

    @Override
    public byte getCommandCode() {
        return COMMAND_CODE;
    }
}
