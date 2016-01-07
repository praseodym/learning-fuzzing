import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class Problem4 {
	public Random random = new Random();
	static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

	private String[] inputs = {"B","E","C","A","D"};

	private static Problem4External ex = new Problem4External();

	public int a59 = 4;
	public int a8 = 9;
	public int a52 = 4;
	public int a1 = 3;
	public int a187 = 10;
	public int a176 = 4;
	public boolean cf = true;
	public int a87 = 5;
	public int a181 = 3;
	public int a85 = 13;
	public int a139 = 9;
	public int a180 = 13;
	public int a20 = 1;
	public int a21 = 11;
	public int a101 = 15;
	public int a121 = 4;
	public int a55 = 11;
	public int a35 = 4;
	public int a159 = 6;
	public int a78 = 10;
	public int a193 = 15;
	public int a63 = 3;
	public int a107 = 12;
	public int a95 = 3;
	public int a170 = 12;
	public int a173 = -15;
	public int a166 = 1;
	public int a106 = 1;
	public int a130 = 1;
	public int a126 = 1;
	public int a71 = -15;
	public int a19 = 1;
	public int a50 = 1;
	public int a32 = 1;
	public int a115 = 0;
	public int a132 = 1;
	public int a99 = 2;
	public int a75 = 1;
	public int a42 = 1;
	public int a117 = 1;
	public int a196 = 1;
	public int a162 = -15;
	public int a36 = 1;
	public int a155 = 1;
	public int a182 = 1;
	public int a18 = -15;
	public int a175 = 1;
	public int a135 = 3;
	public int a82 = 3;
	public int a58 = 2;
	public int a198 = 1;
	public int a136 = 1;
	public int a38 = 1;
	public int a133 = -15;
	public int a144 = 1;
	public int a131 = -15;
	public int a10 = 3;
	public int a81 = 1;
	public int a100 = 3;
	public int a168 = 1;
	public int a54 = -15;
	public int a22 = 1;
	public int a102 = 0;


	private void errorCheck() {
	    if((((a55 == 12) && (a176 == 9)) && (a193 == 16))){
	    	cf = false;
	    	throw new IllegalStateException( "error_0" );
	    }
	    if((((a121 == 10) && ex.check29(9)) && (a193 == 10))){
	    	cf = false;
	    	throw new IllegalStateException( "error_1" );
	    }
	    if((((a101 == 15) && (a176 == 8)) && (a193 == 16))){
	    	cf = false;
	    	throw new IllegalStateException( "error_2" );
	    }
	    if(((ex.check46(5) && ex.check42(6)) && (a193 == 15))){
	    	cf = false;
	    	throw new IllegalStateException( "error_3" );
	    }
	    if(((ex.check22(14) && (a176 == 4)) && (a193 == 16))){
	    	cf = false;
	    	throw new IllegalStateException( "error_4" );
	    }
	    if((((a181 == 6) && (a78 == 7)) && (a193 == 13))){
	    	cf = false;
	    	throw new IllegalStateException( "error_5" );
	    }
	    if((((a55 == 15) && ex.check48(11)) && (a193 == 11))){
	    	cf = false;
	    	throw new IllegalStateException( "error_6" );
	    }
	    if(((ex.check33(7) && ex.check42(13)) && (a193 == 15))){
	    	cf = false;
	    	throw new IllegalStateException( "error_7" );
	    }
	    if((((a35 == 3) && ex.check29(12)) && (a193 == 10))){
	    	cf = false;
	    	throw new IllegalStateException( "error_8" );
	    }
	    if((((a95 == 5) && ex.check41(6)) && (a193 == 17))){
	    	cf = false;
	    	throw new IllegalStateException( "error_9" );
	    }
	    if((((a1 == 6) && (a78 == 8)) && (a193 == 13))){
	    	cf = false;
	    	throw new IllegalStateException( "error_10" );
	    }
	    if((((a121 == 5) && ex.check37(7)) && (a193 == 14))){
	    	cf = false;
	    	throw new IllegalStateException( "error_11" );
	    }
	    if(((ex.check27(9) && (a21 == 12)) && (a193 == 12))){
	    	cf = false;
	    	throw new IllegalStateException( "error_12" );
	    }
	    if(((ex.check29(9) && ex.check37(4)) && (a193 == 14))){
	    	cf = false;
	    	throw new IllegalStateException( "error_13" );
	    }
	    if((((a121 == 4) && ex.check29(9)) && (a193 == 10))){
	    	cf = false;
	    	throw new IllegalStateException( "error_14" );
	    }
	    if((((a59 == 2) && ex.check29(11)) && (a193 == 10))){
	    	cf = false;
	    	throw new IllegalStateException( "error_15" );
	    }
	    if(((ex.check50(4) && (a21 == 7)) && (a193 == 12))){
	    	cf = false;
	    	throw new IllegalStateException( "error_16" );
	    }
	    if(((ex.check29(7) && ex.check37(4)) && (a193 == 14))){
	    	cf = false;
	    	throw new IllegalStateException( "error_17" );
	    }
	    if((((a55 == 12) && ex.check48(11)) && (a193 == 11))){
	    	cf = false;
	    	throw new IllegalStateException( "error_18" );
	    }
	    if((((a35 == 9) && ex.check48(12)) && (a193 == 11))){
	    	cf = false;
	    	throw new IllegalStateException( "error_19" );
	    }
	    if(((ex.check43(9) && (a21 == 13)) && (a193 == 12))){
	    	cf = false;
	    	throw new IllegalStateException( "error_20" );
	    }
	    if(((ex.check46(8) && ex.check42(6)) && (a193 == 15))){
	    	cf = false;
	    	throw new IllegalStateException( "error_21" );
	    }
	    if((((a181 == 5) && (a78 == 7)) && (a193 == 13))){
	    	cf = false;
	    	throw new IllegalStateException( "error_22" );
	    }
	    if((((a8 == 5) && ex.check29(7)) && (a193 == 10))){
	    	cf = false;
	    	throw new IllegalStateException( "error_23" );
	    }
	    if(((ex.check43(13) && ex.check48(10)) && (a193 == 11))){
	    	cf = false;
	    	throw new IllegalStateException( "error_24" );
	    }
	    if((((a59 == 7) && ex.check41(3)) && (a193 == 17))){
	    	cf = false;
	    	throw new IllegalStateException( "error_25" );
	    }
	    if(((ex.check30(11) && ex.check48(9)) && (a193 == 11))){
	    	cf = false;
	    	throw new IllegalStateException( "error_26" );
	    }
	    if((((a63 == 6) && ex.check41(7)) && (a193 == 17))){
	    	cf = false;
	    	throw new IllegalStateException( "error_27" );
	    }
	    if(((ex.check39(12) && (a78 == 10)) && (a193 == 13))){
	    	cf = false;
	    	throw new IllegalStateException( "error_28" );
	    }
	    if(((ex.check30(10) && ex.check48(9)) && (a193 == 11))){
	    	cf = false;
	    	throw new IllegalStateException( "error_29" );
	    }
	    if((((a139 == 11) && ex.check29(6)) && (a193 == 10))){
	    	cf = false;
	    	throw new IllegalStateException( "error_30" );
	    }
	    if((((a52 == 8) && ex.check41(9)) && (a193 == 17))){
	    	cf = false;
	    	throw new IllegalStateException( "error_31" );
	    }
	    if(((ex.check47(9) && ex.check37(2)) && (a193 == 14))){
	    	cf = false;
	    	throw new IllegalStateException( "error_32" );
	    }
	    if(((ex.check24(10) && ex.check42(8)) && (a193 == 15))){
	    	cf = false;
	    	throw new IllegalStateException( "error_33" );
	    }
	    if((((a20 == 1) && ex.check41(2)) && (a193 == 17))){
	    	cf = false;
	    	throw new IllegalStateException( "error_34" );
	    }
	    if((((a159 == 6) && (a78 == 9)) && (a193 == 13))){
	    	cf = false;
	    	throw new IllegalStateException( "error_35" );
	    }
	    if((((a85 == 14) && (a21 == 6)) && (a193 == 12))){
	    	cf = false;
	    	throw new IllegalStateException( "error_36" );
	    }
	    if(((ex.check39(6) && (a78 == 10)) && (a193 == 13))){
	    	cf = false;
	    	throw new IllegalStateException( "error_37" );
	    }
	    if((((a187 == 12) && ex.check29(13)) && (a193 == 10))){
	    	cf = false;
	    	throw new IllegalStateException( "error_38" );
	    }
	    if((((a59 == 3) && ex.check29(11)) && (a193 == 10))){
	    	cf = false;
	    	throw new IllegalStateException( "error_39" );
	    }
	    if(((ex.check30(17) && ex.check48(9)) && (a193 == 11))){
	    	cf = false;
	    	throw new IllegalStateException( "error_40" );
	    }
	    if((((a85 == 16) && (a21 == 6)) && (a193 == 12))){
	    	cf = false;
	    	throw new IllegalStateException( "error_41" );
	    }
	    if((((a52 == 10) && ex.check41(9)) && (a193 == 17))){
	    	cf = false;
	    	throw new IllegalStateException( "error_42" );
	    }
	    if((((a35 == 6) && ex.check29(12)) && (a193 == 10))){
	    	cf = false;
	    	throw new IllegalStateException( "error_43" );
	    }
	    if(((ex.check49(10) && ex.check37(6)) && (a193 == 14))){
	    	cf = false;
	    	throw new IllegalStateException( "error_44" );
	    }
	    if((((a63 == 3) && ex.check41(7)) && (a193 == 17))){
	    	cf = false;
	    	throw new IllegalStateException( "error_45" );
	    }
	    if(((ex.check24(11) && ex.check42(8)) && (a193 == 15))){
	    	cf = false;
	    	throw new IllegalStateException( "error_46" );
	    }
	    if(((ex.check28(14) && ex.check37(8)) && (a193 == 14))){
	    	cf = false;
	    	throw new IllegalStateException( "error_47" );
	    }
	    if((((a59 == 5) && ex.check41(3)) && (a193 == 17))){
	    	cf = false;
	    	throw new IllegalStateException( "error_48" );
	    }
	    if(((ex.check34(6) && (a176 == 7)) && (a193 == 16))){
	    	cf = false;
	    	throw new IllegalStateException( "error_49" );
	    }
	    if(((ex.check46(7) && (a78 == 4)) && (a193 == 13))){
	    	cf = false;
	    	throw new IllegalStateException( "error_50" );
	    }
	    if(((ex.check50(5) && (a21 == 7)) && (a193 == 12))){
	    	cf = false;
	    	throw new IllegalStateException( "error_51" );
	    }
	    if((((a55 == 11) && ex.check48(11)) && (a193 == 11))){
	    	cf = false;
	    	throw new IllegalStateException( "error_52" );
	    }
	    if(((ex.check24(9) && ex.check42(8)) && (a193 == 15))){
	    	cf = false;
	    	throw new IllegalStateException( "error_53" );
	    }
	    if((((a170 == 10) && ex.check42(12)) && (a193 == 15))){
	    	cf = false;
	    	throw new IllegalStateException( "error_54" );
	    }
	    if(((ex.check34(10) && (a176 == 7)) && (a193 == 16))){
	    	cf = false;
	    	throw new IllegalStateException( "error_55" );
	    }
	    if((((a180 == 12) && ex.check37(3)) && (a193 == 14))){
	    	cf = false;
	    	throw new IllegalStateException( "error_56" );
	    }
	    if((((a87 == 9) && (a78 == 5)) && (a193 == 13))){
	    	cf = false;
	    	throw new IllegalStateException( "error_57" );
	    }
	    if((((a187 == 8) && ex.check29(8)) && (a193 == 10))){
	    	cf = false;
	    	throw new IllegalStateException( "error_58" );
	    }
	    if((((a85 == 14) && ex.check48(7)) && (a193 == 11))){
	    	cf = false;
	    	throw new IllegalStateException( "error_59" );
	    }
	    if((((a1 == 3) && (a78 == 8)) && (a193 == 13))){
	    	cf = false;
	    	throw new IllegalStateException( "error_60" );
	    }
	    if((((a35 == 9) && ex.check48(14)) && (a193 == 11))){
	    	cf = false;
	    	throw new IllegalStateException( "error_61" );
	    }
	    if((((a63 == 7) && (a176 == 6)) && (a193 == 16))){
	    	cf = false;
	    	throw new IllegalStateException( "error_62" );
	    }
	    if(((ex.check23(8) && ex.check48(8)) && (a193 == 11))){
	    	cf = false;
	    	throw new IllegalStateException( "error_63" );
	    }
	    if((((a20 == 4) && ex.check41(2)) && (a193 == 17))){
	    	cf = false;
	    	throw new IllegalStateException( "error_64" );
	    }
	    if(((ex.check25(7) && ex.check37(5)) && (a193 == 14))){
	    	cf = false;
	    	throw new IllegalStateException( "error_65" );
	    }
	    if((((a139 == 10) && ex.check29(6)) && (a193 == 10))){
	    	cf = false;
	    	throw new IllegalStateException( "error_66" );
	    }
	    if((((a87 == 11) && ex.check29(10)) && (a193 == 10))){
	    	cf = false;
	    	throw new IllegalStateException( "error_67" );
	    }
	    if((((a180 == 11) && ex.check37(3)) && (a193 == 14))){
	    	cf = false;
	    	throw new IllegalStateException( "error_68" );
	    }
	    if((((a20 == 1) && ex.check42(10)) && (a193 == 15))){
	    	cf = false;
	    	throw new IllegalStateException( "error_69" );
	    }
	    if(((ex.check43(9) && ex.check48(10)) && (a193 == 11))){
	    	cf = false;
	    	throw new IllegalStateException( "error_70" );
	    }
	    if((((a107 == 16) && (a21 == 8)) && (a193 == 12))){
	    	cf = false;
	    	throw new IllegalStateException( "error_71" );
	    }
	    if((((a139 == 14) && ex.check29(6)) && (a193 == 10))){
	    	cf = false;
	    	throw new IllegalStateException( "error_72" );
	    }
	    if((((a35 == 4) && ex.check29(12)) && (a193 == 10))){
	    	cf = false;
	    	throw new IllegalStateException( "error_73" );
	    }
	    if(((ex.check34(4) && (a176 == 7)) && (a193 == 16))){
	    	cf = false;
	    	throw new IllegalStateException( "error_74" );
	    }
	    if(((ex.check49(11) && ex.check37(6)) && (a193 == 14))){
	    	cf = false;
	    	throw new IllegalStateException( "error_75" );
	    }
	    if(((ex.check23(8) && ex.check42(9)) && (a193 == 15))){
	    	cf = false;
	    	throw new IllegalStateException( "error_76" );
	    }
	    if(((ex.check46(2) && ex.check42(6)) && (a193 == 15))){
	    	cf = false;
	    	throw new IllegalStateException( "error_77" );
	    }
	    if((((a121 == 4) && ex.check37(7)) && (a193 == 14))){
	    	cf = false;
	    	throw new IllegalStateException( "error_78" );
	    }
	    if(((ex.check46(4) && ex.check42(6)) && (a193 == 15))){
	    	cf = false;
	    	throw new IllegalStateException( "error_79" );
	    }
	    if((((a78 == 4) && ex.check41(5)) && (a193 == 17))){
	    	cf = false;
	    	throw new IllegalStateException( "error_80" );
	    }
	    if(((ex.check25(5) && ex.check37(5)) && (a193 == 14))){
	    	cf = false;
	    	throw new IllegalStateException( "error_81" );
	    }
	    if(((ex.check29(9) && ex.check41(8)) && (a193 == 17))){
	    	cf = false;
	    	throw new IllegalStateException( "error_82" );
	    }
	    if(((ex.check27(7) && (a21 == 12)) && (a193 == 12))){
	    	cf = false;
	    	throw new IllegalStateException( "error_83" );
	    }
	    if((((a35 == 5) && ex.check48(14)) && (a193 == 11))){
	    	cf = false;
	    	throw new IllegalStateException( "error_84" );
	    }
	    if((((a35 == 5) && ex.check48(12)) && (a193 == 11))){
	    	cf = false;
	    	throw new IllegalStateException( "error_85" );
	    }
	    if(((ex.check43(11) && ex.check48(10)) && (a193 == 11))){
	    	cf = false;
	    	throw new IllegalStateException( "error_86" );
	    }
	    if((((a20 == 5) && ex.check41(2)) && (a193 == 17))){
	    	cf = false;
	    	throw new IllegalStateException( "error_87" );
	    }
	    if((((a180 == 6) && ex.check37(3)) && (a193 == 14))){
	    	cf = false;
	    	throw new IllegalStateException( "error_88" );
	    }
	    if(((ex.check23(13) && ex.check42(9)) && (a193 == 15))){
	    	cf = false;
	    	throw new IllegalStateException( "error_89" );
	    }
	    if(((ex.check25(9) && ex.check37(5)) && (a193 == 14))){
	    	cf = false;
	    	throw new IllegalStateException( "error_90" );
	    }
	    if(((ex.check28(12) && ex.check37(8)) && (a193 == 14))){
	    	cf = false;
	    	throw new IllegalStateException( "error_91" );
	    }
	    if((((a35 == 5) && ex.check29(12)) && (a193 == 10))){
	    	cf = false;
	    	throw new IllegalStateException( "error_92" );
	    }
	    if((((a95 == 7) && ex.check41(6)) && (a193 == 17))){
	    	cf = false;
	    	throw new IllegalStateException( "error_93" );
	    }
	    if(((ex.check33(11) && ex.check42(13)) && (a193 == 15))){
	    	cf = false;
	    	throw new IllegalStateException( "error_94" );
	    }
	    if(((ex.check33(6) && ex.check48(13)) && (a193 == 11))){
	    	cf = false;
	    	throw new IllegalStateException( "error_95" );
	    }
	    if(((ex.check43(8) && ex.check48(10)) && (a193 == 11))){
	    	cf = false;
	    	throw new IllegalStateException( "error_96" );
	    }
	    if((((a35 == 10) && ex.check29(12)) && (a193 == 10))){
	    	cf = false;
	    	throw new IllegalStateException( "error_97" );
	    }
	    if((((a85 == 11) && ex.check42(11)) && (a193 == 15))){
	    	cf = false;
	    	throw new IllegalStateException( "error_98" );
	    }
	    if(((ex.check49(8) && ex.check37(6)) && (a193 == 14))){
	    	cf = false;
	    	throw new IllegalStateException( "error_99" );
	    }
	}private  void calculateOutputm2(String input) {
    if(((input.equals("E")) && cf)) {
    	a168 += (a168 + 20) > a168 ? 2 : 0;
    	cf = false;
    	a193 = (a8 + 5);
    	a85 = (a8 + 7);
    	ex.update58(a8,a193,10); 
    	System.out.println("U");
    } 
    if((((input.equals("A")) && cf) && a32 <= -22)) {
    	a144 -= (a144 - 20) < a144 ? 4 : 0;
    	cf = false;
    	a176 = (a193 - 6);
    	ex.update6(a176,a193,0);
    	a193 = (a176 - -12); 
    	System.out.println("W");
    } 
    if((cf && (input.equals("C")))) {
    	a182 -= (a182 - 20) < a182 ? 2 : 0;
    	cf = false;
    	a59 = (a193 - 8);
    	ex.update44(a193,a8,-1);
    	a193 = ((a59 * a59) + 13); 
    	System.out.println("V");
    } 
    if((((input.equals("D")) && cf) && a115 >= 27)) {
    	a106 += (a106 + 20) > a106 ? 3 : 0;
    	cf = false;
    	a193 = (a8 - -7);
    	a1 = (a8 - 3);
    	a78 = (a1 - -5); 
    	System.out.println("T");
    } 
    if((cf && (input.equals("B")))) {
    	a32 += (a32 + 20) > a32 ? 3 : 0;
    	cf = false;
    	ex.update36(a8,a193,-59);
    	a193 = 14;
    	a187 = (a193 - 7); 
    	System.out.println("W");
    } 
}
private  void calculateOutputm1(String input) {
    if(((a8 == 6) && cf)) {
    	calculateOutputm2(input);
    } 
}
private  void calculateOutputm4(String input) {
    if((cf && (input.equals("E")))) {
    	a36 += (a36 + 20) > a36 ? 2 : 0;
    	cf = false;
    	a20 = (a121 + 2);
    	ex.update43(a121,a121,-2);
    	a193 = ((a20 - a20) - -17); 
    	System.out.println("W");
    } 
    if((((input.equals("A")) && cf) && a126 == 8364)) {
    	a32 -= (a32 - 20) < a32 ? 1 : 0;
    	cf = false;
    	ex.update45(a193,a193,-5);
    	ex.update54(a193,a193,15);
    	a193 = ((a121 / a121) - -14); 
    	System.out.println("Y");
    } 
    if(((input.equals("B")) && cf)) {
    	a82 -= (a82 - 20) < a82 ? 2 : 0;
    	cf = false;
    	a121 = ((a193 - a193) + 7);
    	ex.update35(a121,a121,42);
    	a193 = ((a121 / a121) + 13); 
    	System.out.println("T");
    } 
    if((cf && (input.equals("C")))) {
    	a132 += (a132 + 20) > a132 ? 4 : 0;
    	a133 += (a133 + 20) > a133 ? 2 : 0;
    	a102 += (a102 + 20) > a102 ? 2 : 0;
    	cf = false;
    	a59 = ((a193 + a193) - 16);
    	ex.update43(a59,a121,-4);
    	a193 = (a59 + 13); 
    	System.out.println("S");
    } 
    if((((input.equals("D")) && cf) && (a71 % 2==0))) {
    	a106 += (a106 + 20) > a106 ? 4 : 0;
    	cf = false;
    	ex.update58(a193,a121,8);
    	a193 = 11;
    	a85 = (a193 - -3); 
    	System.out.println("S");
    } 
}
private  void calculateOutputm3(String input) {
    if(((a121 == 5) && cf)) {
    	calculateOutputm4(input);
    } 
}
private  void calculateOutputm6(String input) {
    if((cf && (input.equals("B")))) {
    	a130 += (a130 + 20) > a130 ? 1 : 0;
    	cf = false;
    	 
    	System.out.println("Y");
    } 
    if((((input.equals("C")) && cf) && a198 <= -44)) {
    	a166 += (a166 + 20) > a166 ? 4 : 0;
    	cf = false;
    	a35 = (a193 - 6);
    	ex.update19(a85,a35,4);
    	a193 = (a85 + -3); 
    	System.out.println("Y");
    } 
    if((((input.equals("A")) && cf) && a58 >= 23)) {
    	cf = false;
    	a21 = (a85 - 6);
    	ex.update61(a21,a193,9);
    	a193 = (a21 - -5); 
    	System.out.println("T");
    } 
    if(((input.equals("E")) && cf)) {
    	a36 += (a36 + 20) > a36 ? 1 : 0;
    	cf = false;
    	a35 = (a85 - 5);
    	ex.update57(a85,a85,-157); 
    	System.out.println("T");
    } 
}
private  void calculateOutputm5(String input) {
    if((cf && (a85 == 13))) {
    	calculateOutputm6(input);
    } 
}
private  void calculateOutputm8(String input) {
    if((cf && (input.equals("C")))) {
    	cf = false;
    	a193 = (a35 + 9);
    	a59 = (a35 - 4);
    	ex.update43(a193,a59,10); 
    	System.out.println("T");
    } 
    if(((cf && (input.equals("A"))) && a81 == 6156)) {
    	a175 += (a175 + 20) > a175 ? 2 : 0;
    	cf = false;
    	a193 = (a35 + 5);
    	a78 = (a35 + -4);
    	ex.update54(a78,a193,10); 
    	System.out.println("S");
    } 
    if(((input.equals("E")) && cf)) {
    	a58 -= (a58 - 20) < a58 ? 4 : 0;
    	cf = false;
    	ex.update43(a193,a35,0);
    	a193 = (a35 - -9);
    	a59 = (a193 - 13); 
    	System.out.println("S");
    } 
    if(((cf && (input.equals("D"))) && a10 >= 23)) {
    	a130 += (a130 + 20) > a130 ? 1 : 0;
    	cf = false;
    	ex.update58(a35,a35,3);
    	ex.update27(a35,a35,-58); 
    	System.out.println("U");
    } 
    if((cf && (input.equals("B")))) {
    	a162 += (a162 + 20) > a162 ? 2 : 0;
    	a54 += (a54 + 20) > a54 ? 2 : 0;
    	a166 += (a166 + 20) > a166 ? 4 : 0;
    	cf = false;
    	a121 = (a35 - 3);
    	ex.update20(a193,a121,7);
    	a193 = (a121 - -5); 
    	System.out.println("Y");
    } 
}
private  void calculateOutputm7(String input) {
    if((cf && (a35 == 8))) {
    	calculateOutputm8(input);
    } 
}
private  void calculateOutputm10(String input) {
    if((cf && (input.equals("B")))) {
    	a136 += (a136 + 20) > a136 ? 2 : 0;
    	a144 += (a144 + 20) > a144 ? 1 : 0;
    	a100 += (a100 + 20) > a100 ? 1 : 0;
    	cf = false;
    	a193 = (a21 - -8);
    	ex.update44(a21,a193,12);
    	ex.update30(a21,a21,74); 
    	System.out.println("Z");
    } 
    if(((input.equals("C")) && cf)) {
    	a106 += (a106 + 20) > a106 ? 1 : 0;
    	cf = false;
    	a193 = (a21 - -8);
    	ex.update44(a21,a21,3);
    	a59 = (a21 + -5); 
    	System.out.println("V");
    } 
    if(((cf && (input.equals("A"))) && a144 >= 21)) {
    	a10 -= (a10 - 20) < a10 ? 2 : 0;
    	cf = false;
    	ex.update44(a21,a21,3);
    	a59 = ((a21 - a193) - -10);
    	a193 = ((a59 - a59) + 17); 
    	System.out.println("S");
    } 
    if(((cf && (input.equals("D"))) && (a131 % 2==0))) {
    	a115 -= (a115 - 20) < a115 ? 3 : 0;
    	cf = false;
    	a35 = (a193 - 6);
    	ex.update19(a35,a193,18);
    	a193 = (a35 - -4); 
    	System.out.println("V");
    } 
    if(((input.equals("E")) && cf)) {
    	cf = false;
    	a193 = ((a21 * a21) - 65);
    	a176 = ((a193 + a193) - 29);
    	a21 = (a193 - 3); 
    	System.out.println("Y");
    } 
}
private  void calculateOutputm9(String input) {
    if((cf && ex.check26(8))) {
    	calculateOutputm10(input);
    } 
}
private  void calculateOutputm12(String input) {
    if(((cf && (input.equals("C"))) && a102 >= 38)) {
    	a106 -= (a106 - 20) < a106 ? 2 : 0;
    	cf = false;
    	ex.update44(a21,a193,4);
    	a78 = (a193 - 8);
    	a193 = ((a78 / a78) - -16); 
    	System.out.println("V");
    } 
    if((cf && (input.equals("B")))) {
    	a42 += (a42 + 20) > a42 ? 1 : 0;
    	cf = false;
    	ex.update58(a193,a193,12);
    	a35 = ((a193 / a21) - -8);
    	a193 = ((a35 / a35) + 10); 
    	System.out.println("U");
    } 
}
private  void calculateOutputm11(String input) {
    if((cf && ex.check43(7))) {
    	calculateOutputm12(input);
    } 
}
private  void calculateOutputm14(String input) {
    if(((input.equals("B")) && cf)) {
    	cf = false;
    	a193 = ((a78 * a78) + -6);
    	ex.update20(a78,a78,1);
    	a8 = (a193 - 4); 
    	System.out.println("V");
    } 
    if((cf && (input.equals("E")))) {
    	a135 -= (a135 - 20) < a135 ? 1 : 0;
    	cf = false;
    	a193 = ((a78 + a78) + 8);
    	a176 = (a193 + -12);
    	ex.update5(a176,a176,4); 
    	System.out.println("U");
    } 
    if((((input.equals("D")) && cf) && a106 <= -45)) {
    	a130 += (a130 + 20) > a130 ? 3 : 0;
    	cf = false;
    	ex.update43(a78,a78,-9);
    	a193 = (a78 - -13);
    	a52 = (a193 + -9); 
    	System.out.println("W");
    } 
    if(((input.equals("C")) && cf)) {
    	cf = false;
    	ex.update57(a193,a193,-155);
    	a193 = (a78 - -7);
    	a35 = ((a78 / a78) - -4); 
    	System.out.println("S");
    } 
    if(((cf && (input.equals("A"))) && a130 <= -29)) {
    	cf = false;
    	ex.update21(a78,a193,-41);
    	ex.update58(a193,a193,17);
    	a193 = 11; 
    	System.out.println("S");
    } 
}
private  void calculateOutputm13(String input) {
    if((cf && ex.check46(4))) {
    	calculateOutputm14(input);
    } 
}
private  void calculateOutputm16(String input) {
    if(((cf && (input.equals("B"))) && a175 <= -27)) {
    	a99 -= (a99 - 20) < a99 ? 2 : 0;
    	cf = false;
    	a176 = (a187 + 2);
    	a193 = (a176 + 7);
    	a55 = ((a176 + a176) + -6); 
    	System.out.println("V");
    } 
    if((cf && (input.equals("C")))) {
    	cf = false;
    	ex.update44(a193,a187,-3);
    	a193 = (a187 - -10);
    	ex.update30(a193,a187,112); 
    	System.out.println("S");
    } 
    if(((cf && (input.equals("E"))) && (a18 % 2==0))) {
    	a115 -= (a115 - 20) < a115 ? 3 : 0;
    	cf = false;
    	a193 = ((a187 - a187) - -10);
    	ex.update19(a193,a193,6);
    	a139 = ((a193 * a193) + -89); 
    	System.out.println("S");
    } 
}
private  void calculateOutputm15(String input) {
    if((cf && (a187 == 7))) {
    	calculateOutputm16(input);
    } 
}
private  void calculateOutputm18(String input) {
    if(((input.equals("E")) && cf)) {
    	a36 += (a36 + 20) > a36 ? 2 : 0;
    	cf = false;
    	a176 = (a193 + -9);
    	a193 = (a121 + 9);
    	a8 = ((a193 * a193) - 250); 
    	System.out.println("S");
    } 
    if((((input.equals("D")) && cf) && a36 <= -40)) {
    	a106 += (a106 + 20) > a106 ? 4 : 0;
    	cf = false;
    	a121 = (a193 + -9); 
    	System.out.println("U");
    } 
    if((((input.equals("C")) && cf) && a196 == 480)) {
    	a198 += (a198 + 20) > a198 ? 2 : 0;
    	cf = false;
    	ex.update17(a121,a121,-11);
    	ex.update35(a193,a193,188); 
    	System.out.println("S");
    } 
    if((((input.equals("B")) && cf) && (a162 % 2==0))) {
    	a166 += (a166 + 20) > a166 ? 1 : 0;
    	cf = false;
    	a35 = (a121 - -2);
    	a193 = ((a121 / a35) - -11);
    	ex.update58(a35,a121,4); 
    	System.out.println("W");
    } 
    if((((input.equals("A")) && cf) && a117 == 2186)) {
    	a166 += (a166 + 20) > a166 ? 3 : 0;
    	cf = false;
    	ex.update59(a193,a121,-8);
    	ex.update35(a193,a193,190); 
    	System.out.println("Z");
    } 
}
private  void calculateOutputm17(String input) {
    if((cf && (a121 == 7))) {
    	calculateOutputm18(input);
    } 
}
private  void calculateOutputm20(String input) {
    if((cf && (input.equals("D")))) {
    	cf = false;
    	a176 = ((a193 / a193) + 2);
    	a21 = (a193 + -2);
    	a193 = (a176 + 13); 
    	System.out.println("U");
    } 
}
private  void calculateOutputm19(String input) {
    if((ex.check33(10) && cf)) {
    	calculateOutputm20(input);
    } 
}
private  void calculateOutputm22(String input) {
    if(((input.equals("C")) && cf)) {
    	cf = false;
    	a193 = a21;
    	ex.update54(a193,a176,12);
    	a78 = ((a21 + a21) + -22); 
    	System.out.println("S");
    } 
    if((cf && (input.equals("E")))) {
    	cf = false;
    	a121 = (a193 - 11);
    	ex.update19(a121,a21,17);
    	a193 = (a176 + 7); 
    	System.out.println("Y");
    } 
    if((((input.equals("B")) && cf) && (a173 % 2==0))) {
    	a130 += (a130 + 20) > a130 ? 4 : 0;
    	cf = false;
    	ex.update30(a193,a176,44);
    	a176 = ((a193 - a193) + 7); 
    	System.out.println("T");
    } 
    if((((input.equals("D")) && cf) && a166 <= -44)) {
    	a166 -= (a166 - 20) < a166 ? 2 : 0;
    	cf = false;
    	a63 = (a21 + -7);
    	ex.update43(a193,a63,3);
    	a193 = (a176 - -14); 
    	System.out.println("V");
    } 
}
private  void calculateOutputm21(String input) {
    if(((a21 == 13) && cf)) {
    	calculateOutputm22(input);
    } 
}
private  void calculateOutputm24(String input) {
    if(((input.equals("C")) && cf)) {
    	cf = false;
    	ex.update19(a193,a193,8);
    	ex.update44(a176,a176,8);
    	a193 = 17; 
    	System.out.println("T");
    } 
    if((((input.equals("B")) && cf) && a50 == 9160)) {
    	a106 += (a106 + 20) > a106 ? 4 : 0;
    	cf = false;
    	ex.update36(a193,a193,-251);
    	a193 = (a176 - -10);
    	ex.update12(a176,a176,-1); 
    	System.out.println("T");
    } 
    if((((input.equals("D")) && cf) && a19 == 6764)) {
    	a99 += (a99 + 20) > a99 ? 1 : 0;
    	cf = false;
    	ex.update36(a193,a176,-59);
    	ex.update12(a176,a176,-3);
    	a193 = (a176 + 10); 
    	System.out.println("U");
    } 
}
private  void calculateOutputm23(String input) {
    if((ex.check22(12) && cf)) {
    	calculateOutputm24(input);
    } 
}
private  void calculateOutputm26(String input) {
    if(((input.equals("E")) && cf)) {
    	a82 -= (a82 - 20) < a82 ? 1 : 0;
    	cf = false;
    	a193 = ((a176 - a8) + 15);
    	ex.update35(a8,a193,77);
    	a121 = (a8 + 1); 
    	System.out.println("T");
    } 
}
private  void calculateOutputm27(String input) {
    if((((input.equals("C")) && cf) && a168 >= 26)) {
    	cf = false;
    	ex.update19(a193,a8,1);
    	a121 = (a8 - -2);
    	a193 = ((a121 / a176) - -8); 
    	System.out.println("Y");
    } 
    if(((cf && (input.equals("D"))) && a22 <= -29)) {
    	a175 += (a175 + 20) > a175 ? 2 : 0;
    	cf = false;
    	a193 = ((a176 * a8) + -28);
    	a21 = (a193 - 6);
    	a85 = ((a21 - a193) - -20); 
    	System.out.println("W");
    } 
    if((((input.equals("A")) && cf) && (a54 % 2==0))) {
    	a106 += (a106 + 20) > a106 ? 3 : 0;
    	cf = false;
    	a95 = ((a193 / a176) - -4);
    	ex.update44(a193,a193,6);
    	a193 = (a95 - -10); 
    	System.out.println("W");
    } 
    if((cf && (input.equals("E")))) {
    	a130 += (a130 + 20) > a130 ? 3 : 0;
    	cf = false;
    	a121 = a176;
    	ex.update19(a193,a121,-2);
    	a193 = (a8 + 2); 
    	System.out.println("Y");
    } 
    if(((cf && (input.equals("B"))) && a100 >= 31)) {
    	a115 -= (a115 - 20) < a115 ? 2 : 0;
    	cf = false;
    	a78 = ((a176 - a193) + 21);
    	ex.update40(a193,a78,6);
    	a193 = (a78 - -3); 
    	System.out.println("Y");
    } 
}
private  void calculateOutputm25(String input) {
    if((cf && (a8 == 6))) {
    	calculateOutputm26(input);
    } 
    if((cf && (a8 == 8))) {
    	calculateOutputm27(input);
    } 
}
private  void calculateOutputm29(String input) {
    if((((input.equals("D")) && cf) && a42 <= -42)) {
    	a100 -= (a100 - 20) < a100 ? 4 : 0;
    	cf = false;
    	ex.update20(a193,a20,18);
    	a139 = ((a193 / a193) - -9);
    	a193 = a139; 
    	System.out.println("T");
    } 
    if((((input.equals("A")) && cf) && a75 == 8590)) {
    	a32 += (a32 + 20) > a32 ? 3 : 0;
    	cf = false;
    	ex.update57(a20,a193,-109);
    	a193 = 11;
    	ex.update47(a193,a193,13); 
    	System.out.println("S");
    } 
    if((cf && (input.equals("C")))) {
    	a42 -= (a42 - 20) < a42 ? 2 : 0;
    	cf = false;
    	ex.update48(a193,a193,6);
    	a21 = (a20 + 6);
    	a193 = ((a21 - a21) - -12); 
    	System.out.println("Z");
    } 
    if(((input.equals("B")) && cf)) {
    	a115 -= (a115 - 20) < a115 ? 2 : 0;
    	cf = false;
    	ex.update30(a20,a20,42);
    	ex.update44(a193,a20,-6); 
    	System.out.println("U");
    } 
    if((cf && (input.equals("E")))) {
    	a182 += (a182 + 20) > a182 ? 1 : 0;
    	cf = false;
    	a193 = ((a20 - a20) - -16);
    	a176 = ((a20 / a20) + 4);
    	a8 = (a193 + -8); 
    	System.out.println("S");
    } 
}
private  void calculateOutputm28(String input) {
    if((cf && (a20 == 7))) {
    	calculateOutputm29(input);
    } 
}
private  void calculateOutputm31(String input) {
    if((((input.equals("A")) && cf) && a135 >= 29)) {
    	a155 += (a155 + 20) > a155 ? 2 : 0;
    	cf = false;
    	ex.update57(a59,a193,-24);
    	a193 = ((a59 + a59) - -7);
    	ex.update47(a193,a193,8); 
    	System.out.println("S");
    } 
    if(((input.equals("E")) && cf)) {
    	a99 -= (a99 - 20) < a99 ? 3 : 0;
    	cf = false;
    	 
    	System.out.println("T");
    } 
    if(((cf && (input.equals("B"))) && a82 >= 21)) {
    	a166 += (a166 + 20) > a166 ? 2 : 0;
    	cf = false;
    	a193 = ((a59 - a59) + 11);
    	a55 = (a59 + 13);
    	ex.update58(a193,a193,11); 
    	System.out.println("S");
    } 
}
private  void calculateOutputm32(String input) {
    if(((input.equals("B")) && cf)) {
    	a42 -= (a42 - 20) < a42 ? 4 : 0;
    	a117 += (a117 + 20) > a117 ? 4 : 0;
    	a38 += (a38 + 20) > a38 ? 4 : 0;
    	a168 += (a168 + 20) > a168 ? 1 : 0;
    	cf = false;
    	a193 = ((a59 * a59) + -4);
    	a21 = (a59 - -5);
    	ex.update13(a59,a59,-8); 
    	System.out.println("Z");
    } 
    if(((cf && (input.equals("A"))) && a99 >= 24)) {
    	a32 += (a32 + 20) > a32 ? 1 : 0;
    	cf = false;
    	ex.update36(a59,a59,-12);
    	a193 = (a59 + 10);
    	ex.update20(a193,a59,9); 
    	System.out.println("T");
    } 
    if(((input.equals("C")) && cf)) {
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
    	cf = false;
    	a193 = (a59 + 7);
    	a35 = ((a59 * a59) + -8);
    	ex.update57(a59,a193,-32); 
    	System.out.println("Y");
    } 
    if(((input.equals("E")) && cf)) {
    	a155 += (a155 + 20) > a155 ? 2 : 0;
    	cf = false;
    	ex.update43(a193,a193,-9);
    	a52 = (a193 - 14); 
    	System.out.println("U");
    } 
    if(((cf && (input.equals("D"))) && a132 == 7462)) {
    	a166 += (a166 + 20) > a166 ? 3 : 0;
    	cf = false;
    	a20 = (a59 - -1);
    	ex.update43(a59,a193,-15); 
    	System.out.println("U");
    } 
}
private  void calculateOutputm30(String input) {
    if(((a59 == 2) && cf)) {
    	calculateOutputm31(input);
    } 
    if((cf && (a59 == 4))) {
    	calculateOutputm32(input);
    } 
}
private  void calculateOutputm34(String input) {
    if((cf && (input.equals("C")))) {
    	a99 += (a99 + 20) > a99 ? 2 : 0;
    	a75 += (a75 + 20) > a75 ? 2 : 0;
    	a196 += (a196 + 20) > a196 ? 4 : 0;
    	a10 += (a10 + 20) > a10 ? 2 : 0;
    	a81 += (a81 + 20) > a81 ? 4 : 0;
    	cf = false;
    	ex.update20(a193,a193,25);
    	a193 = 10;
    	a121 = (a193 - 5); 
    	System.out.println("Y");
    } 
    if((cf && (input.equals("E")))) {
    	cf = false;
    	ex.update19(a193,a193,9);
    	a121 = ((a193 + a193) - 29);
    	a193 = (a121 - -5); 
    	System.out.println("Y");
    } 
    if((cf && (input.equals("B")))) {
    	a115 -= (a115 - 20) < a115 ? 4 : 0;
    	cf = false;
    	 
    	System.out.println("U");
    } 
}
private  void calculateOutputm33(String input) {
    if((cf && ex.check34(7))) {
    	calculateOutputm34(input);
    } 
}
private  void calculateOutputm36(String input) {
    if(((cf && (input.equals("A"))) && a182 >= 27)) {
    	a42 += (a42 + 20) > a42 ? 2 : 0;
    	cf = false;
    	ex.update43(a193,a193,-7);
    	a63 = (a193 + -14); 
    	System.out.println("V");
    } 
    if((cf && (input.equals("B")))) {
    	a155 += (a155 + 20) > a155 ? 4 : 0;
    	cf = false;
    	a193 = 11;
    	ex.update58(a193,a193,10);
    	a35 = (a193 + -3); 
    	System.out.println("S");
    } 
    if((((input.equals("C")) && cf) && a155 <= -28)) {
    	a135 -= (a135 - 20) < a135 ? 3 : 0;
    	cf = false;
    	a187 = (a193 + -5);
    	ex.update19(a187,a193,18);
    	a193 = (a187 - 2); 
    	System.out.println("S");
    } 
}
private  void calculateOutputm35(String input) {
    if((cf && ex.check29(8))) {
    	calculateOutputm36(input);
    } 
}
private  void calculateOutputm38(String input) {
    if((cf && (input.equals("B")))) {
    	a36 -= (a36 - 20) < a36 ? 2 : 0;
    	a22 -= (a22 - 20) < a22 ? 2 : 0;
    	a130 += (a130 + 20) > a130 ? 3 : 0;
    	cf = false;
    	ex.update43(a52,a52,-4);
    	ex.update30(a193,a193,282); 
    	System.out.println("S");
    } 
    if(((cf && (input.equals("A"))) && a136 >= 35)) {
    	cf = false;
    	a121 = ((a193 * a52) - 47);
    	ex.update19(a121,a193,22);
    	a193 = ((a121 / a121) + 9); 
    	System.out.println("W");
    } 
    if(((input.equals("C")) && cf)) {
    	a136 -= (a136 - 20) < a136 ? 3 : 0;
    	cf = false;
    	 
    	System.out.println("U");
    } 
    if((((input.equals("E")) && cf) && a38 == 4382)) {
    	a32 += (a32 + 20) > a32 ? 3 : 0;
    	cf = false;
    	a193 = ((a52 * a52) - -6);
    	ex.update45(a193,a193,-10);
    	a85 = (a52 + 8); 
    	System.out.println("Y");
    } 
    if((((input.equals("D")) && cf) && (a133 % 2==0))) {
    	a99 -= (a99 - 20) < a99 ? 4 : 0;
    	cf = false;
    	ex.update15(a52,a52,6);
    	a21 = (a52 - -9);
    	a193 = a21; 
    	System.out.println("Y");
    } 
}
private  void calculateOutputm37(String input) {
    if((cf && (a52 == 3))) {
    	calculateOutputm38(input);
    } 
}



public  void calculateOutput(String input) {
 	cf = true;
    if(((a193 == 10) && cf)) {
    	if((ex.check29(7) && cf)) {
    		calculateOutputm1(input);
    	} 
    	if((cf && ex.check29(9))) {
    		calculateOutputm3(input);
    	} 
    } 
    if((cf && (a193 == 11))) {
    	if((cf && ex.check48(7))) {
    		calculateOutputm5(input);
    	} 
    	if((cf && ex.check48(12))) {
    		calculateOutputm7(input);
    	} 
    } 
    if(((a193 == 12) && cf)) {
    	if(((a21 == 9) && cf)) {
    		calculateOutputm9(input);
    	} 
    	if((cf && (a21 == 13))) {
    		calculateOutputm11(input);
    	} 
    } 
    if((cf && (a193 == 13))) {
    	if((cf && (a78 == 4))) {
    		calculateOutputm13(input);
    	} 
    } 
    if(((a193 == 14) && cf)) {
    	if((ex.check37(1) && cf)) {
    		calculateOutputm15(input);
    	} 
    	if((cf && ex.check37(7))) {
    		calculateOutputm17(input);
    	} 
    } 
    if(((a193 == 15) && cf)) {
    	if((cf && ex.check42(13))) {
    		calculateOutputm19(input);
    	} 
    } 
    if(((a193 == 16) && cf)) {
    	if(((a176 == 3) && cf)) {
    		calculateOutputm21(input);
    	} 
    	if(((a176 == 4) && cf)) {
    		calculateOutputm23(input);
    	} 
    	if((cf && (a176 == 5))) {
    		calculateOutputm25(input);
    	} 
    } 
    if((cf && (a193 == 17))) {
    	if((cf && ex.check41(2))) {
    		calculateOutputm28(input);
    	} 
    	if((ex.check41(3) && cf)) {
    		calculateOutputm30(input);
    	} 
    	if((cf && ex.check41(4))) {
    		calculateOutputm33(input);
    	} 
    	if((ex.check41(8) && cf)) {
    		calculateOutputm35(input);
    	} 
    	if((ex.check41(9) && cf)) {
    		calculateOutputm37(input);
    	} 
    } 

    errorCheck();
    if(cf)
    	throw new IllegalArgumentException("Current state has no transition for this input!");
}


	public static void main(String[] args) throws Exception {
		// init system and input reader
		Problem4 eca = new Problem4();

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