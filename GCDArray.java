//配列の全要素の最大公約数を求める。
import java.util.Arrays;
import java.util.Scanner;

public class GCDArray {

	static int gcd(int x, int y){
		if(y == 0)
			return x;
		else
			return gcd(y, x % y);
	}

	static int gcdArray(int[] a){
		int ans = 1;
		for(int i=0; i<a.length; i++){
			if(i == 0){
				ans=gcd(a[i], a[i+1]);
				i++;
			}
			else
				ans=gcd(ans, a[i]);
		}
		return ans;
	}

	public static void main(String[] args){
		Scanner stdIn = new Scanner(System.in);

		System.out.print("配列の要素数:");
		int num = stdIn.nextInt();
		int[] x = new int[num];

		for(int i=0; i<num; i++){
			System.out.print("x[" + i + "]:");
			x[i] = stdIn.nextInt();
		}

		System.out.println("gcd" + Arrays.toString(x) + "=" + gcdArray(x));
	}

}
