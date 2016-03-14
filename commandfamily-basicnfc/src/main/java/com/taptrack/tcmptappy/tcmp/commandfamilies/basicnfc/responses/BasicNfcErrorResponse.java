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

package com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.responses;

import com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.AbstractBasicNfcMessage;
import com.taptrack.tcmptappy.tcmp.MalformedPayloadException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * Basic error response for NFC library errors
 */
public class BasicNfcErrorResponse extends AbstractBasicNfcMessage {
    public static final byte COMMAND_CODE = 0x7F;
    byte errorCode;
    byte internalError;
    byte pn532Status;
    String errorMessage;

    public BasicNfcErrorResponse() {
        errorCode = 0x00;
        internalError= 0x00;
        pn532Status = 0x00;
    }

    public BasicNfcErrorResponse(byte errorCode, byte internalError, byte pn532Status, String errorMessage) {
        this.errorCode = errorCode;
        this.internalError = internalError;
        this.pn532Status = pn532Status;
        this.errorMessage = errorMessage;
    }

    public static BasicNfcErrorResponse fromPayload(byte[] payload) throws MalformedPayloadException {
        if(payload.length >= 3) {
            byte errorCode = (byte) (payload[0] & 0xff);
            byte internalError = (byte) (payload[1] & 0xff);
            byte pn532Status = (byte) (payload[2] & 0xff);
            String messageString;
            if(payload.length > 3) {
                byte[] message = Arrays.copyOfRange(payload, 3, payload.length);
                messageString = new String(message);
            }
            else {
                messageString = "";
            }

            return new BasicNfcErrorResponse(errorCode,internalError,pn532Status,messageString);
        }
        else {
            throw new MalformedPayloadException("Payload too short");
        }

    }

    public byte getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(byte errorCode) {
        this.errorCode = errorCode;
    }

    public byte getInternalError() {
        return internalError;
    }

    public void setInternalError(byte internalError) {
        this.internalError = internalError;
    }

    public byte getPn532Status() {
        return pn532Status;
    }

    public void setPn532Status(byte pn532Status) {
        this.pn532Status = pn532Status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public byte[] getPayload() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(3+errorMessage.length());
        outputStream.write(errorCode);
        outputStream.write(internalError);
        outputStream.write(pn532Status);
        try {
            outputStream.write(errorMessage.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] payload = outputStream.toByteArray();
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return payload;
    }

    @Override
    public byte getCommandCode() {
        return COMMAND_CODE;
    }
}
