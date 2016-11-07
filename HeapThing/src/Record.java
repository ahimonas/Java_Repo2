public class Record implements Comparable<Record> {

	private String myproc;
	private long myDeadlin;
	private long totalT;

	Record(String process, long deadline, long duration) {
		myproc = process;
		myDeadlin = deadline;
		totalT = duration;
	}

	Record(Record myR, long duration) {
		this.myproc = myR.myproc;
		this.myDeadlin = myR.myDeadlin;
		this.totalT = duration;
	}

	public long GetDuration() {
		return totalT;
	}

	public String toString() {
		return myproc + " with deadline " + myDeadlin + " and duration "
				+ totalT;
	}

	public String toString(long myT) {
		if (myT > myDeadlin)
			return myproc + " (late)";
		return myproc;
	}

	@Override
	public int compareTo(Record passedR) {
		long l = this.myDeadlin - passedR.myDeadlin;
		int i = (int) l;
		return i;
	}
}
