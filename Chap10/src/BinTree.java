// ２分探索木

import java.util.Comparator;

class BinTree<K,V> {

	//--- ノード ---//
	static class Node<K,V> {
		K key;					// キー値
		V data;					// データ
		Node<K,V> left;			// 左子ノード
		Node<K,V> right;		// 右子ノード

		//--- コンストラクタ ---//
		Node(K key, V data, Node<K,V> left, Node<K,V> right) {
			this.key   = key;
			this.data  = data;
			this.left  = left;
			this.right = right;
		}

		//--- キー値を返す ---//
		K getKey() {
			return key;
		}

		//--- データを返す ---//
		V getValue() {
			return data;
		}

		//--- データの表示 ---//
		void print() {
			System.out.println(data.toString());
		}
	}

	Node<K,V> root;		// 根
	private Comparator<? super K> comparator = null;	// コンパレータ

	//--- コンストラクタ ---//
	BinTree() {
		root = null;
	}

	//--- コンストラクタ ---//
	BinTree(Comparator<? super K> c) {
		this();
		comparator = c;
	}

	//--- 二つのキーを比較 ---//
	private int comp(K key1, K key2) {
		return (comparator == null)
					? ((Comparable<K>)key1).compareTo(key2)
					: comparator.compare(key1, key2);
	}

	//--- キーによる探索 ---//
	V search(K key)	{
		Node<K,V> p = root;						// 根に着目

		while (true) {
			if (p == null)						// これ以上進めなければ
				return null;					// …探索失敗
			int cond = comp(key, p.getKey());	// keyとノードpのキーを比較
			if (cond == 0)						// 等しければ
				return p.getValue();			// …探索成功
			else if (cond < 0)					// keyのほうが小さければ
				p = p.left;						// …左部分木から探索
			else								// keyのほうが大きければ
				p = p.right;					// …右部分木から探索
		}
	}

	//--- nodeを根とする部分木にノード<K,V>を挿入 ---//
	private void addNode(Node<K,V> node, K key, V data) {
		int cond = comp(key, node.getKey());
		if (cond == 0)
			return;							// keyは２分探索木上に既に存在
		else if (cond < 0) {
			if (node.left == null)
				node.left = new Node<K,V>(key, data, null, null);
			else
				addNode(node.left, key, data);			// 左部分木に着目
		} else {
			if (node.right == null)
				node.right = new Node<K,V>(key, data, null, null);
			else
				addNode(node.right, key, data);			// 右部分木に着目
		}
	}

	//--- ノードを挿入 ---//
	void add(K key, V data) {
		if (root == null)
			root = new Node<K,V>(key, data, null, null);
		else
			addNode(root, key, data);
	}

	//--- キー値がkeyであるノードを削除 --//
	boolean remove(K key) {
		Node<K,V> p = root;				// 走査中のノード
		Node<K,V> parent = null;		// 走査中のノードの親ノード
		boolean isLeftChild = true;		// pはparentの左子ノードか？

		while (true) {
			if (p == null)						// これ以上進めなければ
				return false;					// …そのキー値は存在しない
			int cond = comp(key, p.getKey());	// keyとノードpのキー値を比較
			if (cond == 0)						// 等しければ
				break;							// …探索成功
			else {
				parent = p;						// 枝を下る前に親を設定
				if (cond < 0) {					// keyのほうが小さければ
					isLeftChild = true;			// …これから下るのは左の子
					p = p.left;					// …左部分木から探索
				} else {						// keyのほうが大きければ
					isLeftChild = false;		// …これから下るのは右の子
					p = p.right;				// …右部分木から探索
				}
			}
		}

		if (p.left == null) {				// pには左の子がない…
			if (p == root)
				root = p.right;
			else if (isLeftChild)
				parent.left  = p.right;		// 親の左ポインタが右の子を指す
			else
				parent.right = p.right;		// 親の右ポインタが右の子を指す
		} else if (p.right == null) {		// pには右の子がない…
			if (p == root)
				root = p.left;
			else if (isLeftChild)
				parent.left  = p.left;		// 親の左ポインタが左の子を指す
			else
				parent.right = p.left;		// 親の右ポインタが左の子を指す
		} else {
			parent = p;
			Node<K,V> left = p.left;			// 部分木の中の最大ノード
			isLeftChild = true;
			while (left.right != null) {		// 最大ノードleftを探索
				parent = left;
				left = left.right;
				isLeftChild = false;
			}
			p.key  = left.key;					// leftのキー値をpに移動
			p.data = left.data;					// leftのデータをpに移動
			if (isLeftChild)
				parent.left  = left.left;		// leftを削除
			else
				parent.right = left.left;		// leftを削除
		}
		return true;
	}

	//--- nodeを根とする部分木のノードをキー値の昇順に表示 ---//
	private void printSubTree(Node node) {
		if (node != null) {
			printSubTree(node.left);		// 左部分木をキー値の昇順に表示
			System.out.println(node.key.toString() + " " +
							   node.data.toString());		// nodeを表示
			printSubTree(node.right);		// 右部分木をキー値の昇順に表示
		}
	}

	//--- 全ノードをキー値の昇順に表示 ---//
	void print() {
		printSubTree(root);
	}
}
