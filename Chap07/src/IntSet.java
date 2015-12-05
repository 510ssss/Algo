//int型の集合

public class IntSet {
	private int max;	//集合の容量
	private int num;	//集合の要素数
	private int[] set;	//集合本体
	
	//-- コンストラクタ --//
	public IntSet(int capasity){
		num = 0;
		max = capasity;
		try{
			set = new int[max]; //集合本体の配列を生成
		}
		catch (OutOfMemoryError e){
			max = 0;			//maxの値が不正ならmaxに0を格納し、配列の生成を行わない
		}
	}
	
	public int indexOf(int n){
		for(int i = 0; i < num; i++)
			if(set[i] == n)
				return i;
		return -1;
	}
	
	public boolean contains(int n){
		return (indexOf(n) == -1) ? true : false;
	}
	
	public boolean add(int n){
		if(num >= max || contains(n) == true)
			return false;
		else{
			set[num++] = n;
			return true;
		}
	}
	
	public boolean remove(int n){
		int idx;
		
		if(num <= 0 || (idx = indexOf(n)) == -1)
			return false;
		else{
			num--;
			for(int i = idx; i < num; i++)
				set[i] = set[i + 1];
			return true;
		}
	}
	
	public int capasity(){
		return num;
	}
	
	public int size(){
		return max;
	}
	
	
}
