package Demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class ArrayListDemo {

	public static void main(String[] args) {
		/*
		 * The difference between a built-in array and an ArrayList in Java, is that the
		 * size of an array cannot be modified
		 */

		int[] a = new int[6];
		int temp = 0;
		a[1] = 1;
		a[2] = 2;
		a[3] = 3;
		a[4] = 4;
		a[5] = 5;
		for (int i = 1; i <= 5; i++) {
			System.out.println(a[i]);
		}
		for (int n = 1; n <= 3; n++) {
			for (int j = 0; j <= 5; j++) {
				a[j] = temp;
				a[j + 1] = a[j];
				while (j == 5) {
					a[j] = temp;
				}
			}
			
			
		}
		
		
		
		ArrayList<String> car = new ArrayList<String>(); // Create an ArrayList object car.add("Volvo");
		car.add("BMW");
		car.add("Ford");
		car.add("Mazda");
		car.add("polo");
		car.add("polo");

		System.out.println(car);
		for (int i = 0; i < car.size(); i++) {
			System.out.println(car.get(i));
		}

		System.out.println(car.get(0));
		System.out.println(car.set(1, "zen"));
		System.out.println("diff");
		System.out.println(car);
		System.out.println(car.remove(1));
		System.out.println(car.size());
		for (int i = 0; i < car.size(); i++) {
			System.out.println(car.get(i));
		}
		ArrayList<String> colours = new ArrayList();

		ArrayList<Integer> myNumber = new ArrayList<Integer>();
		myNumber.add(10);
		myNumber.add(15);
		myNumber.add(20);
		myNumber.add(25);
		for (int i : myNumber) {
			System.out.println(1);
		}
		Collections.sort(car); // Sort cars
		Collections.sort(myNumber); // Sort cars
//if(!car.get(1).contentEquals(myNumber.get(1))
		{

		}
		/*
		 * Elements can be inserted or accessed by their position in the list, using a
		 * zero-based index.
		 * 
		 * A list may contain duplicate elements.
		 */

		// The List interface extends Collection and declares the behavior of a
		// collection that stores a
		// sequence of elements.
		// List<String> supplierNames = new List<String>(); List is an interface, and
		// you can not initialize an
		// interface.
		// Instantiate an implementing class instead.
		List<String> supplierNames1 = new ArrayList<String>();
		supplierNames1.add("lekha");
		supplierNames1.add("harish");
		supplierNames1.add("selenium");
		// supplierNames1.get(2);

		System.out.println(supplierNames1.get(2));
		System.out.println(supplierNames1);

		/*
		 * All Known Implementing Classes: AbstractList, AbstractSequentialList,
		 * ArrayList, AttributeList, CopyOnWriteArrayList, LinkedList, RoleList,
		 * RoleUnresolvedList, Stack, Vector
		 */

		// Set demonstration using HashSet , will not allow duplicates, no order.
		Set<String> hash_Set = new HashSet<String>();
		hash_Set.add("selenium");
		hash_Set.add("For");
		hash_Set.add("Automation");
		hash_Set.add("Example");
		hash_Set.add("Set");
		hash_Set.add("selenium");

		System.out.print("Set output without the duplicates");

		System.out.println(hash_Set);

		SortedSet<String> sortedSet = new TreeSet<String>();
		sortedSet.add("ikea");
		sortedSet.add("iknokiaea");
		sortedSet.add("inorbit");

		// SortedSet<String>sortedSet1 = new TreeSet<String>();

		String sortedSet1 = "inorbit,iknokiaea";

		if (sortedSet.contains(sortedSet1)) {
			System.out.println(sortedSet);
		}

		System.out.println(sortedSet);
		// no duplicates in set and map

		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("one", "Automation for selenium");
		// supplierNames1.add("harish");
		lhm.put("two", "learning hash map");
		lhm.put("four", "missed 3rd value and prinitng 4th");
		lhm.put("2", "shilpa");
		lhm.put("1", "Hyd");
		lhm.put("RT046", "Harish");
		lhm.put("0005", "AUS");
		lhm.put("0007", "US");

		// It prints the elements in same order
		// as they were inserted
		System.out.println(lhm);

		System.out.println("Getting value for key 'one': " + lhm.get("0005"));
		System.out.println("Size of the map: " + lhm.size());

		System.out.println("Is map empty? " + lhm.isEmpty());
		System.out.println("Contains key 'map'? " + lhm.containsKey("RT046"));
		System.out.println("value of 1 " + lhm.get("1"));
		System.out.println("value of 1 " + lhm.get("2"));
		// System.out.println("Contains value 'Autom"+ lhm.containsValue("four"));
		System.out.println("delete element 'two': " + lhm.remove("1"));
		System.out.println(lhm);

	}

}
