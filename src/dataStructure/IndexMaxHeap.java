package dataStructure;

/*
 * 索引从1开始
*/
public class IndexMaxHeap<T extends Comparable> {

	private int count;
	private T[] data;
	private int[] indexes;

	public IndexMaxHeap(int capacity) {

		data = (T[]) new Comparable[capacity + 1];
		indexes = new int[capacity + 1];
		count = 0;
	}

	public void insert(T t) {

		if (count + 1 >= data.length)
			return;

		data[count + 1] = t;
		indexes[count + 1] = count + 1;

		shiftUp(++count);
	}

	public T extractMax() {
		if (count > 0) {
			T temp = data[indexes[1]];

			// 没必要进行交换操作，赋值就行
			indexes[1] = indexes[count];

			count--;
			shiftDown(1);

			return temp;
		} else
			return null;
	}
	
	public int extractMaxIndex() {
		if(count > 0) {
			int ret = indexes[1] -1;
			
			indexes[1] = indexes[count--];
			shiftDown(1);
			
			return ret;
		}
		return -1;
	}
	
	public void change(int i, T t) {
		
	}
	
	public T getItem(int i) {
		return data[i+1];
	}

	public int getCount() {
		return this.count;
	}

	public boolean isEmpty() {
		return count == 0;
	}

	public int getCapacity() {
		return data.length - 1;
	}

	private void shiftUp(int k) {

		int temp = indexes[k];
		while (k > 1 && data[indexes[k / 2]].compareTo(data[temp]) < 0) {
			indexes[k] = indexes[k / 2];
			k /= 2;
		}
		indexes[k] = temp;
	}

	private void shiftDown(int k) {
		T temp = data[indexes[k]];
		int t = indexes[k];
		while (k * 2 <= count) {
			int j = 2 * k;

			if (j + 1 <= count && data[indexes[j]].compareTo(data[indexes[j + 1]]) < 0)
				j++;

			if (data[indexes[j]].compareTo(temp) < 0)
				break;

			indexes[k] = indexes[j];

			k = j;
		}
		indexes[k] = t;
	}

	private void swap(int i, int j) {

		int t = indexes[i];
		indexes[i] = indexes[j];
		indexes[j] = t;
	}
}
