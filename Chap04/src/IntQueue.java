//int型キュー

public class IntQueue {

	private int max;
	private int front;
	private int rear;
	private int num;
	private int[] que;

	//--- 実行時例外:キューが空 ---//
	public class EmptyIntQueueException extends RuntimeException{
		public EmptyIntQueueException(){}
	}

	//--- 実行時例外:キューが満杯---//
	public class OverflowIntQueueException extends RuntimeException{
		public OverflowIntQueueException(){}
	}

	public IntQueue(int capacity){
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
		que[rear++]=x;
		num++;
		if(rear == max)
			rear = 0;
		return x;
	}

	//--- デキュー ---//
	public int deque() throws EmptyIntQueueException{
		if(num <= 0)
			throw new EmptyIntQueueException();
		int x = que[front++];
		num--;
		if(front == max)
			front = 0;
		return x;
	}

	//--- キューからデータをピーク ---//
	public int peak() throws EmptyIntQueueException{
		if(num <= 0)
			throw new EmptyIntQueueException();
		return que[front];		//積まれているデータの末尾の値を返す
	}

	//--- キューからxを探してインデックス ---//
	public int indexOf(int x){
		//--- 線形探索 ---//
		for(int i = num-1; i >= 0; i--){
			int idx = (i + front) % max;
			if(que[idx] == x)
				return idx;
		}
		return -1;
	}

	//--- キューを空にする ---//
	public void clear(){
		num = front = rear = 0;			//キューのデータ数を0にすることはすなわちスタックを空にすることと同義
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
				System.out.println(que[(i + front) % max] + " ");
			System.out.println();
		}
	}

}
