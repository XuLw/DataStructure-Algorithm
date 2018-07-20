package algorithm;

import java.util.Random;

public class SortTestHelper {

	/*
	 * ����һ������Ϊn����Χ��[rangeR,rangeL]����������
	 */
	public static Integer[] generateRandomArray(int n, int rangeR, int rangeL) {
		Random r = new Random();
		Integer[] arr = new Integer[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Math.abs(r.nextInt()) % (rangeL - rangeR) + rangeR;
		}
		return arr;
	}

	/*
	 * �������
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
	 * ���������Ƿ��ź���
	 */
	public static <T extends Comparable> boolean isSorted(T[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[i].compareTo(arr[i + 1]) > 0)
				return false;
		}
		return true;
	}

	
	
}
