package com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc;

import com.taptrack.tcmptappy.tcmp.MalformedPayloadException;
import com.taptrack.tcmptappy.tcmp.TCMPMessage;
import com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.commands.GetBasicNfcLibraryVersionCommand;
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
import com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.responses.TagFoundResponse;
import com.taptrack.tcmptappy.tcmp.commandfamilies.basicnfc.responses.TagWrittenResponse;
import com.taptrack.tcmptappy.tcmp.common.CommandCodeNotSupportedException;
import com.taptrack.tcmptappy.tcmp.common.ResponseCodeNotSupportedException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class BasicNfcCommandLibraryTest {
    BasicNfcCommandLibrary library = new BasicNfcCommandLibrary();

    private static class FakeCommand extends AbstractBasicNfcMessage {

        @Override
        public void parsePayload(byte[] payload) throws MalformedPayloadException {

        }

        @Override
        public byte[] getPayload() {
            return new byte[0];
        }

        @Override
        public byte getCommandCode() {
            return 0x76;
        }
    }

    private static class FakeResponse extends AbstractBasicNfcMessage {

        @Override
        public void parsePayload(byte[] payload) throws MalformedPayloadException {

        }

        @Override
        public byte[] getPayload() {
            return new byte[0];
        }

        @Override
        public byte getCommandCode() {
            return 0x76;
        }
    }

    @Test
    public void testParseCommand() throws Exception {
        testCommand(new GetBasicNfcLibraryVersionCommand(),GetBasicNfcLibraryVersionCommand.class);
        testCommand(new ScanNdefCommand(), ScanNdefCommand.class);
        testCommand(new ScanTagCommand(), ScanTagCommand.class);
        testCommand(new StopCommand(), StopCommand.class);
        testCommand(new StreamNdefCommand(), StreamNdefCommand.class);
        testCommand(new StreamTagsCommand(), StreamTagsCommand.class);
        testCommand(new WriteNdefCustomMessageCommand(), WriteNdefCustomMessageCommand.class);
        testCommand(new WriteNdefTextRecordCommand(), WriteNdefTextRecordCommand.class);
        testCommand(new WriteNdefUriRecordCommand(), WriteNdefUriRecordCommand.class);

        boolean commandCodeNotSupportedThrown = false;
        try {
            testCommand(new FakeCommand(),FakeCommand.class);
        }
        catch (CommandCodeNotSupportedException e) {
            commandCodeNotSupportedThrown = true;
        }

        assertTrue(commandCodeNotSupportedThrown);
    }

    private void testCommand(TCMPMessage message,Class<? extends TCMPMessage> clazz)
            throws CommandCodeNotSupportedException, MalformedPayloadException {
        TCMPMessage parsedMessage = library.parseCommand(message);
        assertThat(parsedMessage,instanceOf(clazz));
        assertArrayEquals(message.getPayload(), parsedMessage.getPayload());
    }

    @Test
    public void testParseResponse() throws Exception {
        testResponse(new BasicNfcErrorResponse(),BasicNfcErrorResponse.class);
        testResponse(new BasicNfcLibraryVersionResponse(),BasicNfcLibraryVersionResponse.class);
        testResponse(new NdefFoundResponse(),NdefFoundResponse.class);
        testResponse(new ScanTimeoutResponse(),ScanTimeoutResponse.class);
        testResponse(new TagFoundResponse(),TagFoundResponse.class);
        testResponse(new TagWrittenResponse(),TagWrittenResponse.class);
    }

    private void testResponse(TCMPMessage message,Class<? extends TCMPMessage> clazz)
            throws ResponseCodeNotSupportedException, MalformedPayloadException {
        TCMPMessage parsedMessage = library.parseResponse(message);
        assertThat(parsedMessage,instanceOf(clazz));
        assertArrayEquals(message.getPayload(),parsedMessage.getPayload());

        boolean responseCodeNotSupportedThrown = false;
        try {
            testResponse(new FakeResponse(), FakeResponse.class);
        }
        catch (ResponseCodeNotSupportedException e) {
            responseCodeNotSupportedThrown = true;
        }

        assertTrue(responseCodeNotSupportedThrown);
    }

    @Test
    public void testGetCommandFamilyId() throws Exception {
        assertArrayEquals(library.getCommandFamilyId(),new byte[]{0x00,0x01});
    }
}