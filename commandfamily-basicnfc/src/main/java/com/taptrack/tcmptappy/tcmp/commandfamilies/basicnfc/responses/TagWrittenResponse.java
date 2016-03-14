package com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.responses;

import com.taptrack.tcmptappy.tappy.constants.TagTypes;
import com.taptrack.tcmptappy.tcmp.MalformedPayloadException;
import com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.AbstractBasicNfcMessage;

import java.util.Arrays;

/**
 * A tag has been written by the Tappy
 */
public class TagWrittenResponse extends AbstractBasicNfcMessage {
    public static final byte COMMAND_CODE = 0x05;
    byte[] mTagCode;
    byte mTagType;

    public TagWrittenResponse() {
        mTagCode = new byte[7];
        mTagType = TagTypes.TAG_UNKNOWN;
    }

    public TagWrittenResponse(byte[] tagCode, byte tagType) {
        mTagCode = tagCode;
        mTagType = tagType;
    }

    public byte[] getTagCode() {
        return mTagCode;
    }

    public byte getTagType() {
        return mTagType;
    }

    public static TagWrittenResponse fromPayload(byte[] payload) throws MalformedPayloadException {
        if(payload.length < 5) //at least a 4 byte uid
            throw new MalformedPayloadException();
        byte tagType  = payload[0];
        byte[] tagCode = Arrays.copyOfRange(payload, 1, payload.length);
        return new TagWrittenResponse(tagCode,tagType);
    }

    @Override
    public byte[] getPayload() {
        byte[] payload = new byte[mTagCode.length+1];
        payload[0] = mTagType;
        System.arraycopy(mTagCode,0,payload,1,mTagCode.length);
        return payload;
    }

    @Override
    public byte getCommandCode() {
        return COMMAND_CODE;
    }
}
