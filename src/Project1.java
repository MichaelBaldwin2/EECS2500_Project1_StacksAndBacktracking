import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Project1 {
	public static void main(String[] args) {
		//First we load the file, initialize the stacks, and build the lists
		List<Person> groupA = new ArrayList<>();
		List<Person> groupB = new ArrayList<>();
		Stack<Pairing> pairings = new Stack<>();
		int numInGroup = 0;
		String testDataFilePath = System.getenv("user.dir") + "/Project1TestData.txt";

		try (BufferedReader reader = Files.newBufferedReader(Paths.get(testDataFilePath))) {
			if (reader.ready())
				numInGroup = Integer.parseInt(reader.readLine());
			int i = 0;
			while(reader.ready()) {
				Person person = new Person(reader);
			}

		} catch (IOException exc) {
			System.out.println("IOException encountered: " + exc);
		}
	}
}
