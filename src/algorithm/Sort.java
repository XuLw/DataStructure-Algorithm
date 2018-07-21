package algorithm;

import java.util.Arrays;

public class Sort {

	public static final String SELECT_SORT = "selectSort";
	public static final String INSERT_SORT = "insertSort";
	public static final String INSERT_SORT_AD = "insertSortImproved";
	public static final String BUBBLE_SORT = "bubbleSort";
	public static final String BUBBLE_SORT_AD_V1 = "bubbleSortImprovedV1";
	public static final String BUBBLE_SORT_AD_V2 = "bubbleSortImprovedV2";
	public static final String BUBBLE_SORT_AD_V3 = "bubbleSortImprovedV3";
	public static final String MERGE_SORT = "mergeSort";

	private Sort() {
	}

	/*
	 * ѡ������
	 */
	public static <T extends Comparable> void selectSort(T[] arr) {

		for (int i = 0; i < arr.length - 1; i++) {

			int minIndex = i;
			for (int j = minIndex + 1; j < arr.length; j++) {
				if (arr[j].compareTo(arr[minIndex]) < 0) {
					minIndex = j;
				}
			}
			// ѡ���iС����
			T temp = arr[i];
			arr[i] = arr[minIndex];
			arr[minIndex] = temp;
		}
	}

	/*
	 * ��������
	 * 
	 * �Խ�����������������Ч�ʷǳ���
	 * 
	 * ԭʼ���� ��ν������˷�ʱ��
	 */
	public static <T extends Comparable> void insertSort(T[] arr) {
		for (int i = 1; i < arr.length; i++) {

			for (int j = i; j > 0 && arr[j].compareTo(arr[j - 1]) < 0; j--) {
				// �ұ߱����С���򽻻�
				T temp = arr[j];
				arr[j] = arr[j - 1];
				arr[j - 1] = temp;
			}

		}
	}

	/*
	 * �Ľ���������
	 *
	 * �Ż���ķ��� �������ٽ���
	 */
	public static <T extends Comparable> void insertSortImproved(T[] arr) {
		for (int i = 0; i < arr.length; i++) {
			int j = i;// iԪ������Ҫ�����λ��
			T temp = arr[i];
			for (; j > 0 && arr[j - 1].compareTo(temp) > 0; j--) {
				arr[j] = arr[j - 1];
			}
			arr[j] = temp;
		}
	}

	/*
	 * ����ð������
	 * 
	 */
	public static <T extends Comparable> void bubbleSort(T[] arr) {

		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length - 1 - i; j++) {
				if (arr[j + 1].compareTo(arr[j]) < 0) {
					T temp = arr[j + 1];
					arr[j + 1] = arr[j];
					arr[j] = temp;
				}
			}
		}
	}

	/*
	 * �Ľ���ð���㷨
	 * 
	 * ��¼����λ��
	 */
	public static <T extends Comparable> void bubbleSortImprovedV1(T[] arr) {

		for (int i = 0; i < arr.length - 1; i++) {
			int indexOfMax = arr.length - 1 - i;
			for (int j = 0; j < arr.length - 1 - i; j++) {
				if (arr[j].compareTo(arr[indexOfMax]) > 0)
					indexOfMax = j;
			}
			T temp = arr[indexOfMax];
			arr[indexOfMax] = arr[arr.length - 1 - i];
			arr[arr.length - 1 - i] = temp;
		}
	}

	/*
	 * ��һ����־�����ĳһ��û����������˵���Ѿ�����
	 * 
	 * ����������Ԫ��������ģ�����Ҫ��������ѭ��
	 */
	public static <T extends Comparable> void bubbleSortImprovedV2(T[] arr) {

		int n = arr.length;
		boolean swapped = false;

		do {
			swapped = false;
			for (int i = 1; i < n; i++)
				if (arr[i - 1].compareTo(arr[i]) > 0) {
					T temp = arr[i - 1];
					arr[i - 1] = arr[i];
					arr[i] = temp;
					swapped = true;
				}

			// �Ż�, ÿһ��Bubble Sort��������Ԫ�ط���������λ��
			// ������һ������, ����Ԫ�ؿ��Բ��ٿ���
			n--;
		} while (swapped);
	}

	/*
	 * �����θĽ�
	 */
	public static <T extends Comparable> void bubbleSortImprovedV3(T[] arr) {

		int n = arr.length;
		int newn; // ʹ��newn�����Ż�

		do {
			newn = 0;
			for (int i = 1; i < n; i++)
				if (arr[i - 1].compareTo(arr[i]) > 0) {
					T temp = arr[i - 1];
					arr[i - 1] = arr[i];
					arr[i] = temp;
					// ��¼���һ�εĽ���λ��,�ڴ�֮���Ԫ������һ��ɨ���о�������
					newn = i;
				}
			n = newn;
		} while (newn > 0);
	}

	public static <T extends Comparable> void mergeSort(T[] arr) {
		subMergeSort(arr, 0, arr.length - 1);
	}

	// �ݹ�ʹ�ù鲢����,��arr[l...r]�ķ�Χ��������
	private static <T extends Comparable> void subMergeSort(T[] arr, int l, int r) {
		// �Ѿ�����
		if (l >= r)
			return;

		// ��ֹ�����������Խ��
		long sum = r + l;
		int middle = (int) (sum / 2);

		subMergeSort(arr, l, middle);
		subMergeSort(arr, middle + 1, r);
		merge(arr, l, middle, r);
	}

	// ��arr[l...mid]��arr[mid+1...r]�����ֽ��й鲢
	private static <T extends Comparable> void merge(T[] arr, int l, int mid, int r) {

		T[] aux = Arrays.copyOfRange(arr, l, r + 1);

		int i = l;// ָ��ǰ�벿�ֵ���ʼλ��`
		int j = mid + 1;// ָ���벿�ֵ���ʼλ��
		int k = l;// ָ��ԭ����Ĳ���λ��

		for (; k <= r; k++) {
			if (i > mid) {
				// ����Ѿ�����
				arr[k] = aux[j - l];
				j++;
			} else if (j > r) {
				// �ұ��Ѿ�������
				arr[k] = aux[i - l];
				i++;
			} else if (aux[i - l].compareTo(aux[j - l]) < 0) {
				arr[k] = aux[i - l];
				i++;
			} else {
				arr[k] = aux[j - l];
				j++;
			}
		}

		
        
	}
}
