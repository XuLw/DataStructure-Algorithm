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
	 * 选择排序
	 */
	public static <T extends Comparable> void selectSort(T[] arr) {

		for (int i = 0; i < arr.length - 1; i++) {

			int minIndex = i;
			for (int j = minIndex + 1; j < arr.length; j++) {
				if (arr[j].compareTo(arr[minIndex]) < 0) {
					minIndex = j;
				}
			}
			// 选择第i小的数
			T temp = arr[i];
			arr[i] = arr[minIndex];
			arr[minIndex] = temp;
		}
	}

	/*
	 * 插入排序
	 * 
	 * 对近乎有序的数组的排序效率非常高
	 * 
	 * 原始方法 多次交换，浪费时间
	 */
	public static <T extends Comparable> void insertSort(T[] arr) {
		for (int i = 1; i < arr.length; i++) {

			for (int j = i; j > 0 && arr[j].compareTo(arr[j - 1]) < 0; j--) {
				// 右边比左边小，则交换
				T temp = arr[j];
				arr[j] = arr[j - 1];
				arr[j - 1] = temp;
			}

		}
	}

	/*
	 * 改进插入排序
	 *
	 * 优化后的方法 大量减少交换
	 */
	public static <T extends Comparable> void insertSortImproved(T[] arr) {
		for (int i = 0; i < arr.length; i++) {
			int j = i;// i元素最终要插入的位置
			T temp = arr[i];
			for (; j > 0 && arr[j - 1].compareTo(temp) > 0; j--) {
				arr[j] = arr[j - 1];
			}
			arr[j] = temp;
		}
	}

	/*
	 * 基本冒泡排序
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
	 * 改进的冒泡算法
	 * 
	 * 记录最大的位置
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
	 * 用一个标志，如果某一趟没发生交换，说明已经有序
	 * 
	 * 并且最后面的元素是有序的，不需要参与后面的循环
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

			// 优化, 每一趟Bubble Sort都将最大的元素放在了最后的位置
			// 所以下一次排序, 最后的元素可以不再考虑
			n--;
		} while (swapped);
	}

	/*
	 * 第三次改进
	 */
	public static <T extends Comparable> void bubbleSortImprovedV3(T[] arr) {

		int n = arr.length;
		int newn; // 使用newn进行优化

		do {
			newn = 0;
			for (int i = 1; i < n; i++)
				if (arr[i - 1].compareTo(arr[i]) > 0) {
					T temp = arr[i - 1];
					arr[i - 1] = arr[i];
					arr[i] = temp;
					// 记录最后一次的交换位置,在此之后的元素在下一轮扫描中均不考虑
					newn = i;
				}
			n = newn;
		} while (newn > 0);
	}

	public static <T extends Comparable> void mergeSort(T[] arr) {
		subMergeSort(arr, 0, arr.length - 1);
	}

	// 递归使用归并排序,对arr[l...r]的范围进行排序
	private static <T extends Comparable> void subMergeSort(T[] arr, int l, int r) {
		// 已经排完
		if (l >= r)
			return;

		// 防止两个整形相加越界
		long sum = r + l;
		int middle = (int) (sum / 2);

		subMergeSort(arr, l, middle);
		subMergeSort(arr, middle + 1, r);
		merge(arr, l, middle, r);
	}

	// 将arr[l...mid]和arr[mid+1...r]两部分进行归并
	private static <T extends Comparable> void merge(T[] arr, int l, int mid, int r) {

		T[] aux = Arrays.copyOfRange(arr, l, r + 1);

		int i = l;// 指向前半部分的起始位置`
		int j = mid + 1;// 指向后半部分的起始位置
		int k = l;// 指向原数组的插入位置

		for (; k <= r; k++) {
			if (i > mid) {
				// 左边已经排完
				arr[k] = aux[j - l];
				j++;
			} else if (j > r) {
				// 右边已经排完了
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
