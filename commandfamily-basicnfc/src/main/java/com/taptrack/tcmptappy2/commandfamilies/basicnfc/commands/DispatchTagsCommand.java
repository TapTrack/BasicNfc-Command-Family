/*
 * Copyright (c) 2016. Papyrus Electronics, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * you may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.taptrack.tcmptappy2.commandfamilies.basicnfc.commands;

import com.taptrack.tcmptappy2.commandfamilies.basicnfc.AbstractBasicNfcMessage;
import com.taptrack.tcmptappy2.MalformedPayloadException;
import com.taptrack.tcmptappy2.commandfamilies.basicnfc.responses.NdefFoundResponse;
import com.taptrack.tcmptappy2.commandfamilies.basicnfc.responses.ScanTimeoutResponse;
import com.taptrack.tcmptappy2.commandfamilies.basicnfc.responses.TagFoundResponse;

/**
 * Tell the Tappy to continuously report tags it encounters. If the
 * tag contains NDEF data, the Tappy will read that data and responde with
 * a {@link NdefFoundResponse}
 * while tags with no NDEF content will instead be reported using
 * {@link TagFoundResponse}
 *
 * Note that this command is only present in Tappies with firmware that supports
 * version 1.4 of the BasicNfc command family
 */
public class DispatchTagsCommand extends AbstractBasicNfcMessage {
    public static final byte COMMAND_CODE = (byte)0x0C;

    private byte timeout;

    public DispatchTagsCommand() {
        this((byte) 0x00);
    }

    public DispatchTagsCommand(byte timeout) {
        super();
        this.timeout = timeout;
    }

    @Override
    public void parsePayload(byte[] payload) throws MalformedPayloadException {
        if(payload == null || payload.length < 1) {
            throw new MalformedPayloadException("Payload too short");
        }

        this.timeout = payload[0];
    }


    /**
     * Set the timeout after which the Tappy will stop scanning and send a
     * {@link ScanTimeoutResponse}
     *
     * 0x00 disables timeout
     * @param timeout
     */
    public void setTimeout(byte timeout) {
        this.timeout = timeout;
    }

    /**
     * Retreive the timeout after which the Tappy will stop scanning and send a
     * {@link ScanTimeoutResponse}
     *
     * 0x00 disables timeout
     * @return
     */
    public byte getTimeout() {
        return timeout;
    }

    @Override
    public byte[] getPayload() {
        return new byte[]{timeout};
    }

    @Override
    public byte getCommandCode() {
        return COMMAND_CODE;
    }
}
