//int型スタック

public class IntStack {

	private int max;	//スタックの容量
	private int ptr;	//スタックのポインタ
	private int[] stk;	//スタックの本体

	//--- 実行時例外:スタックが空 ---//
	public class EmptyIntStackException extends RuntimeException{
		public EmptyIntStackException(){}
	}

	//--- 実行時例外:スタックが満杯---//
	public class OverflowIntStackException extends RuntimeException{
		public OverflowIntStackException(){}
	}

	//--- コンストラクタ ---//
	public IntStack(int capacity){
		ptr = 0;
		max = capacity;
		try{
			stk = new int[max];	//スタック本体用の配列を生成
		} catch(OutOfMemoryError e){	//生成できなかった
			max = 0;
		}
	}

	//--- スタックにxをプッシュ ---//
	public int push(int x) throws OverflowIntStackException{
		if(ptr >= max)
			throw new OverflowIntStackException();
		return stk[ptr++] = x;
	}

	//--- スタックからデータをポップ ---//
	public int pop() throws EmptyIntStackException{
		if(ptr <= 0)
			throw new EmptyIntStackException();
		return stk[--ptr];
	}

	//--- スタックからデータをピーク ---//
	public int peak() throws EmptyIntStackException{
		if(ptr <= 0)
			throw new EmptyIntStackException();
		return stk[ptr-1];
	}

	//--- スタックからxを探してインデックス ---//
	public int indexOf(int x){
		for(int i = ptr-1; i >= 0; i--)
			if(stk[ptr] == x)
				return i;
		return -1;
	}

	//--- スタックを空にする ---//
	public void clear(){
		ptr = 0;
	}

	//--- スタックの容量を返す ---//
	public int capacity(){
		return max;
	}

	//--- スタックに積まれているデータ数を返す ---//
	public int size(){
		return ptr;
	}

	//--- スタックが空であるか ---//
	public boolean isEmpty(){
		return ptr <= 0;
	}

	//--- スタックが満杯であるか ---//
	public boolean isFull(){
		return ptr >= max;
	}

	//--- スタック内のデータを底から表示 ---//
	public void dump(){
		if(isEmpty())
			System.out.println("This stack is empty.");
		else{
			for(int i=0; i < ptr; i++)
				System.out.println(stk[i] + " ");
			System.out.println();
		}
	}

}
