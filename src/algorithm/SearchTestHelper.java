package algorithm;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SearchTestHelper<T extends Comparable> {

	private Class searchClass;
	private T[] data;

	public SearchTestHelper(String className, T[] arr) {
		try {
			searchClass = Class.forName(className);
			data = arr;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void testSearch(String searchName, T target) {
		try {
			Method search = searchClass.getMethod(searchName, Comparable[].class, Comparable.class);
			long start = System.currentTimeMillis();
			int result = (Integer) search.invoke(this, (Object) data, target);
			long end = System.currentTimeMillis();

			System.out.println(searchName + "  " + result + " : " + (end - start));

		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

	}
}
