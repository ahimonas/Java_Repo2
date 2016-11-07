import java.util.Random;
import java.util.Scanner;

public class GuessGame {
	public static int totalGuesses = 0;
	public static int totalGames = 0;
	public static int bestGame = 0;
	public static int numberOfGuesses = 0;
	public static int totalGuess = 0;
	public static final int max = 100;

	public static void main(String[] args) {

		Scanner console = new Scanner(System.in);
		introduceGame();

		do {
			// call playGame to play the game, it's number of guesses
			numberOfGuesses = playGame(console);

			if (bestGame == 0) {
				bestGame = numberOfGuesses;
			} else if (bestGame > numberOfGuesses) {
				bestGame = numberOfGuesses;
			}
			totalGuess += numberOfGuesses;

		} while (continueToPlay(console));
		// display all results;
		printOverallResult(totalGuess, totalGames, bestGame);
	}

	// This method allows the user to decide whether they'd be desired to play
	// again or if they'd like to stop.
	public static boolean continueToPlay(Scanner console) {
		System.out.print("Do you want to play again? ");
		String answer = console.next();
		return answer.charAt(0) == 'Y' || answer.charAt(0) == 'y';
	}

	/*
	 * Introduce the game to user
	 */
	public static void introduceGame() {
		System.out
				.println("This program allows you to play a guessing game. \n"
						+ "I will think of a number between 1 and 100 and will allow you to guess until you get it. \n"
						+ "For each guess, I will tell you whether the right answer is higher or lower than your guess.\n");

		System.out.println("<<Welcome to Haiku");
		System.out.println("This is my Haiku Program");
		System.out.println("I Hope you enjoy>>");
	}

	public static void printOverallResult(int numGuess, int numGames,
			int bestGame) {
		System.out.println(" total games = " + numGames);
		System.out.println(" total guesses = " + numGuess);
		System.out.println(" guesses/game = "
				+ (double) Math.round((1.0 * numGuess / numGames) * 10) / 10);
		System.out.println(" best game = " + bestGame);

	}

	public static int playGame(Scanner sc) {
		totalGames++;
		System.out.println("I'm thinking of a number between 1 and " + max);
		Random r = new Random();
		int n = r.nextInt(max) + 1;

		// this line of code was used for testing if the random number was
		// changing.
		// System.out.println("RANDOM? " + n);
		int guessNum = 0;
		int i = 0;
		while (i != n) {
			System.out.print("Your guess? ");
			i = sc.nextInt();
			if (i < n)
				System.out.println("It's higher");
			else if (i > n) // TODO4: Correct this if command
				System.out.println("It's smaller");
			guessNum++;
		}

		System.out.println("You got it right in " + guessNum + " guesses");
		return guessNum;
	}
}