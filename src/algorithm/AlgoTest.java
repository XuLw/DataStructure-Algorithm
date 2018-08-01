package algorithm;

import java.util.Arrays;

public class AlgoTest {

	public static final int N = 1000000;
	public static final int RANGE_R = 20;
	public static final int RANGE_L = 400;
	public static final int MAX_OF_STUDENT_NUM = 2000;

	public static final String SORT_CLASS_NAME = "algorithm.Sort";
	public static final String SEARCH_CLASS_NAME = "algorithm.Search";

	public AlgoTest() {
		Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 100);
		// testSort();
		testSearch();
	}

	private void testSearch() {
		Integer[] arr = SortTestHelper.generateNearlyOrderedArray(N, 523);
		Integer target = arr[(int) (Math.random() * N)];
		Sort.quickSortV2(arr);
		SearchTestHelper<Integer> sth = new SearchTestHelper<>(SEARCH_CLASS_NAME, arr);

		String[] searches = { Search.BINARY_SEARCH_V1, Search.SEQUENCE_SEARCH, Search.BINARY_SEARCH_V2 };

		for (String s : searches) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					sth.testSearch(s, target);
				}

			}).start();

		}

	}

	private void testSort() {
		// Integer[] arr = SortTestHelper.generateNearlyOrderedArray(N, 100);
		Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 800);
		String[] sorts = { Sort.HEAPSORT, Sort.HEAPSORT_V1, Sort.HEAPSORT_V2 };
		for (int i = 0; i < sorts.length; i++) {
			handleSort(sorts[i], arr.clone());
		}
	}

	private <T extends Comparable> void handleSort(String sortName, T[] arr) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				SortTestHelper.testSort(sortName, SORT_CLASS_NAME, arr);
			}
		}).start();
	}

	public static void main(String[] args) {
		new AlgoTest();
	}

}
