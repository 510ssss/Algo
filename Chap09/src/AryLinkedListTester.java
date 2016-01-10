// 線形リストクラスAryLinkedList<E>の利用例

import java.util.Scanner;
import java.util.Comparator;

class AryLinkedListTester {
	static Scanner stdIn = new Scanner(System.in);

	//--- データ（会員番号＋氏名） ---//
	static class Data {
		public static final int NO   = 1;	// 番号を読み込むか？
		public static final int NAME = 2;	// 氏名を読み込むか？

		Integer no;					// 会員番号
		String  name;				// 氏名

		//--- 文字列表現を返す ---//
		public String toString() {
			return "(" + no + ") " + name;
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

		//--- 会員番号による順序付けを行うコンパレータ  ---//
		public static final Comparator<Data> NO_ORDER =
											 new NoOrderComparator();

		private static class NoOrderComparator implements Comparator<Data> {
			public int compare(Data d1, Data d2) {
				return (d1.no > d2.no) ?  1 : (d1.no < d2.no) ? -1 : 0;
			}
		}

		//--- 氏名による順序付けを行うコンパレータ  ---//
		public static final Comparator<Data> NAME_ORDER =
											 new NameOrderComparator();

		private static class NameOrderComparator implements Comparator<Data> {
			public int compare(Data d1, Data d2) {
				return d1.name.compareTo(d2.name);
			}
		}
	}

	//--- メニュー列挙型 ---//
	enum Menu {
		ADD_FIRST(  "先頭にノードを挿入"),
		ADD_LAST(   "末尾にノードを挿入"),
		RMV_FIRST(  "先頭ノードを削除"),
		RMV_LAST(   "末尾ノードを削除"),
		RMV_CRNT(   "着目ノードを削除"),
		CLEAR(      "全ノードを削除"),
		SEARCH_NO(  "番号で探索"),
		SEARCH_NAME("氏名で探索"),
		NEXT(       "着目ノードを進める"),
		PRINT_CRNT( "着目ノードを表示"),
		DUMP(       "全ノードを表示"),
		TERMINATE(  "終了");

		private final String message;				// 表示用文字列

		static Menu MenuAt(int idx) {	// 序数がidxである列挙を返す
			for (Menu m : Menu.values())
				if (m.ordinal() == idx)
					return m;
			return null;
		}

		Menu(String string) {					// コンストラクタ
			message = string;
		}

		String getMessage() {			// 表示用文字列を返す
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
		} while (key < Menu.ADD_FIRST.ordinal() || 
											key > Menu.TERMINATE.ordinal());
		return Menu.MenuAt(key);
	}

	public static void main(String[] args) {
		Menu menu;					// メニュー
		Data data;					// 追加用データ参照
		Data ptr;					// 探索用データ参照
		Data temp = new Data();	// 読込み用データ

		AryLinkedList<Data> list = new AryLinkedList<Data>(100);

		do {
			switch (menu = SelectMenu()) {

			 case ADD_FIRST :						// 先頭にノードを挿入
					data = new Data();
				 	data.scanData("先頭に挿入", Data.NO | Data.NAME);
					list.addFirst(data);
		 			break;

			 case ADD_LAST :						// 末尾にノードを挿入
					data = new Data();
				 	data.scanData("末尾に挿入", Data.NO | Data.NAME);
			 		list.addLast(data);
			 		break;

			 case RMV_FIRST :						// 先頭ノードを削除
					list.removeFirst();
					break;

			 case RMV_LAST :						// 末尾ノードを削除
					list.removeLast();
					break;

			 case RMV_CRNT :						// 着目ノードを削除
					list.removeCurrentNode();
					break;

			 case SEARCH_NO :						// 会員番号で探索
				 	temp.scanData("探索", Data.NO);
					ptr = list.search(temp, Data.NO_ORDER);
					if (ptr == null)
						System.out.println("その番号のデータはありません。");
					else
						System.out.println("探索成功：" + ptr);
			 		break;

			 case SEARCH_NAME :					// 氏名で探索
				 	temp.scanData("探索", Data.NAME);
					ptr = list.search(temp, Data.NAME_ORDER);
					if (ptr == null)
						System.out.println("その名前のデータはありません。");
					else
						System.out.println("探索成功：" + ptr);
			 		break;

			 case NEXT :							// 着目ノードを後方に進める
					list.next();
			 		break;

			 case PRINT_CRNT :					// 着目ノードのデータを表示
					list.printCurrentNode();
			 		break;

			 case DUMP :							// 全データをリスト順に表示
					list.dump();
			 		break;

			 case CLEAR :							// 全ノードを削除
					list.clear();
			 		break;
			}
		} while (menu != Menu.TERMINATE);
	}
}
