import java.util.Scanner;

public class Tester {

	public static void main(String[] args) {
		boolean flag = false;
		String[] array = new String[6];
		Scanner input = new Scanner(System.in);

		do {
			System.out.println("Enter seat number:");
			System.out.println("Choose from the following:");
			System.out.println("1a");
			System.out.println("1b");
			System.out.println("2a");
			System.out.println("2b");
			System.out.println("3a");
			System.out.println("3b");

			String P_Snum = input.nextLine();

			if (P_Snum.equals("1a") || P_Snum.equals("1b")
					|| P_Snum.equals("2a") || P_Snum.equals("2b")
					|| P_Snum.equals("3a") || P_Snum.equals("3b")) {

				flag = false;
			}

			else {
				flag = true;
				// user entered a wrong choice
				if (!((P_Snum.equals("1a") || P_Snum.equals("1b")
						|| P_Snum.equals("2a") || P_Snum.equals("2b")
						|| P_Snum.equals("3a") || P_Snum.equals("3b"))))
					System.out.println("Invalid entry");
				else {
					// is a duplicate we will talk about this
				}

			}
		} while (flag);
		// P_Snum = input.nextLine();

	}
}
