//階乗を再帰的に求める
import java.util.Scanner;

public class Factorial {

	static int factorial(int n){
		if(n>0)
			return n * factorial(n-1);	//再帰呼び出し
		else
			return 1;
	}

	public static void main(String[] args) {

		Scanner stdIn = new Scanner(System.in);

		System.out.print("整数を入力せよ:");
		int num = stdIn.nextInt();

		System.out.println(num + "! = " + factorial(num));

	}

}
