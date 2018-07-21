package algorithm;

import java.util.Arrays;

public class AlgoTest {

	public static final int N = 50000;
	public static final int RANGE_R = 20;
	public static final int RANGE_L = 400;
	public static final int MAX_OF_STUDENT_NUM = 2000;

	public static final String CLASS_NAME = "algorithm.Sort";

	public AlgoTest() {
		// Integer[] arr = SortTestHelper.generateNearlyOrderedArray(N, 100);
		Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 400);
		handleSort(Sort.INSERT_SORT, arr.clone());
		handleSort(Sort.INSERT_SORT_AD, arr.clone());
		handleSort(Sort.BUBBLE_SORT, arr.clone());
		// handleSort(Sort.BUBBLE_SORT_AD_V1, arr.clone());
		// handleSort(Sort.BUBBLE_SORT_AD_V3, arr.clone());
		handleSort(Sort.MERGE_SORT, arr.clone());
		handleSort(Sort.SELECT_SORT, arr.clone());
	}

	private static <T extends Comparable> void handleSort(String sortName, T[] arr) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				SortTestHelper.testSort(sortName, CLASS_NAME, arr);
			}
		}).start();
	}

	public static void main(String[] args) {
		new AlgoTest();
	}

}
