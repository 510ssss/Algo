// 力まかせ法による文字列探索

import java.util.Scanner;

class KMPmatch {

	//--- KMP法による文字列探索 ---//
	static int kmpMatch(String txt, String pat) {
		int pt = 1;											// txtをなぞるカーソル
		int pp = 0;											// patをなぞるカーソル
		int[] skip = new int[pat.length() + 1];	// スキップテーブル

		// スキップテーブルの作成
		skip[pt] = 0;
		while (pt != pat.length()) {
			if (pat.charAt(pt) == pat.charAt(pp))
				skip[++pt] = ++pp;
			else if (pp == 0)
				skip[++pt] = pp;
			else
				pp = skip[pp];
		}

		// 探索
		pt = pp = 0;
		while (pt != txt.length() && pp != pat.length()) {
			if (txt.charAt(pt) == pat.charAt(pp)) {
				pt++;
				pp++;
			} else if (pp == 0)
				pt++;
			else
				pp = skip[pp];
		}

		if (pp == pat.length())		// パターンの全文字を照合
			return pt - pp;
		return -1;					// 探索失敗
	}


	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.print("テキスト：");
		String s1 = stdIn.next(); 					// テキスト用文字列

		System.out.print("パターン：");
		String s2 = stdIn.next();					// パターン用文字列

		int idx = kmpMatch(s1, s2);	// 文字列s1から文字列s2を力まかせ法で探索

		if (idx == -1)
			System.out.println("テキスト中にパターンは存在しません。");
		else {
			// マッチ文字の直前までの《半角》での文字数を求める
			int len = 0;
			for (int i = 0; i < idx; i++)
				len += s1.substring(i, i + 1).getBytes().length;
			len += s2.length();

			System.out.println((idx + 1) + "文字目にマッチします。");
			System.out.println("テキスト：" + s1);
			System.out.printf(String.format("パターン：%%%ds\n", len), s2);
		}
	}
}