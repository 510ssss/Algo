// �͂܂����@�ɂ�镶����T��

import java.util.Scanner;

class KMPmatch {

	//--- KMP�@�ɂ�镶����T�� ---//
	static int kmpMatch(String txt, String pat) {
		int pt = 1;											// txt���Ȃ���J�[�\��
		int pp = 0;											// pat���Ȃ���J�[�\��
		int[] skip = new int[pat.length() + 1];	// �X�L�b�v�e�[�u��

		// �X�L�b�v�e�[�u���̍쐬
		skip[pt] = 0;
		while (pt != pat.length()) {
			if (pat.charAt(pt) == pat.charAt(pp))
				skip[++pt] = ++pp;
			else if (pp == 0)
				skip[++pt] = pp;
			else
				pp = skip[pp];
		}

		// �T��
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

		if (pp == pat.length())		// �p�^�[���̑S�������ƍ�
			return pt - pp;
		return -1;					// �T�����s
	}


	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.print("�e�L�X�g�F");
		String s1 = stdIn.next(); 					// �e�L�X�g�p������

		System.out.print("�p�^�[���F");
		String s2 = stdIn.next();					// �p�^�[���p������

		int idx = kmpMatch(s1, s2);	// ������s1���當����s2��͂܂����@�ŒT��

		if (idx == -1)
			System.out.println("�e�L�X�g���Ƀp�^�[���͑��݂��܂���B");
		else {
			// �}�b�`�����̒��O�܂ł́s���p�t�ł̕����������߂�
			int len = 0;
			for (int i = 0; i < idx; i++)
				len += s1.substring(i, i + 1).getBytes().length;
			len += s2.length();

			System.out.println((idx + 1) + "�����ڂɃ}�b�`���܂��B");
			System.out.println("�e�L�X�g�F" + s1);
			System.out.printf(String.format("�p�^�[���F%%%ds\n", len), s2);
		}
	}
}