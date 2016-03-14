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

import com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.AbstractBasicNfcMessage;

/**
 * Tell the Tappy to continuously report tags it encounters
 * until the timeout is reached.
 */
public class StreamTagsCommand extends AbstractBasicNfcMessage {
    public static final byte COMMAND_CODE = (byte)0x01;
    protected byte mDuration;
    protected byte mType;

    public StreamTagsCommand() {
        mType = (byte) 0x02;
        mDuration = (byte) 0x00;
    }

    public static StreamTagsCommand fromPayload(byte[] payload) {
        byte duration;
        byte type;
        if(payload.length >= 2) {
            duration = payload[0];
            type = payload[1];
        }
        else if (payload.length > 0) {
            duration = payload[0];
            type = (byte) 0x02;
        }
        else {
            duration = (byte) 0x00;
            type = (byte) 0x02;
        }

        return new StreamTagsCommand(duration,type);
    }

    public StreamTagsCommand (byte duration) {
        mDuration = duration;
        mType = (byte) 0x02;
    }

    public StreamTagsCommand (byte duration, byte type) {
        mDuration = duration;
        mType = type;
    }

    public void setDuration(byte duration) {
        mDuration = duration;
    }

    public byte getDuration() {
        return mDuration;
    }

    public byte getType() {
        return mType;
    }

    public void setType(byte type) {
        mType = type;
    }

    @Override
    public byte[] getPayload() {
        return new byte[]{mDuration,mType};
    }

    @Override
    public byte getCommandCode() {
        return COMMAND_CODE;
    }
}
