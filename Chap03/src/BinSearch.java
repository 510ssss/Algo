import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class BinSearch {
	
	//--- 二分探索メソッド ---//
	static int binSearch(int[] a, int n, int key){
		int p1=0;	//探索範囲の先頭のインデックス
		int pr=n-1;	//探索範囲の末尾のインデックス

		do{
			int pc = (p1 + pr) / 2;	//探索範囲の中心
			if(a[pc] == key)
				return pc;	//中心要素とキーが一致したらそのインデックスを返す
			else if(a[pc] < key)
				p1 = pc + 1;	//探索範囲前半にキーはないから、探索範囲の先頭を中心の次の要素とする。
			else
				pr = pc -1;	//探索範囲後半にキーはないから、探索範囲の末尾を中心の前の要素とする。
		}while(p1<=pr);

		return -1;			//探索失敗
	}

	public static void main(String[] args) {

		Scanner stdIn = new Scanner(System.in);	//Scannerクラスのインスタンスを生成
		Random rnd = new Random();		//Randomクラスのインスタンスを生成

		System.out.print("要素数:");
		int num = stdIn.nextInt();
		int[] arr = new int[num];		//要素数numの配列arrを生成

		for(int i=0; i < num; i++)
			arr[i] = rnd.nextInt(50);	//50までの乱数をarrの要素に格納

		Arrays.sort(arr);			//arrを昇順でソート

		for(int i=0; i < num; i++)
			System.out.println("x[" + i + "]:" + arr[i]);	//arrの要素を表示

		System.out.print("キー:");
		int ky = stdIn.nextInt();

		int idx = binSearch(arr, num, ky);	//二分探索メソッドの呼び出し

		if(idx < 0)
			System.out.println("その値はありません。" );
		else
			System.out.println(ky + "はx[" + idx + "]にあります。");

	}

}
