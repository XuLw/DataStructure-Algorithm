package algorithm;

public class Sort {

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
	 */
	public static <T extends Comparable> void insertSort(T[] arr) {
		for (int i = 1; i < arr.length; i++) {
			// 原始方法 多次交换，浪费时间
			// for (int j = i; j > 0 && arr[j].compareTo(arr[j - 1]) < 0; j--) {
			// // 右边比左边小，则交换
			// T temp = arr[j];
			// arr[j] = arr[j - 1];
			// arr[j - 1] = temp;
			// }
			// 优化后的方法 大量减少交换
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
	 */
	public static <T extends Comparable> void bubbleSort(T[] arr) {

		for (int i = 1; i < arr.length; i++) {
			for (int j = 0; j < arr.length - i; j++) {
				if (arr[j + 1].compareTo(arr[j]) < 0) {
					T temp = arr[j + 1];
					arr[j + 1] = arr[j];
					arr[j] = temp;
				}
			}
		}
	}

	public static <T extends Comparable> void bubbleSortImproved(T[] arr) {

	}

}
