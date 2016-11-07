import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class PrintAnimal {

	static ObjectInputStream myCurrIn = null;
	static File myF1 = new File("name.dat");
	static String myListofTheAnima = null;

	public static void main(String[] args) throws FileNotFoundException,
			IOException, ClassNotFoundException {

		try {
			myCurrIn = new ObjectInputStream(new FileInputStream(myF1));

		} catch (EOFException e) {
			e.printStackTrace();
		}

		try {
			while (true) {
				myListofTheAnima = myCurrIn.readUTF();
				System.out.println(myListofTheAnima);
			}
		} catch (EOFException e) {

		}

	}

}
