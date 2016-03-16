package com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.commands;

import com.taptrack.tcmptappy.tcmp.MalformedPayloadException;
import com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.AbstractBasicNfcMessage;
import com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.PollingModes;

/**
 * Base class for the various Basic NFC polling commands
 */
public abstract class AbstractPollingCommand extends AbstractBasicNfcMessage {
    byte timeout;
    byte pollingMode;

    public AbstractPollingCommand() {
        timeout = (byte) 0x00;
        pollingMode = PollingModes.MODE_GENERAL;
    }

    public AbstractPollingCommand(byte timeout, byte pollingMode) {
        this.timeout = timeout;
        this.pollingMode = pollingMode;
    }

    @Override
    public void parsePayload(byte[] payload) throws MalformedPayloadException {
        if (payload.length == 2) {
            timeout = payload[0];
            pollingMode = payload[1];
        } else if (payload.length == 1) {
            // this is not current format, but the tappy supports this
            // for legacy reasons
            timeout = payload[0];
            pollingMode = PollingModes.MODE_GENERAL;
        }
        else {
            throw new MalformedPayloadException("Payload not sufficient length");
        }
    }

    /**
     * Set the timeout after which the Tappy will stop scanning and send a
     * {@link com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.responses.ScanTimeoutResponse}
     *
     * 0x00 disables timeout
     * @param timeout
     */
    public void setTimeout(byte timeout) {
        this.timeout = timeout;
    }

    /**
     * Retreive the timeout after which the Tappy will stop scanning and send a
     * {@link com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.responses.ScanTimeoutResponse}
     *
     * 0x00 disables timeout
     * @return
     */
    public byte getTimeout() {
        return timeout;
    }

    /**
     * Tappy polling mode, 0x01 for NFC Forum Type 1, 0x02 for Type 2/4
     *
     * See {@link com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.PollingModes}
     * @return single byte polling mode
     */
    public byte getPollingMode() {
        return pollingMode;
    }

    /**
     * Tappy polling mode. 0x01 corresponds to NFC Forum Type 1, 0x02 for Type2/4
     *
     * See {@link com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.PollingModes}
     * @param pollingMode
     */
    public void setPollingMode(byte pollingMode) {
        this.pollingMode = pollingMode;
    }

    @Override
    public byte[] getPayload() {
        return new byte[]{timeout,pollingMode};
    }
}
