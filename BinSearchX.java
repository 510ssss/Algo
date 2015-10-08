import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class BinSearchX {
  
  //--- 探索する要素が複数ある場合にその先頭要素を返すよう改良した二分探索メソッド ---//
	static int binSearchX(int[] a, int n, int key){
		int p1=0;
		int pr=n-1;

		do{
			int pc = (p1 + pr) / 2;
			if(a[pc] == key){
				while(a[pc] == a[pc-1]){
					pc--;
				}
				return pc;
			}
			else if(a[pc] < key)
				p1 = pc + 1;
			else
				pr = pc -1;
		}while(p1<=pr);

		return -1;
	}

	public static void main(String[] args) {

		Scanner stdIn = new Scanner(System.in);
		Random rnd = new Random();

		System.out.print("要素数:");
		int num = stdIn.nextInt();
		int[] arr = new int[num];

		for(int i=0; i < num; i++)
			arr[i] = rnd.nextInt(num-1);

		Arrays.sort(arr);

		for(int i=0; i < num; i++)
			System.out.println("x[" + i + "]:" + arr[i]);

		System.out.print("キー:");
		int ky = stdIn.nextInt();

		int idx = binSearchX(arr, num, ky);

		if(idx < 0)
			System.out.println("その値はありません。" );
		else
			System.out.println(ky + "はx[" + idx + "]にあります。");

	}

}
