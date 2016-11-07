import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class EDF {

	public static final int CURR_SIZE = 10;

	public static void main(String[] args) {

		// the line to read
		String myL;
		// to read the input from file
		BufferedReader inputStrem = null;

		String myC;
		String myProc;
		long myDeadL;
		long myTotalTime;

		long time;

		// check input validity
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments: " + args.length);
			System.err.println("java hw6.EDF <input-file>");
			System.exit(1);
		}

		File file = new File(args[0]);
		MyPriorityQueue<Record> queue = new MyPriorityQueue<Record>(CURR_SIZE);
		long current_time = 0;

		// try opening file
		try {
			inputStrem = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			System.err.println("Failed to open " + file);
			System.exit(1);
		}

		// try reading the file line by line
		try {
			while ((myL = inputStrem.readLine()) != null) {
				String[] arguments = myL.split(" ");
				myC = arguments[0];

				// adding schedule
				if (myC.equals("schedule")) {
					myProc = arguments[1];
					myDeadL = Long.parseLong(arguments[2]);
					myTotalTime = Long.parseLong(arguments[3]);
					Record adding = new Record(myProc, myDeadL, myTotalTime);
					queue.add(adding);
					System.out.println(current_time + ": adding "
							+ adding.toString());

					// running
				} else if (myC.equals("run")) {

					// total time to run
					time = Long.parseLong(arguments[1]) - current_time;

					// while there is still time
					while (time > 0) {

						// work on/remove the task
						Record doing = queue.poll();
						// stop if no task anymore
						if (doing == null) {
							break;
						}
						System.out.println(current_time + ": busy with "
								+ doing.toString());
						// subtract duration from remaining time
						time -= doing.GetDuration();

						// time up but task not done yet. add the record back to
						// queue
						if (time < 0) {
							// add back the overdrawn time
							current_time += (doing.GetDuration() + time);
							// add back the remaining part of the task
							doing = new Record(doing, -time);
							queue.add(doing);
							// clear remaining time
							time = 0;
							System.out.println(current_time + ": adding "
									+ doing.toString());

							// task done
						} else {
							// proceed along timeline
							current_time += doing.GetDuration();
							System.out.println(current_time + ": done with "
									+ doing.toString(current_time));
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
