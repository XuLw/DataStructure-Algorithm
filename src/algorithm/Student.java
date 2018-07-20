package algorithm;

public class Student implements Comparable<Student> {

	public static final int MAX_AGE = 100;

	private int num;
	private int age;
	private String name;

	public Student() {
		this.name = "";
	}

	public Student(int num, int age, String name) {
		this.num = num;
		this.age = age;
		this.name = new String(name);
	}

	public static Student[] generateStudentArray(int n, int numLength) {
		Student[] students = new Student[n];
		for (int i = 0; i < n; i++) {
			students[i] = new Student((int) (Math.random() * numLength), (int) (Math.random() * MAX_AGE), "" + i);
		}
		return students;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(Student other) {
		if (this.num == other.num) {
			if (this.age == other.age) {
				return this.name.compareTo(other.name);
			} else
				return this.age - other.age;
		} else
			return this.num - other.num;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + num;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (num != other.num)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[ num:" + this.num + " age:" + this.age + " name:" + this.name + " ]";
	}

}
