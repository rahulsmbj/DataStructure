package sunbeam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Student {
	private int roll;
	private String name;
	private double marks;
	public Student() {
	}
	public Student(int roll, String name, double marks) {
		this.roll = roll;
		this.name = name;
		this.marks = marks;
	}
	public int getRoll() {
		return roll;
	}
	public void setRoll(int roll) {
		this.roll = roll;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getMarks() {
		return marks;
	}
	public void setMarks(double marks) {
		this.marks = marks;
	}
	@Override
	public String toString() {
		return "Student [roll=" + roll + ", name=" + name + ", marks=" + marks + "]";
	}
	// for this demo hashCode() & equals() is not required, because Student is used as value.
	
	// for a assign like Map<Student,ProgressCard>, we must implement hashCode() and equals().
	
	/*
	@Override
	public int hashCode() {
		// valid ... different keys may have same hashCode()
		// however, very poor performance - O(n), bcoz all Student will go in same bucket.
		return 30;
	}
	*/
	/*
	@Override
	public int hashCode() {
		// invalid ... for same key it must return same hashCode() for each call.
		Random random = new Random();
		return random.nextInt(100);
	}
	*/
	
	/*
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + roll;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Student))
			return false;
		Student other = (Student) obj;
		if (roll != other.roll)
			return false;
		return true;
	}
	*/
}

class HashTable {
	static class Entry {
		private int key; // roll no
		private Student value; // Student object
		public Entry() {
		}
		public Entry(int key, Student value) {
			this.key = key;
			this.value = value;
		}
	}
	
	private static  final int SIZE = 100;
	private List<Entry> [] table;
	
	public static int hashFn(int roll) {
		return roll % SIZE;
	}
	
	public HashTable() {
		table = new List[SIZE]; // initially all array elements are null reference
		for (int i = 0; i < SIZE; i++)
			table[i] = new ArrayList<>(); // create empty lists (buckets)
	}
	
	public Student put(int key, Student value) {
		//1. create entry object for key & value.
		Entry newEntry = new Entry(key, value);
		//2. call hash(key) and find the slot/bucket in which entry can be added.
		int slot = hashFn(key);
		//3. traverse the bucket and check if key already exists.
		for (Entry entry: table[slot]) {
			//4. if key exists, replace the new value and return old value.
			if(entry.key == key) {
				Student oldValue = entry.value; 
				entry.value = value;
				return oldValue;
			}
		}
		//5. if key not found, append entry into the bucket.
		table[slot].add(newEntry);
		return null;
	}
	
	public Student get(int key) {
		//2. call hash(key) and find the slot/bucket in which entry may be found.
		int slot = hashFn(key);
		//3. traverse the bucket and check if key is found, then return value.
		for (Entry entry: table[slot]) {
			//4. if key exists, return thevalue.
			if(entry.key == key)
				return entry.value;
		}	
		//5. if key not found, return null
		return null;
	}
}

public class HashTableMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		HashTable ht = new HashTable();
		ht.put(3412, new Student(3412, "Nilesh", 95));
		ht.put(4002, new Student(4002, "Vishal", 95));
		ht.put(2051, new Student(2051, "Smita", 95));
		ht.put(3499, new Student(3499, "Sachin", 95));
		ht.put(4612, new Student(4612, "Nitin", 95));
		ht.put(3651, new Student(3651, "Amit", 95));
		ht.put(1651, new Student(1651, "Rahul", 95));
	
		Student old = ht.put(3651, new Student(3651, "Ameet", 95));
		System.out.println(old);
		
		System.out.print("Enter student roll: ");
		int roll = sc.nextInt();
		Student stud = ht.get(roll);
		if(stud == null)
			System.out.println("Student is not found.");
		else
			System.out.println("Student found: " + stud);
		
		HashMap<Integer, Student> hm = new HashMap<Integer, Student>();
		// internally hashCode() & equals() is called of key=Integer class.
		sc.close();
	}
}






