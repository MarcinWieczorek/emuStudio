/*
 * Copyright (C) 2008-2015 Peter Jakubčo
 * KISS, YAGNI, DRY
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 */
package net.sf.emustudio.devices.mits88sio.impl;

import emulib.plugins.device.DeviceContext;

import java.util.Objects;

/**
 * This is the data port of 88-SIO card.
 * 
 * This port is attached to a CPU.
 * 
 * A read to the data port gets the buffered character, a write to the data port
 * writes the character to the device.
 */
public class Port2 implements DeviceContext<Short> {
    private final Transmitter transmitter;

    public Port2(Transmitter transmitter) {
        this.transmitter = Objects.requireNonNull(transmitter);
    }

    @Override
    public void write(Short data) {
        transmitter.writeToDevice(data);
    }

    @Override
    public Short read() {
        return transmitter.readBuffer();
    }

    @Override
    public Class<?> getDataType() {
        return Short.class;
    }
}
