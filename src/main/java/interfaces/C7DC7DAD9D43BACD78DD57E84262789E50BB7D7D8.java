/*
 * IBrainCPUContext.java
 * CA94FB669670978583FDAF7D8A43BA801E49447E7.java
 *
 * KISS, YAGNI
 *
 * Copyright (C) 2009-2012 Peter Jakubčo <pjakubco@gmail.com>
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
 */

package interfaces;

import emulib.plugins.cpu.ICPUContext;
import emulib.plugins.device.IDeviceContext;

public interface C7DC7DAD9D43BACD78DD57E84262789E50BB7D7D8 extends ICPUContext {
    public boolean attachDevice(IDeviceContext device);
    public void detachDevice();
}
