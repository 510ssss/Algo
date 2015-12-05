// 単純選択ソート

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class SelectionSort {

	//--- 交換メソッド ---//
	static void swap(int[] a, int idx1, int idx2){
		int t = a[idx1]; a[idx1] = a[idx2]; a[idx2] = t;
	}

	//--- 単純選択ソート ---//
	static void selectionSort(int[] a, int n){
		for(int i = 0; i < n-1; i++){
			int min = i;			//選択範囲の最小値のインデックスを宣言(デフォルトは選択範囲の先頭)
			for(int j = i+1; j < n; j++)
				if(a[j] < a[min])
					min = j;		//選択範囲の最小値のインデックスを格納
			swap(a, i, min);		//選択範囲の先頭と最小値を交換
		}
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

		selectionSort(arr, num);

		System.out.println(Arrays.toString(arr));

	}

}
