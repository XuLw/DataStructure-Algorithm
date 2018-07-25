package algorithm;

/*
 * 最大堆定义  ：  1.是一颗完全二叉树  2.父节点比所有的子节点都大
 * 
 *为方便处理，空出索引为0的位置 
 */

public class MaxHeap<T extends Comparable> {

	protected T[] data;
	protected int count;

	public MaxHeap(int capacity) {
		// 空出索引为0的位置
		data = (T[]) new Comparable[capacity + 1];
		count = 0;
	}

	/*
	 * 构造函数，通过一个给定的数组，构造一个最大堆，时间复杂度O(n)
	 */
	public MaxHeap(T[] arr) {
		data = (T[]) new Comparable[arr.length + 1];

		for (int i = 0; i < arr.length; i++)
			data[i + 1] = arr[i];
		// 注意改变count 的值
		count = arr.length;
		for (int i = count / 2; i >= 1; i--)
			shiftDown(i);
	}

	public int size() {
		return this.count;
	}

	public boolean isEmpty() {
		return this.count == 0;
	}

	/*
	 * 将数据插入到堆最后，然后进行shiftup操作
	 */
	public void insert(T t) {
		if (count + 1 > data.length) {
			System.out.println("空间已满");
			return;
		}
		data[++count] = t;
		shiftUp(count);
	}

	/*
	 * 弹出堆顶元素
	 */
	public T extractMax() {

		if (count > 0) {
			T result = data[1];

			// 如果非常清楚可以写这样的 count--的代码，如果不是还是尽量少写把
			swap(1, count--);
			shiftDown(1);

			return result;
		}

		return null;
	}

	// 取得最大堆的栈顶元素
	public T getMax() {
		if (count > 0)
			return data[1];
		else
			return null;
	}

	/*
	 * 只要用到索引，就要进行越界检查
	 * 
	 * shiftUp跟父节点进行比较，如果比父节点大，则与父节点进行交换
	 * 
	 */
	private void shiftUp(int k) {
		while (k > 1 && data[k].compareTo(data[k / 2]) > 0) {
			swap(k, k / 2);
			k /= 2;
		}
	}

	/*
	 * 将当前判断的元素进行往下排操作，找到最终合适的位置
	 * 
	 * 具体操作是，将元素跟子节点中大的那个进行交换
	 * 
	 * 如果跟小的换，那么根节点还是比另一个子节点小，不符合性质
	 */
	private void shiftDown(int k) {

		// 还有孩子
		// int j = 2 * k;
		// while (j <= count) {
		// // 有右孩子
		// if (j + 1 <= count && data[j + 1].compareTo(data[j]) > 0)
		// j++;
		//
		// if (data[k].compareTo(data[j]) >= 0)
		// break;
		//
		// swap(k, j);
		// k = j;
		// j = 2 * k;
		// }

		// 记录索引优化
		int j = 2 * k;
		T temp = data[k];
		while (j <= count) {
			// 有右孩子
			if (j + 1 <= count && data[j + 1].compareTo(data[j]) > 0)
				j++;

			if (temp.compareTo(data[j]) >= 0)
				break;

			data[k] = data[j];
			k = j;
			j = 2 * k;
		}
		data[k] = temp;
		
	}

	private void swap(int i, int j) {
		T temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}

}
