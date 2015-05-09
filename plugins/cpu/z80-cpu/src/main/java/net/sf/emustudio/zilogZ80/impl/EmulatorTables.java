/*
 * Copyright (C) 2015, Peter Jakubčo
 * KISS, YAGNI, DRY
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package net.sf.emustudio.zilogZ80.impl;

public class EmulatorTables {
    
    /* parityTable[i] = (number of 1's in i is odd) ? 0 : 4, i = 0..255 */
    public final static short PARITY_TABLE[] = {
        4, 0, 0, 4, 0, 4, 4, 0, 0, 4, 4, 0, 4, 0, 0, 4, 0, 4, 4, 0, 4, 0, 0, 4, 4, 0, 0, 4, 0, 4, 4, 0, 0, 4, 4, 0, 4, 0, 0, 4, 4, 0, 0, 4, 0, 4, 4, 0, 4, 0, 0, 4, 0, 4, 4, 0, 0, 4, 4, 0, 4, 0, 0, 4,
        0, 4, 4, 0, 4, 0, 0, 4, 4, 0, 0, 4, 0, 4, 4, 0, 4, 0, 0, 4, 0, 4, 4, 0, 0, 4, 4, 0, 4, 0, 0, 4, 4, 0, 0, 4, 0, 4, 4, 0, 0, 4, 4, 0, 4, 0, 0, 4, 0, 4, 4, 0, 4, 0, 0, 4, 4, 0, 0, 4, 0, 4, 4, 0,
        0, 4, 4, 0, 4, 0, 0, 4, 4, 0, 0, 4, 0, 4, 4, 0, 4, 0, 0, 4, 0, 4, 4, 0, 0, 4, 4, 0, 4, 0, 0, 4, 4, 0, 0, 4, 0, 4, 4, 0, 0, 4, 4, 0, 4, 0, 0, 4, 0, 4, 4, 0, 4, 0, 0, 4, 4, 0, 0, 4, 0, 4, 4, 0,
        4, 0, 0, 4, 0, 4, 4, 0, 0, 4, 4, 0, 4, 0, 0, 4, 0, 4, 4, 0, 4, 0, 0, 4, 4, 0, 0, 4, 0, 4, 4, 0, 0, 4, 4, 0, 4, 0, 0, 4, 4, 0, 0, 4, 0, 4, 4, 0, 4, 0, 0, 4, 0, 4, 4, 0, 0, 4, 4, 0, 4, 0, 0, 4
    };
    
    // incTable[i] = (i & 0x28) | ((!i) << 6) | ((!(i&0xf))<<4) | (((i&0x80) > 0)<<7) | ((i==0x80)<<2);
    public final static short INC_TABLE[] = {
        80, 0, 0, 0, 0, 0, 0, 0, 8, 8, 8, 8, 8, 8, 8, 8, 16, 0, 0, 0, 0, 0, 0, 0, 8, 8, 8, 8, 8, 8, 8, 8,
        48, 32, 32, 32, 32, 32, 32, 32, 40, 40, 40, 40, 40, 40, 40, 40, 48, 32, 32, 32, 32, 32, 32, 32, 40, 40, 40, 40, 40, 40, 40, 40,
        16, 0, 0, 0, 0, 0, 0, 0, 8, 8, 8, 8, 8, 8, 8, 8, 16, 0, 0, 0, 0, 0, 0, 0, 8, 8, 8, 8, 8, 8, 8, 8,
        48, 32, 32, 32, 32, 32, 32, 32, 40, 40, 40, 40, 40, 40, 40, 40, 48, 32, 32, 32, 32, 32, 32, 32, 40, 40, 40, 40, 40, 40, 40, 40,
        144, 128, 128, 128, 128, 128, 128, 128, 136, 136, 136, 136, 136, 136, 136, 136, 144, 128, 128, 128, 128, 128, 128, 128, 136, 136, 136, 136, 136, 136, 136, 136,
        176, 160, 160, 160, 160, 160, 160, 160, 168, 168, 168, 168, 168, 168, 168, 168, 176, 160, 160, 160, 160, 160, 160, 160, 168, 168, 168, 168, 168, 168, 168, 168,
        144, 128, 128, 128, 128, 128, 128, 128, 136, 136, 136, 136, 136, 136, 136, 136, 144, 128, 128, 128, 128, 128, 128, 128, 136, 136, 136, 136, 136, 136, 136, 136,
        176, 160, 160, 160, 160, 160, 160, 160, 168, 168, 168, 168, 168, 168, 168, 168, 176, 160, 160, 160, 160, 160, 160, 160, 168, 168, 168, 168, 168, 168, 168, 168, 80
    };
    
    // decTable[i] = (i & 0x28) | ((!i) << 6) | (((i&0xf) == 0xf)<<4) | (((i&0x80) > 0)<<7) | ((i==0x7f)<<2) | 2;
    public final static short DEC_TABLE[] = {
        66, 2, 2, 2, 2, 2, 2, 2, 10, 10, 10, 10, 10, 10, 10, 26, 2, 2, 2, 2, 2, 2, 2, 2, 10, 10, 10, 10, 10, 10, 10, 26,
        34, 34, 34, 34, 34, 34, 34, 34, 42, 42, 42, 42, 42, 42, 42, 58, 34, 34, 34, 34, 34, 34, 34, 34, 42, 42, 42, 42, 42, 42, 42, 58,
        2, 2, 2, 2, 2, 2, 2, 2, 10, 10, 10, 10, 10, 10, 10, 26, 2, 2, 2, 2, 2, 2, 2, 2, 10, 10, 10, 10, 10, 10, 10, 26,
        34, 34, 34, 34, 34, 34, 34, 34, 42, 42, 42, 42, 42, 42, 42, 58, 34, 34, 34, 34, 34, 34, 34, 34, 42, 42, 42, 42, 42, 42, 42, 58,
        130, 130, 130, 130, 130, 130, 130, 130, 138, 138, 138, 138, 138, 138, 138, 154, 130, 130, 130, 130, 130, 130, 130, 130, 138, 138, 138, 138, 138, 138, 138, 154,
        162, 162, 162, 162, 162, 162, 162, 162, 170, 170, 170, 170, 170, 170, 170, 186, 162, 162, 162, 162, 162, 162, 162, 162, 170, 170, 170, 170, 170, 170, 170, 186,
        130, 130, 130, 130, 130, 130, 130, 130, 138, 138, 138, 138, 138, 138, 138, 154, 130, 130, 130, 130, 130, 130, 130, 130, 138, 138, 138, 138, 138, 138, 138, 154,
        162, 162, 162, 162, 162, 162, 162, 162, 170, 170, 170, 170, 170, 170, 170, 186, 162, 162, 162, 162, 162, 162, 162, 162, 170, 170, 170, 170, 170, 170, 170, 186
    };
    
    // used for add - determines carry and half-carry
    // cbitsTable[i] = (i & 0x10) | ((i >> 8) & 1), i = 0..511
    public final static short CBITS_TABLE[] = {
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16,
        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17,
        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17,
        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17,
        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17,};
    
    // rrcaTable[i] = ((i & 1) << 7)|(i>>1);
    public final static short RRCA_TABLE[] = {
        0, 128, 1, 129, 2, 130, 3, 131, 4, 132, 5, 133, 6, 134, 7, 135, 8, 136, 9, 137, 10, 138, 11, 139, 12, 140, 13, 141, 14, 142, 15, 143, 16, 144, 17, 145, 18, 146, 19, 147, 20, 148, 21, 149, 22, 150,
        23, 151, 24, 152, 25, 153, 26, 154, 27, 155, 28, 156, 29, 157, 30, 158, 31, 159, 32, 160, 33, 161, 34, 162, 35, 163, 36, 164, 37, 165, 38, 166, 39, 167, 40, 168, 41, 169, 42, 170, 43, 171, 44, 172, 45,
        173, 46, 174, 47, 175, 48, 176, 49, 177, 50, 178, 51, 179, 52, 180, 53, 181, 54, 182, 55, 183, 56, 184, 57, 185, 58, 186, 59, 187, 60, 188, 61, 189, 62, 190, 63, 191, 64, 192, 65, 193, 66, 194, 67, 195,
        68, 196, 69, 197, 70, 198, 71, 199, 72, 200, 73, 201, 74, 202, 75, 203, 76, 204, 77, 205, 78, 206, 79, 207, 80, 208, 81, 209, 82, 210, 83, 211, 84, 212, 85, 213, 86, 214, 87, 215, 88, 216, 89, 217, 90,
        218, 91, 219, 92, 220, 93, 221, 94, 222, 95, 223, 96, 224, 97, 225, 98, 226, 99, 227, 100, 228, 101, 229, 102, 230, 103, 231, 104, 232, 105, 233, 106, 234, 107, 235, 108, 236, 109, 237, 110, 238, 111, 239, 112, 240,
        113, 241, 114, 242, 115, 243, 116, 244, 117, 245, 118, 246, 119, 247, 120, 248, 121, 249, 122, 250, 123, 251, 124, 252, 125, 253, 126, 254, 127, 255
    };
    
    // daaTable[i] = (i & 0x80)|((i==0) << 6)|parityTable[i]
    public final static short DAA_TABLE[] = {
        68, 0, 0, 4, 0, 4, 4, 0, 0, 4, 4, 0, 4, 0, 0, 4, 0, 4, 4, 0, 4, 0, 0, 4, 4, 0, 0, 4, 0, 4, 4, 0, 0, 4, 4, 0, 4, 0, 0, 4, 4, 0, 0, 4, 0, 4, 4, 0, 4, 0, 0, 4, 0, 4, 4, 0, 0, 4, 4, 0, 4,
        0, 0, 4, 0, 4, 4, 0, 4, 0, 0, 4, 4, 0, 0, 4, 0, 4, 4, 0, 4, 0, 0, 4, 0, 4, 4, 0, 0, 4, 4, 0, 4, 0, 0, 4, 4, 0, 0, 4, 0, 4, 4, 0, 0, 4, 4, 0, 4, 0, 0, 4, 0, 4, 4, 0, 4, 0, 0, 4, 4,
        0, 0, 4, 0, 4, 4, 0, 128, 132, 132, 128, 132, 128, 128, 132, 132, 128, 128, 132, 128, 132, 132, 128, 132, 128, 128, 132, 128, 132, 132, 128, 128, 132, 132, 128, 132, 128, 128, 132, 132, 128, 128, 132, 128, 132,
        132, 128, 128, 132, 132, 128, 132, 128, 128, 132, 128, 132, 132, 128, 132, 128, 128, 132, 132, 128, 128, 132, 128, 132, 132, 128, 132, 128, 128, 132,
        128, 132, 132, 128, 128, 132, 132, 128, 132, 128, 128, 132, 128, 132, 132, 128, 132, 128, 128, 132, 132, 128, 128, 132, 128, 132, 132, 128, 128, 132,
        132, 128, 132, 128, 128, 132, 132, 128, 128, 132, 128, 132, 132, 128, 132, 128, 128, 132, 128, 132, 132, 128, 128, 132, 132, 128, 132, 128, 128, 132,};
    
    // cbits2Z80Table[i]       0..511  (i & 0x10) | (((i >> 6) ^ (i >> 5)) & 4) | ((i >> 8) & 1) | 2
    public static final short CBITS2Z80_TABLE[] = {
        2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18,
        2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22,
        6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23,
        7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23,
        3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19,
        3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19
    };
    
    // negTable[i] = (i&0x80)|((i==0)<<6)|2|((i==0x80)<<2)|(i!=0)|(((i&0x0f)!=0)<<4)
    public static final short NEG_TABLE[] = {
        66, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 3, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 3, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19,
        19, 19, 3, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 3, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 3, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19,
        19, 19, 19, 19, 19, 3, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 3, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 135, 147, 147, 147, 147, 147, 147, 147,
        147, 147, 147, 147, 147, 147, 147, 147, 131, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 131, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 131, 147, 147, 147, 147,
        147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 131, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 131, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 131, 147,
        147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 131, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147,};
    
    // cbitsZ80Table[i] = (i & 0x10) | (((i >> 6) ^ (i >> 5)) & 4) | ((i >> 8) & 1), i = 0..511
    public static final short CBITSZ80_TABLE[] = {
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16,
        4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20,
        4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20,
        5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21,
        5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21,
        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17,
        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17
    };

    /* incZ80Table[i] = (i & 0xa8) | (((i & 0xff) == 0) << 6) |(((i & 0xf) == 0) << 4) | ((i == 0x80) << 2), i = 0..256 */
    public static final short INCZ80_TABLE[] = {
        80, 0, 0, 0, 0, 0, 0, 0, 8, 8, 8, 8, 8, 8, 8, 8, 16, 0, 0, 0, 0, 0, 0, 0, 8, 8, 8, 8, 8, 8, 8, 8,
        48, 32, 32, 32, 32, 32, 32, 32, 40, 40, 40, 40, 40, 40, 40, 40, 48, 32, 32, 32, 32, 32, 32, 32, 40, 40, 40, 40, 40, 40, 40, 40,
        16, 0, 0, 0, 0, 0, 0, 0, 8, 8, 8, 8, 8, 8, 8, 8, 16, 0, 0, 0, 0, 0, 0, 0, 8, 8, 8, 8, 8, 8, 8, 8,
        48, 32, 32, 32, 32, 32, 32, 32, 40, 40, 40, 40, 40, 40, 40, 40, 48, 32, 32, 32, 32, 32, 32, 32, 40, 40, 40, 40, 40, 40, 40, 40,
        148, 128, 128, 128, 128, 128, 128, 128, 136, 136, 136, 136, 136, 136, 136, 136, 144, 128, 128, 128, 128, 128, 128, 128, 136, 136, 136, 136, 136, 136, 136, 136,
        176, 160, 160, 160, 160, 160, 160, 160, 168, 168, 168, 168, 168, 168, 168, 168, 176, 160, 160, 160, 160, 160, 160, 160, 168, 168, 168, 168, 168, 168, 168, 168,
        144, 128, 128, 128, 128, 128, 128, 128, 136, 136, 136, 136, 136, 136, 136, 136, 144, 128, 128, 128, 128, 128, 128, 128, 136, 136, 136, 136, 136, 136, 136, 136,
        176, 160, 160, 160, 160, 160, 160, 160, 168, 168, 168, 168, 168, 168, 168, 168, 176, 160, 160, 160, 160, 160, 160, 160, 168, 168, 168, 168, 168, 168, 168, 168, 80,};
    
    /* decZ80Table[i] = (i & 0xa8) | (((i & 0xff) == 0) << 6)|(((i & 0xf) == 0xf) << 4) | ((i == 0x7f) << 2) | 2, i = 0..255 */
    public static final short DECZ80_TABLE[] = {
        66, 2, 2, 2, 2, 2, 2, 2, 10, 10, 10, 10, 10, 10, 10, 26, 2, 2, 2, 2, 2, 2, 2, 2, 10, 10, 10, 10, 10, 10, 10, 26, 34, 34, 34, 34, 34, 34, 34, 34, 42, 42, 42, 42, 42, 42, 42, 58, 34, 34, 34, 34, 34, 34, 34, 34, 42, 42, 42, 42, 42, 42, 42, 58,
        2, 2, 2, 2, 2, 2, 2, 2, 10, 10, 10, 10, 10, 10, 10, 26, 2, 2, 2, 2, 2, 2, 2, 2, 10, 10, 10, 10, 10, 10, 10, 26, 34, 34, 34, 34, 34, 34, 34, 34, 42, 42, 42, 42, 42, 42, 42, 58,
        34, 34, 34, 34, 34, 34, 34, 34, 42, 42, 42, 42, 42, 42, 42, 62, 130, 130, 130, 130, 130, 130, 130, 130, 138, 138, 138, 138, 138, 138, 138, 154, 130, 130, 130, 130, 130, 130, 130, 130, 138, 138, 138, 138, 138, 138, 138, 154,
        162, 162, 162, 162, 162, 162, 162, 162, 170, 170, 170, 170, 170, 170, 170, 186, 162, 162, 162, 162, 162, 162, 162, 162, 170, 170, 170, 170, 170, 170, 170, 186, 130, 130, 130, 130, 130, 130, 130, 130, 138, 138, 138, 138, 138, 138, 138, 154,
        130, 130, 130, 130, 130, 130, 130, 130, 138, 138, 138, 138, 138, 138, 138, 154, 162, 162, 162, 162, 162, 162, 162, 162, 170, 170, 170, 170, 170, 170, 170, 186, 162, 162, 162, 162, 162, 162, 162, 162, 170, 170, 170, 170, 170, 170, 170, 186
    };
    
    // andTable[i] = (i&0x80)|((i==0)<<6)|0x10|parityTable[i];
    public static final short AND_TABLE[] = {
        84, 16, 16, 20, 16, 20, 20, 16, 16, 20, 20, 16, 20, 16, 16, 20, 16, 20, 20, 16, 20, 16, 16, 20, 20, 16, 16, 20, 16, 20, 20, 16, 16, 20, 20, 16, 20, 16, 16, 20, 20, 16, 16, 20, 16, 20, 20, 16, 20, 16, 16, 20, 16, 20, 20, 16, 16, 20, 20, 16, 20, 16, 16, 20, 16, 20, 20, 16, 20, 16, 16, 20, 20, 16, 16, 20,
        16, 20, 20, 16, 20, 16, 16, 20, 16, 20, 20, 16, 16, 20, 20, 16, 20, 16, 16, 20, 20, 16, 16, 20, 16, 20, 20, 16, 16, 20, 20, 16, 20, 16, 16, 20, 16, 20, 20, 16, 20, 16, 16, 20, 20,
        16, 16, 20, 16, 20, 20, 16, 144, 148, 148, 144, 148, 144, 144, 148, 148, 144, 144, 148, 144, 148, 148, 144, 148, 144, 144, 148, 144, 148, 148, 144, 144, 148, 148, 144, 148, 144, 144, 148, 148, 144, 144, 148, 144, 148,
        148, 144, 144, 148, 148, 144, 148, 144, 144, 148, 144, 148, 148, 144, 148, 144, 144, 148, 148, 144, 144, 148, 144, 148, 148, 144, 148, 144, 144, 148, 144, 148, 148, 144, 144, 148, 148, 144, 148, 144, 144, 148, 144, 148, 148,
        144, 148, 144, 144, 148, 148, 144, 144, 148, 144, 148, 148, 144, 144, 148, 148, 144, 148, 144, 144, 148, 148, 144, 144, 148, 144, 148, 148, 144, 148, 144, 144, 148, 144, 148, 148, 144, 144, 148, 148, 144, 148, 144, 144, 148,};
    
    // cpTable[i] = (i&0x80)|((i==0)<<6)|2
    public static final short CP_TABLE[] = {
        66, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
        2, 2, 2, 2, 2, 2, 2, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130,
        130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130,
        130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130,};
    
}