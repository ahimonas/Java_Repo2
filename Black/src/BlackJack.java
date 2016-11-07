import java.util.Scanner;

public class BlackJack extends DeckOfCards {
	Scanner sc = new Scanner(System.in);

	public BlackJack() {

	}

	public void run() {
		// by this point we can use
		/*
		 * deck is initialized so
		 */

		shuffle();

		System.out.println("Playing Black Jack");
		// Araylist <Card> dealerHand = new Arraylist<Card>();

		int yourNumber = 0;
		Card c1 = dealCard();
		Card c2 = dealCard();
		yourNumber = parseCard(c1) + parseCard(c2);

		String choice;

		System.out.println("Your hand is " + c1.toString() + " "
				+ c2.toString());
		System.out.println("Your total Value is: " + yourNumber);
		System.out.println("Hit or Hold");
		choice = sc.nextLine();

		while (yourNumber < 21 && choice.equals("Hit")) {
			Card c3 = dealCard();
			yourNumber += parseCard(c3);
			System.out.println("Your new Card is: " + c2.toString());
			System.out.println("Your total Value is: " + yourNumber);
			System.out.println("Hit or Hold");
			choice = sc.nextLine();

		}

		int dealersNumber = 0;
		while (dealersNumber < 18) {
			Card myC = dealCard();
			int number = parseCard(myC);
			dealersNumber += number;
		}
		if (dealersNumber > 21)
			System.out.println("Dealer Busts with: " + dealersNumber);

		else if (dealersNumber > yourNumber) {
			System.out.println("You Loose");
			System.out.println("You got " + yourNumber + " " + "Dealer Got :"
					+ dealersNumber);
		} else if (yourNumber > dealersNumber) {
			System.out.println("You Win");
			System.out.println("You got " + yourNumber + " " + "Dealer Got :"
					+ dealersNumber);
		} else {
			System.out.println("Tie");
		}
	}

	public int parseCard(Card c) {
		/*
		 * String[] faces = {"Ace", "Deuce", "Three", "Four", "Five", "Six",
		 * "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
		 */
		if (c.getFace().equals("Ace"))
			return 1;
		else if (c.getFace().equals("Deuce"))
			return 2;
		else if (c.getFace().equals("Three"))
			return 3;
		else if (c.getFace().equals("Four"))
			return 4;
		else if (c.getFace().equals("Five"))
			return 5;
		else if (c.getFace().equals("Six"))
			return 6;
		else if (c.getFace().equals("Seven"))
			return 7;
		else if (c.getFace().equals("Eight"))
			return 8;
		else if (c.getFace().equals("Nine"))
			return 9;
		else if (c.getFace().equals("Ten"))
			return 10;
		else if (c.getFace().equals("Jack"))
			return 11;
		else if (c.getFace().equals("Queen"))
			return 12;
		else if (c.getFace().equals("King"))
			return 13; // KING

		return 0;
	}

}
