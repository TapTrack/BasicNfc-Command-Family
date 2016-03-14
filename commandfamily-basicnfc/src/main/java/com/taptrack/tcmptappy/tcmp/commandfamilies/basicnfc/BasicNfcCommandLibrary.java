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

import com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.commands.GetBasicNfcLibraryVersionCommand;
import com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.commands.ScanNdefCommand;
import com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.commands.ScanTagCommand;
import com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.commands.StopCommand;
import com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.commands.StreamNdefCommand;
import com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.commands.StreamTagsCommand;
import com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.commands.WriteNdefCustomRecordCommand;
import com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.commands.WriteNdefTextRecordCommand;
import com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.commands.WriteNdefUriRecordCommand;
import com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.responses.BasicNfcLibraryVersionResponse;
import com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.responses.NdefFoundResponse;
import com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.responses.ScanTimeoutResponse;
import com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.responses.TagFoundResponse;
import com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.responses.TagWrittenResponse;
import com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.responses.BasicNfcErrorResponse;
import com.taptrack.tcmptappy.tcmp.MalformedPayloadException;
import com.taptrack.tcmptappy.tcmp.common.CommandCodeNotSupportedException;
import com.taptrack.tcmptappy.tcmp.common.CommandFamily;
import com.taptrack.tcmptappy.tcmp.common.ResponseCodeNotSupportedException;

public class BasicNfcCommandLibrary implements CommandFamily {
    public static final byte[] FAMILY_ID = new byte[]{0x00, 0x01};

    @Override
    public com.taptrack.tcmptappy.tcmp.TCMPMessage parseCommand(com.taptrack.tcmptappy.tcmp.TCMPMessage message) throws CommandCodeNotSupportedException, MalformedPayloadException {
        switch (message.getCommandCode()) {
            case GetBasicNfcLibraryVersionCommand.COMMAND_CODE:
                return GetBasicNfcLibraryVersionCommand.fromPayload(message.getPayload());

            case ScanNdefCommand.COMMAND_CODE:
                return ScanNdefCommand.fromPayload(message.getPayload());

            case ScanTagCommand.COMMAND_CODE:
                return ScanTagCommand.fromPayload(message.getPayload());

            case StopCommand.COMMAND_CODE:
                return StopCommand.fromPayload(message.getPayload());

            case StreamNdefCommand.COMMAND_CODE:
                return StreamNdefCommand.fromPayload(message.getPayload());

            case StreamTagsCommand.COMMAND_CODE:
                return StreamTagsCommand.fromPayload(message.getPayload());

            case WriteNdefCustomRecordCommand.COMMAND_CODE:
                return WriteNdefCustomRecordCommand.fromPayload(message.getPayload());

            case WriteNdefTextRecordCommand.COMMAND_CODE:
                return WriteNdefTextRecordCommand.fromPayload(message.getPayload());

            case WriteNdefUriRecordCommand.COMMAND_CODE:
                return WriteNdefUriRecordCommand.fromPayload(message.getPayload());

            default:
                throw new CommandCodeNotSupportedException(
                        BasicNfcCommandLibrary.class.getSimpleName() +
                                " doesn't support response code " + String.format("%02X", message.getCommandCode()));
        }
    }

    @Override
    public com.taptrack.tcmptappy.tcmp.TCMPMessage parseResponse(com.taptrack.tcmptappy.tcmp.TCMPMessage message) throws ResponseCodeNotSupportedException, MalformedPayloadException {
        switch (message.getCommandCode()) {
            case BasicNfcErrorResponse.COMMAND_CODE:
                return BasicNfcErrorResponse.fromPayload(message.getPayload());

            case BasicNfcLibraryVersionResponse.COMMAND_CODE:
                return BasicNfcLibraryVersionResponse.fromPayload(message.getPayload());

            case NdefFoundResponse.COMMAND_CODE:
                return NdefFoundResponse.fromPayload(message.getPayload());

            case ScanTimeoutResponse.COMMAND_CODE:
                return ScanTimeoutResponse.fromPayload(message.getPayload());

            case TagFoundResponse.COMMAND_CODE:
                return TagFoundResponse.fromPayload(message.getPayload());

            case TagWrittenResponse.COMMAND_CODE:
                return TagWrittenResponse.fromPayload(message.getPayload());

            default:
                throw new ResponseCodeNotSupportedException(
                        BasicNfcCommandLibrary.class.getSimpleName() +
                                " doesn't support response code " + String.format("%02X", message.getCommandCode()));
        }
    }

    @Override
    public byte[] getCommandFamilyId() {
        return FAMILY_ID;
    }
}
