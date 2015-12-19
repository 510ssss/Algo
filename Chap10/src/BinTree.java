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

	//--- �L�[�l��key�ł���m�[�h���폜 --//
	boolean remove(K key) {
		Node<K,V> p = root;				// �������̃m�[�h
		Node<K,V> parent = null;		// �������̃m�[�h�̐e�m�[�h
		boolean isLeftChild = true;		// p��parent�̍��q�m�[�h���H

		while (true) {
			if (p == null)						// ����ȏ�i�߂Ȃ����
				return false;					// �c���̃L�[�l�͑��݂��Ȃ�
			int cond = comp(key, p.getKey());	// key�ƃm�[�hp�̃L�[�l���r
			if (cond == 0)						// ���������
				break;							// �c�T������
			else {
				parent = p;						// �}������O�ɐe��ݒ�
				if (cond < 0) {					// key�̂ق������������
					isLeftChild = true;			// �c���ꂩ�牺��͍̂��̎q
					p = p.left;					// �c�������؂���T��
				} else {						// key�̂ق����傫�����
					isLeftChild = false;		// �c���ꂩ�牺��͉̂E�̎q
					p = p.right;				// �c�E�����؂���T��
				}
			}
		}

		if (p.left == null) {				// p�ɂ͍��̎q���Ȃ��c
			if (p == root)
				root = p.right;
			else if (isLeftChild)
				parent.left  = p.right;		// �e�̍��|�C���^���E�̎q���w��
			else
				parent.right = p.right;		// �e�̉E�|�C���^���E�̎q���w��
		} else if (p.right == null) {		// p�ɂ͉E�̎q���Ȃ��c
			if (p == root)
				root = p.left;
			else if (isLeftChild)
				parent.left  = p.left;		// �e�̍��|�C���^�����̎q���w��
			else
				parent.right = p.left;		// �e�̉E�|�C���^�����̎q���w��
		} else {
			parent = p;
			Node<K,V> left = p.left;			// �����؂̒��̍ő�m�[�h
			isLeftChild = true;
			while (left.right != null) {		// �ő�m�[�hleft��T��
				parent = left;
				left = left.right;
				isLeftChild = false;
			}
			p.key  = left.key;					// left�̃L�[�l��p�Ɉړ�
			p.data = left.data;					// left�̃f�[�^��p�Ɉړ�
			if (isLeftChild)
				parent.left  = left.left;		// left���폜
			else
				parent.right = left.left;		// left���폜
		}
		return true;
	}


}

