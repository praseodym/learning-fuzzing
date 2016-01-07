#include <stdio.h> 
#include "../assert.h"
#include <math.h>
#include <stdlib.h>


	 int check43(int v); 
	 void update11(int a, int b, int c);
	 int check26(int v); 
	 void update54(int a, int b, int c);
	 void update28(int a, int b, int c);
	 int check23(int v); 
	 void update14(int a, int b, int c);
	 void update61(int a, int b, int c);
	 void update60(int a, int b, int c);
	 void update55(int a, int b, int c);
	 void update58(int a, int b, int c);
	 void update46(int a, int b, int c);
	 void update9(int a, int b, int c);
	 void update62(int a, int b, int c);
	 int check22(int v); 
	 void update5(int a, int b, int c);
	 void update15(int a, int b, int c);
	 void update8(int a, int b, int c);
	 void update30(int a, int b, int c);
	 void update16(int a, int b, int c);
	 int check34(int v); 
	 void update21(int a, int b, int c);
	 void update18(int a, int b, int c);
	 int check49(int v); 
	 int check41(int v); 
	 void update44(int a, int b, int c);
	 void update47(int a, int b, int c);
	 int check25(int v); 
	 void update6(int a, int b, int c);
	 int check28(int v); 
	 void update7(int a, int b, int c);
	 void update27(int a, int b, int c);
	 void update36(int a, int b, int c);
	 void update56(int a, int b, int c);
	 void update45(int a, int b, int c);
	 int check48(int v); 
	 void update22(int a, int b, int c);
	 void update59(int a, int b, int c);
	 void update19(int a, int b, int c);
	 void update53(int a, int b, int c);
	 int check50(int v); 
	 void update40(int a, int b, int c);
	 int check46(int v); 
	 int check37(int v); 
	 int check24(int v); 
	 void update20(int a, int b, int c);
	 void update39(int a, int b, int c);
	 int check27(int v); 
	 void update17(int a, int b, int c);
	 void update57(int a, int b, int c);
	 void update12(int a, int b, int c);
	 int check33(int v); 
	 void update10(int a, int b, int c);
	 void update13(int a, int b, int c);
	 void update43(int a, int b, int c);
	 int check30(int v); 
	 int check47(int v); 
	 int check29(int v); 
	 int check39(int v); 
	 void update48(int a, int b, int c);
	 int check42(int v); 
	 void update29(int a, int b, int c);
	 void update35(int a, int b, int c);

	// inputs
	int inputs[] = {2,5,3,1,4};

	const int error_0 = 1;
	const int error_1 = 1;
	const int error_2 = 1;
	const int error_3 = 1;
	const int error_4 = 1;
	const int error_5 = 1;
	const int error_6 = 1;
	const int error_7 = 1;
	const int error_8 = 1;
	const int error_9 = 1;
	const int error_10 = 1;
	const int error_11 = 1;
	const int error_12 = 1;
	const int error_13 = 1;
	const int error_14 = 1;
	const int error_15 = 1;
	const int error_16 = 1;
	const int error_17 = 1;
	const int error_18 = 1;
	const int error_19 = 1;
	const int error_20 = 1;
	const int error_21 = 1;
	const int error_22 = 1;
	const int error_23 = 1;
	const int error_24 = 1;
	const int error_25 = 1;
	const int error_26 = 1;
	const int error_27 = 1;
	const int error_28 = 1;
	const int error_29 = 1;
	const int error_30 = 1;
	const int error_31 = 1;
	const int error_32 = 1;
	const int error_33 = 1;
	const int error_34 = 1;
	const int error_35 = 1;
	const int error_36 = 1;
	const int error_37 = 1;
	const int error_38 = 1;
	const int error_39 = 1;
	const int error_40 = 1;
	const int error_41 = 1;
	const int error_42 = 1;
	const int error_43 = 1;
	const int error_44 = 1;
	const int error_45 = 1;
	const int error_46 = 1;
	const int error_47 = 1;
	const int error_48 = 1;
	const int error_49 = 1;
	const int error_50 = 1;
	const int error_51 = 1;
	const int error_52 = 1;
	const int error_53 = 1;
	const int error_54 = 1;
	const int error_55 = 1;
	const int error_56 = 1;
	const int error_57 = 1;
	const int error_58 = 1;
	const int error_59 = 1;
	const int error_60 = 1;
	const int error_61 = 1;
	const int error_62 = 1;
	const int error_63 = 1;
	const int error_64 = 1;
	const int error_65 = 1;
	const int error_66 = 1;
	const int error_67 = 1;
	const int error_68 = 1;
	const int error_69 = 1;
	const int error_70 = 1;
	const int error_71 = 1;
	const int error_72 = 1;
	const int error_73 = 1;
	const int error_74 = 1;
	const int error_75 = 1;
	const int error_76 = 1;
	const int error_77 = 1;
	const int error_78 = 1;
	const int error_79 = 1;
	const int error_80 = 1;
	const int error_81 = 1;
	const int error_82 = 1;
	const int error_83 = 1;
	const int error_84 = 1;
	const int error_85 = 1;
	const int error_86 = 1;
	const int error_87 = 1;
	const int error_88 = 1;
	const int error_89 = 1;
	const int error_90 = 1;
	const int error_91 = 1;
	const int error_92 = 1;
	const int error_93 = 1;
	const int error_94 = 1;
	const int error_95 = 1;
	const int error_96 = 1;
	const int error_97 = 1;
	const int error_98 = 1;
	const int error_99 = 1;
	 int a180 = 13;
	 int a59 = 4;
	 int a52 = 4;
	 int a55 = 11;
	 int a20 = 1;
	 int a139 = 9;
	 int a87 = 5;
	 int a63 = 3;
	 int a176 = 4;
	 int a193 = 15;
	 int cf = 1;
	 int a107 = 12;
	 int a35 = 4;
	 int a187 = 10;
	 int a85 = 13;
	 int a21 = 11;
	 int a95 = 3;
	 int a121 = 4;
	 int a8 = 9;
	 int a159 = 6;
	 int a170 = 12;
	 int a1 = 3;
	 int a78 = 10;
	 int a101 = 15;
	 int a181 = 3;
	 int a173 = -15;
	 int a166 = 1;
	 int a106 = 1;
	 int a130 = 1;
	 int a126 = 1;
	 int a71 = -15;
	 int a19 = 1;
	 int a50 = 1;
	 int a32 = 1;
	 int a115 = 0;
	 int a132 = 1;
	 int a99 = 2;
	 int a75 = 1;
	 int a42 = 1;
	 int a117 = 1;
	 int a196 = 1;
	 int a162 = -15;
	 int a36 = 1;
	 int a155 = 1;
	 int a182 = 1;
	 int a18 = -15;
	 int a175 = 1;
	 int a135 = 3;
	 int a82 = 3;
	 int a58 = 2;
	 int a198 = 1;
	 int a136 = 1;
	 int a38 = 1;
	 int a133 = -15;
	 int a144 = 1;
	 int a131 = -15;
	 int a10 = 3;
	 int a81 = 1;
	 int a100 = 3;
	 int a168 = 1;
	 int a54 = -15;
	 int a22 = 1;
	 int a102 = 0;


	void errorCheck() {
	    if((((a55 == 12) && (a176 == 9)) && (a193 == 16))){
	    cf = 0;
	    error_0: assert(!error_0);
	    }
	    if((((a121 == 10) && check29(9)) && (a193 == 10))){
	    cf = 0;
	    error_1: assert(!error_1);
	    }
	    if((((a101 == 15) && (a176 == 8)) && (a193 == 16))){
	    cf = 0;
	    error_2: assert(!error_2);
	    }
	    if(((check46(5) && check42(6)) && (a193 == 15))){
	    cf = 0;
	    error_3: assert(!error_3);
	    }
	    if(((check22(14) && (a176 == 4)) && (a193 == 16))){
	    cf = 0;
	    error_4: assert(!error_4);
	    }
	    if((((a181 == 6) && (a78 == 7)) && (a193 == 13))){
	    cf = 0;
	    error_5: assert(!error_5);
	    }
	    if((((a55 == 15) && check48(11)) && (a193 == 11))){
	    cf = 0;
	    error_6: assert(!error_6);
	    }
	    if(((check33(7) && check42(13)) && (a193 == 15))){
	    cf = 0;
	    error_7: assert(!error_7);
	    }
	    if((((a35 == 3) && check29(12)) && (a193 == 10))){
	    cf = 0;
	    error_8: assert(!error_8);
	    }
	    if((((a95 == 5) && check41(6)) && (a193 == 17))){
	    cf = 0;
	    error_9: assert(!error_9);
	    }
	    if((((a1 == 6) && (a78 == 8)) && (a193 == 13))){
	    cf = 0;
	    error_10: assert(!error_10);
	    }
	    if((((a121 == 5) && check37(7)) && (a193 == 14))){
	    cf = 0;
	    error_11: assert(!error_11);
	    }
	    if(((check27(9) && (a21 == 12)) && (a193 == 12))){
	    cf = 0;
	    error_12: assert(!error_12);
	    }
	    if(((check29(9) && check37(4)) && (a193 == 14))){
	    cf = 0;
	    error_13: assert(!error_13);
	    }
	    if((((a121 == 4) && check29(9)) && (a193 == 10))){
	    cf = 0;
	    error_14: assert(!error_14);
	    }
	    if((((a59 == 2) && check29(11)) && (a193 == 10))){
	    cf = 0;
	    error_15: assert(!error_15);
	    }
	    if(((check50(4) && (a21 == 7)) && (a193 == 12))){
	    cf = 0;
	    error_16: assert(!error_16);
	    }
	    if(((check29(7) && check37(4)) && (a193 == 14))){
	    cf = 0;
	    error_17: assert(!error_17);
	    }
	    if((((a55 == 12) && check48(11)) && (a193 == 11))){
	    cf = 0;
	    error_18: assert(!error_18);
	    }
	    if((((a35 == 9) && check48(12)) && (a193 == 11))){
	    cf = 0;
	    error_19: assert(!error_19);
	    }
	    if(((check43(9) && (a21 == 13)) && (a193 == 12))){
	    cf = 0;
	    error_20: assert(!error_20);
	    }
	    if(((check46(8) && check42(6)) && (a193 == 15))){
	    cf = 0;
	    error_21: assert(!error_21);
	    }
	    if((((a181 == 5) && (a78 == 7)) && (a193 == 13))){
	    cf = 0;
	    error_22: assert(!error_22);
	    }
	    if((((a8 == 5) && check29(7)) && (a193 == 10))){
	    cf = 0;
	    error_23: assert(!error_23);
	    }
	    if(((check43(13) && check48(10)) && (a193 == 11))){
	    cf = 0;
	    error_24: assert(!error_24);
	    }
	    if((((a59 == 7) && check41(3)) && (a193 == 17))){
	    cf = 0;
	    error_25: assert(!error_25);
	    }
	    if(((check30(11) && check48(9)) && (a193 == 11))){
	    cf = 0;
	    error_26: assert(!error_26);
	    }
	    if((((a63 == 6) && check41(7)) && (a193 == 17))){
	    cf = 0;
	    error_27: assert(!error_27);
	    }
	    if(((check39(12) && (a78 == 10)) && (a193 == 13))){
	    cf = 0;
	    error_28: assert(!error_28);
	    }
	    if(((check30(10) && check48(9)) && (a193 == 11))){
	    cf = 0;
	    error_29: assert(!error_29);
	    }
	    if((((a139 == 11) && check29(6)) && (a193 == 10))){
	    cf = 0;
	    error_30: assert(!error_30);
	    }
	    if((((a52 == 8) && check41(9)) && (a193 == 17))){
	    cf = 0;
	    error_31: assert(!error_31);
	    }
	    if(((check47(9) && check37(2)) && (a193 == 14))){
	    cf = 0;
	    error_32: assert(!error_32);
	    }
	    if(((check24(10) && check42(8)) && (a193 == 15))){
	    cf = 0;
	    error_33: assert(!error_33);
	    }
	    if((((a20 == 1) && check41(2)) && (a193 == 17))){
	    cf = 0;
	    error_34: assert(!error_34);
	    }
	    if((((a159 == 6) && (a78 == 9)) && (a193 == 13))){
	    cf = 0;
	    error_35: assert(!error_35);
	    }
	    if((((a85 == 14) && (a21 == 6)) && (a193 == 12))){
	    cf = 0;
	    error_36: assert(!error_36);
	    }
	    if(((check39(6) && (a78 == 10)) && (a193 == 13))){
	    cf = 0;
	    error_37: assert(!error_37);
	    }
	    if((((a187 == 12) && check29(13)) && (a193 == 10))){
	    cf = 0;
	    error_38: assert(!error_38);
	    }
	    if((((a59 == 3) && check29(11)) && (a193 == 10))){
	    cf = 0;
	    error_39: assert(!error_39);
	    }
	    if(((check30(17) && check48(9)) && (a193 == 11))){
	    cf = 0;
	    error_40: assert(!error_40);
	    }
	    if((((a85 == 16) && (a21 == 6)) && (a193 == 12))){
	    cf = 0;
	    error_41: assert(!error_41);
	    }
	    if((((a52 == 10) && check41(9)) && (a193 == 17))){
	    cf = 0;
	    error_42: assert(!error_42);
	    }
	    if((((a35 == 6) && check29(12)) && (a193 == 10))){
	    cf = 0;
	    error_43: assert(!error_43);
	    }
	    if(((check49(10) && check37(6)) && (a193 == 14))){
	    cf = 0;
	    error_44: assert(!error_44);
	    }
	    if((((a63 == 3) && check41(7)) && (a193 == 17))){
	    cf = 0;
	    error_45: assert(!error_45);
	    }
	    if(((check24(11) && check42(8)) && (a193 == 15))){
	    cf = 0;
	    error_46: assert(!error_46);
	    }
	    if(((check28(14) && check37(8)) && (a193 == 14))){
	    cf = 0;
	    error_47: assert(!error_47);
	    }
	    if((((a59 == 5) && check41(3)) && (a193 == 17))){
	    cf = 0;
	    error_48: assert(!error_48);
	    }
	    if(((check34(6) && (a176 == 7)) && (a193 == 16))){
	    cf = 0;
	    error_49: assert(!error_49);
	    }
	    if(((check46(7) && (a78 == 4)) && (a193 == 13))){
	    cf = 0;
	    error_50: assert(!error_50);
	    }
	    if(((check50(5) && (a21 == 7)) && (a193 == 12))){
	    cf = 0;
	    error_51: assert(!error_51);
	    }
	    if((((a55 == 11) && check48(11)) && (a193 == 11))){
	    cf = 0;
	    error_52: assert(!error_52);
	    }
	    if(((check24(9) && check42(8)) && (a193 == 15))){
	    cf = 0;
	    error_53: assert(!error_53);
	    }
	    if((((a170 == 10) && check42(12)) && (a193 == 15))){
	    cf = 0;
	    error_54: assert(!error_54);
	    }
	    if(((check34(10) && (a176 == 7)) && (a193 == 16))){
	    cf = 0;
	    error_55: assert(!error_55);
	    }
	    if((((a180 == 12) && check37(3)) && (a193 == 14))){
	    cf = 0;
	    error_56: assert(!error_56);
	    }
	    if((((a87 == 9) && (a78 == 5)) && (a193 == 13))){
	    cf = 0;
	    error_57: assert(!error_57);
	    }
	    if((((a187 == 8) && check29(8)) && (a193 == 10))){
	    cf = 0;
	    error_58: assert(!error_58);
	    }
	    if((((a85 == 14) && check48(7)) && (a193 == 11))){
	    cf = 0;
	    error_59: assert(!error_59);
	    }
	    if((((a1 == 3) && (a78 == 8)) && (a193 == 13))){
	    cf = 0;
	    error_60: assert(!error_60);
	    }
	    if((((a35 == 9) && check48(14)) && (a193 == 11))){
	    cf = 0;
	    error_61: assert(!error_61);
	    }
	    if((((a63 == 7) && (a176 == 6)) && (a193 == 16))){
	    cf = 0;
	    error_62: assert(!error_62);
	    }
	    if(((check23(8) && check48(8)) && (a193 == 11))){
	    cf = 0;
	    error_63: assert(!error_63);
	    }
	    if((((a20 == 4) && check41(2)) && (a193 == 17))){
	    cf = 0;
	    error_64: assert(!error_64);
	    }
	    if(((check25(7) && check37(5)) && (a193 == 14))){
	    cf = 0;
	    error_65: assert(!error_65);
	    }
	    if((((a139 == 10) && check29(6)) && (a193 == 10))){
	    cf = 0;
	    error_66: assert(!error_66);
	    }
	    if((((a87 == 11) && check29(10)) && (a193 == 10))){
	    cf = 0;
	    error_67: assert(!error_67);
	    }
	    if((((a180 == 11) && check37(3)) && (a193 == 14))){
	    cf = 0;
	    error_68: assert(!error_68);
	    }
	    if((((a20 == 1) && check42(10)) && (a193 == 15))){
	    cf = 0;
	    error_69: assert(!error_69);
	    }
	    if(((check43(9) && check48(10)) && (a193 == 11))){
	    cf = 0;
	    error_70: assert(!error_70);
	    }
	    if((((a107 == 16) && (a21 == 8)) && (a193 == 12))){
	    cf = 0;
	    error_71: assert(!error_71);
	    }
	    if((((a139 == 14) && check29(6)) && (a193 == 10))){
	    cf = 0;
	    error_72: assert(!error_72);
	    }
	    if((((a35 == 4) && check29(12)) && (a193 == 10))){
	    cf = 0;
	    error_73: assert(!error_73);
	    }
	    if(((check34(4) && (a176 == 7)) && (a193 == 16))){
	    cf = 0;
	    error_74: assert(!error_74);
	    }
	    if(((check49(11) && check37(6)) && (a193 == 14))){
	    cf = 0;
	    error_75: assert(!error_75);
	    }
	    if(((check23(8) && check42(9)) && (a193 == 15))){
	    cf = 0;
	    error_76: assert(!error_76);
	    }
	    if(((check46(2) && check42(6)) && (a193 == 15))){
	    cf = 0;
	    error_77: assert(!error_77);
	    }
	    if((((a121 == 4) && check37(7)) && (a193 == 14))){
	    cf = 0;
	    error_78: assert(!error_78);
	    }
	    if(((check46(4) && check42(6)) && (a193 == 15))){
	    cf = 0;
	    error_79: assert(!error_79);
	    }
	    if((((a78 == 4) && check41(5)) && (a193 == 17))){
	    cf = 0;
	    error_80: assert(!error_80);
	    }
	    if(((check25(5) && check37(5)) && (a193 == 14))){
	    cf = 0;
	    error_81: assert(!error_81);
	    }
	    if(((check29(9) && check41(8)) && (a193 == 17))){
	    cf = 0;
	    error_82: assert(!error_82);
	    }
	    if(((check27(7) && (a21 == 12)) && (a193 == 12))){
	    cf = 0;
	    error_83: assert(!error_83);
	    }
	    if((((a35 == 5) && check48(14)) && (a193 == 11))){
	    cf = 0;
	    error_84: assert(!error_84);
	    }
	    if((((a35 == 5) && check48(12)) && (a193 == 11))){
	    cf = 0;
	    error_85: assert(!error_85);
	    }
	    if(((check43(11) && check48(10)) && (a193 == 11))){
	    cf = 0;
	    error_86: assert(!error_86);
	    }
	    if((((a20 == 5) && check41(2)) && (a193 == 17))){
	    cf = 0;
	    error_87: assert(!error_87);
	    }
	    if((((a180 == 6) && check37(3)) && (a193 == 14))){
	    cf = 0;
	    error_88: assert(!error_88);
	    }
	    if(((check23(13) && check42(9)) && (a193 == 15))){
	    cf = 0;
	    error_89: assert(!error_89);
	    }
	    if(((check25(9) && check37(5)) && (a193 == 14))){
	    cf = 0;
	    error_90: assert(!error_90);
	    }
	    if(((check28(12) && check37(8)) && (a193 == 14))){
	    cf = 0;
	    error_91: assert(!error_91);
	    }
	    if((((a35 == 5) && check29(12)) && (a193 == 10))){
	    cf = 0;
	    error_92: assert(!error_92);
	    }
	    if((((a95 == 7) && check41(6)) && (a193 == 17))){
	    cf = 0;
	    error_93: assert(!error_93);
	    }
	    if(((check33(11) && check42(13)) && (a193 == 15))){
	    cf = 0;
	    error_94: assert(!error_94);
	    }
	    if(((check33(6) && check48(13)) && (a193 == 11))){
	    cf = 0;
	    error_95: assert(!error_95);
	    }
	    if(((check43(8) && check48(10)) && (a193 == 11))){
	    cf = 0;
	    error_96: assert(!error_96);
	    }
	    if((((a35 == 10) && check29(12)) && (a193 == 10))){
	    cf = 0;
	    error_97: assert(!error_97);
	    }
	    if((((a85 == 11) && check42(11)) && (a193 == 15))){
	    cf = 0;
	    error_98: assert(!error_98);
	    }
	    if(((check49(8) && check37(6)) && (a193 == 14))){
	    cf = 0;
	    error_99: assert(!error_99);
	    }
	}
 void calculate_outputm2(int input) {
    if((((input == 1) && (cf==1)) && a32 <= -22)) {
    	a144 -= (a144 - 20) < a144 ? 4 : 0;
    	cf = 0;
    	a176 = (a193 - 6);
    	update6(a176,a193,0);
    	a193 = (a176 - -12); 
    	 printf("%d\n", 23); fflush(stdout); 
    } 
    if(((cf==1) && (input == 3))) {
    	a182 -= (a182 - 20) < a182 ? 2 : 0;
    	cf = 0;
    	a59 = (a193 - 8);
    	update44(a193,a8,-1);
    	a193 = ((a59 * a59) + 13); 
    	 printf("%d\n", 22); fflush(stdout); 
    } 
    if(((cf==1) && (input == 2))) {
    	a32 += (a32 + 20) > a32 ? 3 : 0;
    	cf = 0;
    	update36(a8,a193,-59);
    	a193 = 14;
    	a187 = (a193 - 7); 
    	 printf("%d\n", 23); fflush(stdout); 
    } 
    if(((input == 5) && (cf==1))) {
    	a168 += (a168 + 20) > a168 ? 2 : 0;
    	cf = 0;
    	a193 = (a8 + 5);
    	a85 = (a8 + 7);
    	update58(a8,a193,10); 
    	 printf("%d\n", 21); fflush(stdout); 
    } 
    if((((input == 4) && (cf==1)) && a115 >= 27)) {
    	a106 += (a106 + 20) > a106 ? 3 : 0;
    	cf = 0;
    	a193 = (a8 - -7);
    	a1 = (a8 - 3);
    	a78 = (a1 - -5); 
    	 printf("%d\n", 20); fflush(stdout); 
    } 
}
 void calculate_outputm1(int input) {
    if(((a8 == 6) && (cf==1))) {
    	calculate_outputm2(input);
    } 
}
 void calculate_outputm4(int input) {
    if(((cf==1) && (input == 3))) {
    	a132 += (a132 + 20) > a132 ? 4 : 0;
    	a133 += (a133 + 20) > a133 ? 2 : 0;
    	a102 += (a102 + 20) > a102 ? 2 : 0;
    	cf = 0;
    	a59 = ((a193 + a193) - 16);
    	update43(a59,a121,-4);
    	a193 = (a59 + 13); 
    	 printf("%d\n", 19); fflush(stdout); 
    } 
    if((((input == 1) && (cf==1)) && a126 == 8364)) {
    	a32 -= (a32 - 20) < a32 ? 1 : 0;
    	cf = 0;
    	update45(a193,a193,-5);
    	update54(a193,a193,15);
    	a193 = ((a121 / a121) - -14); 
    	 printf("%d\n", 25); fflush(stdout); 
    } 
    if(((input == 2) && (cf==1))) {
    	a82 -= (a82 - 20) < a82 ? 2 : 0;
    	cf = 0;
    	a121 = ((a193 - a193) + 7);
    	update35(a121,a121,42);
    	a193 = ((a121 / a121) + 13); 
    	 printf("%d\n", 20); fflush(stdout); 
    } 
    if(((cf==1) && (input == 5))) {
    	a36 += (a36 + 20) > a36 ? 2 : 0;
    	cf = 0;
    	a20 = (a121 + 2);
    	update43(a121,a121,-2);
    	a193 = ((a20 - a20) - -17); 
    	 printf("%d\n", 23); fflush(stdout); 
    } 
    if((((input == 4) && (cf==1)) && (a71 % 2==0))) {
    	a106 += (a106 + 20) > a106 ? 4 : 0;
    	cf = 0;
    	update58(a193,a121,8);
    	a193 = 11;
    	a85 = (a193 - -3); 
    	 printf("%d\n", 19); fflush(stdout); 
    } 
}
 void calculate_outputm3(int input) {
    if(((a121 == 5) && (cf==1))) {
    	calculate_outputm4(input);
    } 
}
 void calculate_outputm6(int input) {
    if(((input == 5) && (cf==1))) {
    	a36 += (a36 + 20) > a36 ? 1 : 0;
    	cf = 0;
    	a35 = (a85 - 5);
    	update57(a85,a85,-157); 
    	 printf("%d\n", 20); fflush(stdout); 
    } 
    if(((cf==1) && (input == 2))) {
    	a130 += (a130 + 20) > a130 ? 1 : 0;
    	cf = 0;
    	 
    	 printf("%d\n", 25); fflush(stdout); 
    } 
    if((((input == 3) && (cf==1)) && a198 <= -44)) {
    	a166 += (a166 + 20) > a166 ? 4 : 0;
    	cf = 0;
    	a35 = (a193 - 6);
    	update19(a85,a35,4);
    	a193 = (a85 + -3); 
    	 printf("%d\n", 25); fflush(stdout); 
    } 
    if((((input == 1) && (cf==1)) && a58 >= 23)) {
    	cf = 0;
    	a21 = (a85 - 6);
    	update61(a21,a193,9);
    	a193 = (a21 - -5); 
    	 printf("%d\n", 20); fflush(stdout); 
    } 
}
 void calculate_outputm5(int input) {
    if(((cf==1) && (a85 == 13))) {
    	calculate_outputm6(input);
    } 
}
 void calculate_outputm8(int input) {
    if((((cf==1) && (input == 1)) && a81 == 6156)) {
    	a175 += (a175 + 20) > a175 ? 2 : 0;
    	cf = 0;
    	a193 = (a35 + 5);
    	a78 = (a35 + -4);
    	update54(a78,a193,10); 
    	 printf("%d\n", 19); fflush(stdout); 
    } 
    if((((cf==1) && (input == 4)) && a10 >= 23)) {
    	a130 += (a130 + 20) > a130 ? 1 : 0;
    	cf = 0;
    	update58(a35,a35,3);
    	update27(a35,a35,-58); 
    	 printf("%d\n", 21); fflush(stdout); 
    } 
    if(((cf==1) && (input == 3))) {
    	cf = 0;
    	a193 = (a35 + 9);
    	a59 = (a35 - 4);
    	update43(a193,a59,10); 
    	 printf("%d\n", 20); fflush(stdout); 
    } 
    if(((input == 5) && (cf==1))) {
    	a58 -= (a58 - 20) < a58 ? 4 : 0;
    	cf = 0;
    	update43(a193,a35,0);
    	a193 = (a35 - -9);
    	a59 = (a193 - 13); 
    	 printf("%d\n", 19); fflush(stdout); 
    } 
    if(((cf==1) && (input == 2))) {
    	a162 += (a162 + 20) > a162 ? 2 : 0;
    	a54 += (a54 + 20) > a54 ? 2 : 0;
    	a166 += (a166 + 20) > a166 ? 4 : 0;
    	cf = 0;
    	a121 = (a35 - 3);
    	update20(a193,a121,7);
    	a193 = (a121 - -5); 
    	 printf("%d\n", 25); fflush(stdout); 
    } 
}
 void calculate_outputm7(int input) {
    if(((cf==1) && (a35 == 8))) {
    	calculate_outputm8(input);
    } 
}
 void calculate_outputm10(int input) {
    if(((input == 5) && (cf==1))) {
    	cf = 0;
    	a193 = ((a21 * a21) - 65);
    	a176 = ((a193 + a193) - 29);
    	a21 = (a193 - 3); 
    	 printf("%d\n", 25); fflush(stdout); 
    } 
    if(((input == 3) && (cf==1))) {
    	a106 += (a106 + 20) > a106 ? 1 : 0;
    	cf = 0;
    	a193 = (a21 - -8);
    	update44(a21,a21,3);
    	a59 = (a21 + -5); 
    	 printf("%d\n", 22); fflush(stdout); 
    } 
    if((((cf==1) && (input == 1)) && a144 >= 21)) {
    	a10 -= (a10 - 20) < a10 ? 2 : 0;
    	cf = 0;
    	update44(a21,a21,3);
    	a59 = ((a21 - a193) - -10);
    	a193 = ((a59 - a59) + 17); 
    	 printf("%d\n", 19); fflush(stdout); 
    } 
    if((((cf==1) && (input == 4)) && (a131 % 2==0))) {
    	a115 -= (a115 - 20) < a115 ? 3 : 0;
    	cf = 0;
    	a35 = (a193 - 6);
    	update19(a35,a193,18);
    	a193 = (a35 - -4); 
    	 printf("%d\n", 22); fflush(stdout); 
    } 
    if(((cf==1) && (input == 2))) {
    	a136 += (a136 + 20) > a136 ? 2 : 0;
    	a144 += (a144 + 20) > a144 ? 1 : 0;
    	a100 += (a100 + 20) > a100 ? 1 : 0;
    	cf = 0;
    	a193 = (a21 - -8);
    	update44(a21,a193,12);
    	update30(a21,a21,74); 
    	 printf("%d\n", 26); fflush(stdout); 
    } 
}
 void calculate_outputm9(int input) {
    if(((cf==1) && check26(8))) {
    	calculate_outputm10(input);
    } 
}
 void calculate_outputm12(int input) {
    if((((cf==1) && (input == 3)) && a102 >= 38)) {
    	a106 -= (a106 - 20) < a106 ? 2 : 0;
    	cf = 0;
    	update44(a21,a193,4);
    	a78 = (a193 - 8);
    	a193 = ((a78 / a78) - -16); 
    	 printf("%d\n", 22); fflush(stdout); 
    } 
    if(((cf==1) && (input == 2))) {
    	a42 += (a42 + 20) > a42 ? 1 : 0;
    	cf = 0;
    	update58(a193,a193,12);
    	a35 = ((a193 / a21) - -8);
    	a193 = ((a35 / a35) + 10); 
    	 printf("%d\n", 21); fflush(stdout); 
    } 
}
 void calculate_outputm11(int input) {
    if(((cf==1) && check43(7))) {
    	calculate_outputm12(input);
    } 
}
 void calculate_outputm14(int input) {
    if(((input == 3) && (cf==1))) {
    	cf = 0;
    	update57(a193,a193,-155);
    	a193 = (a78 - -7);
    	a35 = ((a78 / a78) - -4); 
    	 printf("%d\n", 19); fflush(stdout); 
    } 
    if((((input == 4) && (cf==1)) && a106 <= -45)) {
    	a130 += (a130 + 20) > a130 ? 3 : 0;
    	cf = 0;
    	update43(a78,a78,-9);
    	a193 = (a78 - -13);
    	a52 = (a193 + -9); 
    	 printf("%d\n", 23); fflush(stdout); 
    } 
    if(((input == 2) && (cf==1))) {
    	cf = 0;
    	a193 = ((a78 * a78) + -6);
    	update20(a78,a78,1);
    	a8 = (a193 - 4); 
    	 printf("%d\n", 22); fflush(stdout); 
    } 
    if(((cf==1) && (input == 5))) {
    	a135 -= (a135 - 20) < a135 ? 1 : 0;
    	cf = 0;
    	a193 = ((a78 + a78) + 8);
    	a176 = (a193 + -12);
    	update5(a176,a176,4); 
    	 printf("%d\n", 21); fflush(stdout); 
    } 
    if((((cf==1) && (input == 1)) && a130 <= -29)) {
    	cf = 0;
    	update21(a78,a193,-41);
    	update58(a193,a193,17);
    	a193 = 11; 
    	 printf("%d\n", 19); fflush(stdout); 
    } 
}
 void calculate_outputm13(int input) {
    if(((cf==1) && check46(4))) {
    	calculate_outputm14(input);
    } 
}
 void calculate_outputm16(int input) {
    if(((cf==1) && (input == 3))) {
    	cf = 0;
    	update44(a193,a187,-3);
    	a193 = (a187 - -10);
    	update30(a193,a187,112); 
    	 printf("%d\n", 19); fflush(stdout); 
    } 
    if((((cf==1) && (input == 5)) && (a18 % 2==0))) {
    	a115 -= (a115 - 20) < a115 ? 3 : 0;
    	cf = 0;
    	a193 = ((a187 - a187) - -10);
    	update19(a193,a193,6);
    	a139 = ((a193 * a193) + -89); 
    	 printf("%d\n", 19); fflush(stdout); 
    } 
    if((((cf==1) && (input == 2)) && a175 <= -27)) {
    	a99 -= (a99 - 20) < a99 ? 2 : 0;
    	cf = 0;
    	a176 = (a187 + 2);
    	a193 = (a176 + 7);
    	a55 = ((a176 + a176) + -6); 
    	 printf("%d\n", 22); fflush(stdout); 
    } 
}
 void calculate_outputm15(int input) {
    if(((cf==1) && (a187 == 7))) {
    	calculate_outputm16(input);
    } 
}
 void calculate_outputm18(int input) {
    if((((input == 3) && (cf==1)) && a196 == 480)) {
    	a198 += (a198 + 20) > a198 ? 2 : 0;
    	cf = 0;
    	update17(a121,a121,-11);
    	update35(a193,a193,188); 
    	 printf("%d\n", 19); fflush(stdout); 
    } 
    if((((input == 1) && (cf==1)) && a117 == 2186)) {
    	a166 += (a166 + 20) > a166 ? 3 : 0;
    	cf = 0;
    	update59(a193,a121,-8);
    	update35(a193,a193,190); 
    	 printf("%d\n", 26); fflush(stdout); 
    } 
    if(((input == 5) && (cf==1))) {
    	a36 += (a36 + 20) > a36 ? 2 : 0;
    	cf = 0;
    	a176 = (a193 + -9);
    	a193 = (a121 + 9);
    	a8 = ((a193 * a193) - 250); 
    	 printf("%d\n", 19); fflush(stdout); 
    } 
    if((((input == 4) && (cf==1)) && a36 <= -40)) {
    	a106 += (a106 + 20) > a106 ? 4 : 0;
    	cf = 0;
    	a121 = (a193 + -9); 
    	 printf("%d\n", 21); fflush(stdout); 
    } 
    if((((input == 2) && (cf==1)) && (a162 % 2==0))) {
    	a166 += (a166 + 20) > a166 ? 1 : 0;
    	cf = 0;
    	a35 = (a121 - -2);
    	a193 = ((a121 / a35) - -11);
    	update58(a35,a121,4); 
    	 printf("%d\n", 23); fflush(stdout); 
    } 
}
 void calculate_outputm17(int input) {
    if(((cf==1) && (a121 == 7))) {
    	calculate_outputm18(input);
    } 
}
 void calculate_outputm20(int input) {
    if(((cf==1) && (input == 4))) {
    	cf = 0;
    	a176 = ((a193 / a193) + 2);
    	a21 = (a193 + -2);
    	a193 = (a176 + 13); 
    	 printf("%d\n", 21); fflush(stdout); 
    } 
}
 void calculate_outputm19(int input) {
    if((check33(10) && (cf==1))) {
    	calculate_outputm20(input);
    } 
}
 void calculate_outputm22(int input) {
    if((((input == 2) && (cf==1)) && (a173 % 2==0))) {
    	a130 += (a130 + 20) > a130 ? 4 : 0;
    	cf = 0;
    	update30(a193,a176,44);
    	a176 = ((a193 - a193) + 7); 
    	 printf("%d\n", 20); fflush(stdout); 
    } 
    if(((input == 3) && (cf==1))) {
    	cf = 0;
    	a193 = a21;
    	update54(a193,a176,12);
    	a78 = ((a21 + a21) + -22); 
    	 printf("%d\n", 19); fflush(stdout); 
    } 
    if((((input == 4) && (cf==1)) && a166 <= -44)) {
    	a166 -= (a166 - 20) < a166 ? 2 : 0;
    	cf = 0;
    	a63 = (a21 + -7);
    	update43(a193,a63,3);
    	a193 = (a176 - -14); 
    	 printf("%d\n", 22); fflush(stdout); 
    } 
    if(((cf==1) && (input == 5))) {
    	cf = 0;
    	a121 = (a193 - 11);
    	update19(a121,a21,17);
    	a193 = (a176 + 7); 
    	 printf("%d\n", 25); fflush(stdout); 
    } 
}
 void calculate_outputm21(int input) {
    if(((a21 == 13) && (cf==1))) {
    	calculate_outputm22(input);
    } 
}
 void calculate_outputm24(int input) {
    if((((input == 4) && (cf==1)) && a19 == 6764)) {
    	a99 += (a99 + 20) > a99 ? 1 : 0;
    	cf = 0;
    	update36(a193,a176,-59);
    	update12(a176,a176,-3);
    	a193 = (a176 + 10); 
    	 printf("%d\n", 21); fflush(stdout); 
    } 
    if((((input == 2) && (cf==1)) && a50 == 9160)) {
    	a106 += (a106 + 20) > a106 ? 4 : 0;
    	cf = 0;
    	update36(a193,a193,-251);
    	a193 = (a176 - -10);
    	update12(a176,a176,-1); 
    	 printf("%d\n", 20); fflush(stdout); 
    } 
    if(((input == 3) && (cf==1))) {
    	cf = 0;
    	update19(a193,a193,8);
    	update44(a176,a176,8);
    	a193 = 17; 
    	 printf("%d\n", 20); fflush(stdout); 
    } 
}
 void calculate_outputm23(int input) {
    if((check22(12) && (cf==1))) {
    	calculate_outputm24(input);
    } 
}
 void calculate_outputm26(int input) {
    if(((input == 5) && (cf==1))) {
    	a82 -= (a82 - 20) < a82 ? 1 : 0;
    	cf = 0;
    	a193 = ((a176 - a8) + 15);
    	update35(a8,a193,77);
    	a121 = (a8 + 1); 
    	 printf("%d\n", 20); fflush(stdout); 
    } 
}
 void calculate_outputm27(int input) {
    if((((input == 1) && (cf==1)) && (a54 % 2==0))) {
    	a106 += (a106 + 20) > a106 ? 3 : 0;
    	cf = 0;
    	a95 = ((a193 / a176) - -4);
    	update44(a193,a193,6);
    	a193 = (a95 - -10); 
    	 printf("%d\n", 23); fflush(stdout); 
    } 
    if((((input == 3) && (cf==1)) && a168 >= 26)) {
    	cf = 0;
    	update19(a193,a8,1);
    	a121 = (a8 - -2);
    	a193 = ((a121 / a176) - -8); 
    	 printf("%d\n", 25); fflush(stdout); 
    } 
    if((((cf==1) && (input == 4)) && a22 <= -29)) {
    	a175 += (a175 + 20) > a175 ? 2 : 0;
    	cf = 0;
    	a193 = ((a176 * a8) + -28);
    	a21 = (a193 - 6);
    	a85 = ((a21 - a193) - -20); 
    	 printf("%d\n", 23); fflush(stdout); 
    } 
    if(((cf==1) && (input == 5))) {
    	a130 += (a130 + 20) > a130 ? 3 : 0;
    	cf = 0;
    	a121 = a176;
    	update19(a193,a121,-2);
    	a193 = (a8 + 2); 
    	 printf("%d\n", 25); fflush(stdout); 
    } 
    if((((cf==1) && (input == 2)) && a100 >= 31)) {
    	a115 -= (a115 - 20) < a115 ? 2 : 0;
    	cf = 0;
    	a78 = ((a176 - a193) + 21);
    	update40(a193,a78,6);
    	a193 = (a78 - -3); 
    	 printf("%d\n", 25); fflush(stdout); 
    } 
}
 void calculate_outputm25(int input) {
    if(((cf==1) && (a8 == 6))) {
    	calculate_outputm26(input);
    } 
    if(((cf==1) && (a8 == 8))) {
    	calculate_outputm27(input);
    } 
}
 void calculate_outputm29(int input) {
    if((((input == 4) && (cf==1)) && a42 <= -42)) {
    	a100 -= (a100 - 20) < a100 ? 4 : 0;
    	cf = 0;
    	update20(a193,a20,18);
    	a139 = ((a193 / a193) - -9);
    	a193 = a139; 
    	 printf("%d\n", 20); fflush(stdout); 
    } 
    if((((input == 1) && (cf==1)) && a75 == 8590)) {
    	a32 += (a32 + 20) > a32 ? 3 : 0;
    	cf = 0;
    	update57(a20,a193,-109);
    	a193 = 11;
    	update47(a193,a193,13); 
    	 printf("%d\n", 19); fflush(stdout); 
    } 
    if(((cf==1) && (input == 5))) {
    	a182 += (a182 + 20) > a182 ? 1 : 0;
    	cf = 0;
    	a193 = ((a20 - a20) - -16);
    	a176 = ((a20 / a20) + 4);
    	a8 = (a193 + -8); 
    	 printf("%d\n", 19); fflush(stdout); 
    } 
    if(((cf==1) && (input == 3))) {
    	a42 -= (a42 - 20) < a42 ? 2 : 0;
    	cf = 0;
    	update48(a193,a193,6);
    	a21 = (a20 + 6);
    	a193 = ((a21 - a21) - -12); 
    	 printf("%d\n", 26); fflush(stdout); 
    } 
    if(((input == 2) && (cf==1))) {
    	a115 -= (a115 - 20) < a115 ? 2 : 0;
    	cf = 0;
    	update30(a20,a20,42);
    	update44(a193,a20,-6); 
    	 printf("%d\n", 21); fflush(stdout); 
    } 
}
 void calculate_outputm28(int input) {
    if(((cf==1) && (a20 == 7))) {
    	calculate_outputm29(input);
    } 
}
 void calculate_outputm31(int input) {
    if((((input == 1) && (cf==1)) && a135 >= 29)) {
    	a155 += (a155 + 20) > a155 ? 2 : 0;
    	cf = 0;
    	update57(a59,a193,-24);
    	a193 = ((a59 + a59) - -7);
    	update47(a193,a193,8); 
    	 printf("%d\n", 19); fflush(stdout); 
    } 
    if(((input == 5) && (cf==1))) {
    	a99 -= (a99 - 20) < a99 ? 3 : 0;
    	cf = 0;
    	 
    	 printf("%d\n", 20); fflush(stdout); 
    } 
    if((((cf==1) && (input == 2)) && a82 >= 21)) {
    	a166 += (a166 + 20) > a166 ? 2 : 0;
    	cf = 0;
    	a193 = ((a59 - a59) + 11);
    	a55 = (a59 + 13);
    	update58(a193,a193,11); 
    	 printf("%d\n", 19); fflush(stdout); 
    } 
}
 void calculate_outputm32(int input) {
    if(((input == 3) && (cf==1))) {
    	a173 += (a173 + 20) > a173 ? 2 : 0;
    	a166 -= (a166 - 20) < a166 ? 4 : 0;
    	a106 -= (a106 - 20) < a106 ? 2 : 0;
    	a130 -= (a130 - 20) < a130 ? 4 : 0;
    	a126 += (a126 + 20) > a126 ? 4 : 0;
    	a71 += (a71 + 20) > a71 ? 2 : 0;
    	a19 += (a19 + 20) > a19 ? 2 : 0;
    	a50 += (a50 + 20) > a50 ? 6 : 0;
    	a32 -= (a32 - 20) < a32 ? 4 : 0;
    	a115 += (a115 + 20) > a115 ? 1 : 0;
    	a155 -= (a155 - 20) < a155 ? 2 : 0;
    	a182 += (a182 + 20) > a182 ? 2 : 0;
    	a18 += (a18 + 20) > a18 ? 2 : 0;
    	a175 -= (a175 - 20) < a175 ? 4 : 0;
    	a135 += (a135 + 20) > a135 ? 2 : 0;
    	a82 += (a82 + 20) > a82 ? 1 : 0;
    	a58 += (a58 + 20) > a58 ? 1 : 0;
    	a198 -= (a198 - 20) < a198 ? 4 : 0;
    	a131 += (a131 + 20) > a131 ? 2 : 0;
    	cf = 0;
    	a193 = (a59 + 7);
    	a35 = ((a59 * a59) + -8);
    	update57(a59,a193,-32); 
    	 printf("%d\n", 25); fflush(stdout); 
    } 
    if(((input == 2) && (cf==1))) {
    	a42 -= (a42 - 20) < a42 ? 4 : 0;
    	a117 += (a117 + 20) > a117 ? 4 : 0;
    	a38 += (a38 + 20) > a38 ? 4 : 0;
    	a168 += (a168 + 20) > a168 ? 1 : 0;
    	cf = 0;
    	a193 = ((a59 * a59) + -4);
    	a21 = (a59 - -5);
    	update13(a59,a59,-8); 
    	 printf("%d\n", 26); fflush(stdout); 
    } 
    if((((cf==1) && (input == 1)) && a99 >= 24)) {
    	a32 += (a32 + 20) > a32 ? 1 : 0;
    	cf = 0;
    	update36(a59,a59,-12);
    	a193 = (a59 + 10);
    	update20(a193,a59,9); 
    	 printf("%d\n", 20); fflush(stdout); 
    } 
    if(((input == 5) && (cf==1))) {
    	a155 += (a155 + 20) > a155 ? 2 : 0;
    	cf = 0;
    	update43(a193,a193,-9);
    	a52 = (a193 - 14); 
    	 printf("%d\n", 21); fflush(stdout); 
    } 
    if((((cf==1) && (input == 4)) && a132 == 7462)) {
    	a166 += (a166 + 20) > a166 ? 3 : 0;
    	cf = 0;
    	a20 = (a59 - -1);
    	update43(a59,a193,-15); 
    	 printf("%d\n", 21); fflush(stdout); 
    } 
}
 void calculate_outputm30(int input) {
    if(((a59 == 2) && (cf==1))) {
    	calculate_outputm31(input);
    } 
    if(((cf==1) && (a59 == 4))) {
    	calculate_outputm32(input);
    } 
}
 void calculate_outputm34(int input) {
    if(((cf==1) && (input == 3))) {
    	a99 += (a99 + 20) > a99 ? 2 : 0;
    	a75 += (a75 + 20) > a75 ? 2 : 0;
    	a196 += (a196 + 20) > a196 ? 4 : 0;
    	a10 += (a10 + 20) > a10 ? 2 : 0;
    	a81 += (a81 + 20) > a81 ? 4 : 0;
    	cf = 0;
    	update20(a193,a193,25);
    	a193 = 10;
    	a121 = (a193 - 5); 
    	 printf("%d\n", 25); fflush(stdout); 
    } 
    if(((cf==1) && (input == 5))) {
    	cf = 0;
    	update19(a193,a193,9);
    	a121 = ((a193 + a193) - 29);
    	a193 = (a121 - -5); 
    	 printf("%d\n", 25); fflush(stdout); 
    } 
    if(((cf==1) && (input == 2))) {
    	a115 -= (a115 - 20) < a115 ? 4 : 0;
    	cf = 0;
    	 
    	 printf("%d\n", 21); fflush(stdout); 
    } 
}
 void calculate_outputm33(int input) {
    if(((cf==1) && check34(7))) {
    	calculate_outputm34(input);
    } 
}
 void calculate_outputm36(int input) {
    if((((input == 3) && (cf==1)) && a155 <= -28)) {
    	a135 -= (a135 - 20) < a135 ? 3 : 0;
    	cf = 0;
    	a187 = (a193 + -5);
    	update19(a187,a193,18);
    	a193 = (a187 - 2); 
    	 printf("%d\n", 19); fflush(stdout); 
    } 
    if(((cf==1) && (input == 2))) {
    	a155 += (a155 + 20) > a155 ? 4 : 0;
    	cf = 0;
    	a193 = 11;
    	update58(a193,a193,10);
    	a35 = (a193 + -3); 
    	 printf("%d\n", 19); fflush(stdout); 
    } 
    if((((cf==1) && (input == 1)) && a182 >= 27)) {
    	a42 += (a42 + 20) > a42 ? 2 : 0;
    	cf = 0;
    	update43(a193,a193,-7);
    	a63 = (a193 + -14); 
    	 printf("%d\n", 22); fflush(stdout); 
    } 
}
 void calculate_outputm35(int input) {
    if(((cf==1) && check29(8))) {
    	calculate_outputm36(input);
    } 
}
 void calculate_outputm38(int input) {
    if(((cf==1) && (input == 2))) {
    	a36 -= (a36 - 20) < a36 ? 2 : 0;
    	a22 -= (a22 - 20) < a22 ? 2 : 0;
    	a130 += (a130 + 20) > a130 ? 3 : 0;
    	cf = 0;
    	update43(a52,a52,-4);
    	update30(a193,a193,282); 
    	 printf("%d\n", 19); fflush(stdout); 
    } 
    if((((cf==1) && (input == 1)) && a136 >= 35)) {
    	cf = 0;
    	a121 = ((a193 * a52) - 47);
    	update19(a121,a193,22);
    	a193 = ((a121 / a121) + 9); 
    	 printf("%d\n", 23); fflush(stdout); 
    } 
    if((((input == 5) && (cf==1)) && a38 == 4382)) {
    	a32 += (a32 + 20) > a32 ? 3 : 0;
    	cf = 0;
    	a193 = ((a52 * a52) - -6);
    	update45(a193,a193,-10);
    	a85 = (a52 + 8); 
    	 printf("%d\n", 25); fflush(stdout); 
    } 
    if(((input == 3) && (cf==1))) {
    	a136 -= (a136 - 20) < a136 ? 3 : 0;
    	cf = 0;
    	 
    	 printf("%d\n", 21); fflush(stdout); 
    } 
    if((((input == 4) && (cf==1)) && (a133 % 2==0))) {
    	a99 -= (a99 - 20) < a99 ? 4 : 0;
    	cf = 0;
    	update15(a52,a52,6);
    	a21 = (a52 - -9);
    	a193 = a21; 
    	 printf("%d\n", 25); fflush(stdout); 
    } 
}
 void calculate_outputm37(int input) {
    if(((cf==1) && (a52 == 3))) {
    	calculate_outputm38(input);
    } 
}

 void calculate_output(int input) {
        cf = 1;

    if(((a193 == 10) && (cf==1))) {
    	if((check29(7) && (cf==1))) {
    		calculate_outputm1(input);
    	} 
    	if(((cf==1) && check29(9))) {
    		calculate_outputm3(input);
    	} 
    } 
    if(((cf==1) && (a193 == 11))) {
    	if(((cf==1) && check48(7))) {
    		calculate_outputm5(input);
    	} 
    	if(((cf==1) && check48(12))) {
    		calculate_outputm7(input);
    	} 
    } 
    if(((a193 == 12) && (cf==1))) {
    	if(((a21 == 9) && (cf==1))) {
    		calculate_outputm9(input);
    	} 
    	if(((cf==1) && (a21 == 13))) {
    		calculate_outputm11(input);
    	} 
    } 
    if(((cf==1) && (a193 == 13))) {
    	if(((cf==1) && (a78 == 4))) {
    		calculate_outputm13(input);
    	} 
    } 
    if(((a193 == 14) && (cf==1))) {
    	if((check37(1) && (cf==1))) {
    		calculate_outputm15(input);
    	} 
    	if(((cf==1) && check37(7))) {
    		calculate_outputm17(input);
    	} 
    } 
    if(((a193 == 15) && (cf==1))) {
    	if(((cf==1) && check42(13))) {
    		calculate_outputm19(input);
    	} 
    } 
    if(((a193 == 16) && (cf==1))) {
    	if(((a176 == 3) && (cf==1))) {
    		calculate_outputm21(input);
    	} 
    	if(((a176 == 4) && (cf==1))) {
    		calculate_outputm23(input);
    	} 
    	if(((cf==1) && (a176 == 5))) {
    		calculate_outputm25(input);
    	} 
    } 
    if(((cf==1) && (a193 == 17))) {
    	if(((cf==1) && check41(2))) {
    		calculate_outputm28(input);
    	} 
    	if((check41(3) && (cf==1))) {
    		calculate_outputm30(input);
    	} 
    	if(((cf==1) && check41(4))) {
    		calculate_outputm33(input);
    	} 
    	if((check41(8) && (cf==1))) {
    		calculate_outputm35(input);
    	} 
    	if((check41(9) && (cf==1))) {
    		calculate_outputm37(input);
    	} 
    } 
    errorCheck();

	if (cf==1) {
		printf("invalid_state\n");
		exit(2);
	}
}

int main() {
	#ifdef __AFL_HAVE_MANUAL_CONTROL
		__AFL_INIT();
	#endif

	// main i/o-loop
	while (1) {
		// read input
		int input = 0;
		int ret = scanf("%d", &input);
		if (ret == EOF)
			exit(0);
		else if (ret == 0) {
			printf("invalid_input\n");
			exit(1);
		} else {
			// operate eca engine
			calculate_output(input);
		}
	}
}