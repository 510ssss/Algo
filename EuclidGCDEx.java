//再帰メソッド呼び出しを用いず、ユークリッド互除法によってGCDを求める
import java.util.Scanner;

public class EuclidGCDEx {

	static int gcd(int x, int y){
		int sp = x % y;
		while(sp != 0){
			x = y;
			y = sp;
			sp = x % y;
		}
		return y;
	}

	public static void main(String[] args) {

		Scanner stdIn = new Scanner(System.in);

		System.out.println("2つの整数を入力");

		System.out.print("num1:");
		int num1 = stdIn.nextInt();
		System.out.print("num2:");
		int num2 = stdIn.nextInt();

		System.out.println("gcd(" + num1 + ", " + num2 + ")=" + gcd(num1, num2));

	}
}
