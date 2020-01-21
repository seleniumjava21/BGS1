package Demo;

public class ArrayDemo {

	public static void main(String[] args) {
		String[] cars = { "Volvo", "BMW", "Ford", "Mazda" };

		int[] myNum = { 10, 20, 30, 40 };

		cars[0] = "i10";
		System.out.println(cars[1]);

		System.out.println(cars.length);

		for (int i = 0; i < cars.length; i++) {

			System.out.println(cars[i]);
			
			//check
		}

		for (int i : myNum) {
			System.out.println(i);
		}

		int[][] myNumbers = { { 1, 2, 3, 4 }, { 5, 6, 7 } };

		int x = myNumbers[1][0];
		System.out.println(x);

		
		/*
		 * If you compare the for loop and for-each loop, you will see that the for-each
		 * // method is easier t // o write, it does not require a counter (using the
		 * length property), and it is // more readable.
		 */
String[] laptops = {"dell","hp","thinkpad","apple"};

laptops[0]="Acer";
System.out.println(laptops[0]);
	}

}
