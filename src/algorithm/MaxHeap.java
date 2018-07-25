package algorithm;

/*
 * ���Ѷ���  ��  1.��һ����ȫ������  2.���ڵ�����е��ӽڵ㶼��
 * 
 *Ϊ���㴦���ճ�����Ϊ0��λ�� 
 */

public class MaxHeap<T extends Comparable> {

	protected T[] data;
	protected int count;

	public MaxHeap(int capacity) {
		// �ճ�����Ϊ0��λ��
		data = (T[]) new Comparable[capacity + 1];
		count = 0;
	}

	/*
	 * ���캯����ͨ��һ�����������飬����һ�����ѣ�ʱ�临�Ӷ�O(n)
	 */
	public MaxHeap(T[] arr) {
		data = (T[]) new Comparable[arr.length + 1];

		for (int i = 0; i < arr.length; i++)
			data[i + 1] = arr[i];
		// ע��ı�count ��ֵ
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
	 * �����ݲ��뵽�����Ȼ�����shiftup����
	 */
	public void insert(T t) {
		if (count + 1 > data.length) {
			System.out.println("�ռ�����");
			return;
		}
		data[++count] = t;
		shiftUp(count);
	}

	/*
	 * �����Ѷ�Ԫ��
	 */
	public T extractMax() {

		if (count > 0) {
			T result = data[1];

			// ����ǳ��������д������ count--�Ĵ��룬������ǻ��Ǿ�����д��
			swap(1, count--);
			shiftDown(1);

			return result;
		}

		return null;
	}

	// ȡ�����ѵ�ջ��Ԫ��
	public T getMax() {
		if (count > 0)
			return data[1];
		else
			return null;
	}

	/*
	 * ֻҪ�õ���������Ҫ����Խ����
	 * 
	 * shiftUp�����ڵ���бȽϣ�����ȸ��ڵ�����븸�ڵ���н���
	 * 
	 */
	private void shiftUp(int k) {
		while (k > 1 && data[k].compareTo(data[k / 2]) > 0) {
			swap(k, k / 2);
			k /= 2;
		}
	}

	/*
	 * ����ǰ�жϵ�Ԫ�ؽ��������Ų������ҵ����պ��ʵ�λ��
	 * 
	 * ��������ǣ���Ԫ�ظ��ӽڵ��д���Ǹ����н���
	 * 
	 * �����С�Ļ�����ô���ڵ㻹�Ǳ���һ���ӽڵ�С������������
	 */
	private void shiftDown(int k) {

		// ���к���
		// int j = 2 * k;
		// while (j <= count) {
		// // ���Һ���
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

		// ��¼�����Ż�
		int j = 2 * k;
		T temp = data[k];
		while (j <= count) {
			// ���Һ���
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
