import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class Problem1 {
	public Random random = new Random();
	static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

	private String[] inputs = {"B","E","C","A","D"};

	public static int a167 = 14;
	public static int a60 = 16;
	public static int a124 = 11;
	public static int a86 = 11;
	public static int a56 = 12;
	public static int a41 = 3;
	public static int a153 = 10;
	public static int a85 = 6;
	public static int a101 = 10;
	public static int a135 = 6;
	public static int a168 = 4;
	public static int a159 = 10;
	public static int a10 = 11;
	public static int a18 = 10;
	public static int a148 = 7;
	public static int a146 = 6;
	public static int a34 = 1;
	public static int a170 = 7;
	public static int a69 = 4;
	public static int a102 = 10;
	public static int a166 = 5;
	public static int a50 = 9;
	public static int a1 = 15;
	public static int a36 = 10;
	public static int a65 = 11;
	public static int a105 = 10;
	public static int a31 = 15;
	public static int a68 = 7;
	public static int a52 = 11;
	public static int a131 = 12;
	public static int a9 = 5;
	public static int a123 = 4;
	public static int a151 = 12;
	public static int a63 = 8;
	public static int a53 = 12;
	public static int a160 = 6;
	public static int a164 = 7;
	public static int a42 = 11;
	public static int a119 = 7;
	public static int a114 = 8;
	public static int a20 = 14;
	public static int a73 = 12;
	public static int a190 = 9;
	public static boolean cf = true;
	public static int a144 = 9;
	public static int a95 = 11;
	public static int a61 = 8;
	public static int a158 = 5;
	public static int a94 = 10;
	public static int a29 = 5;
	public static int a193 = 1;
	public static int a7 = 1;
	public static int a78 = 0;
	public static int a134 = -15;
	public static int a187 = 1;
	public static int a39 = 0;
	public static int a185 = 2;
	public static int a38 = -15;
	public static int a83 = 1;
	public static int a186 = 1;
	public static int a118 = 2;
	public static int a57 = 0;
	public static int a165 = -15;
	public static int a181 = -15;
	public static int a30 = 0;
	public static int a26 = 3;
	public static int a157 = -15;


	private void errorCheck() {
	    if((((a31 == 15) && (a146 == 7)) && (a166 == 9))){
	    	cf = false;
	    	throw new IllegalStateException( "error_0" );
	    }
	    if((((a164 == 10) && (a119 == 4)) && (a166 == 12))){
	    	cf = false;
	    	throw new IllegalStateException( "error_1" );
	    }
	    if((((a158 == 7) && (a146 == 5)) && (a166 == 9))){
	    	cf = false;
	    	throw new IllegalStateException( "error_2" );
	    }
	    if((((a65 == 13) && (a146 == 6)) && (a166 == 9))){
	    	cf = false;
	    	throw new IllegalStateException( "error_3" );
	    }
	    if((((a29 == 3) && (a119 == 6)) && (a166 == 5))){
	    	cf = false;
	    	throw new IllegalStateException( "error_4" );
	    }
	    if((((a36 == 7) && (a151 == 9)) && (a166 == 8))){
	    	cf = false;
	    	throw new IllegalStateException( "error_5" );
	    }
	    if((((a61 == 12) && (a119 == 8)) && (a166 == 5))){
	    	cf = false;
	    	throw new IllegalStateException( "error_6" );
	    }
	    if((((a53 == 16) && (a119 == 9)) && (a166 == 12))){
	    	cf = false;
	    	throw new IllegalStateException( "error_7" );
	    }
	    if((((a159 == 8) && (a119 == 2)) && (a166 == 5))){
	    	cf = false;
	    	throw new IllegalStateException( "error_8" );
	    }
	    if((((a124 == 8) && (a119 == 9)) && (a166 == 5))){
	    	cf = false;
	    	throw new IllegalStateException( "error_9" );
	    }
	    if((((a123 == 4) && (a65 == 14)) && (a166 == 7))){
	    	cf = false;
	    	throw new IllegalStateException( "error_10" );
	    }
	    if((((a9 == 5) && (a105 == 6)) && (a166 == 6))){
	    	cf = false;
	    	throw new IllegalStateException( "error_11" );
	    }
	    if((((a31 == 11) && (a105 == 7)) && (a166 == 6))){
	    	cf = false;
	    	throw new IllegalStateException( "error_12" );
	    }
	    if((((a131 == 12) && (a65 == 16)) && (a166 == 7))){
	    	cf = false;
	    	throw new IllegalStateException( "error_13" );
	    }
	    if((((a69 == 1) && (a146 == 10)) && (a166 == 9))){
	    	cf = false;
	    	throw new IllegalStateException( "error_14" );
	    }
	    if((((a56 == 13) && (a34 == 2)) && (a166 == 10))){
	    	cf = false;
	    	throw new IllegalStateException( "error_15" );
	    }
	    if((((a94 == 8) && (a65 == 10)) && (a166 == 7))){
	    	cf = false;
	    	throw new IllegalStateException( "error_16" );
	    }
	    if((((a61 == 13) && (a119 == 8)) && (a166 == 5))){
	    	cf = false;
	    	throw new IllegalStateException( "error_17" );
	    }
	    if((((a34 == 5) && (a119 == 5)) && (a166 == 5))){
	    	cf = false;
	    	throw new IllegalStateException( "error_18" );
	    }
	    if((((a61 == 8) && (a119 == 8)) && (a166 == 5))){
	    	cf = false;
	    	throw new IllegalStateException( "error_19" );
	    }
	    if((((a63 == 14) && (a151 == 10)) && (a166 == 8))){
	    	cf = false;
	    	throw new IllegalStateException( "error_20" );
	    }
	    if((((a29 == 5) && (a119 == 6)) && (a166 == 5))){
	    	cf = false;
	    	throw new IllegalStateException( "error_21" );
	    }
	    if((((a153 == 6) && (a119 == 6)) && (a166 == 12))){
	    	cf = false;
	    	throw new IllegalStateException( "error_22" );
	    }
	    if((((a9 == 7) && (a105 == 6)) && (a166 == 6))){
	    	cf = false;
	    	throw new IllegalStateException( "error_23" );
	    }
	    if((((a190 == 11) && (a151 == 7)) && (a166 == 8))){
	    	cf = false;
	    	throw new IllegalStateException( "error_24" );
	    }
	    if((((a102 == 16) && (a105 == 10)) && (a166 == 6))){
	    	cf = false;
	    	throw new IllegalStateException( "error_25" );
	    }
	    if((((a36 == 9) && (a119 == 7)) && (a166 == 12))){
	    	cf = false;
	    	throw new IllegalStateException( "error_26" );
	    }
	    if((((a164 == 8) && (a119 == 4)) && (a166 == 12))){
	    	cf = false;
	    	throw new IllegalStateException( "error_27" );
	    }
	    if((((a60 == 10) && (a119 == 8)) && (a166 == 12))){
	    	cf = false;
	    	throw new IllegalStateException( "error_28" );
	    }
	    if((((a102 == 11) && (a105 == 10)) && (a166 == 6))){
	    	cf = false;
	    	throw new IllegalStateException( "error_29" );
	    }
	    if((((a52 == 10) && (a105 == 13)) && (a166 == 6))){
	    	cf = false;
	    	throw new IllegalStateException( "error_30" );
	    }
	    if((((a10 == 11) && (a146 == 11)) && (a166 == 9))){
	    	cf = false;
	    	throw new IllegalStateException( "error_31" );
	    }
	    if((((a124 == 13) && (a146 == 8)) && (a166 == 9))){
	    	cf = false;
	    	throw new IllegalStateException( "error_32" );
	    }
	    if((((a63 == 12) && (a151 == 10)) && (a166 == 8))){
	    	cf = false;
	    	throw new IllegalStateException( "error_33" );
	    }
	    if((((a50 == 8) && (a86 == 11)) && (a166 == 11))){
	    	cf = false;
	    	throw new IllegalStateException( "error_34" );
	    }
	    if((((a69 == 3) && (a146 == 10)) && (a166 == 9))){
	    	cf = false;
	    	throw new IllegalStateException( "error_35" );
	    }
	    if((((a31 == 13) && (a146 == 7)) && (a166 == 9))){
	    	cf = false;
	    	throw new IllegalStateException( "error_36" );
	    }
	    if((((a36 == 7) && (a119 == 3)) && (a166 == 5))){
	    	cf = false;
	    	throw new IllegalStateException( "error_37" );
	    }
	    if((((a68 == 2) && (a65 == 9)) && (a166 == 7))){
	    	cf = false;
	    	throw new IllegalStateException( "error_38" );
	    }
	    if((((a61 == 9) && (a119 == 8)) && (a166 == 5))){
	    	cf = false;
	    	throw new IllegalStateException( "error_39" );
	    }
	    if((((a18 == 12) && (a65 == 15)) && (a166 == 7))){
	    	cf = false;
	    	throw new IllegalStateException( "error_40" );
	    }
	    if((((a124 == 11) && (a34 == 3)) && (a166 == 10))){
	    	cf = false;
	    	throw new IllegalStateException( "error_41" );
	    }
	    if((((a1 == 11) && (a146 == 4)) && (a166 == 9))){
	    	cf = false;
	    	throw new IllegalStateException( "error_42" );
	    }
	    if((((a131 == 10) && (a65 == 16)) && (a166 == 7))){
	    	cf = false;
	    	throw new IllegalStateException( "error_43" );
	    }
	    if((((a158 == 11) && (a146 == 5)) && (a166 == 9))){
	    	cf = false;
	    	throw new IllegalStateException( "error_44" );
	    }
	    if((((a123 == 6) && (a65 == 14)) && (a166 == 7))){
	    	cf = false;
	    	throw new IllegalStateException( "error_45" );
	    }
	    if((((a94 == 13) && (a65 == 10)) && (a166 == 7))){
	    	cf = false;
	    	throw new IllegalStateException( "error_46" );
	    }
	    if((((a50 == 6) && (a86 == 11)) && (a166 == 11))){
	    	cf = false;
	    	throw new IllegalStateException( "error_47" );
	    }
	    if((((a68 == 8) && (a65 == 9)) && (a166 == 7))){
	    	cf = false;
	    	throw new IllegalStateException( "error_48" );
	    }
	    if((((a123 == 8) && (a65 == 14)) && (a166 == 7))){
	    	cf = false;
	    	throw new IllegalStateException( "error_49" );
	    }
	    if((((a18 == 8) && (a105 == 8)) && (a166 == 6))){
	    	cf = false;
	    	throw new IllegalStateException( "error_50" );
	    }
	    if((((a42 == 16) && (a34 == 5)) && (a166 == 10))){
	    	cf = false;
	    	throw new IllegalStateException( "error_51" );
	    }
	    if((((a135 == 3) && (a151 == 14)) && (a166 == 8))){
	    	cf = false;
	    	throw new IllegalStateException( "error_52" );
	    }
	    if((((a34 == 4) && (a119 == 5)) && (a166 == 5))){
	    	cf = false;
	    	throw new IllegalStateException( "error_53" );
	    }
	    if((((a164 == 11) && (a119 == 4)) && (a166 == 12))){
	    	cf = false;
	    	throw new IllegalStateException( "error_54" );
	    }
	    if((((a18 == 9) && (a105 == 8)) && (a166 == 6))){
	    	cf = false;
	    	throw new IllegalStateException( "error_55" );
	    }
	    if((((a148 == 6) && (a65 == 13)) && (a166 == 7))){
	    	cf = false;
	    	throw new IllegalStateException( "error_56" );
	    }
	    if((((a123 == 9) && (a65 == 14)) && (a166 == 7))){
	    	cf = false;
	    	throw new IllegalStateException( "error_57" );
	    }
	    if((((a41 == 8) && (a151 == 8)) && (a166 == 8))){
	    	cf = false;
	    	throw new IllegalStateException( "error_58" );
	    }
	    if((((a95 == 5) && (a105 == 9)) && (a166 == 6))){
	    	cf = false;
	    	throw new IllegalStateException( "error_59" );
	    }
	    if((((a168 == 8) && (a119 == 7)) && (a166 == 5))){
	    	cf = false;
	    	throw new IllegalStateException( "error_60" );
	    }
	    if((((a114 == 13) && (a105 == 12)) && (a166 == 6))){
	    	cf = false;
	    	throw new IllegalStateException( "error_61" );
	    }
	    if((((a158 == 8) && (a146 == 5)) && (a166 == 9))){
	    	cf = false;
	    	throw new IllegalStateException( "error_62" );
	    }
	    if((((a18 == 13) && (a86 == 10)) && (a166 == 11))){
	    	cf = false;
	    	throw new IllegalStateException( "error_63" );
	    }
	    if((((a123 == 5) && (a65 == 14)) && (a166 == 7))){
	    	cf = false;
	    	throw new IllegalStateException( "error_64" );
	    }
	    if((((a153 == 4) && (a34 == 6)) && (a166 == 10))){
	    	cf = false;
	    	throw new IllegalStateException( "error_65" );
	    }
	    if((((a158 == 8) && (a34 == 7)) && (a166 == 10))){
	    	cf = false;
	    	throw new IllegalStateException( "error_66" );
	    }
	    if((((a29 == 6) && (a119 == 6)) && (a166 == 5))){
	    	cf = false;
	    	throw new IllegalStateException( "error_67" );
	    }
	    if((((a131 == 6) && (a65 == 16)) && (a166 == 7))){
	    	cf = false;
	    	throw new IllegalStateException( "error_68" );
	    }
	    if((((a95 == 4) && (a34 == 4)) && (a166 == 10))){
	    	cf = false;
	    	throw new IllegalStateException( "error_69" );
	    }
	    if((((a153 == 11) && (a119 == 6)) && (a166 == 12))){
	    	cf = false;
	    	throw new IllegalStateException( "error_70" );
	    }
	    if((((a168 == 5) && (a119 == 7)) && (a166 == 5))){
	    	cf = false;
	    	throw new IllegalStateException( "error_71" );
	    }
	    if((((a18 == 6) && (a105 == 8)) && (a166 == 6))){
	    	cf = false;
	    	throw new IllegalStateException( "error_72" );
	    }
	    if((((a61 == 12) && (a151 == 13)) && (a166 == 8))){
	    	cf = false;
	    	throw new IllegalStateException( "error_73" );
	    }
	    if((((a170 == 14) && (a86 == 14)) && (a166 == 11))){
	    	cf = false;
	    	throw new IllegalStateException( "error_74" );
	    }
	    if((((a41 == 10) && (a151 == 8)) && (a166 == 8))){
	    	cf = false;
	    	throw new IllegalStateException( "error_75" );
	    }
	    if((((a53 == 9) && (a119 == 9)) && (a166 == 12))){
	    	cf = false;
	    	throw new IllegalStateException( "error_76" );
	    }
	    if((((a69 == 7) && (a151 == 12)) && (a166 == 8))){
	    	cf = false;
	    	throw new IllegalStateException( "error_77" );
	    }
	    if((((a151 == 11) && (a105 == 11)) && (a166 == 6))){
	    	cf = false;
	    	throw new IllegalStateException( "error_78" );
	    }
	    if((((a69 == 1) && (a151 == 12)) && (a166 == 8))){
	    	cf = false;
	    	throw new IllegalStateException( "error_79" );
	    }
	    if((((a95 == 10) && (a34 == 4)) && (a166 == 10))){
	    	cf = false;
	    	throw new IllegalStateException( "error_80" );
	    }
	    if((((a170 == 11) && (a86 == 14)) && (a166 == 11))){
	    	cf = false;
	    	throw new IllegalStateException( "error_81" );
	    }
	    if((((a20 == 7) && (a86 == 13)) && (a166 == 11))){
	    	cf = false;
	    	throw new IllegalStateException( "error_82" );
	    }
	    if((((a146 == 9) && (a86 == 8)) && (a166 == 11))){
	    	cf = false;
	    	throw new IllegalStateException( "error_83" );
	    }
	    if((((a56 == 12) && (a34 == 2)) && (a166 == 10))){
	    	cf = false;
	    	throw new IllegalStateException( "error_84" );
	    }
	    if((((a63 == 10) && (a151 == 10)) && (a166 == 8))){
	    	cf = false;
	    	throw new IllegalStateException( "error_85" );
	    }
	    if((((a135 == 4) && (a151 == 14)) && (a166 == 8))){
	    	cf = false;
	    	throw new IllegalStateException( "error_86" );
	    }
	    if((((a18 == 6) && (a86 == 10)) && (a166 == 11))){
	    	cf = false;
	    	throw new IllegalStateException( "error_87" );
	    }
	    if((((a144 == 14) && (a34 == 1)) && (a166 == 10))){
	    	cf = false;
	    	throw new IllegalStateException( "error_88" );
	    }
	    if((((a101 == 13) && (a65 == 11)) && (a166 == 7))){
	    	cf = false;
	    	throw new IllegalStateException( "error_89" );
	    }
	    if((((a124 == 7) && (a119 == 9)) && (a166 == 5))){
	    	cf = false;
	    	throw new IllegalStateException( "error_90" );
	    }
	    if((((a167 == 9) && (a119 == 3)) && (a166 == 12))){
	    	cf = false;
	    	throw new IllegalStateException( "error_91" );
	    }
	    if((((a61 == 14) && (a151 == 13)) && (a166 == 8))){
	    	cf = false;
	    	throw new IllegalStateException( "error_92" );
	    }
	    if((((a153 == 8) && (a34 == 6)) && (a166 == 10))){
	    	cf = false;
	    	throw new IllegalStateException( "error_93" );
	    }
	    if((((a42 == 14) && (a34 == 5)) && (a166 == 10))){
	    	cf = false;
	    	throw new IllegalStateException( "error_94" );
	    }
	    if((((a1 == 8) && (a146 == 4)) && (a166 == 9))){
	    	cf = false;
	    	throw new IllegalStateException( "error_95" );
	    }
	    if((((a131 == 8) && (a65 == 16)) && (a166 == 7))){
	    	cf = false;
	    	throw new IllegalStateException( "error_96" );
	    }
	    if((((a159 == 15) && (a119 == 2)) && (a166 == 5))){
	    	cf = false;
	    	throw new IllegalStateException( "error_97" );
	    }
	    if((((a18 == 12) && (a105 == 8)) && (a166 == 6))){
	    	cf = false;
	    	throw new IllegalStateException( "error_98" );
	    }
	    if((((a10 == 9) && (a146 == 11)) && (a166 == 9))){
	    	cf = false;
	    	throw new IllegalStateException( "error_99" );
	    }
	}private  void calculateOutputm2(String input) {
    if((((input.equals("A")) && cf) && a83 == 6500)) {
    	cf = false;
    	a151 = (a159 - 1);
    	a166 = ((a119 / a159) - -8);
    	a41 = (a159 + -1); 
    	System.out.println("W");
    } 
    if(((input.equals("C")) && cf)) {
    	a39 -= (a39 - 20) < a39 ? 4 : 0;
    	cf = false;
    	a166 = (a159 + 3);
    	a153 = (a159 - -1);
    	a119 = (a153 + -4); 
    	System.out.println("Z");
    } 
    if((cf && (input.equals("B")))) {
    	a118 -= (a118 - 20) < a118 ? 2 : 0;
    	cf = false;
    	a69 = (a119 - 1);
    	a146 = ((a159 / a119) + 6);
    	a166 = ((a119 * a69) - -7); 
    	System.out.println("Y");
    } 
    if(((cf && (input.equals("D"))) && (a38 % 2==0))) {
    	a185 -= (a185 - 20) < a185 ? 2 : 0;
    	cf = false;
    	a166 = (a119 + 8);
    	a34 = ((a166 - a159) - -5);
    	a153 = (a166 - 2); 
    	System.out.println("Z");
    } 
}
private  void calculateOutputm3(String input) {
    if((cf && (input.equals("B")))) {
    	a118 -= (a118 - 20) < a118 ? 4 : 0;
    	cf = false;
    	a166 = (a119 - -4);
    	a105 = (a159 - 1);
    	a95 = (a166 + -1); 
    	System.out.println("X");
    } 
    if(((input.equals("D")) && cf)) {
    	a118 -= (a118 - 20) < a118 ? 1 : 0;
    	cf = false;
    	a151 = (a166 + 9);
    	a166 = ((a119 - a119) - -8);
    	a135 = (a151 + -4); 
    	System.out.println("S");
    } 
}
private  void calculateOutputm1(String input) {
    if((cf && (a159 == 9))) {
    	calculateOutputm2(input);
    } 
    if(((a159 == 10) && cf)) {
    	calculateOutputm3(input);
    } 
}
private  void calculateOutputm5(String input) {
    if((cf && (input.equals("B")))) {
    	cf = false;
    	a119 = (a36 + -4);
    	a159 = ((a166 - a166) - -10); 
    	System.out.println("T");
    } 
    if(((input.equals("C")) && cf)) {
    	cf = false;
    	a151 = (a166 + 9);
    	a166 = (a151 - 6);
    	a135 = (a151 - 7); 
    	System.out.println("Y");
    } 
    if((cf && (input.equals("D")))) {
    	cf = false;
    	a166 = (a119 - -9);
    	a160 = (a119 + 4);
    	a119 = (a160 + -5); 
    	System.out.println("V");
    } 
}
private  void calculateOutputm4(String input) {
    if((cf && (a36 == 6))) {
    	calculateOutputm5(input);
    } 
}
private  void calculateOutputm7(String input) {
    if((cf && (input.equals("B")))) {
    	a186 += (a186 + 20) > a186 ? 4 : 0;
    	cf = false;
    	a105 = (a119 + 9);
    	a166 = (a105 - 7);
    	a52 = ((a166 * a105) + -68); 
    	System.out.println("S");
    } 
    if(((input.equals("C")) && cf)) {
    	a30 += (a30 + 20) > a30 ? 1 : 0;
    	cf = false;
    	a166 = (a86 + 2);
    	a34 = (a166 + -2);
    	a85 = (a166 - 6); 
    	System.out.println("T");
    } 
}
private  void calculateOutputm6(String input) {
    if(((a86 == 8) && cf)) {
    	calculateOutputm7(input);
    } 
}
private  void calculateOutputm9(String input) {
    if((cf && (input.equals("A")))) {
    	cf = false;
    	a119 = (a166 + -2);
    	a36 = ((a166 * a166) - 19); 
    	System.out.println("T");
    } 
}
private  void calculateOutputm8(String input) {
    if((cf && (a168 == 4))) {
    	calculateOutputm9(input);
    } 
}
private  void calculateOutputm11(String input) {
    if((((input.equals("C")) && cf) && a193 >= 32)) {
    	cf = false;
    	a166 = (a119 - -2);
    	a34 = ((a61 * a61) - 97);
    	a124 = ((a61 + a34) - 2); 
    	System.out.println("T");
    } 
    if(((input.equals("B")) && cf)) {
    	cf = false;
    	a146 = (a61 + -4);
    	a166 = (a119 + 1);
    	a65 = ((a146 - a146) + 11); 
    	System.out.println("Z");
    } 
    if((cf && (input.equals("D")))) {
    	cf = false;
    	a166 = (a61 - -2);
    	a153 = (a119 - -2);
    	a119 = (a166 - 6); 
    	System.out.println("Z");
    } 
    if((cf && (input.equals("E")))) {
    	cf = false;
    	a105 = (a61 - 2);
    	a166 = (a61 - 4);
    	a18 = (a105 - -4); 
    	System.out.println("T");
    } 
}
private  void calculateOutputm10(String input) {
    if((cf && (a61 == 10))) {
    	calculateOutputm11(input);
    } 
}
private  void calculateOutputm13(String input) {
    if((cf && (input.equals("D")))) {
    	cf = false;
    	a166 = (a105 - -1);
    	a34 = ((a166 / a95) - -7);
    	a85 = ((a34 + a166) + -14); 
    	System.out.println("W");
    } 
    if((((input.equals("E")) && cf) && (a157 % 2==0))) {
    	a30 -= (a30 - 20) < a30 ? 4 : 0;
    	cf = false;
    	a9 = ((a95 - a105) + 8);
    	a105 = a166; 
    	System.out.println("S");
    } 
    if(((input.equals("B")) && cf)) {
    	cf = false;
    	a166 = ((a105 - a105) - -8);
    	a73 = ((a95 / a166) - -11);
    	a151 = (a166 + 3); 
    	System.out.println("S");
    } 
    if(((cf && (input.equals("A"))) && a26 >= 22)) {
    	cf = false;
    	a166 = (a105 - -3);
    	a119 = ((a105 + a95) - 13);
    	a164 = ((a95 * a166) + -86); 
    	System.out.println("W");
    } 
    if((cf && (input.equals("C")))) {
    	a78 -= (a78 - 20) < a78 ? 1 : 0;
    	cf = false;
    	a166 = ((a105 / a105) + 4);
    	a119 = (a166 - 3);
    	a159 = ((a95 - a119) + 4); 
    	System.out.println("T");
    } 
}
private  void calculateOutputm12(String input) {
    if(((a95 == 8) && cf)) {
    	calculateOutputm13(input);
    } 
}
private  void calculateOutputm15(String input) {
    if((cf && (input.equals("C")))) {
    	a193 -= (a193 - 20) < a193 ? 4 : 0;
    	cf = false;
    	a65 = (a102 - 3);
    	a166 = ((a102 + a102) + -27);
    	a123 = (a166 + -3); 
    	System.out.println("T");
    } 
    if((cf && (input.equals("B")))) {
    	a39 -= (a39 - 20) < a39 ? 4 : 0;
    	cf = false;
    	a166 = ((a105 - a102) - -19);
    	a119 = (a166 - a105);
    	a160 = (a119 + 4); 
    	System.out.println("W");
    } 
}
private  void calculateOutputm14(String input) {
    if((cf && (a102 == 17))) {
    	calculateOutputm15(input);
    } 
}
private  void calculateOutputm17(String input) {
    if((cf && (input.equals("C")))) {
    	a187 += (a187 + 20) > a187 ? 4 : 0;
    	cf = false;
    	a146 = (a164 - 7);
    	a65 = ((a146 - a164) - -18);
    	a166 = ((a65 + a65) + -13); 
    	System.out.println("Z");
    } 
    if((cf && (input.equals("E")))) {
    	a57 -= (a57 - 20) < a57 ? 2 : 0;
    	cf = false;
    	a123 = ((a164 + a166) - 15);
    	a65 = (a164 - -1); 
    	System.out.println("S");
    } 
    if(((input.equals("D")) && cf)) {
    	a30 -= (a30 - 20) < a30 ? 1 : 0;
    	cf = false;
    	a61 = (a166 - -3);
    	a119 = (a61 - 2);
    	a166 = (a61 + -5); 
    	System.out.println("Z");
    } 
    if((((input.equals("A")) && cf) && a57 >= 41)) {
    	cf = false;
    	a151 = (a166 + 2);
    	a36 = (a65 + -5);
    	a166 = ((a36 + a36) - 6); 
    	System.out.println("W");
    } 
}
private  void calculateOutputm16(String input) {
    if((cf && (a164 == 13))) {
    	calculateOutputm17(input);
    } 
}
private  void calculateOutputm19(String input) {
    if(((input.equals("B")) && cf)) {
    	cf = false;
    	a119 = (a166 - 5);
    	a166 = (a119 + 10);
    	a160 = (a119 - -5); 
    	System.out.println("V");
    } 
}
private  void calculateOutputm18(String input) {
    if(((a123 == 7) && cf)) {
    	calculateOutputm19(input);
    } 
}
private  void calculateOutputm21(String input) {
    if((cf && (input.equals("E")))) {
    	a186 -= (a186 - 20) < a186 ? 1 : 0;
    	cf = false;
    	a65 = ((a151 - a166) + 10);
    	a94 = ((a166 / a151) - -12);
    	a166 = ((a41 + a65) - 6); 
    	System.out.println("T");
    } 
    if((((input.equals("D")) && cf) && a186 <= -28)) {
    	a57 -= (a57 - 20) < a57 ? 3 : 0;
    	cf = false;
    	a166 = (a41 - -9);
    	a119 = ((a41 * a41) - 3);
    	a153 = (a41 - -8); 
    	System.out.println("X");
    } 
    if(((input.equals("C")) && cf)) {
    	a39 -= (a39 - 20) < a39 ? 2 : 0;
    	cf = false;
    	a151 = (a41 - -8);
    	a73 = ((a151 + a166) + -7); 
    	System.out.println("S");
    } 
    if((((input.equals("A")) && cf) && a118 >= 43)) {
    	cf = false;
    	a50 = a166;
    	a86 = (a151 - -3);
    	a166 = a86; 
    	System.out.println("W");
    } 
    if(((input.equals("B")) && cf)) {
    	cf = false;
    	a151 = (a41 + 8);
    	a73 = (a166 + 4); 
    	System.out.println("X");
    } 
}
private  void calculateOutputm20(String input) {
    if(((a41 == 3) && cf)) {
    	calculateOutputm21(input);
    } 
}
private  void calculateOutputm23(String input) {
    if(((input.equals("D")) && cf)) {
    	a157 += (a157 + 20) > a157 ? 2 : 0;
    	a78 -= (a78 - 20) < a78 ? 1 : 0;
    	cf = false;
    	a86 = (a73 + a166);
    	a20 = (a151 - -1);
    	a166 = a151; 
    	System.out.println("V");
    } 
    if((cf && (input.equals("C")))) {
    	a7 += (a7 + 20) > a7 ? 4 : 0;
    	a181 += (a181 + 20) > a181 ? 2 : 0;
    	a26 += (a26 + 20) > a26 ? 2 : 0;
    	a39 -= (a39 - 20) < a39 ? 2 : 0;
    	cf = false;
    	a166 = (a73 + 1);
    	a95 = (a73 + 3);
    	a105 = ((a166 - a166) - -9); 
    	System.out.println("Z");
    } 
    if(((input.equals("B")) && cf)) {
    	a78 -= (a78 - 20) < a78 ? 1 : 0;
    	cf = false;
    	a61 = (a73 + 8);
    	a119 = (a61 - 5);
    	a166 = (a61 + -8); 
    	System.out.println("W");
    } 
    if(((cf && (input.equals("E"))) && (a134 % 2==0))) {
    	a39 -= (a39 - 20) < a39 ? 3 : 0;
    	cf = false;
    	a34 = (a151 - 6);
    	a166 = ((a73 - a151) + 16);
    	a42 = (a166 - -6); 
    	System.out.println("Y");
    } 
}
private  void calculateOutputm24(String input) {
    if((cf && (input.equals("B")))) {
    	a186 += (a186 + 20) > a186 ? 1 : 0;
    	cf = false;
    	 
    	System.out.println("S");
    } 
    if((cf && (input.equals("D")))) {
    	cf = false;
    	a166 = (a73 - 6);
    	a114 = (a151 + 2);
    	a105 = ((a166 / a151) - -12); 
    	System.out.println("Z");
    } 
    if((((input.equals("E")) && cf) && a187 <= -33)) {
    	cf = false;
    	a159 = (a73 - 4);
    	a119 = ((a166 / a166) - -1);
    	a166 = (a73 + -7); 
    	System.out.println("Z");
    } 
    if((cf && (input.equals("C")))) {
    	a26 -= (a26 - 20) < a26 ? 3 : 0;
    	cf = false;
    	 
    	System.out.println("X");
    } 
    if((((input.equals("A")) && cf) && a39 >= 40)) {
    	a193 -= (a193 - 20) < a193 ? 1 : 0;
    	cf = false;
    	a166 = (a151 + -5);
    	a105 = (a73 - 5);
    	a31 = (a166 + 5); 
    	System.out.println("Z");
    } 
}
private  void calculateOutputm22(String input) {
    if(((a73 == 5) && cf)) {
    	calculateOutputm23(input);
    } 
    if((cf && (a73 == 12))) {
    	calculateOutputm24(input);
    } 
}
private  void calculateOutputm26(String input) {
    if(((input.equals("E")) && cf)) {
    	cf = false;
    	a166 = ((a135 + a151) + -14);
    	a65 = (a166 + 7);
    	a123 = ((a151 + a65) - 19); 
    	System.out.println("S");
    } 
    if((cf && (input.equals("C")))) {
    	cf = false;
    	a61 = ((a151 - a135) + 3);
    	a119 = (a135 + 1);
    	a166 = ((a119 + a119) + -11); 
    	System.out.println("Z");
    } 
}
private  void calculateOutputm27(String input) {
    if(((cf && (input.equals("A"))) && a7 == 9554)) {
    	a185 -= (a185 - 20) < a185 ? 2 : 0;
    	cf = false;
    	a166 = (a135 - -1);
    	a86 = a135;
    	a18 = (a86 - -3); 
    	System.out.println("S");
    } 
    if((cf && (input.equals("C")))) {
    	a134 += (a134 + 20) > a134 ? 2 : 0;
    	a187 += (a187 + 20) > a187 ? 3 : 0;
    	cf = false;
    	a151 = (a166 + 3);
    	a73 = (a151 - 6); 
    	System.out.println("T");
    } 
    if((cf && (input.equals("E")))) {
    	cf = false;
    	a146 = ((a151 + a166) - 11);
    	a166 = ((a146 + a151) + -16);
    	a10 = ((a166 / a146) + 11); 
    	System.out.println("X");
    } 
}
private  void calculateOutputm25(String input) {
    if((cf && (a135 == 7))) {
    	calculateOutputm26(input);
    } 
    if(((a135 == 10) && cf)) {
    	calculateOutputm27(input);
    } 
}
private  void calculateOutputm29(String input) {
    if(((input.equals("D")) && cf)) {
    	a193 += (a193 + 20) > a193 ? 2 : 0;
    	a78 += (a78 + 20) > a78 ? 1 : 0;
    	a39 += (a39 + 20) > a39 ? 1 : 0;
    	a185 += (a185 + 20) > a185 ? 1 : 0;
    	a83 += (a83 + 20) > a83 ? 4 : 0;
    	a118 += (a118 + 20) > a118 ? 2 : 0;
    	a57 += (a57 + 20) > a57 ? 2 : 0;
    	a165 += (a165 + 20) > a165 ? 2 : 0;
    	cf = false;
    	a164 = (a65 + 2);
    	a166 = ((a65 / a164) + 7);
    	a65 = (a164 + -1); 
    	System.out.println("Y");
    } 
    if(((input.equals("B")) && cf)) {
    	a78 -= (a78 - 20) < a78 ? 2 : 0;
    	cf = false;
    	a65 = ((a166 + a146) + -1);
    	a123 = ((a166 / a166) - -7);
    	a166 = ((a65 + a65) - 21); 
    	System.out.println("T");
    } 
    if((cf && (input.equals("C")))) {
    	cf = false;
    	a86 = ((a146 / a146) + 13);
    	a166 = (a146 + 5);
    	a170 = (a65 - -1); 
    	System.out.println("V");
    } 
}
private  void calculateOutputm28(String input) {
    if(((a65 == 11) && cf)) {
    	calculateOutputm29(input);
    } 
}
private  void calculateOutputm31(String input) {
    if(((input.equals("C")) && cf)) {
    	cf = false;
    	a42 = (a56 + -2);
    	a34 = (a166 - 5); 
    	System.out.println("V");
    } 
    if((cf && (input.equals("B")))) {
    	a78 -= (a78 - 20) < a78 ? 3 : 0;
    	cf = false;
    	a166 = (a56 + -8);
    	a151 = (a34 + 9);
    	a73 = (a151 - -1); 
    	System.out.println("U");
    } 
}
private  void calculateOutputm30(String input) {
    if((cf && (a56 == 16))) {
    	calculateOutputm31(input);
    } 
}
private  void calculateOutputm33(String input) {
    if((cf && (input.equals("D")))) {
    	a185 -= (a185 - 20) < a185 ? 2 : 0;
    	cf = false;
    	 
    	System.out.println("V");
    } 
}
private  void calculateOutputm32(String input) {
    if(((a158 == 10) && cf)) {
    	calculateOutputm33(input);
    } 
}
private  void calculateOutputm35(String input) {
    if(((input.equals("C")) && cf)) {
    	a193 += (a193 + 20) > a193 ? 1 : 0;
    	cf = false;
    	 
    	System.out.println("W");
    } 
    if((((input.equals("A")) && cf) && a185 >= 43)) {
    	cf = false;
    	a1 = ((a34 * a85) - 24);
    	a166 = ((a85 / a34) - -9);
    	a146 = ((a85 * a166) + -32); 
    	System.out.println("Y");
    } 
    if(((input.equals("D")) && cf)) {
    	cf = false;
    	a69 = ((a34 / a166) - -7);
    	a151 = ((a166 - a69) - -9);
    	a166 = (a151 + -4); 
    	System.out.println("V");
    } 
}
private  void calculateOutputm34(String input) {
    if(((a85 == 4) && cf)) {
    	calculateOutputm35(input);
    } 
}
private  void calculateOutputm37(String input) {
    if(((input.equals("A")) && cf)) {
    	a185 -= (a185 - 20) < a185 ? 4 : 0;
    	cf = false;
    	a34 = ((a86 + a20) - 18);
    	a166 = ((a34 + a86) - 10);
    	a158 = (a86 + -5); 
    	System.out.println("Z");
    } 
    if((cf && (input.equals("D")))) {
    	a187 += (a187 + 20) > a187 ? 4 : 0;
    	cf = false;
    	a151 = a166;
    	a73 = (a166 - -1);
    	a166 = ((a20 * a20) - 136); 
    	System.out.println("S");
    } 
    if((((input.equals("B")) && cf) && (a181 % 2==0))) {
    	cf = false;
    	a18 = (a166 + -3);
    	a105 = (a166 - 3);
    	a166 = (a86 - 7); 
    	System.out.println("W");
    } 
    if((((input.equals("E")) && cf) && a30 >= 42)) {
    	a78 -= (a78 - 20) < a78 ? 1 : 0;
    	cf = false;
    	a105 = a166;
    	a151 = a105;
    	a166 = (a105 - 5); 
    	System.out.println("S");
    } 
    if(((input.equals("C")) && cf)) {
    	a30 += (a30 + 20) > a30 ? 1 : 0;
    	a187 += (a187 + 20) > a187 ? 1 : 0;
    	cf = false;
    	a166 = (a86 - 5);
    	a151 = (a166 + 6);
    	a135 = (a20 + -2); 
    	System.out.println("S");
    } 
}
private  void calculateOutputm36(String input) {
    if(((a20 == 12) && cf)) {
    	calculateOutputm37(input);
    } 
}
private  void calculateOutputm39(String input) {
    if((cf && (input.equals("C")))) {
    	a57 -= (a57 - 20) < a57 ? 3 : 0;
    	cf = false;
    	a166 = (a86 + -4);
    	a34 = ((a166 * a166) + -92);
    	a85 = ((a86 - a170) + 2); 
    	System.out.println("W");
    } 
    if((cf && (input.equals("B")))) {
    	a187 += (a187 + 20) > a187 ? 1 : 0;
    	cf = false;
    	a119 = ((a170 - a166) - -7);
    	a166 = (a119 + -3);
    	a61 = ((a86 * a86) + -186); 
    	System.out.println("Z");
    } 
    if((cf && (input.equals("D")))) {
    	cf = false;
    	a151 = (a170 + -1);
    	a166 = (a151 + -3);
    	a73 = ((a170 + a86) - 14); 
    	System.out.println("X");
    } 
    if(((cf && (input.equals("A"))) && (a165 % 2==0))) {
    	a186 += (a186 + 20) > a186 ? 1 : 0;
    	cf = false;
    	a166 = ((a170 * a86) + -158);
    	a34 = ((a170 * a86) + -167);
    	a144 = (a166 + 4); 
    	System.out.println("W");
    } 
    if((cf && (input.equals("E")))) {
    	a193 -= (a193 - 20) < a193 ? 3 : 0;
    	cf = false;
    	a166 = ((a86 - a170) + 6);
    	a151 = (a166 - -6);
    	a135 = (a151 + -10); 
    	System.out.println("Y");
    } 
}
private  void calculateOutputm38(String input) {
    if((cf && (a170 == 12))) {
    	calculateOutputm39(input);
    } 
}
private  void calculateOutputm41(String input) {
    if((cf && (input.equals("A")))) {
    	a193 -= (a193 - 20) < a193 ? 1 : 0;
    	cf = false;
    	a29 = (a166 + -6);
    	a119 = a160;
    	a166 = (a119 + -1); 
    	System.out.println("V");
    } 
    if((cf && (input.equals("C")))) {
    	cf = false;
    	a158 = ((a160 / a166) - -10);
    	a34 = (a160 - -1);
    	a166 = a158; 
    	System.out.println("V");
    } 
    if((cf && (input.equals("B")))) {
    	cf = false;
    	a123 = (a160 - -1);
    	a65 = (a166 + 2);
    	a166 = a123; 
    	System.out.println("V");
    } 
}
private  void calculateOutputm42(String input) {
    if(((input.equals("E")) && cf)) {
    	a193 -= (a193 - 20) < a193 ? 3 : 0;
    	cf = false;
    	a146 = (a119 + 5);
    	a31 = ((a160 - a119) + 10);
    	a166 = (a31 + -6); 
    	System.out.println("S");
    } 
    if(((input.equals("D")) && cf)) {
    	a26 -= (a26 - 20) < a26 ? 4 : 0;
    	cf = false;
    	a166 = (a160 - 2);
    	a86 = (a160 + 1);
    	a119 = ((a86 * a166) - 36); 
    	System.out.println("V");
    } 
    if((cf && (input.equals("B")))) {
    	cf = false;
    	a166 = ((a160 * a119) - 4);
    	a34 = a119;
    	a56 = ((a34 + a34) - -12); 
    	System.out.println("V");
    } 
    if((cf && (input.equals("C")))) {
    	a193 -= (a193 - 20) < a193 ? 4 : 0;
    	cf = false;
    	a105 = ((a119 - a166) + 20);
    	a166 = (a160 + -1);
    	a102 = (a166 + 11); 
    	System.out.println("Y");
    } 
}
private  void calculateOutputm40(String input) {
    if(((a160 == 6) && cf)) {
    	calculateOutputm41(input);
    } 
    if(((a160 == 7) && cf)) {
    	calculateOutputm42(input);
    } 
}
private  void calculateOutputm44(String input) {
    if(((input.equals("E")) && cf)) {
    	cf = false;
    	a166 = (a153 + -5);
    	a61 = (a153 + 2);
    	a119 = (a153 + -2); 
    	System.out.println("U");
    } 
    if(((input.equals("D")) && cf)) {
    	a187 -= (a187 - 20) < a187 ? 2 : 0;
    	a38 += (a38 + 20) > a38 ? 2 : 0;
    	a186 -= (a186 - 20) < a186 ? 2 : 0;
    	a78 -= (a78 - 20) < a78 ? 2 : 0;
    	cf = false;
    	a119 = (a153 + -8);
    	a166 = ((a119 / a153) + 5);
    	a159 = (a153 + -1); 
    	System.out.println("S");
    } 
    if((cf && (input.equals("C")))) {
    	cf = false;
    	a151 = ((a119 * a153) - 52);
    	a166 = a151;
    	a41 = (a166 - 5); 
    	System.out.println("U");
    } 
    if(((cf && (input.equals("B"))) && a78 >= 20)) {
    	cf = false;
    	a86 = ((a153 / a119) - -9);
    	a18 = a119;
    	a166 = (a153 - -1); 
    	System.out.println("W");
    } 
}
private  void calculateOutputm43(String input) {
    if((cf && (a153 == 10))) {
    	calculateOutputm44(input);
    } 
}



public  void calculateOutput(String input) {
 	cf = true;
    if(((a166 == 5) && cf)) {
    	if(((a119 == 2) && cf)) {
    		calculateOutputm1(input);
    	} 
    	if((cf && (a119 == 3))) {
    		calculateOutputm4(input);
    	} 
    	if(((a119 == 4) && cf)) {
    		calculateOutputm6(input);
    	} 
    	if(((a119 == 7) && cf)) {
    		calculateOutputm8(input);
    	} 
    	if(((a119 == 8) && cf)) {
    		calculateOutputm10(input);
    	} 
    } 
    if((cf && (a166 == 6))) {
    	if(((a105 == 9) && cf)) {
    		calculateOutputm12(input);
    	} 
    	if((cf && (a105 == 10))) {
    		calculateOutputm14(input);
    	} 
    } 
    if(((a166 == 7) && cf)) {
    	if(((a65 == 12) && cf)) {
    		calculateOutputm16(input);
    	} 
    	if(((a65 == 14) && cf)) {
    		calculateOutputm18(input);
    	} 
    } 
    if(((a166 == 8) && cf)) {
    	if((cf && (a151 == 8))) {
    		calculateOutputm20(input);
    	} 
    	if((cf && (a151 == 11))) {
    		calculateOutputm22(input);
    	} 
    	if(((a151 == 14) && cf)) {
    		calculateOutputm25(input);
    	} 
    } 
    if(((a166 == 9) && cf)) {
    	if(((a146 == 6) && cf)) {
    		calculateOutputm28(input);
    	} 
    } 
    if(((a166 == 10) && cf)) {
    	if(((a34 == 2) && cf)) {
    		calculateOutputm30(input);
    	} 
    	if((cf && (a34 == 7))) {
    		calculateOutputm32(input);
    	} 
    	if(((a34 == 8) && cf)) {
    		calculateOutputm34(input);
    	} 
    } 
    if((cf && (a166 == 11))) {
    	if((cf && (a86 == 13))) {
    		calculateOutputm36(input);
    	} 
    	if((cf && (a86 == 14))) {
    		calculateOutputm38(input);
    	} 
    } 
    if(((a166 == 12) && cf)) {
    	if((cf && (a119 == 2))) {
    		calculateOutputm40(input);
    	} 
    	if(((a119 == 6) && cf)) {
    		calculateOutputm43(input);
    	} 
    } 

    errorCheck();
    if(cf)
    	throw new IllegalArgumentException("Current state has no transition for this input!");
}


	public static void main(String[] args) throws Exception {
		// init system and input reader
		Problem1 eca = new Problem1();

		// main i/o-loop
		while(true) {
			//read input
			String input = stdin.readLine();
			try {
				//operate eca engine output = 
				eca.calculateOutput(input);
			} catch(IllegalArgumentException e) {
				System.err.println("Invalid input: " + e.getMessage());
			}
		}
	}
}