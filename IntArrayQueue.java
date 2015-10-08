//配列によるint型スタック

public class IntArrayQueue {

	private int max;		//キューの容量
	private int num;		//現在のデータ数
	private int[] que;		//キューの本体

	//--- 実行時例外:キューが空 ---//
	public class EmptyIntQueueException extends RuntimeException{
		public EmptyIntQueueException(){}
	}

	//--- 実行時例外:キューが満杯---//
	public class OverflowIntQueueException extends RuntimeException{
		public OverflowIntQueueException(){}
	}

	public IntArrayQueue(int capacity){
		num = 0;			//生成した段階で、データ数は0
		max = capacity;
		try{
			que = new int[capacity];	//キュー本体用の配列を生成
		} catch(OutOfMemoryError e){	//生成失敗
			max = 0;
		}
	}

	//--- エンキュー ---//
	public int enque(int x) throws OverflowIntQueueException{
		if(num >= max)
			throw new OverflowIntQueueException();
		return que[num++] = x;
	}

	//--- デキュー ---//
	public int deque() throws EmptyIntQueueException{
		if(num <= 0)
			throw new EmptyIntQueueException();
		num--;
		int out = que[0];
		for(int i=0; i<num; i++)
			que[i] = que[i+1];
		return out;
	}

	//--- キューからデータをピーク ---//
	public int peak() throws EmptyIntQueueException{
		if(num <= 0)
			throw new EmptyIntQueueException();
		return que[0];		//積まれているデータの末尾の値を返す
	}

	//--- キューからxを探してインデックス ---//
	public int indexOf(int x){
		//--- 線形探索 ---//
		for(int i = num-1; i >= 0; i--)
			if(que[num] == x)
				return i;
		return -1;
	}

	//--- キューを空にする ---//
	public void clear(){
		num = 0;			//キューのデータ数を0にすることはすなわちスタックを空にすることと同義
	}

	//--- キューの容量を返す ---//
	public int capacity(){
		return max;
	}

	//--- キューに積まれているデータ数を返す ---//
	public int size(){
		return num;
	}

	//--- キューが空であるか ---//
	public boolean isEmpty(){
		return num <= 0;
	}

	//--- キューが満杯であるか ---//
	public boolean isFull(){
		return num >= max;
	}

	//--- スタック内のデータを底から表示 ---//
	public void dump(){
		if(isEmpty())
			System.out.println("This stack is empty.");
		else{
			for(int i=0; i < num; i++)
				System.out.println(que[i] + " ");
			System.out.println();
		}
	}
}
