// ���`���X�g�N���XAryLinkedList<E>�̗��p��

import java.util.Scanner;
import java.util.Comparator;

class AryLinkedListTester {
	static Scanner stdIn = new Scanner(System.in);

	//--- �f�[�^�i����ԍ��{�����j ---//
	static class Data {
		public static final int NO   = 1;	// �ԍ���ǂݍ��ނ��H
		public static final int NAME = 2;	// ������ǂݍ��ނ��H

		Integer no;					// ����ԍ�
		String  name;				// ����

		//--- ������\����Ԃ� ---//
		public String toString() {
			return "(" + no + ") " + name;
		}

		//--- �f�[�^�̓Ǎ��� ---//
		public void scanData(String guide, int sw) {
			System.out.println(guide + "����f�[�^����͂��Ă��������B");

			if ((sw & NO) == NO) {
				System.out.print("�ԍ��F");
				no = stdIn.nextInt();
			}
			if ((sw & NAME) == NAME) {
				System.out.print("���O�F");
				name = stdIn.next();
			}
		}

		//--- ����ԍ��ɂ�鏇���t�����s���R���p���[�^  ---//
		public static final Comparator<Data> NO_ORDER =
											 new NoOrderComparator();

		private static class NoOrderComparator implements Comparator<Data> {
			public int compare(Data d1, Data d2) {
				return (d1.no > d2.no) ?  1 : (d1.no < d2.no) ? -1 : 0;
			}
		}

		//--- �����ɂ�鏇���t�����s���R���p���[�^  ---//
		public static final Comparator<Data> NAME_ORDER =
											 new NameOrderComparator();

		private static class NameOrderComparator implements Comparator<Data> {
			public int compare(Data d1, Data d2) {
				return d1.name.compareTo(d2.name);
			}
		}
	}

	//--- ���j���[�񋓌^ ---//
	enum Menu {
		ADD_FIRST(  "�擪�Ƀm�[�h��}��"),
		ADD_LAST(   "�����Ƀm�[�h��}��"),
		RMV_FIRST(  "�擪�m�[�h���폜"),
		RMV_LAST(   "�����m�[�h���폜"),
		RMV_CRNT(   "���ڃm�[�h���폜"),
		CLEAR(      "�S�m�[�h���폜"),
		SEARCH_NO(  "�ԍ��ŒT��"),
		SEARCH_NAME("�����ŒT��"),
		NEXT(       "���ڃm�[�h��i�߂�"),
		PRINT_CRNT( "���ڃm�[�h��\��"),
		DUMP(       "�S�m�[�h��\��"),
		TERMINATE(  "�I��");

		private final String message;				// �\���p������

		static Menu MenuAt(int idx) {	// ������idx�ł���񋓂�Ԃ�
			for (Menu m : Menu.values())
				if (m.ordinal() == idx)
					return m;
			return null;
		}

		Menu(String string) {					// �R���X�g���N�^
			message = string;
		}

		String getMessage() {			// �\���p�������Ԃ�
			return message;
		}
	}

	//--- ���j���[�I�� ---//
	static Menu SelectMenu() {
		int key;
		do {
			for (Menu m : Menu.values()) {
				System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
				if ((m.ordinal() % 3) == 2 &&
					  m.ordinal() != Menu.TERMINATE.ordinal())
					System.out.println();
			}
			System.out.print("�F");
			key = stdIn.nextInt();
		} while (key < Menu.ADD_FIRST.ordinal() || 
											key > Menu.TERMINATE.ordinal());
		return Menu.MenuAt(key);
	}

	public static void main(String[] args) {
		Menu menu;					// ���j���[
		Data data;					// �ǉ��p�f�[�^�Q��
		Data ptr;					// �T���p�f�[�^�Q��
		Data temp = new Data();	// �Ǎ��ݗp�f�[�^

		AryLinkedList<Data> list = new AryLinkedList<Data>(100);

		do {
			switch (menu = SelectMenu()) {

			 case ADD_FIRST :						// �擪�Ƀm�[�h��}��
					data = new Data();
				 	data.scanData("�擪�ɑ}��", Data.NO | Data.NAME);
					list.addFirst(data);
		 			break;

			 case ADD_LAST :						// �����Ƀm�[�h��}��
					data = new Data();
				 	data.scanData("�����ɑ}��", Data.NO | Data.NAME);
			 		list.addLast(data);
			 		break;

			 case RMV_FIRST :						// �擪�m�[�h���폜
					list.removeFirst();
					break;

			 case RMV_LAST :						// �����m�[�h���폜
					list.removeLast();
					break;

			 case RMV_CRNT :						// ���ڃm�[�h���폜
					list.removeCurrentNode();
					break;

			 case SEARCH_NO :						// ����ԍ��ŒT��
				 	temp.scanData("�T��", Data.NO);
					ptr = list.search(temp, Data.NO_ORDER);
					if (ptr == null)
						System.out.println("���̔ԍ��̃f�[�^�͂���܂���B");
					else
						System.out.println("�T�������F" + ptr);
			 		break;

			 case SEARCH_NAME :					// �����ŒT��
				 	temp.scanData("�T��", Data.NAME);
					ptr = list.search(temp, Data.NAME_ORDER);
					if (ptr == null)
						System.out.println("���̖��O�̃f�[�^�͂���܂���B");
					else
						System.out.println("�T�������F" + ptr);
			 		break;

			 case NEXT :							// ���ڃm�[�h������ɐi�߂�
					list.next();
			 		break;

			 case PRINT_CRNT :					// ���ڃm�[�h�̃f�[�^��\��
					list.printCurrentNode();
			 		break;

			 case DUMP :							// �S�f�[�^�����X�g���ɕ\��
					list.dump();
			 		break;

			 case CLEAR :							// �S�m�[�h���폜
					list.clear();
			 		break;
			}
		} while (menu != Menu.TERMINATE);
	}
}
