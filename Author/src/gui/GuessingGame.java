package gui;

import javax.swing.JApplet;

import logic.GuessingGameModel;

public class GuessingGame extends JApplet

{
	public void init() {

		initializeGame();
	}

	public void initializeGame() {
		GuessingGameGUI myG = new GuessingGameGUI(new GuessingGameModel());
		getContentPane().add(myG.returnGUI());
		GuessingGameModel myM = new GuessingGameModel();
	}

}
