//力まかせ法による文字列探索

import java.util.Scanner;

public class BFmatch {

	//-- 力まかせ法による文字列探索 --//
	static int bfMatch(String txt, String pat){
		int pt = 0;	//txtをなぞるカーソル
		int pp = 0;	//patをなぞるカーソル
		
		//カーソルがなぞる対象文字列の長さになるまで繰り返す
		while(pt != txt.length() && pp != pat.length()){
			if(txt.charAt(pt) == pat.charAt(pp)){
				pt++;	pp++;
			}else{
				pt = pt - pp + 1;	pp = 0;
			}
		}
		if(pp == pat.length())
			return pt-pp;	//探索成功
		return -1;	//探索失敗
	}
	
	public static void main(String[] args) {
		
		Scanner stdIn = new Scanner(System.in);
		
		System.out.print("Text :");
		String s1 = stdIn.next();	//テキスト用文字列
		
		System.out.print("Pattern :");
		String s2 = stdIn.next();	//パターン用文字列
		
		int idx = bfMatch(s1, s2);
		
		if(idx == -1)
			System.out.println("テキスト中にパターンは存在しません。");
		else{
			//マッチ文字の直前までの半角での文字数を求める。
			int len = 0;
			for(int i = 0; i < idx; i++)
				len += s1.substring(i, i + 1).getBytes().length;
			len += s2.length();
			
			System.out.println((idx + 1) + "文字目にマッチします");
			System.out.println("テキスト:" + s1);
			System.out.printf(String.format("パターン:%%%ds", len),s2);
		}

	}

}
