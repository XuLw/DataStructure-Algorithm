package algorithm;

import java.util.Arrays;

import dataStructure.BST;

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
		// new AlgoTest();
		int n = 100;
		BST<Integer, Integer> bst = new BST<>();
		for (int i = 0; i < n; i++) {
			Integer key = (int) (Math.random() * n);
			bst.insert(key, key);
		}

		// bst.levelOrder();

		System.out.println();
		Integer order[] = new Integer[n];
		for (int i = 0; i < n; i++) {
			order[i] = i;
		}
		// 打乱数组
		for (int i = n - 1; i > 0; i--) {
			int pos = (int) (Math.random() * (i + 1));
			int temp = order[pos];
			order[pos] = order[i];
			order[i] = temp;
		}

		for (int i = 0; i < n; i++) {
			if (bst.contain(order[i])) {
				bst.remove(order[i]);
				System.out.println("After remove " + order[i] + " size = " + bst.size());
			}
		}

		// 最终整个二分搜索树应该为空
		System.out.println(bst.size());

		// bst.levelOrder();

	}

}
