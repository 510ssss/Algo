//	線形リストクラス（配列カーソル版）

import java.util.Comparator;

public class AryLinkedList<E> {

	//--- ノード ---//
	class Node<E> {
		private E data;		// データ
		private int next;		// リストの後続ポインタ
		private int dnext;	// フリーリストの後続ポインタ

		//--- dataとnextを設定 ---//
		void set(E data, int next) {
			this.data = data;
			this.next = next;
		}
	}

	private Node<E>[] n;			// リスト本体
	private int size;				// リストの容量（最大データ数）
	private int max;				// 利用中の末尾レコード
	private int head;				// 先頭ノード
	private int crnt;				// 着目ノード
	private int deleted;			// フリーリストの先頭ノード
	private static final int NULL = -1;	// 後続ノードはない／リストは満杯

	//--- コンストラクタ ---//
	public AryLinkedList(int capacity) {
		head = crnt = max = deleted = NULL;
		try {
			n = new Node[capacity];
			for (int i = 0; i < capacity; i++)
				n[i] = new Node<E>();
			size = capacity;
		}
		catch (OutOfMemoryError e) {				// 配列の生成に失敗
			size = 0;
		}
	}

	//--- 次に挿入するレコードのインデックスを求める ---//
	private int getInsertIndex() {
		if (deleted == NULL) {						// 削除レコードは存在しない
			if (max < size)
				return ++max;							// 新しいレコードを利用
			else
				return NULL;							// 容量オーバ
		} else {
			int rec = deleted;						// フリーリストから
			deleted = n[rec].dnext;					// 先頭recを取り出す
			return rec;
		}
	}

	//--- レコードidxをフリーリストに登録 ---//
	private void deleteIndex(int idx) {
		if (deleted == NULL) {						// 削除レコードは存在しない
			deleted = idx;								// idxをフリーリストの
			n[idx].dnext = NULL;						// 先頭に登録
		} else {
			int rec = deleted;						// idxをフリーリストの
			deleted = idx;								// 先頭に挿入
			n[rec].dnext = rec;
		}
	}

	//--- ノードを探索 ---//
	public E search(E obj, Comparator<? super E> c) {
		int ptr = head;								// 現在走査中のノード

		while (ptr != NULL) {
			if (c.compare(obj, n[ptr].data) == 0) {
				crnt = ptr;
				return n[ptr].data;					// 探索成功
			}
			ptr = n[ptr].next;						// 後続ノードに着目
		}
		return null;									// 探索失敗
	}

	//--- 先頭にノードを挿入 ---//
	public void addFirst(E obj) {
		int ptr = head;								// 挿入前の先頭ノード
		int rec = getInsertIndex();
		if (rec != NULL) {
			head = crnt = rec;						// 第recレコードに挿入
			n[head].set(obj, ptr);
		}
	}

	//--- 末尾にノードを挿入 ---//
	public void addLast(E obj) {
		if (head == NULL)								// リストが空であれば
			addFirst(obj);								// 先頭に挿入
		else {
			int ptr = head;
			while (n[ptr].next != NULL)
				ptr = n[ptr].next;
			int rec = getInsertIndex();
			if (rec != NULL) {						// 第recレコードに挿入
				n[ptr].next = crnt = rec;
				n[rec].set(obj, NULL);
			}
		}
	}

	//--- 先頭ノードを削除 ---//
	public void removeFirst() {
		if (head != NULL) {							// リストが空でなければ
			int ptr = n[head].next;
			deleteIndex(ptr);
			head = crnt = ptr;
		}
	}

	//--- 末尾ノードを削除 ---//
	public void removeLast() {
		if (head != NULL) {
			if (n[head].next == NULL)				// ノードが一つだけであれば
				removeFirst();							// 先頭ノードを削除
			else {
				int ptr = head;						// 走査中のノード
				int pre = head;						// 走査中のノードの先行ノード

				while (n[ptr].next != NULL) {
					pre = ptr;
					ptr = n[ptr].next;
				}
				n[pre].next = NULL;					// preは削除後の末尾ノード
				deleteIndex(pre);
				crnt = pre;
			}
		}
	}

	//--- レコードpを削除 ---//
	public void remove(int p) {
		if (head != NULL) {
			if (p == head)								// pが先頭ノードであれば
				removeFirst();							// 先頭ノードを削除
			else {
				int ptr = head;

				while (n[ptr].next != p) {
					ptr = n[ptr].next;
					if (ptr == NULL) return;		// pはリスト上に存在しない
				}
				n[ptr].next = NULL;
				deleteIndex(ptr);
				n[ptr].next = n[p].next;
				crnt = ptr;
			}
		}
	}

	//--- 着目ノードを削除 ---//
	public void removeCurrentNode() {
		remove(crnt);
	}

	//--- 全ノードを削除 ---//
	public void clear() {
		while (head != NULL)			// 空になるまで
			removeFirst();				// 先頭ノードを削除
		crnt = NULL;
	}

	//--- 着目ノードを一つ後方に進める ---//
	public boolean next() {
		if (crnt == NULL || n[crnt].next == NULL)
			return false;						// 進めることはできなかった
		crnt = n[crnt].next;
		return true;
	}

	//--- 着目ノードを表示 ---//
	public void printCurrentNode() {
		if (crnt == NULL)
			System.out.println("着目ノードはありません。");
		else
			System.out.println(n[crnt].data);
	}

	//--- 全ノードを表示 ---//
	public void dump() {
		int ptr = head;

		while (ptr != NULL) {
			System.out.println(n[ptr].data);
			ptr = n[ptr].next;
		}
	}
}
