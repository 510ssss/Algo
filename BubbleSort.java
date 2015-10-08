import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class BubbleSort {

	//--- 交換メソッド ---//
	static void swap(int[] a, int idx1, int idx2){
		int t = a[idx1]; a[idx1] = a[idx2]; a[idx2] = t;
	}

	//--- バブルソートメソッド ---//
	static void bubbleSort(int [] a, int n){
		for(int i = 0; i < n-1; i++)		//泡の終着点iのループ
			for(int j = n-1; j > i; j--)	//比較する場所の移動
				if(a[j-1] > a[j])			//隣接する要素が降順の場合、交換する
					swap(a, j-1, j);
	}

	public static void main(String[] args) {

		Scanner stdIn = new Scanner(System.in);
		Random rnd = new Random();

		System.out.print("要素数:");
		int num = stdIn.nextInt();
		int[] arr = new int[num];

		for(int i=0; i < num; i++)
			arr[i] = rnd.nextInt(50);


		System.out.println(Arrays.toString(arr));

		bubbleSort(arr, num);

		System.out.println(Arrays.toString(arr));
	}

}
