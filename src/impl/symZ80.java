
//----------------------------------------------------
// The following code was generated by CUP v0.11a beta 20060608
// Tue Aug 12 12:11:11 CEST 2008
//----------------------------------------------------

package impl;

/** CUP generated interface containing symbol constants. */
public interface symZ80 {
  /* terminals */
  public static final int OPERATOR_AND = 120;
  public static final int RESERVED_RRCA = 58;
  public static final int OPERATOR_SUBTRACT = 113;
  public static final int RESERVED_CPD = 9;
  public static final int RESERVED_OUTI = 43;
  public static final int RESERVED_LDDR = 33;
  public static final int RESERVED_ADD = 3;
  public static final int RESERVED_ADC = 2;
  public static final int OPERATOR_XOR = 122;
  public static final int RESERVED_OUTD = 42;
  public static final int PREPROCESSOR_INCLUDE = 88;
  public static final int RESERVED_JR = 30;
  public static final int SEPARATOR_RPAR = 107;
  public static final int PREPROCESSOR_ENDM = 83;
  public static final int RESERVED_JP = 29;
  public static final int RESERVED_DI = 16;
  public static final int RESERVED_BIT = 5;
  public static final int SEPARATOR_COMMA = 108;
  public static final int RESERVED_CCF = 7;
  public static final int RESERVED_PO = 76;
  public static final int RESERVED_INI = 27;
  public static final int REGISTERS_R = 105;
  public static final int OPERATOR_LE = 126;
  public static final int TIDENTIFIER = 132;
  public static final int RESERVED_SRL = 66;
  public static final int RESERVED_IND = 25;
  public static final int RESERVED_INC = 24;
  public static final int OPERATOR_DIVIDE = 115;
  public static final int REGISTERS_L = 95;
  public static final int PREPROCESSOR_ORG = 77;
  public static final int RESERVED_PE = 75;
  public static final int RESERVED_SLA = 64;
  public static final int REGISTERS_I = 104;
  public static final int RESERVED_RLCA = 53;
  public static final int REGISTERS_H = 94;
  public static final int RESERVED_RLD = 54;
  public static final int RESERVED_RLC = 52;
  public static final int REGISTERS_E = 93;
  public static final int RESERVED_SRA = 65;
  public static final int REGISTERS_D = 92;
  public static final int RESERVED_RLA = 51;
  public static final int REGISTERS_C = 91;
  public static final int RESERVED_LDIR = 35;
  public static final int RESERVED_SET = 63;
  public static final int RESERVED_PUSH = 45;
  public static final int REGISTERS_B = 90;
  public static final int RESERVED_RRD = 59;
  public static final int REGISTERS_A = 89;
  public static final int RESERVED_CP = 8;
  public static final int RESERVED_RRC = 57;
  public static final int RESERVED_RRA = 56;
  public static final int RESERVED_RET = 47;
  public static final int RESERVED_RES = 46;
  public static final int RESERVED_IN = 23;
  public static final int PREPROCESSOR_ADDR = 87;
  public static final int RESERVED_IM = 22;
  public static final int RESERVED_OR = 38;
  public static final int OPERATOR_GREATER = 125;
  public static final int OPERATOR_EQUAL = 123;
  public static final int OPERATOR_SHR = 117;
  public static final int LITERAL_STRING = 130;
  public static final int OPERATOR_NOT = 119;
  public static final int LITERAL_DECIMAL_16BIT = 129;
  public static final int OPERATOR_SHL = 118;
  public static final int RESERVED_NZ = 72;
  public static final int RESERVED_AND = 4;
  public static final int PREPROCESSOR_MACRO = 82;
  public static final int RESERVED_CPDR = 10;
  public static final int RESERVED_DAA = 14;
  public static final int RESERVED_NEG = 36;
  public static final int REGISTERS_DE = 100;
  public static final int RESERVED_XOR = 68;
  public static final int LITERAL_DECIMAL_8BIT = 128;
  public static final int OPERATOR_MOD = 116;
  public static final int OPERATOR_MULTIPLY = 114;
  public static final int RESERVED_Z = 71;
  public static final int RESERVED_NC = 70;
  public static final int REGISTERS_IY = 97;
  public static final int REGISTERS_IX = 96;
  public static final int SEPARATOR_EOL = 109;
  public static final int RESERVED_P = 74;
  public static final int RESERVED_EXX = 20;
  public static final int RESERVED_M = 73;
  public static final int SEPARATOR_INDEXLPAR = 110;
  public static final int PREPROCESSOR_EQU = 78;
  public static final int RESERVED_RETN = 49;
  public static final int OPERATOR_OR = 121;
  public static final int RESERVED_OTDR = 39;
  public static final int RESERVED_CPIR = 12;
  public static final int RESERVED_RETI = 48;
  public static final int RESERVED_C = 69;
  public static final int RESERVED_SCF = 62;
  public static final int OPERATOR_ADD = 112;
  public static final int RESERVED_CALL = 6;
  public static final int EOF = 0;
  public static final int RESERVED_INDR = 26;
  public static final int PREPROCESSOR_VAR = 79;
  public static final int RESERVED_LDI = 34;
  public static final int RESERVED_LDD = 32;
  public static final int PREPROCESSOR_DW = 85;
  public static final int PREPROCESSOR_DS = 86;
  public static final int RESERVED_POP = 44;
  public static final int RESERVED_HALT = 21;
  public static final int RESERVED_SUB = 67;
  public static final int SEPARATOR_INDEXRPAR = 111;
  public static final int OPERATOR_LESS = 124;
  public static final int PREPROCESSOR_ENDIF = 81;
  public static final int RESERVED_OUT = 41;
  public static final int error = 1;
  public static final int REGISTERS_AFF = 103;
  public static final int REGISTERS_HL = 101;
  public static final int RESERVED_NOP = 37;
  public static final int TLABEL = 131;
  public static final int RESERVED_DEC = 15;
  public static final int REGISTERS_BC = 99;
  public static final int RESERVED_RR = 55;
  public static final int RESERVED_SBC = 61;
  public static final int RESERVED_OTIR = 40;
  public static final int PREPROCESSOR_DB = 84;
  public static final int RESERVED_RL = 50;
  public static final int RESERVED_INIR = 28;
  public static final int RESERVED_LD = 31;
  public static final int RESERVED_EX = 19;
  public static final int TCOMMENT = 133;
  public static final int SEPARATOR_LPAR = 106;
  public static final int REGISTERS_AF = 102;
  public static final int RESERVED_EI = 18;
  public static final int OPERATOR_GE = 127;
  public static final int REGISTERS_SP = 98;
  public static final int PREPROCESSOR_IF = 80;
  public static final int RESERVED_DJNZ = 17;
  public static final int RESERVED_CPL = 13;
  public static final int RESERVED_RST = 60;
  public static final int RESERVED_CPI = 11;
}

