// ２分探索木クラスBinTree<K,V>の利用例

import java.util.Scanner;

class BinTreeTester {
	static Scanner stdIn = new Scanner(System.in);

	//--- データ（会員番号＋氏名） ---//
	static class Data {
		public final static int NO   = 1;	// 番号を読み込むか？
		public final static int NAME = 2;	// 氏名を読み込むか？

		Integer no;					// 会員番号（キー値）
		String  name;				// 氏名

		//--- キー値 ---//
		public Integer keyCode() {
			return no;
		}

		//--- 文字列表現を返す ---//
		public String toString() {
			return name;
		}

		//--- データの読込み ---//
		public void scanData(String guide, int sw) {
			System.out.println(guide + "するデータを入力してください。");

			if ((sw & NO) == NO) {
				System.out.print("番号：");
				no = stdIn.nextInt();
			}
			if ((sw & NAME) == NAME) {
				System.out.print("名前：");
				name = stdIn.next();
			}
		}
	}

	//--- メニュー列挙型 ---//
	public enum Menu {
		ADD(	  "ノード挿入"),
		REMOVE(	  "ノード削除"),
		SEARCH(	  "ノード探索"),
		PRINT(	  "全データ表示"),
		TERMINATE("終了");

		private final String message;			// 表示用文字列

		static public Menu MenuAt(int idx) {	// 序数がidxである列挙を返す
			for (Menu m : Menu.values())
				if (m.ordinal() == idx)
					return m;
			return null;
		}

		Menu(String string) {				// コンストラクタ
			message = string;
		}

		public String getMessage() {		// 表示用文字列を返す
			return message;
		}
	}

	//--- メニュー選択 ---//
	static Menu SelectMenu() {
		int key;
		do {
			for (Menu m : Menu.values()) {
				System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
				if ((m.ordinal() % 3) == 2 &&
					 m.ordinal() != Menu.TERMINATE.ordinal())
					System.out.println();
			}
			System.out.print("：");
			key = stdIn.nextInt();
		} while (key < Menu.ADD.ordinal() || key > Menu.TERMINATE.ordinal());

		return Menu.MenuAt(key);
	}

	public static void main(String[] args) {
		Menu menu;					// メニュー
		Data data;					// 追加用データ参照
		Data ptr;					// 探索用データ参照
		Data temp = new Data();		// 読込み用データ
		BinTree<Integer, Data> tree = new BinTree<Integer, Data>();

		do {
			switch (menu = SelectMenu()) {
			 case ADD :				// ノードの挿入
					data = new Data();
			 		data.scanData("挿入", Data.NO | Data.NAME);
			 		tree.add(data.keyCode(), data);
			 		break;

			 case REMOVE :			// ノードの削除
					temp.scanData("削除", Data.NO);
			 		tree.remove(temp.keyCode());
			 		break;

			 case SEARCH :			// ノードの探索
					temp.scanData("探索", Data.NO);
			 		ptr = tree.search(temp.keyCode());
			 		if (ptr != null)
						System.out.println("その番号の氏名は" +
											ptr.toString() + "です。");
					else
			 			System.out.println("該当データはありません。");
			 		break;

			 case PRINT :			// 全ノードをキー値の昇順に表示
					tree.print();
					break;
			}
		} while (menu != Menu.TERMINATE);
	}
}
