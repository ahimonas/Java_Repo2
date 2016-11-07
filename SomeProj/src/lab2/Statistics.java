package lab2;

public class Statistics {
	private int[] myLows = new int[5];
	private int[] myHighs = new int[5];
	private float[] myAvgs = new float[5];

	public int[] getLowscores() {
		return myLows;
	}

	public void setLowscores(int[] lowscores) {
		this.myLows = lowscores;
	}

	public int[] getHighscores() {
		return myHighs;
	}

	public void setHighscores(int[] highscores) {
		this.myHighs = highscores;
	}

	public float[] getAvgscores() {
		return myAvgs;
	}

	public void setAvgscores(float[] avgscores) {
		this.myAvgs = avgscores;
	}

	public void findlow(Student[] someS) {
		Integer[] scores = someS[0].getScores();
		for (int i = 0; i < 5; i++) {
			myLows[i] = scores[i];
		}
		for (int i = 1; i < someS.length; i++) {
			scores = someS[i].getScores();
			for (int j = 0; j < 5; j++) {
				if (scores[j] < myLows[j]) {
					myLows[j] = scores[j];
				}
			}
		}
	}

	public void findhigh(Student[] someS) {
		Integer[] scores = someS[0].getScores();
		for (int i = 0; i < 5; i++) {
			myHighs[i] = scores[i];
		}
		for (int i = 1; i < someS.length; i++) {
			scores = someS[i].getScores();
			for (int j = 0; j < 5; j++) {
				if (scores[j] > myHighs[j]) {
					myHighs[j] = scores[j];
				}
			}
		}
	}

	public void findavg(Student[] someS) {
		Integer[] mySSS = someS[0].getScores();
		float[] totSSSSS = { 0, 0, 0, 0, 0 };
		for (int i = 0; i < someS.length; i++) {
			mySSS = someS[i].getScores();
			for (int j = 0; j < 5; j++) {
				totSSSSS[j] += mySSS[j];
			}
		}
		for (int i = 0; i < 5; i++) {
			myAvgs[i] = totSSSSS[i] / someS.length;
		}
	}

	void printscores() {
		System.out.printf("%-14s", "High Score");
		for (int i = 0; i < 5; i++) {
			System.out.printf("%-14d", myHighs[i]);
		}
		System.out.println();
		System.out.printf("%-14s", "Low Score");
		for (int i = 0; i < 5; i++) {
			System.out.printf("%-14d", myLows[i]);
		}
		System.out.println();
		System.out.printf("%-14s", "Average");
		for (int i = 0; i < 5; i++) {
			System.out.printf("%-14f", myAvgs[i]);
		}
		System.out.println();
	}
}