import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class Board extends JFrame implements ActionListener, MouseListener {
	private int rows = 10;
	private int columns = 10;
	private int mines = 10;
	JFrame frame = new JFrame("Dhruv's Minesweeper");
	JMenuBar bar = new JMenuBar();
	JPanel scoresPane = new JPanel(new GridLayout(2, 3, 5, 3));
	// JMenu menu = new JMenu("Options");
	JButton reset = new JButton("Reset");
	// only things you see, outside, buttons
	JButton[][] buttons = new JButton[10][10];
	// location of mines, inside
	int[][] counts = new int[10][10];
	Container grid = new Container();
	private JMenuItem itemReset;
	private JMenuItem itemTopTen;
	private JMenuItem itemQuit;
	private JMenuItem itemHelp;
	private JMenuItem itemAbout;
	private JLabel txtMinesLeft;
	private JLabel txtTime;
	private JTextArea txtTest;
	private JButton btnStart;
	private int minesLeft = mines;
	private int currentTime = 0;
	private javax.swing.Timer timer;
	private boolean firstClick = true;
	private int bestScore;
	private JPanel field;
	private JLabel lblTime;
	private Timerthread tim;

	public Board() {
		frame.setSize(250, 250); // initlize the size
		frame.setLayout(new BorderLayout()); // create the grid with the border
		frame.setJMenuBar(bar); // intilize the menu bar
		// bar.add(menu);
		bar.add(myMenu()); // add the menu to the bar

		JPanel scoresPane = new JPanel(new GridLayout(2, 3, 5, 3)); // initlize
																	// panel to
																	// hole
																	// reeset
																	// button,
																	// number of
																	// mine, and
																	// time
																	// elapsed
		frame.add(scoresPane, BorderLayout.NORTH); // position on the panel
													// where the above elemts
													// are located
		// start of score things

		// add number of mines left
		JLabel lblMinesLeft = new JLabel("Mines Left");// initlize label which
														// displays mines left
		lblMinesLeft.setHorizontalAlignment(JLabel.CENTER); // postion where the
															// dsiplay would be
															// aligned
		scoresPane.add(lblMinesLeft); // add it to the pannel

		reset.setPreferredSize(new Dimension(25, 25)); // initlize the reset
														// button with size as
														// specified
		reset.addActionListener(this); // add an action listner to this button
										// so we can keep track of the activity
										// like cliks on the button
		scoresPane.add(reset); // now add the button to the Panel

		lblTime = new JLabel("Time"); // intilize label with timer
		lblTime.setHorizontalAlignment(JLabel.CENTER); // set the position to
														// the center
		scoresPane.add(lblTime, 2); // set the button to the panel

		// JLabel that shows number of mines left on a field
		txtMinesLeft = new JLabel("" + minesLeft);
		txtMinesLeft.setHorizontalAlignment(JLabel.CENTER);
		txtMinesLeft.setForeground(Color.red);
		txtMinesLeft.setFont(new Font("DialogInput", Font.BOLD, 18));
		scoresPane.add(txtMinesLeft);

		scoresPane.add(new JLabel(""));

		// JLabel that shows elapsed time
		txtTime = new JLabel("000");
		txtTime.setHorizontalAlignment(JLabel.CENTER);
		txtTime.setForeground(Color.red);
		txtTime.setFont(new Font("DialogInput", Font.BOLD, 18));
		scoresPane.add(txtTime); // emd of score things

		creatBoard();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		// timer = new javax.swing.Timer( 1000, this );

	}

	// call the constructor
	public static void main(String[] args) {
		new Board();
	}

	// create the board and lay the mines
	public void creatBoard() {
		grid.setLayout(new GridLayout(10, 10)); // initialize the board
		System.out.println("Hello World");
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons.length; j++) {
				buttons[i][j] = new JButton(); // set the buttons
				// buttons[i][j].addActionListener(this); // add action lister
				// to the buttons
				buttons[i][j].addMouseListener(this);
				grid.add(buttons[i][j]); // add buttons to the grid
			}
		}
		frame.add(grid, BorderLayout.CENTER); // add the grid to the frame
		createMines(); // lay down the mines

	}

	public void storeScores() {

	}

	public boolean getScores() {

		return true;
	}

	private void resetBoard() {
		// go throught all the miens
		for (int i = 0; i < rows; i++)
			// go though the entire grid
			for (int j = 0; j < columns; j++) {
				buttons[i][j] = new JButton(); // add button the the gird again
				// buttons[i][j].addActionListener( this ); // add action
				// listner to all those buttos
				buttons[i][j].addMouseListener(this);
				grid.add(buttons[i][j]); // add them to the grid
			}
		currentTime = 0; // sett he current time to be 0
		minesLeft = mines; // set the mines to 10 back
		txtMinesLeft.setText("" + minesLeft); // display the text
		txtTime.setText("00" + currentTime); // since current time is 0 we want
												// it to display 000 that's why
												// we ad this line

	}

	// default function for action listner
	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	// create the mines
	public void createMines() {
		counts = new int[10][10]; // set an array which will be connected to the
									// fgird
		for (int x = 0; x <= 10; x++) { // go though and add 10 mines
			int choice = (int) (Math.random() * 10); // get random x position
			int choice1 = (int) (Math.random() * 10); // get random y position
			counts[choice][choice1] = 10; // add the mines to the position
		}

		for (int x = 0; x < counts.length; x++) {
			for (int y = 0; y < counts.length; y++) {
				if (counts[x][y] != 10) { // if it's not a mines

					int neighborcount = 0; // count neighbours

					if (x > 0 && y > 0 && counts[x - 1][y - 1] == 10) { // left,
																		// up
						neighborcount++;
					}
					if (y > 0 && counts[x][y - 1] == 10) { // Up
						neighborcount++;
					}
					if (x < counts.length - 1 && y > 0
							&& counts[x + 1][y - 1] == 10) { // right, up
						neighborcount++;
					}
					if (x > 0 && counts[x - 1][y] == 10) { // left
						neighborcount++;
					}
					if (x < counts.length - 1 && counts[x + 1][y] == 10) { // right
						neighborcount++;
					}
					if (x > 0 && y < counts.length - 1
							&& counts[x - 1][y + 1] == 10) { // left, down
						neighborcount++;
					}
					if (y < counts.length - 1 && counts[x][y + 1] == 10) { // down
						neighborcount++;
					}
					if (x < counts.length - 1 && y < counts.length - 1
							&& counts[x + 1][y + 1] == 10) { // right, down
						neighborcount++;
					}

					counts[x][y] = neighborcount; // set the count to that
													// particular buton
				}

			}

		}

	}

	/**
	 * Creates a Menu Bar with JMenu and JMenuItems
	 * 
	 * @return JMenuBar
	 */
	/**
	 * Creates a Menu Bar with JMenu and JMenuItems
	 * 
	 * @return JMenuBar
	 */
	private JMenuBar myMenu() // function for thee menu
	{
		JMenuBar menuBar = new JMenuBar(); // set up the drop down menu

		JMenu menuGame = new JMenu("Game"); // intilize the menu with button
											// Game

		itemReset = new JMenuItem("Reset"); // also initlize a button Reset
		itemReset.addActionListener(this); // give it an action listner

		itemTopTen = new JMenuItem("Top Ten"); // add a button called TopTen
		itemTopTen.addActionListener(this); // give it an action listner

		itemQuit = new JMenuItem("Exit"); // add a button for exit
		itemQuit.addActionListener(this); // give it an action listner

		menuGame.add(itemReset); // add all reset to the menu
		menuGame.add(itemTopTen); // add Top Ten to the menu
		menuGame.add(itemQuit); // add Exit to the menu

		JMenu menuHelp = new JMenu("Help"); // creatin a help button in the
											// panel
		itemHelp = new JMenuItem("Help"); // add a add button insdie
		itemHelp.addActionListener(this); // give it an action listner
		itemAbout = new JMenuItem("About"); // add and baout button iside and
		itemAbout.addActionListener(this); // give it an action listner
		menuHelp.add(itemHelp); // add help to the menu
		menuHelp.add(itemAbout); // add about button to the menu

		menuBar.add(menuGame); // add the game and
		menuBar.add(menuHelp); // add help to the menubaar

		return menuBar; // retur th new mwnubar
	}

	public void lostGame() { // display the enitre board once player loses or
								// clickes on mine

		for (int x = 0; x < counts.length; x++) {
			for (int y = 0; y < counts.length; y++) { // go throught the gid
				if (buttons[x][y].isEnabled()) { // if button is not enables and
					if (counts[x][y] != 10) { // it is not a mine
						buttons[x][y].setText(counts[x][y] + ""); // set it's
																	// text to
																	// count at
																	// that
																	// postion
						buttons[x][y].setEnabled(false); // and set engble to
															// false so it seems
															// like they r
															// pressed

					} else {
						buttons[x][y].setText("M"); // set it's text to "M" at
													// that postion
						buttons[x][y].setEnabled(false); // and set engble to
															// false so it seems
															// like they r
															// pressed
					}
				}
			}
		}
		tim.interrupt(); // stop the clock
	}

	public void mouseClicked(MouseEvent e) {
		// if start button was clicked stop the timer and reset the minefield
		if (e.getSource() == btnStart) // check if the start button is pressed
		{
			timer.stop(); // reset the timer
			rows = 10; // set rows to 10
			columns = 10; // set column to 10
			minesLeft = 10; // set minesleft to 10
			resetBoard(); // reset the Board so all theb uttons are displayed
			return;
		}

		if (firstClick) { // check if the player clicks any btton either left or
							// right
			tim = new Timerthread(System.currentTimeMillis(), txtTime); // if
																		// he/she
																		// does
																		// start
																		// the
																		// thread
																		// to
																		// start
																		// the
																		// timer
			tim.start(); // start the timer
			firstClick = false; // then set tthe firts click to false
		}

		for (int x = 0; x < counts.length; x++) { // go thought the grif
			for (int y = 0; y < counts.length; y++) {
				if (SwingUtilities.isLeftMouseButton(e)) { // check if it is
															// left clciked
					currentTime++;
					if (e.getSource().equals(buttons[x][y])) { // if left click
																// on the buttom
						if (buttons[x][y].getText() == "F") { // check if button
																// is flaged
							buttons[x][y].setText(""); // if it is flagged set
														// to to an empty text
							minesLeft++; // once u reset to expty text set the
											// mines to what is was intially
											// because you are not using the
											// mine
							txtMinesLeft.setText("" + minesLeft); // updtae the
																	// amount of
																	// mines on
																	// the panel
						} else if (counts[x][y] == 10) { // check if player left
															// click on a mine
							buttons[x][y].setText("M"); // if he/ she does make
														// it a 'M"
							lostGame(); // and then they loose the gmae and call
										// the functio

						} else { // if not then oepn eveything next to it
							openUpAll(x, y); // call the open all functionw
												// which open eveything empty
												// next to it
						}
					}
				} else if (SwingUtilities.isRightMouseButton(e)) { // if the
																	// user has
																	// right
																	// clciked
					if (e.getSource().equals(buttons[x][y])) { // check the
						System.out.println("Hello WorldBITCH"); // souce on that
						// button
						if (buttons[x][y].getText() == "F") { // if the button
							System.out.println("Hello World 2"); // is
							// flagged
							buttons[x][y].setText("?"); // and u right clcik on
														// it again make it a
														// "?"
							minesLeft++; // and ad the mines back
							txtMinesLeft.setText("" + minesLeft); // display the
																	// updated
																	// mines on
																	// the top
						} else if (buttons[x][y].getText() == "?") {
							buttons[x][y].setText(" ");
							System.out.println("Hello World 3");
						} else {
							System.out.println("Hello World 4");
							buttons[x][y].setText("F"); // if not tthen sett he
														// button to"F', which
														// means flag that
														// button as there is a
														// mine
							minesLeft--; // decrement the amount of mines
							txtMinesLeft.setText("" + minesLeft); // update the
																	// mines in
																	// the panel

						}
					}
				}
			}
		}
	}

	public void openUpAll(int x, int y) { // openUpAll fuction open eveything
											// next to the right clciked empty
											// button
		if (counts[x][y] > 0 && counts[x][y] < 10) { // if the text inside is
														// less then 0 and has
														// 10 mien netxt to the
														// mine
			buttons[x][y].setText("" + counts[x][y]); // diplay the maines
			buttons[x][y].setEnabled(false); // and make the buttn displayed as
												// they have been pressed
		}
		if (counts[x][y] == 0) { // if the button pressed is empty and has no
									// mines nextt to it
			buttons[x][y].setText("0"); // se the stting to "0"
			buttons[x][y].setEnabled(false);
			if (x > 0 && y > 0 && buttons[x - 1][y - 1].isEnabled()
					&& buttons[x - 1][y - 1].getText() != "F") { // check if
																	// buttoms
																	// on it has
																	// no more
																	// mepty
																	// spaces
				openUpAll(x - 1, y - 1); // recursively call the function
			}
			if (y > 0 && buttons[x][y - 1].isEnabled()) { // check the right
															// button
				openUpAll(x, y - 1); // recursilvely call the function
			}
			if (x < counts.length - 1 && y > 0
					&& buttons[x + 1][y - 1].isEnabled()
					&& buttons[x + 1][y - 1].getText() != "F") { // right, up
																	// has
																	// osmethind
																	// around
																	// that is 0
				openUpAll(x + 1, y - 1); // rexursively call the functions pn it
			}
			if (x > 0 && buttons[x - 1][y].isEnabled()
					&& buttons[x - 1][y].getText() != "F") { // left as
																// osmethind
																// around that
																// is 0
				openUpAll(x - 1, y); // recursilvely call the function
			}
			if (x < counts.length - 1 && buttons[x + 1][y].isEnabled()
					&& buttons[x + 1][y].getText() != "F") { // right as
																// osmethind
																// around that
																// is 0
				openUpAll(x + 1, y); // recursilvely call the function
			}
			if (x > 0 && y < counts.length - 1
					&& buttons[x - 1][y + 1].isEnabled()
					&& buttons[x - 1][y + 1].getText() != "F") { // left, down
																	// as
																	// osmethind
																	// around
																	// that is 0
				openUpAll(x - 1, y + 1); // recursilvely call the function
			}
			if (y < counts.length - 1 && buttons[x][y + 1].getText() != "F") { // down
																				// as
																				// osmethind
																				// around
																				// that
																				// is
																				// 0
				openUpAll(x, y + 1); // recursilvely call the function
			}
			if (x < counts.length - 1 && y < counts.length - 1
					&& buttons[x + 1][y + 1].isEnabled()
					&& buttons[x + 1][y + 1].getText() != "F") { // right, down
																	// as
																	// osmethind
																	// around
																	// that is 0
				openUpAll(x + 1, y + 1); // recursilvely call the function
			}
		}
	}

	private class Timerthread extends Thread { // Thread which keeps on going
												// for the timw

		long time; // iitlize the time
		JLabel label; // initlize the lable
		long elapsed; // time elapsed between the game started and ended

		public Timerthread(long time, JLabel label) { // TimerThread costructoe
			this.time = time; // intilize time
			this.label = label; // intialize label
		}

		@Override
		public void run() { // run the program
			while (!isInterrupted()) { // check if it is interupted, but that
										// mean are we told to stop
				elapsed = (System.currentTimeMillis() - time) / 1000; // calcuate
																		// the
																		// time
																		// elapsed
				if (elapsed < 10) { // see if the time elapsed is int Ten's
					label.setText("00" + elapsed + ""); // if it is add two mrw
														// 0's
				} else if (elapsed < 100) { // if itis hundreds
					label.setText("0" + elapsed + ""); // add one more 0
				} else {
					label.setText(elapsed + ""); // if it is in thousands
				}
				if (elapsed == 1000) { // max the time can be is 1000 after that
										// point
					break; // just break
				}

			}
			lostGame(); // and then run loseGame becasue time is up and player
						// can't win
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) { // actional listner for this
													// program

		Object click = e.getSource(); // check the souce od the clikc
		String sHelp = "Left click: uncovers a field (or multiple fields)  \n"; // if
																				// helpbuton
																				// in
																				// the
																				// menu
																				// is
																				// clcike
																				// display
		String sAuthor = " Author: Dhruv Patel \n"; // if it clciks the button
													// about in the menu panel
													// diaplye this prompty

		if (click == itemAbout) // if click is about
			JOptionPane.showMessageDialog(itemAbout, sAuthor, "About",
					JOptionPane.INFORMATION_MESSAGE); // rint about prompt in a
														// box

		else if (click == itemHelp) // is help is clciked
			JOptionPane.showMessageDialog(itemHelp, sHelp, "Help",
					JOptionPane.INFORMATION_MESSAGE); // print the prompt above
														// in

		else if (click == itemQuit) // if the Quit button is clicked
			System.exit(0); // exit the game

		else if (e.getSource().equals(reset) || e.getSource().equals(itemReset)) { // check
																					// the
																					// press
																					// rest
																					// or
																					// the
																					// y
																					// pressed
																					// a
																					// New
																					// Game
																					// button
																					// from
																					// the
																					// menu
																					// if
																					// they
																					// did
			for (int x = 0; x < counts.length; x++) { // go though the grid
				for (int y = 0; y < counts.length; y++) {
					// buttons[x][y].reset();
					buttons[x][y].setEnabled(true); // enable all the butotons
													// as if they are not
													// pressed
					buttons[x][y].setText(""); // set their text to empty
					minesLeft = mines; // reste all them iens to 10
					txtMinesLeft.setText("" + minesLeft); // and set the diapley
															// of miens to 0
				}
			}
			tim.interrupt(); // stop tthe thread and intureupt it
			firstClick = true; // make the first clikc to true back
			txtTime.setText("000"); // sett he time back to "000"
			createMines(); // create mines again ane setup the grid back
		}

	}
}