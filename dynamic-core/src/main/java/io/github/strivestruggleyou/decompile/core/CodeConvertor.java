package io.github.strivestruggleyou.decompile.core;

public class CodeConvertor {
	/**
	 * 十六进制 和 字节码指令 转换
	 * 
	 * @param code_everyone_hexString
	 * @return
	 */
	public static String codeConvertor(String code_everyone_hexString) {
		switch (code_everyone_hexString) {
			case "00":
				return "nop";
			case "01":
				return "aconst_null";
			case "02":
				return "iconst_m1";
			case "03":
				return "iconst_0";
			case "04":
				return "iconst_1";
			case "05":
				return "iconst_2";
			case "06":
				return "iconst_3";
			case "07":
				return "iconst_4";
			case "08":
				return "iconst_5";
			case "09":
				return "lconst_0";
			case "0a":
				return "lconst_1";
			case "0b":
				return "fconst_0";
			case "0c":
				return "fconst_1";
			case "0d":
				return "fconst_2";
			case "0e":
				return "dconst_0";
			case "0f":
				return "dconst_1";
			case "10":
				return "bipush";
			case "11":
				return "sipush";
			case "12":
				return "ldc";
			case "13":
				return "ldc_w";
			case "14":
				return "ldc2_w";
			case "15":
				return "iload";
			case "16":
				return "lload";
			case "17":
				return "fload";
			case "18":
				return "dload";
			case "19":
				return "aload";
			case "1a":
				return "iload_0";
			case "1b":
				return "iload_1";
			case "1c":
				return "iload_2";
			case "1d":
				return "iload_3";
			case "1e":
				return "lload_0";
			case "1f":
				return "lload_1";
			case "20":
				return "lload_2";
			case "21":
				return "lload_3";
			case "22":
				return "fload_0";
			case "23":
				return "fload_1";
			case "24":
				return "fload_2";
			case "25":
				return "fload_3";
			case "26":
				return "dload_0";
			case "27":
				return "dload_1";
			case "28":
				return "dload_2";
			case "29":
				return "dload_3";
			case "2a":
				return "aload_0";
			case "2b":
				return "aload_1";
			case "2c":
				return "aload_2";
			case "2d":
				return "aload_3";
			case "2e":
				return "iaload";
			case "2f":
				return "laload";
			case "30":
				return "faload";
			case "31":
				return "daload";
			case "32":
				return "aaload";
			case "33":
				return "baload";
			case "34":
				return "caload";
			case "35":
				return "saload";
			case "36":
				return "istore";
			case "37":
				return "lstore";
			case "38":
				return "fstore";
			case "39":
				return "dstore";
			case "3a":
				return "astore";
			case "3b":
				return "istore_0";
			case "3c":
				return "istore_1";
			case "3d":
				return "istore_2";
			case "3e":
				return "istore_3";
			case "3f":
				return "lstore_0";
			case "40":
				return "lstore_1";
			case "41":
				return "lstore_2";
			case "42":
				return "lstore_3";
			case "43":
				return "fstore_0";
			case "44":
				return "fstore_1";
			case "45":
				return "fstore_2";
			case "46":
				return "fstore_3";
			case "47":
				return "dstore_0";
			case "48":
				return "dstore_1";
			case "49":
				return "dstore_2";
			case "4a":
				return "dstore_3";
			case "4b":
				return "astore_0";
			case "4c":
				return "astore_1";
			case "4d":
				return "astore_2";
			case "4e":
				return "astore_3";
			case "4f":
				return "iastore";
			case "50":
				return "lastore";
			case "51":
				return "fastore";
			case "52":
				return "dastore";
			case "53":
				return "aastore";
			case "54":
				return "bastore";
			case "55":
				return "castore";
			case "56":
				return "sastore";
			case "57":
				return "pop";
			case "58":
				return "pop2";
			case "59":
				return "dup";
			case "5a":
				return "dup_x1";
			case "5b":
				return "dup_x2";
			case "5c":
				return "dup2";
			case "5d":
				return "dup2_x1";
			case "5e":
				return "dup2_x2";
			case "5f":
				return "swap";
			case "60":
				return "iadd";
			case "61":
				return "ladd";
			case "62":
				return "fadd";
			case "63":
				return "dadd";
			case "64":
				return "is";
			case "65":
				return "ls";
			case "66":
				return "fs";
			case "67":
				return "ds";
			case "68":
				return "imul";
			case "69":
				return "lmul";
			case "6a":
				return "fmul";
			case "6b":
				return "dmul";
			case "6c":
				return "idiv";
			case "6d":
				return "ldiv";
			case "6e":
				return "fdiv";
			case "6f":
				return "ddiv";
			case "70":
				return "irem";
			case "71":
				return "lrem";
			case "72":
				return "frem";
			case "73":
				return "drem";
			case "74":
				return "ineg";
			case "75":
				return "lneg";
			case "76":
				return "fneg";
			case "77":
				return "dneg";
			case "78":
				return "ishl";
			case "79":
				return "lshl";
			case "7a":
				return "ishr";
			case "7b":
				return "lshr";
			case "7c":
				return "iushr";
			case "7d":
				return "lushr";
			case "7e":
				return "iand";
			case "7f":
				return "land";
			case "80":
				return "ior";
			case "81":
				return "lor";
			case "82":
				return "ixor";
			case "83":
				return "lxor";
			case "84":
				return "iinc";
			case "85":
				return "i2l";
			case "86":
				return "i2f";
			case "87":
				return "i2d";
			case "88":
				return "l2i";
			case "89":
				return "l2f";
			case "8a":
				return "l2d";
			case "8b":
				return "f2i";
			case "8c":
				return "f2l";
			case "8d":
				return "f2d";
			case "8e":
				return "d2i";
			case "8f":
				return "d2l";
			case "90":
				return "d2f";
			case "91":
				return "i2b";
			case "92":
				return "i2c";
			case "93":
				return "i2s";
			case "94":
				return "lcmp";
			case "95":
				return "fcmpl";
			case "96":
				return "fcmpg";
			case "97":
				return "dcmpl";
			case "98":
				return "dcmpg";
			case "99":
				return "ifeq";
			case "9a":
				return "ifne";
			case "9b":
				return "iflt";
			case "9c":
				return "ifge";
			case "9d":
				return "ifgt";
			case "9e":
				return "ifle";
			case "9f":
				return "if_icmpeq";
			case "a0":
				return "if_icmpne";
			case "a1":
				return "if_icmplt";
			case "a2":
				return "if_icmpge";
			case "a3":
				return "if_icmpgt";
			case "a4":
				return "if_icmple";
			case "a5":
				return "if_acmpeq";
			case "a6":
				return "if_acmpne";
			case "a7":
				return "goto";
			case "a8":
				return "jsr";
			case "a9":
				return "ret";
			case "aa":
				return "tableswitch";
			case "ab":
				return "lookupswitch";
			case "ac":
				return "ireturn";
			case "ad":
				return "lreturn";
			case "ae":
				return "freturn";
			case "af":
				return "dreturn";
			case "b0":
				return "areturn";
			case "b1":
				return "return";
			case "b2":
				return "getstatic";
			case "b3":
				return "putstatic";
			case "b4":
				return "getfield";
			case "b5":
				return "putfield";
			case "b6":
				return "invokevirtual";
			case "b7":
				return "invokespecial";
			case "b8":
				return "invokestatic";
			case "b9":
				return "invokeinterface";
			case "ba":
				return "–";
			case "bb":
				return "new";
			case "bc":
				return "newarray";
			case "bd":
				return "anewarray";
			case "be":
				return "arraylength";
			case "bf":
				return "athrow";
			case "c0":
				return "checkcast";
			case "c1":
				return "instanceof";
			case "c2":
				return "monitorenter";
			case "c3":
				return "monitorexit";
			case "c4":
				return "wide";
			case "c5":
				return "multianewarray";
			case "c6":
				return "ifnull";
			case "c7":
				return "ifnonnull";
			case "c8":
				return "goto_w";
			case "c9":
				return "jsr_w";
			default:
				break;
		}
		return null;
	}

}
