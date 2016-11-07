package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import logic.GuessingGameModel;

public class GuessingGameGUI extends JPanel implements ActionListener {

	JPanel myDPanel;
	JPanel myMainP;
	JPanel myCp;
	JButton btn1 = new JButton("Yes");
	JButton btn2 = new JButton("No");
	JButton btn3 = new JButton("You guessed my animal!");
	JButton btn4 = new JButton("New Game");
	JLabel btn5 = new JLabel("Question Goes Here");
	JLabel label1 = new JLabel("Answer Goes Here");
	JLabel text1 = new JLabel("Question:");
	JLabel text2 = new JLabel("I think your animal is a :");
	JButton btnWrong = new JButton("Wrong animal!");
	GuessingGameModel myM;

	public GuessingGameGUI(GuessingGameModel model) {
		this.myM = model;
		initPanels();
		this.btn5.setText(model.updateInfo());
	}

	private void initPanels() {
		setMyPannel();
		cpSetup();
		setupMainPanel();
	}

	private void setMyPannel() {

		this.myDPanel = new JPanel();
		this.myDPanel.setLayout(new GridLayout(4, 1));
		this.myDPanel.setPreferredSize(new Dimension(200, 600));
		this.myDPanel.add(text1);
		this.myDPanel.add(btn5);
		this.myDPanel.add(text2);
		this.myDPanel.add(label1);
	}

	private void cpSetup() {
		// Set up the control panel - the yes/no and new game buttons.
		this.myCp = new JPanel();
		this.myCp.setLayout(new FlowLayout());
		this.myCp.setPreferredSize(new Dimension(200, 600));
		this.myCp.add(btn4);
		btn4.addActionListener(this);
		this.myCp.add(btn1);
		btn1.addActionListener(this);
		this.myCp.add(btn2);
		btn2.addActionListener(this);
		this.myCp.add(btn3);
		btn3.addActionListener(this);
		this.myCp.add(btnWrong);
		btnWrong.addActionListener(this);
		btn3.setEnabled(false);
		btnWrong.setEnabled(false);
	}

	private void setupMainPanel() { // Set up the main panel and add all the
									// other panels to it
		myMainP = new JPanel();
		myMainP.setLayout(new BoxLayout(myMainP, BoxLayout.Y_AXIS));
		myMainP.setPreferredSize(new Dimension(800, 600));
		this.myMainP.add(myDPanel);
		this.myMainP.add(myCp);
	}

	public JPanel returnGUI() {
		return this.myMainP;
	}

	public void questionUpdater() {
		if (myM.isQuestion() == true) {
			btn5.setText(myM.updateInfo());

		}

	}

	private void gameover() {
		if (!myM.isQuestion()) {
			this.btn1.setEnabled(false);
			this.btn2.setEnabled(false);
			this.btnWrong.setEnabled(true);
			this.btn3.setEnabled(true);
		}
	}

	private void btnReste() {
		this.btn1.setEnabled(true);
		this.btn2.setEnabled(true);
		this.btnWrong.setEnabled(false);
		this.btn3.setEnabled(false);
	}

	public void actionPerformed(ActionEvent myActionEvent) {

		if (myActionEvent.getSource() == btn4) {
			myM.reInitialize();
		}

		if (myActionEvent.getSource() == btn1) {
			myM.yesSelection();
			if (myM.isQuestion()) {
				this.btn5.setText(myM.updateInfo());
			} else {
				this.label1.setText(myM.updateInfo());
			}
		}
		if (myActionEvent.getSource() == btn2) {
			myM.noSelection();
			if (myM.isQuestion()) {
				this.btn5.setText(myM.updateInfo());
			} else {
				this.label1.setText(myM.updateInfo());
			}
		}
		if (myActionEvent.getSource() == btn3) {
			JOptionPane.showMessageDialog(this, "Excellent!");
		}
		if (myActionEvent.getSource() == btnWrong) {
			JOptionPane
					.showMessageDialog(this,
							"We're sorry. Buy the advanced version to add more animals!");
		}
		if (myActionEvent.getSource() == btn4) {
			this.myM = new GuessingGameModel();
			questionUpdater();
			this.label1.setText("");
			btnReste();
		}
		gameover();

	}
}
