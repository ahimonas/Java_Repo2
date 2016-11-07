package lab2;

abstract class StudentAbstract {
	private Integer SID;
	private Integer myS[] = new Integer[5];

	// use default access modifier
	public StudentAbstract() {
	}

	public StudentAbstract(int SID, int[] passedS) {
		this.SID = SID;
		for (int i = 0; i < 5; i++) {
			this.myS[i] = passedS[i];
		}
	}

	// write public get and set methods for SID and scores
	public Integer getSID() {
		return SID;
	}

	public void setSID(int sID) {
		SID = sID;
	}

	public Integer[] getScores() {
		return myS;
	}

	public void setScores(Integer[] scores) {
		this.myS = scores;
	}

	public abstract void printInfo();
}

public class Student extends StudentAbstract {
	public void printInfo() {
		System.out.printf("%-14s", getSID());
		for (int x = 0; x < 5; x++) {
			System.out.printf("%-14d", getScores()[x]);
		}
		System.out.println();
	}
}
