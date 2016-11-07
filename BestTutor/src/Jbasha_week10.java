import java.util.Scanner;

/**
 * @Course: SDEV 250 ~ Java Programming I
 * @Author Name: ThahirBasha
 * @Assignment Name: jbasha_week10
 * @Date: May 1, 2016
 * @Description:
 */
// Imports

// Begin Class Jbasha_week10
public class Jbasha_week10 {

	public static void main(String[] args) {
		// to prompt for loop

		boolean cont = false;
		Scanner s = new Scanner(System.in);
		String userInput;
		int num;
		String num2 = "";

		do {

			System.out.println("Enter a number: ");
			// int num = s.nextInt();
			num2 = s.nextLine();
			System.out.println("Enter another num");
			// System.out.println(fact(num));

			// System.out.println("Compute another factorial? (Y/N)");
			userInput = s.nextLine();
			int myNum = Integer.parseInt(num2);

			cont = userInput.equalsIgnoreCase("Y");

			System.out.println("This shold be true: " + cont);

		} while (cont);
	}

	public static long fact(long n) {
		if (n <= 1) {
			return n;
		} else {
			return n * fact(n - 1);
		}
	}

} // End Class Jbasha_week10