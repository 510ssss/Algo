import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class binSearch {

	static int binSearch(int[] a, int n, int key){
		int p1=0;
		int pr=n-1;

		do{
			int pc = (p1 + pr) / 2;
			if(a[pc] == key)
				return pc;
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
			arr[i] = rnd.nextInt(50);

		Arrays.sort(arr);

		for(int i=0; i < num; i++)
			System.out.println("x[" + i + "]:" + arr[i]);

		System.out.print("キー:");
		int ky = stdIn.nextInt();

		int idx = binSearch(arr, num, ky);

		if(idx < 0)
			System.out.println("その値はありません。" );
		else
			System.out.println(ky + "はx[" + idx + "]にあります。");

	}

}
