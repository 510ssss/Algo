//再帰メソッド呼び出しを用いず、階乗を求める。
import java.util.Scanner;

public class FactorialEx {

	static int factorial(int n){
		int ans = 1;		//階乗値を格納する変数
		while(n >= 1)
			ans *= n--;	//引数が1以上なら乗算代入、その後デクリメント
		return ans;
	}

	public static void main(String[] args) {

		Scanner stdIn = new Scanner(System.in);

		System.out.print("整数を入力せよ:");
		int num = stdIn.nextInt();

		System.out.println(num + "! = " + factorial(num));

	}

}
