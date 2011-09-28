/**
 * CompilerEnvironment.java
 * 
 *   KISS, YAGNI
 *
 * Copyright (C) 2009-2011 Peter Jakubčo <pjakubco at gmail.com>
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
package ramc_ram.compiled;

import java.util.ArrayList;

import ramc_ram.tree.Label;

public class CompilerEnvironment {
    private static ArrayList<Label> labels = new ArrayList<Label>();
    private static ArrayList<String> inputs = new ArrayList<String>();
    
    public static void addLabel(Label label){
    	labels.add(label);
    }
    
    public static int getLabelAddr(String label) {
    	String l = label.toUpperCase() + ":";
    	for (int i = 0; i < labels.size(); i++) {
    		Label lab = labels.get(i);
    		String ll = lab.getValue().toUpperCase();
    		if (ll.equals(l))
    			return lab.getAddress();
    	}
    	// throw new ...label undefined
    	return -1;
    }
    
    public static Label[] getLabels() {
    	return labels.toArray(new Label[0]);
    }
    
    public static void addInputs(ArrayList<String> inp) {
    	inputs.addAll(inp);
    }
    
    public static ArrayList<String> getInputs() {
    	return inputs;
    }
    
    public static void clear() {
    	inputs.clear();
    	labels.clear();
    }
}
