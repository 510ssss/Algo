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
		return (indexOf(n) != -1) ? true : false;
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
	
	public void copyTo(IntSet s){
		int n = (s.max < num) ? s.max : num;
		for(int i = 0; i < n; i++)
			s.set[i] = set[i];
		num = n;
	}
	
	public void copyFrom(IntSet s){
		int n = (max < s.num) ? num : s.max;
		for(int i = 0; i < n; i++)
			set[i] = s.set[i];
		num = n;
	}
	
	public boolean equalTo(IntSet s){
		if(num != s.num)
			return false;
		
		for(int i = 0; i < num; i++){
			int j = 0;
			for( ; j < s.num; i++)
				if(set[i] == s.set[j])
					break;
			if(j == s.num)
				return false;
		}
		return true;
	}
	
	public void uniofOf(IntSet s1, IntSet s2){
		copyFrom(s1);
		for(int i = 0; i < s2.num; i++)
			add(s2.set[i]);
	}
	
	public String toString(){
		StringBuffer br = new StringBuffer("{");
		for(int i = 0; i < num; i++)
			br.append(set[i] + "");
		br.append("}");
		return br.toString();
	}
}
