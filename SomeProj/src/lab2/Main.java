package lab2;

import Lab2Util.Util;

public class Main {

	public static void main(String[] args) {
		Student[] myL2 = new Student[40];

		myL2 = Util.readFile("student score.txt", myL2);

		Statistics myStatL = new Statistics();
		myStatL.findhigh(myL2);
		myStatL.findlow(myL2);
		myStatL.findavg(myL2);
		System.out.printf("%-14s%-14s%-14s%-14s%-14s%-14s\n", "SID", "Qu1",
				"Qu2", "Qu3", "Qu4", "Qu5");

		for (int i = 0; i < myL2.length; i++) {
			myL2[i].printInfo();
		}
		System.out.println();
		myStatL.printscores();
	}
}
