package algorithm;

public class Sort {

	private Sort() {
	}

	/*
	 * ѡ������
	 */
	public static <T extends Comparable> void selectSort(T[] arr) {

		for (int i = 0; i < arr.length - 1; i++) {

			int minIndex = i;
			for (int j = minIndex + 1; j < arr.length; j++) {
				if (arr[j].compareTo(arr[minIndex]) < 0) {
					minIndex = j;
				}
			}
			// ѡ���iС����
			T temp = arr[i];
			arr[i] = arr[minIndex];
			arr[minIndex] = temp;
		}
	}

	/*
	 * ��������
	 * 
	 * �Խ�����������������Ч�ʷǳ���
	 * 
	 */
	public static <T extends Comparable> void insertSort(T[] arr) {
		for (int i = 1; i < arr.length; i++) {
			// ԭʼ���� ��ν������˷�ʱ��
			// for (int j = i; j > 0 && arr[j].compareTo(arr[j - 1]) < 0; j--) {
			// // �ұ߱����С���򽻻�
			// T temp = arr[j];
			// arr[j] = arr[j - 1];
			// arr[j - 1] = temp;
			// }
			// �Ż���ķ��� �������ٽ���
			int j = i;// iԪ������Ҫ�����λ��
			T temp = arr[i];
			for (; j > 0 && arr[j - 1].compareTo(temp) > 0; j--) {
				arr[j] = arr[j - 1];
			}
			arr[j] = temp;
		}
	}

	/*
	 * ����ð������
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
