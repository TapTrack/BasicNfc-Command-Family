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

import com.taptrack.tcmptappy2.MalformedPayloadException;
import com.taptrack.tcmptappy2.commandfamilies.basicnfc.AbstractBasicNfcMessage;

/**
 * Inform the Tappy to scan for tags. If the Tappy detects a tag, it will
 * stop scanning.
 *
 * The accepted values for ScanModeIndicator are specified in
 * {@link com.taptrack.tcmptappy2.commandfamilies.basicnfc.AutoPollingConstants.ScanModes}
 *
 * A heartbeat period zero disables the heartbeat. Note that only values
 * from 0-255 are accepted, other values have undefined behaviour.
 *
 * By default the Tappy with buzz when a tag enters or exists the field,
 * the buzzerDisabled paramter allows you to turn off this functionality
 */
public class AutoPollCommand extends AbstractBasicNfcMessage {
    public static final byte COMMAND_CODE = (byte)0x10;
    private byte scanModeIndicator;
    private byte heartBeatPeriod;
    private boolean buzzerDisabled;


    public AutoPollCommand() {
        super();
    }

    public AutoPollCommand(
            byte scanModeIndicator,
            byte heartbeatPeriod,
            boolean buzzerDisabled
            ) {
        this.scanModeIndicator = scanModeIndicator;
        this.heartBeatPeriod = heartbeatPeriod;
        this.buzzerDisabled = buzzerDisabled;
    }

    @Override
    public void parsePayload(byte[] payload) throws MalformedPayloadException {
        if (payload.length >= 3) {
            scanModeIndicator = payload[0];
            heartBeatPeriod = payload[1];
            buzzerDisabled = payload[2] != 0x00;
        } else {
            throw new MalformedPayloadException("Invalid raw message");
        }
    }

    @Override
    public byte[] getPayload() {
        return new byte[]{
                scanModeIndicator,
                heartBeatPeriod,
                (byte) (buzzerDisabled ? 0x01 : 0x00)
        };
    }


    /**
     * Gets the configured scanning type
     *
     * @return
     */
    public byte getScanModeIndicator() {
        return scanModeIndicator;
    }

    /**
     * Sets the scanning type
     *
     * @param scanModeIndicator the type of scanning to perform, should be
     *                          one of the SCAN_ constants in this class
     */
    public void setScanModeIndicator(byte scanModeIndicator) {
        this.scanModeIndicator = scanModeIndicator;
    }

    /**
     * Retrieve the configured heartbeat period in seconds.
     *
     * 0x00 corresponds to no heartbeat
     *
     * @return
     */
    public byte getHeartBeatPeriod() {
        return heartBeatPeriod;
    }

    /**
     * Set the heartbeat period in seconds
     *
     * 0x00 corresponds to no heartbeat
     * @param heartBeatPeriod
     */
    public void setHeartBeatPeriod(byte heartBeatPeriod) {
        this.heartBeatPeriod = heartBeatPeriod;
    }

    /**
     * Returns if the buzzer is disabled
     *
     * @return
     */
    public boolean isBuzzerDisabled() {
        return buzzerDisabled;
    }

    /**
     * Sets whether or not the buzzer should be disabled
     *
     * @param buzzerDisabled
     */
    public void setBuzzerDisabled(boolean buzzerDisabled) {
        this.buzzerDisabled = buzzerDisabled;
    }

    @Override
    public byte getCommandCode() {
        return COMMAND_CODE;
    }
}
