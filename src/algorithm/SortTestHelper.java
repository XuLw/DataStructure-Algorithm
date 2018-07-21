package algorithm;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Random;

public class SortTestHelper {

	/*
	 * 产生一个长度为n，范围在[rangeR,rangeL]的整形数组
	 */
	public static Integer[] generateRandomArray(int n, int rangeR, int rangeL) {
		Random r = new Random();
		Integer[] arr = new Integer[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Math.abs(r.nextInt()) % (rangeL - rangeR) + rangeR;
		}
		return arr;
	}

	public static Integer[] generateNearlyOrderedArray(int n, int swapTimes) {
		Integer[] arr = new Integer[n];
		// 有序集合
		for (int i = 0; i < n; i++) {
			arr[i] = i;
		}
		// 进行交换
		for (int j = 0; j < swapTimes; j++) {
			int a = (int) (Math.random() * n);
			int b = (int) (Math.random() * n);
			int t = arr[a];
			arr[a] = arr[b];
			arr[b] = t;
		}

		return arr;
	}

	/*
	 * 输出数组
	 */
	public static <T> void printArray(T[] arr) {
		for (int i = 1; i <= arr.length; i++) {
			System.out.print(arr[i - 1].toString() + "  ");
			if (i % 15 == 0) {
				System.out.println();
			}
		}
	}

	/*
	 * 测试数组是否排好序
	 */
	public static <T extends Comparable> boolean isSorted(T[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[i].compareTo(arr[i + 1]) > 0)
				return false;
		}
		return true;
	}

	/*
	 * 测试排序算法的效率
	 * 
	 */
	public static <T extends Comparable> void testSort(String sortName, String className, T[] arr) {
		try {

			Class sortClass = Class.forName(className);
			Method sort = sortClass.getMethod(sortName, Comparable[].class);

			long start = System.currentTimeMillis();
			// 需要强转，因为接收的参数是可变参数，如果传入的是数组，则展开之后会出现错误，
			// 所以需要进行强制转换为一个参数，反正接收的参数也是 Object
			sort.invoke(null, (Object) arr);
			long end = System.currentTimeMillis();
			if (isSorted(arr))
				System.out.println("\n" + sortName + " cost : " + (end - start));
			else
				System.out.println("\n" + sortName + " cost : 9999999999");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
