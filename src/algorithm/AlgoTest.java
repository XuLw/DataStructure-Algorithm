package algorithm;

import java.util.Arrays;

public class AlgoTest {

	public static final int N = 10000;
	public static final int RANGE_R = 20;
	public static final int RANGE_L = 400;
	public static final int MAX_OF_STUDENT_NUM = 2000;

	public static final String CLASS_NAME = "algorithm.Sort";

	public AlgoTest() {
		// Integer[] arr = SortTestHelper.generateNearlyOrderedArray(N, 100);
		Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 10);

		String[] sorts = { Sort.QUICK_SORT_V3, Sort.MERGE_SORT };

		for (int i = 0; i < sorts.length; i++) {
			handleSort(sorts[i], arr.clone());
		}

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
