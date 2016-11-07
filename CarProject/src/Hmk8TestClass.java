import java.util.Scanner;

public class Hmk8TestClass {

	public static void main(String[] args) {
		/*
		 * Address address = new Address("360 Main St.", "Chicago", "Illinois",
		 * "60507"); Driver driver = new Driver("Id# 11261", "Brandon Dupy",
		 * address); Car car = new Car(2016, "Chevrolet", "Camero", driver);
		 * 
		 * // Driver (String licenseId, String fullName, Address A) {
		 * 
		 * // public Car (int year, String make, String model, Driver driver)
		 * 
		 * // public Address (String streetAddress, String city, String state,
		 * // String zipCode) {
		 * 
		 * // car.setDriver(driver); // car.setMake("Chevrolet"); //
		 * car.setModel("Camero"); // car.setYear(2016);
		 * 
		 * // driver.setAddress(address); // driver.setFullName("Brandon Dupy");
		 * // driver.setLicenseId(12345);
		 * 
		 * // address.setStreetAddress("360 Main St."); //
		 * address.setCity("Chicago"); // address.setState("Illinois"); //
		 * address.setZipCode("60507");
		 * 
		 * StringBuilder output = new StringBuilder();
		 * output.append("Car: \t\t"); output.append(car.getYear());
		 * output.append(" "); output.append(car.getMake()); output.append(" ");
		 * output.append(car.getModel()); output.append("\n");
		 * output.append("Ownded by: \t"); output.append(driver.getFullName());
		 * output.append("\n\t\t"); output.append(address.getStreetAddress());
		 * output.append("\n\t\t"); output.append(address.getCity());
		 * output.append(", "); output.append(address.getState());
		 * output.append(" "); output.append(address.getZipCode());
		 */// System.out.println(output);
			// Scanner sc = new Scanner(System.in);
			// sc.nextInt();
		System.out
				.println("Enter the number n which will be the size of the list");
		Scanner sc = new Scanner(System.in);

		int value = sc.nextInt(); // sc.nextInt() reads a value from the console
		String[] input = new String[value];
		String[] x = getData(input, value);

		int[] wtf = { 2, 2, 2, 2 };
		System.out.println(sumArray3(wtf, 0, wtf.length - 1));

	}

	public static String[] getData(String[] myArray, int n) // the getData
															// method
	{
		// scanner is in java.util, this creates a scanner object
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter your next name ");

		for (int i = 0; i < myArray.length; i++) {

			String value = sc.next(); // sc.nextInt() reads a value from the
										// console
			myArray[i] = value;
			System.out.println("Enter the next name");
		}
		return myArray;
	}

	public static int sumArray3(int[] array, int start, int end) {
		int results = 0;
		int mid = (start + end) / 2;
		System.out.println("START" + start);
		System.out.println("END" + end);
		if (start < end) {
			results += sumArray3(array, start, mid)
					+ sumArray3(array, mid + 1, end);

		} else
			results = array[start];
		return results;
	}

}
