//�͂܂����@�ɂ�镶����T��

import java.util.Scanner;

public class BFmatch {

	//-- �͂܂����@�ɂ�镶����T�� --//
	static int bfMatch(String txt, String pat){
		int pt = 0;	//txt���Ȃ���J�[�\��
		int pp = 0;	//pat���Ȃ���J�[�\��
		
		//�J�[�\�����Ȃ���Ώە�����̒����ɂȂ�܂ŌJ��Ԃ�
		while(pt != txt.length() && pp != pat.length()){
			if(txt.charAt(pt) == pat.charAt(pp)){
				pt++;	pp++;
			}else{
				pt = pt - pp + 1;	pp = 0;
			}
		}
		if(pp == pat.length())
			return pt-pp;	//�T������
		return -1;	//�T�����s
	}
	
	public static void main(String[] args) {
		
		Scanner stdIn = new Scanner(System.in);
		
		System.out.print("Text :");
		String s1 = stdIn.next();	//�e�L�X�g�p������
		
		System.out.print("Pattern :");
		String s2 = stdIn.next();	//�p�^�[���p������
		
		int idx = bfMatch(s1, s2);
		
		if(idx == -1)
			System.out.println("�e�L�X�g���Ƀp�^�[���͑��݂��܂���B");
		else{
			//�}�b�`�����̒��O�܂ł̔��p�ł̕����������߂�B
			int len = 0;
			for(int i = 0; i < idx; i++)
				len += s1.substring(i, i + 1).getBytes().length;
			len += s2.length();
			
			System.out.println((idx + 1) + "�����ڂɃ}�b�`���܂�");
			System.out.println("�e�L�X�g:" + s1);
			System.out.printf(String.format("�p�^�[��:%%%ds", len),s2);
		}

	}

}
