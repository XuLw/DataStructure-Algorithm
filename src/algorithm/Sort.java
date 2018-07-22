package algorithm;

import java.util.Arrays;

public class Sort {

	/*
	 * Խ�������Ҫ
	 */
	public static final String SELECT_SORT = "selectSort";
	public static final String INSERT_SORT = "insertSort";
	public static final String INSERT_SORT_AD = "insertSortImproved";
	public static final String BUBBLE_SORT = "bubbleSort";
	public static final String BUBBLE_SORT_AD_V1 = "bubbleSortImprovedV1";
	public static final String BUBBLE_SORT_AD_V2 = "bubbleSortImprovedV2";
	public static final String BUBBLE_SORT_AD_V3 = "bubbleSortImprovedV3";
	public static final String MERGE_SORT = "mergeSort";
	public static final String MERGE_SORT_BTU = "mergeSortBTU";
	public static final String QUICK_SORT = "quickSort";

	public static final int MERGE_TO_INSERT = 15;

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
	 * �� arr[l...,r]��������
	 */
	public static <T extends Comparable> void insertSort(T[] arr, int l, int r) {
		for (int i = l; i <= r; i++) {
			int j = i;
			T temp = arr[i];
			for (; j - l > 0 && arr[j - 1].compareTo(temp) > 0; j--) {
				arr[j] = arr[j - 1];
			}
			arr[j] = temp;
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

	/*
	 * �ݹ�鲢����
	 */
	public static <T extends Comparable> void mergeSort(T[] arr) {
		subMergeSort(arr, 0, arr.length - 1);
		// SortTestHelper.printArray(arr);
	}

	/*
	 * �Ե����Ϲ鲢����
	 */
	public static <T extends Comparable> void mergeSortBTU(T[] arr) {

		for (int size = 1; size <= arr.length; size += size) {
			for (int i = 0; i + size < arr.length; i = size + size + i) {

				// ��Ϊ������С��һ���̶�ʱ�����������ȹ鲢������ӿ�
				// MERGE_TO_INSERT ���ֵ�Ĳ�ͬ��Ч��Ҳ��ͬ
				if (size <= 15) {
					insertSort(arr, i, Math.min(i + size + size - 1, arr.length - 1));
					continue;
				}
				if (arr[i + size - 1].compareTo(arr[Math.min(i + size, arr.length - 1)]) > 0)
					merge(arr, i, i + size - 1, Math.min(i + size + size - 1, arr.length - 1));
			}
		}
	}

	// �ݹ�ʹ�ù鲢����,��arr[l...r]�ķ�Χ��������
	private static <T extends Comparable> void subMergeSort(T[] arr, int l, int r) {
		// v1 �Ѿ�����
		// if (l >= r)
		// return;

		// v2 ��Ϊ������С��һ���̶�ʱ�����������ȹ鲢������ӿ�
		// MERGE_TO_INSERT ���ֵ�Ĳ�ͬ��Ч��Ҳ��ͬ
		if (r - l <= MERGE_TO_INSERT) {
			insertSort(arr, l, r);
			return;
		}

		// ��ֹ�����������Խ��
		long sum = r + l;
		int mid = (int) (sum / 2);

		subMergeSort(arr, l, mid);
		subMergeSort(arr, mid + 1, r);

		// �Ż� ����Ѿ���������Ҫ���кϲ�
		if (arr[mid].compareTo(arr[mid + 1]) > 0)
			merge(arr, l, mid, r);
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

	public static <T extends Comparable> void quickSort(T[] arr) {
		subQuickSort(arr, 0, arr.length - 1);

	}

	private static <T extends Comparable> void subQuickSort(T[] arr, int l, int r) {

		if (l >= r)
			return;

		int p = patition(arr, l, r);
		subQuickSort(arr, l, p - 1);
		subQuickSort(arr, p + 1, r);
	}

	/*
	 * �� T[l..r]��������
	 * 
	 * ���������ǲ��ȶ���
	 * 
	 * arr[l...p - 1] < arr[l] < arr[p+1...r]
	 * 
	 * �뷨���ǵ�һ���Ȳ�����Ȼ���ں����ı����У����ֳ����������飬����qָ��С��arr[l]��������һ����
	 * 
	 * ֻ��Ҫ�ڻ�����֮�󣬽���һ�� arr[l] �� arr[p];
	 */
	private static <T extends Comparable> int patition(T[] arr, int l, int r) {

		int p = l;
		T temp = arr[l];
		for (int i = l + 1; i <= r; i++) {
			if (arr[i].compareTo(temp) < 0) {
				p++;
				T t = arr[p];
				arr[p] = arr[i];
				arr[i] = t;
			}
		}
		T t = arr[l];
		arr[l] = arr[p];
		arr[p] = t;
		return p;
	}

}
