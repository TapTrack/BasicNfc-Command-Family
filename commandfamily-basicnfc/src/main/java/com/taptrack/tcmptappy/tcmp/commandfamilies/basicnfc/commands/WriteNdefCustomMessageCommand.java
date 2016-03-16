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

package com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.commands;

import com.taptrack.tcmptappy.tcmp.MalformedPayloadException;
import com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.AbstractBasicNfcMessage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * Command the Tappy to write an arbitrary custom NDEF record to tags
 */
public class WriteNdefCustomMessageCommand extends AbstractBasicNfcMessage {

    public static final byte COMMAND_CODE = (byte)0x07;
    protected byte timeout;
    protected byte lockflag; //1 to lock the flag
    protected byte[] content;

    public WriteNdefCustomMessageCommand() {
        timeout = (byte) 0x00;
        lockflag = (byte) 0x00;
        content = new byte[0];
    }


    public WriteNdefCustomMessageCommand(byte timeout, boolean lockTag, byte[] content) {
        this.timeout = timeout;
        this.lockflag = (byte) (lockTag ? 0x01: 0x00);
        this.content = content;
    }

    public WriteNdefCustomMessageCommand(byte timeout, byte lockTag, byte[] content) {
        this.timeout = timeout;
        this.lockflag = lockTag;
        this.content = content;
    }

    @Override
    public void parsePayload(byte[] payload) throws MalformedPayloadException {
        if(payload.length >= 2) {
            timeout = payload[0];
            lockflag = payload[1];
            if(payload.length > 2) {
                content = Arrays.copyOfRange(payload, 2, payload.length);
            }
            else {
                content = new byte[0];
            }
        }
        else {
            throw new MalformedPayloadException("Invalid raw message");
        }
    }

    public byte getTimeout() {
        return timeout;
    }

    public void setTimeout(byte timeout) {
        this.timeout = timeout;
    }

    public byte getLockflag() {
        return lockflag;
    }

    public void setLockflag(byte lockflag) {
        this.lockflag = lockflag;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public boolean willLock() {
        return lockflag == 0x01;
    }

    public void setToLock(boolean lockTag) {
        this.lockflag = (byte) (lockTag ? 0x01:0x00);
    }

    @Override
    public byte[] getPayload() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(3+ content.length);
        outputStream.write(timeout);
        outputStream.write(lockflag);
        try {
            outputStream.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] result = outputStream.toByteArray();
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public byte getCommandCode() {
        return COMMAND_CODE;
    }
}
