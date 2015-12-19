import java.util.Comparator;

public class BinTree<K,V> {

	static class Node<K,V>{
		private K key;
		private V data;
		private Node<K,V> left;
		private Node<K,V> right;

		Node(K key, V data, Node<K,V> left, Node<K,V> right){
			this.key = key;
			this.data = data;
			this.left = left;
			this.right = right;
		}

		K getKey(){
			return key;
		}

		V getValue(){
			return data;
		}

		void print(){
			System.out.println(data);
		}
	}

	private Node<K,V> root;
	private Comparator<? super K> comparator = null;

	private int comp(K key1, K key2){
		return (comparator == null) ? ((Comparable<K>)key1).compareTo(key2)
									: comparator.compare(key1, key2);
	}

	public V search(K key){
		Node<K,V> p = root;

		while(true){
			if(p == null)
				return null;
			int cond = comp(key, p.getKey());
			if(cond == 0)
				return p.getValue();
			else if (cond < 0)
				p = p.left;
			else
				p = p.right;
		}
	}

	private void addNode(Node<K,V> node, K key, V data){
		int cond = comp(key, node.getKey());
		if(cond == 0)
			return;
		else if(cond < 0){
			if(node.left == null)
				node.left = new Node<K,V>(key, data, null, null);
			else
				addNode(node.left, key, data);
		}else{
			if(node.right == null)
				node.right = new Node<K,V>(key, data, null, null);
			else
				addNode(node.right, key, data);
		}
	}

	public void add(K key, V data){
		if(root == null)
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


}

