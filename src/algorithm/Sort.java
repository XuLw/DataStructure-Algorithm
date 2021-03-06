package algorithm;

import java.util.Arrays;

import dataStructure.MaxHeap;

public class Sort {

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
	public static final String QUICK_SORT_V2 = "quickSortV2";
	public static final String QUICK_SORT_V3 = "quickSortV3";
	public static final String HEAPSORT_V1 = "heapSortV1";
	public static final String HEAPSORT_V2 = "heapSortV2";
	public static final String HEAPSORT = "heapSort";

	public static final int MERGE_TO_INSERT = 15;

	private Sort() {
	}

	/**************** 选择排序 ******************/

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
			swap(arr, i, minIndex);
		}
	}

	/**************** 插入排序 ******************/

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
				swap(arr, j, j - 1);

			}

		}
	}

	/*
	 * 对 arr[l...,r]进行排序
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

	/**************** 冒泡排序 ******************/

	/*
	 * 基本冒泡排序
	 * 
	 */
	public static <T extends Comparable> void bubbleSort(T[] arr) {

		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length - 1 - i; j++) {
				if (arr[j + 1].compareTo(arr[j]) < 0) {
					swap(arr, j + 1, j);
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
			swap(arr, indexOfMax, arr.length - 1 - i);

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

					swap(arr, i - 1, i);

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
					swap(arr, i - 1, i);
					// 记录最后一次的交换位置,在此之后的元素在下一轮扫描中均不考虑
					newn = i;
				}
			n = newn;
		} while (newn > 0);
	}

	/**************** 归并排序 ******************/

	/*
	 * 递归归并排序
	 */
	public static <T extends Comparable> void mergeSort(T[] arr) {
		subMergeSort(arr, 0, arr.length - 1);
		// SortTestHelper.printArray(arr);
	}

	/*
	 * 自底向上归并排序
	 */
	public static <T extends Comparable> void mergeSortBTU(T[] arr) {

		for (int size = 1; size <= arr.length; size += size) {
			for (int i = 0; i + size < arr.length; i = size + size + i) {

				// 因为当数量小到一定程度时，插入排序会比归并排序更加快
				// MERGE_TO_INSERT 这个值的不同，效果也不同
				if (size <= 15) {
					insertSort(arr, i, Math.min(i + size + size - 1, arr.length - 1));
					continue;
				}
				if (arr[i + size - 1].compareTo(arr[Math.min(i + size, arr.length - 1)]) > 0)
					merge(arr, i, i + size - 1, Math.min(i + size + size - 1, arr.length - 1));
			}
		}
	}

	// 递归使用归并排序,对arr[l...r]的范围进行排序
	private static <T extends Comparable> void subMergeSort(T[] arr, int l, int r) {
		// v1 已经排完
		// if (l >= r)
		// return;

		// v2 因为当数量小到一定程度时，插入排序会比归并排序更加快
		// MERGE_TO_INSERT 这个值的不同，效果也不同
		if (r - l <= MERGE_TO_INSERT) {
			insertSort(arr, l, r);
			return;
		}

		// 防止两个整形相加越界
		// int mid = (int) (l + r / 2);
		int mid = l + (r - l) / 2;

		subMergeSort(arr, l, mid);
		subMergeSort(arr, mid + 1, r);

		// 优化 如果已经有序则不需要进行合并
		if (arr[mid].compareTo(arr[mid + 1]) > 0)
			merge(arr, l, mid, r);
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

	/**************** 快速排序 ******************/

	/*
	 * 最基本的快速排序
	 * 
	 * 当数据量为1000000时会发生溢出
	 */
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
	 * 对 T[l..r]进行排序
	 * 
	 * 快速排序是不稳定的
	 * 
	 * arr[l...p - 1] < arr[l] < arr[p+1...r]
	 * 
	 * 想法就是第一个先不动，然后在后续的便利中，划分出来两个数组，其中q指向小于arr[l]数组的最后一个，
	 * 
	 * 只需要在划分完之后，交换一下 arr[l] 和 arr[p];
	 */
	private static <T extends Comparable> int patition(T[] arr, int l, int r) {

		int p = l;
		T temp = arr[l];
		for (int i = l + 1; i <= r; i++) {
			if (arr[i].compareTo(temp) < 0) {
				p++;
				swap(arr, p, i);
			}
		}
		swap(arr, l, p);

		return p;
	}

	/*
	 * 优化后的快速排序
	 */
	public static <T extends Comparable> void quickSortV2(T[] arr) {
		subQuickSortV2(arr, 0, arr.length - 1);
	}

	private static <T extends Comparable> void subQuickSortV2(T[] arr, int l, int r) {

		if (r - l <= 15) {
			insertSort(arr, l, r);
			return;
		}

		// if (r < l)
		// return;

		int p = patitionV2(arr, l, r);
		subQuickSortV2(arr, l, p - 1);
		subQuickSortV2(arr, p + 1, r);

	}

	private static <T extends Comparable> int patitionV2(T[] arr, int l, int r) {

		// 不要吝啬定义变量，
		swap(arr, (int) (Math.random() * (r - l + 1) + l), l);

		T v = arr[l];

		int i = l + 1, j = r;
		while (true) {
			while (i <= r && arr[i].compareTo(v) < 0)
				i++;
			while (j >= l + 1 && arr[j].compareTo(v) > 0)
				j--;

			if (i > j)
				break;

			swap(arr, i, j);

			i++;
			j--;
		}

		swap(arr, j, l);

		return j;

	}

	/*
	 * 适用于优化处理存在大量重复元素的集合
	 * 
	 * 同时处理普通的集合性能也不会退化到O(n^2)
	 */
	public static <T extends Comparable> void quickSortV3(T[] arr) {
		subQuickSortV3(arr, 0, arr.length - 1);
	}

	/*
	 * arr[l+1..lt) < arr[lt..i] = v < arr[gt...r]
	 */
	private static <T extends Comparable> void subQuickSortV3(T[] arr, int l, int r) {

		if (r - l <= 15) {
			insertSort(arr, l, r);
			return;
		}

		swap(arr, l, (int) (Math.random() * (r - l + 1) + l));
		T v = arr[l];

		int lt = l, i = l + 1, gt = r + 1;

		// patiton
		while (i < gt) {
			if (arr[i].compareTo(v) > 0)
				swap(arr, i, --gt);
			else if (arr[i].compareTo(v) < 0)
				swap(arr, i++, ++lt);
			else
				i++;
		}

		swap(arr, l, lt);

		// 因为 lt 交换了，所以 arr[lt] == v, 所以时lt - 1
		subQuickSortV3(arr, l, lt - 1);
		subQuickSortV3(arr, gt, r);
	}

	/**************** 堆排序 ******************/

	/*
	 * 通过insert (shiftUp)函数来创建最大堆
	 */
	public static <T extends Comparable> void heapSortV1(T[] arr) {
		MaxHeap<T> heap = new MaxHeap<>(arr.length);
		for (int i = 0; i < arr.length; i++) {
			heap.insert(arr[i]);
		}

		System.out.println();
		for (int i = 0; i < arr.length; i++) {
			arr[i] = heap.extractMax();
		}
	}

	/*
	 * 通过数组创建最大堆 shiftDown
	 */
	public static <T extends Comparable> void heapSortV2(T[] arr) {
		MaxHeap<T> heap = new MaxHeap<>(arr);

		for (int i = 0; i < arr.length; i++) {
			arr[i] = heap.extractMax();
		}
	}

	/*
	 * 原地堆排序
	 */
	public static <T extends Comparable> void heapSort(T[] arr) {
		int n = arr.length;
		// 形成最大堆
		for (int i = (n - 1) / 2; i >= 0; i--) {
			localShiftDown(arr, n - 1, i);
		}

		// 排序
		for (int i = n - 1; i > 0; i--) {
			swap(arr, 0, i);
			localShiftDown(arr, i - 1, 0);
		}

	}

	// 对 arr[0...n] 进行shiftDown 操作
	private static <T extends Comparable> void localShiftDown(T[] arr, int n, int k) {

		T temp = arr[k];
		while (k * 2 + 1 <= n) {

			int j = 2 * k + 1;

			if (j + 1 <= n && arr[j].compareTo(arr[j + 1]) < 0)
				j++;

			if (temp.compareTo(arr[j]) > 0)
				break;

			arr[k] = arr[j];
			k = j;
		}
		arr[k] = temp;
	}

	/***************** 索引最大堆 *********************/

	/*
	 * 交换函数
	 */
	private static <T extends Comparable> void swap(T[] arr, int p1, int p2) {
		T temp = arr[p1];
		arr[p1] = arr[p2];
		arr[p2] = temp;
	}

}