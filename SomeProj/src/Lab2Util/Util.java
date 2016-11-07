package Lab2Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;

import lab2.Student;
import CustomerException.MoreStudentsException;

public class Util {
	@SuppressWarnings("resource")
	public static Student[] readFile(String filename, Student[] stu) {
		String myC;
		int sNum = 0;
		File infoS = new File(filename);
		try {
			FileReader myR = new FileReader(infoS);
			BufferedReader br = new BufferedReader(myR);
			br.readLine();
			while ((myC = br.readLine()) != null) {
				if (myC.length() == 0) {
					continue;
				}
				if (sNum == 40) {
					throw new MoreStudentsException();
				}
				StringTokenizer myTok = new StringTokenizer(myC);
				Integer[] myS = new Integer[5];
				stu[sNum] = new Student();
				for (int i = 0; myTok.hasMoreTokens(); i++) {
					String someT = myTok.nextToken();
					int myScores, SID;
					if (i == 0) {
						SID = Integer.parseInt(someT);
						stu[sNum].setSID(SID);
					} else {
						myScores = Integer.parseInt(someT);
						myS[i - 1] = myScores;
					}
				}
				stu[sNum].setScores(myS);
				sNum++;
			}
			myR.close();
		} catch (MoreStudentsException e) {
			e.printProblems();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Student[] student = new Student[sNum];
		for (int i = 0; i < sNum; i++) {
			student[i] = stu[i];
		}
		return student;
	}
}
