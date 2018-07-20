package algorithm;

public class AlgoTest {

	public static final int N = 2000;
	public static final int RANGE_R = 20;
	public static final int RANGE_L = 400;
	public static final int MAX_OF_STUDENT_NUM = 2000;

	public static final String AFTER_TEXT = "\n after : ";

	public static void testSelectionSort() {
		Integer[] arr = new Integer[N];
		arr = SortTestHelper.generateRandomArray(N, RANGE_R, RANGE_L);
		SortTestHelper.printArray(arr);
		System.out.println(AFTER_TEXT);
		Sort.selectSort(arr);
		SortTestHelper.printArray(arr);
	}

	public static void testBubbleSort() {
		Integer[] arr = new Integer[N];
		arr = SortTestHelper.generateRandomArray(N, RANGE_R, RANGE_L);
		SortTestHelper.printArray(arr);
		Sort.bubbleSort(arr);
		SortTestHelper.printArray(arr);
	}
	
	public static void main(String[] args) {
		// testSelectionSort();
		// Student[] ss = Student.generateStudentArray(N, MAX_OF_STUDENT_NUM);
//		Integer[] arr = SortTestHelper.generateRandomArray(N, RANGE_R, RANGE_L);
//		Sort.insertSort(arr);
//		SortTestHelper.printArray(arr);
//		System.out.println(SortTestHelper.isSorted(arr));
//		
//		
		testBubbleSort();
	}
	
	
}


