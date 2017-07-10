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

package com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc;

import com.taptrack.tcmptappy.tcmp.MalformedPayloadException;
import com.taptrack.tcmptappy.tcmp.TCMPMessage;
import com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.commands.DispatchTagsCommand;
import com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.commands.GetBasicNfcLibraryVersionCommand;
import com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.commands.LockTagCommand;
import com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.commands.ScanNdefCommand;
import com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.commands.ScanTagCommand;
import com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.commands.StopCommand;
import com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.commands.StreamNdefCommand;
import com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.commands.StreamTagsCommand;
import com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.commands.WriteNdefCustomMessageCommand;
import com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.commands.WriteNdefTextRecordCommand;
import com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.commands.WriteNdefUriRecordCommand;
import com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.responses.BasicNfcErrorResponse;
import com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.responses.BasicNfcLibraryVersionResponse;
import com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.responses.NdefFoundResponse;
import com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.responses.ScanTimeoutResponse;
import com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.responses.SignedTagFoundResponse;
import com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.responses.TagFoundResponse;
import com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.responses.TagLockedResponse;
import com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.responses.TagWrittenResponse;
import com.taptrack.tcmptappy.tcmp.common.CommandCodeNotSupportedException;
import com.taptrack.tcmptappy.tcmp.common.CommandFamily;
import com.taptrack.tcmptappy.tcmp.common.ResponseCodeNotSupportedException;

public class BasicNfcCommandLibrary implements CommandFamily {
    public static final byte[] FAMILY_ID = new byte[]{0x00, 0x01};

    @Override
    public com.taptrack.tcmptappy.tcmp.TCMPMessage parseCommand(com.taptrack.tcmptappy.tcmp.TCMPMessage message) throws CommandCodeNotSupportedException, MalformedPayloadException {
        TCMPMessage parsedMessage;
        switch (message.getCommandCode()) {
            case GetBasicNfcLibraryVersionCommand.COMMAND_CODE:
                parsedMessage = new GetBasicNfcLibraryVersionCommand();
                break;

            case ScanNdefCommand.COMMAND_CODE:
                parsedMessage = new ScanNdefCommand();
                break;

            case ScanTagCommand.COMMAND_CODE:
                parsedMessage = new ScanTagCommand();
                break;

            case StopCommand.COMMAND_CODE:
                parsedMessage = new StopCommand();
                break;

            case StreamNdefCommand.COMMAND_CODE:
                parsedMessage = new StreamNdefCommand();
                break;

            case StreamTagsCommand.COMMAND_CODE:
                parsedMessage = new StreamTagsCommand();
                break;

            case WriteNdefCustomMessageCommand.COMMAND_CODE:
                parsedMessage = new WriteNdefCustomMessageCommand();
                break;

            case WriteNdefTextRecordCommand.COMMAND_CODE:
                parsedMessage = new WriteNdefTextRecordCommand();
                break;

            case WriteNdefUriRecordCommand.COMMAND_CODE:
                parsedMessage = new WriteNdefUriRecordCommand();
                break;

            case LockTagCommand.COMMAND_CODE:
                parsedMessage = new LockTagCommand();
                break;

            case DispatchTagsCommand.COMMAND_CODE:
                parsedMessage = new DispatchTagsCommand();
                break;

            default:
                throw new CommandCodeNotSupportedException(
                        BasicNfcCommandLibrary.class.getSimpleName() +
                                " doesn't support response code " + String.format("%02X", message.getCommandCode()));
        }
        parsedMessage.parsePayload(message.getPayload());
        return parsedMessage;
    }

    @Override
    public com.taptrack.tcmptappy.tcmp.TCMPMessage parseResponse(com.taptrack.tcmptappy.tcmp.TCMPMessage message) throws ResponseCodeNotSupportedException, MalformedPayloadException {
        TCMPMessage parsedMessage;
        switch (message.getCommandCode()) {
            case BasicNfcErrorResponse.COMMAND_CODE:
                parsedMessage = new BasicNfcErrorResponse();
                break;

            case BasicNfcLibraryVersionResponse.COMMAND_CODE:
                parsedMessage = new BasicNfcLibraryVersionResponse();
                break;

            case NdefFoundResponse.COMMAND_CODE:
                parsedMessage = new NdefFoundResponse();
                break;

            case ScanTimeoutResponse.COMMAND_CODE:
                parsedMessage = new ScanTimeoutResponse();
                break;

            case TagFoundResponse.COMMAND_CODE:
                parsedMessage = new TagFoundResponse();
                break;

            case SignedTagFoundResponse.COMMAND_CODE:
                parsedMessage = new SignedTagFoundResponse();
                break;

            case TagWrittenResponse.COMMAND_CODE:
                parsedMessage = new TagWrittenResponse();
                break;

            case TagLockedResponse.COMMAND_CODE:
                parsedMessage = new TagLockedResponse();
                break;

            default:
                throw new ResponseCodeNotSupportedException(
                        BasicNfcCommandLibrary.class.getSimpleName() +
                                " doesn't support response code " + String.format("%02X", message.getCommandCode()));
        }
        parsedMessage.parsePayload(message.getPayload());
        return parsedMessage;
    }

    @Override
    public byte[] getCommandFamilyId() {
        return FAMILY_ID;
    }
}
