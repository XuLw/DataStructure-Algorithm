package algorithm;

public class Search {

	public static final String SEQUENCE_SEARCH = "sequenceSearch";
	public static final String BINARY_SEARCH_V1 = "binarySearchV1";
	public static final String BINARY_SEARCH_V2 = "binarySearchV2";

	private Search() {
	}

	/*
	 * ˳����ң���Ϊ����
	 */
	public static <T extends Comparable> int sequenceSearch(T[] arr, T target) {
		for (int i = 0; i < arr.length; i++)
			if (arr[i].compareTo(target) == 0)
				return i;
		return -1;
	}

	/*
	 * �ݹ� ���ֲ��ҷ�
	 */
	public static <T extends Comparable> int binarySearchV1(T[] arr, T target) {
		return subBinarySearchV1(arr, target, 0, arr.length - 1);
	}

	/*
	 * ����arr[l,r]�е�target��λ��
	 */
	private static <T extends Comparable> int subBinarySearchV1(T[] arr, T target, int l, int r) {

		// �����ǵ���
		if (l > r)
			return -1;

		// ��ֹԽ��
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
	 * �ǵݹ�
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
