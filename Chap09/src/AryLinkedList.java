//	���`���X�g�N���X�i�z��J�[�\���Łj

import java.util.Comparator;

public class AryLinkedList<E> {

	//--- �m�[�h ---//
	class Node<E> {
		private E data;		// �f�[�^
		private int next;		// ���X�g�̌㑱�|�C���^
		private int dnext;	// �t���[���X�g�̌㑱�|�C���^

		//--- data��next��ݒ� ---//
		void set(E data, int next) {
			this.data = data;
			this.next = next;
		}
	}

	private Node<E>[] n;			// ���X�g�{��
	private int size;				// ���X�g�̗e�ʁi�ő�f�[�^���j
	private int max;				// ���p���̖������R�[�h
	private int head;				// �擪�m�[�h
	private int crnt;				// ���ڃm�[�h
	private int deleted;			// �t���[���X�g�̐擪�m�[�h
	private static final int NULL = -1;	// �㑱�m�[�h�͂Ȃ��^���X�g�͖��t

	//--- �R���X�g���N�^ ---//
	public AryLinkedList(int capacity) {
		head = crnt = max = deleted = NULL;
		try {
			n = new Node[capacity];
			for (int i = 0; i < capacity; i++)
				n[i] = new Node<E>();
			size = capacity;
		}
		catch (OutOfMemoryError e) {				// �z��̐����Ɏ��s
			size = 0;
		}
	}

	//--- ���ɑ}�����郌�R�[�h�̃C���f�b�N�X�����߂� ---//
	private int getInsertIndex() {
		if (deleted == NULL) {						// �폜���R�[�h�͑��݂��Ȃ�
			if (max < size)
				return ++max;							// �V�������R�[�h�𗘗p
			else
				return NULL;							// �e�ʃI�[�o
		} else {
			int rec = deleted;						// �t���[���X�g����
			deleted = n[rec].dnext;					// �擪rec�����o��
			return rec;
		}
	}

	//--- ���R�[�hidx���t���[���X�g�ɓo�^ ---//
	private void deleteIndex(int idx) {
		if (deleted == NULL) {						// �폜���R�[�h�͑��݂��Ȃ�
			deleted = idx;								// idx���t���[���X�g��
			n[idx].dnext = NULL;						// �擪�ɓo�^
		} else {
			int rec = deleted;						// idx���t���[���X�g��
			deleted = idx;								// �擪�ɑ}��
			n[rec].dnext = rec;
		}
	}

	//--- �m�[�h��T�� ---//
	public E search(E obj, Comparator<? super E> c) {
		int ptr = head;								// ���ݑ������̃m�[�h

		while (ptr != NULL) {
			if (c.compare(obj, n[ptr].data) == 0) {
				crnt = ptr;
				return n[ptr].data;					// �T������
			}
			ptr = n[ptr].next;						// �㑱�m�[�h�ɒ���
		}
		return null;									// �T�����s
	}

	//--- �擪�Ƀm�[�h��}�� ---//
	public void addFirst(E obj) {
		int ptr = head;								// �}���O�̐擪�m�[�h
		int rec = getInsertIndex();
		if (rec != NULL) {
			head = crnt = rec;						// ��rec���R�[�h�ɑ}��
			n[head].set(obj, ptr);
		}
	}

	//--- �����Ƀm�[�h��}�� ---//
	public void addLast(E obj) {
		if (head == NULL)								// ���X�g����ł����
			addFirst(obj);								// �擪�ɑ}��
		else {
			int ptr = head;
			while (n[ptr].next != NULL)
				ptr = n[ptr].next;
			int rec = getInsertIndex();
			if (rec != NULL) {						// ��rec���R�[�h�ɑ}��
				n[ptr].next = crnt = rec;
				n[rec].set(obj, NULL);
			}
		}
	}

	//--- �擪�m�[�h���폜 ---//
	public void removeFirst() {
		if (head != NULL) {							// ���X�g����łȂ����
			int ptr = n[head].next;
			deleteIndex(ptr);
			head = crnt = ptr;
		}
	}

	//--- �����m�[�h���폜 ---//
	public void removeLast() {
		if (head != NULL) {
			if (n[head].next == NULL)				// �m�[�h��������ł����
				removeFirst();							// �擪�m�[�h���폜
			else {
				int ptr = head;						// �������̃m�[�h
				int pre = head;						// �������̃m�[�h�̐�s�m�[�h

				while (n[ptr].next != NULL) {
					pre = ptr;
					ptr = n[ptr].next;
				}
				n[pre].next = NULL;					// pre�͍폜��̖����m�[�h
				deleteIndex(pre);
				crnt = pre;
			}
		}
	}

	//--- ���R�[�hp���폜 ---//
	public void remove(int p) {
		if (head != NULL) {
			if (p == head)								// p���擪�m�[�h�ł����
				removeFirst();							// �擪�m�[�h���폜
			else {
				int ptr = head;

				while (n[ptr].next != p) {
					ptr = n[ptr].next;
					if (ptr == NULL) return;		// p�̓��X�g��ɑ��݂��Ȃ�
				}
				n[ptr].next = NULL;
				deleteIndex(ptr);
				n[ptr].next = n[p].next;
				crnt = ptr;
			}
		}
	}

	//--- ���ڃm�[�h���폜 ---//
	public void removeCurrentNode() {
		remove(crnt);
	}

	//--- �S�m�[�h���폜 ---//
	public void clear() {
		while (head != NULL)			// ��ɂȂ�܂�
			removeFirst();				// �擪�m�[�h���폜
		crnt = NULL;
	}

	//--- ���ڃm�[�h�������ɐi�߂� ---//
	public boolean next() {
		if (crnt == NULL || n[crnt].next == NULL)
			return false;						// �i�߂邱�Ƃ͂ł��Ȃ�����
		crnt = n[crnt].next;
		return true;
	}

	//--- ���ڃm�[�h��\�� ---//
	public void printCurrentNode() {
		if (crnt == NULL)
			System.out.println("���ڃm�[�h�͂���܂���B");
		else
			System.out.println(n[crnt].data);
	}

	//--- �S�m�[�h��\�� ---//
	public void dump() {
		int ptr = head;

		while (ptr != NULL) {
			System.out.println(n[ptr].data);
			ptr = n[ptr].next;
		}
	}
}
