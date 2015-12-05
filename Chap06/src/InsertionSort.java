// 単純挿入ソート

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class InsertionSort {

	//--- 単純挿入ソート ---//
	static void insertionSort(int a[], int n){
		for(int i = 1; i < n; i++){
			int j;
			int tmp = a[i];		//原列の先頭要素の値を確保
			for(j = i; j > 0 && a[j-1] > tmp; j--){
				a[j] = a[j-1];	//a[i]が目的列を挿入位置から1つ後ろにずらす
			}
			a[j]=tmp;		//確保した値を挿入する。
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

		insertionSort(arr, num);

		System.out.println(Arrays.toString(arr));
	}


}
