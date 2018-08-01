package algorithm;

public class Search {

	public static final String SEQUENCE_SEARCH = "sequenceSearch";
	public static final String BINARY_SEARCH_V1 = "binarySearchV1";
	public static final String BINARY_SEARCH_V2 = "binarySearchV2";

	private Search() {
	}

	/*
	 * 顺序查找，作为参照
	 */
	public static <T extends Comparable> int sequenceSearch(T[] arr, T target) {
		for (int i = 0; i < arr.length; i++)
			if (arr[i].compareTo(target) == 0)
				return i;
		return -1;
	}

	/*
	 * 递归 二分查找法
	 */
	public static <T extends Comparable> int binarySearchV1(T[] arr, T target) {
		return subBinarySearchV1(arr, target, 0, arr.length - 1);
	}

	/*
	 * 查找arr[l,r]中的target的位置
	 */
	private static <T extends Comparable> int subBinarySearchV1(T[] arr, T target, int l, int r) {

		// 不能是等于
		if (l > r)
			return -1;

		// 防止越界
		int mid = l + (r - l) / 2;

		int flag = target.compareTo(arr[mid]);

		if (flag == 0) {
			return mid;
		} else if (flag > 0) {
			return subBinarySearchV1(arr, target, mid + 1, r);
		} else {
			return subBinarySearchV1(arr, target, l, mid - 1);
		}
	}

	/*
	 * 非递归
	 */
	public static <T extends Comparable> int binarySearchV2(T[] arr, T target) {
		int l = 0, r = arr.length - 1;

		while (l <= r) {
			int mid = l + (r - l) / 2;
			int flag = target.compareTo(arr[mid]);
			if (flag == 0) {
				return mid;
			} else if (flag > 0) {
				l = mid + 1;
			} else if (flag < 0) {
				r = mid - 1;
			}
		}

		return -1;
	}

}
