import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Project1 {
	public static void main(String[] args) {
		//First we load the file, initialize the stacks, and build the lists
		String testDataFilePath = System.getProperty("user.dir") + "/Project1TestData.txt";
		List<Person> groupA = new ArrayList<>();
		List<Person> groupB = new ArrayList<>();

		try (BufferedReader reader = Files.newBufferedReader(Paths.get(testDataFilePath))) {
			int groupSize = Integer.parseInt(reader.readLine());
			for (int i = 0; i < 2; i++) //Loop over both groups
				for (int j = 0; j < groupSize; j++) { //Loop over each member in the group
					Person person = new Person(reader, groupSize);
					if (i == 0) //If we are currently in the first outer loop iteration, add to groupA, else add to groupB
						groupA.add(person);
					else
						groupB.add(person);
				}

		} catch (IOException exc) {
			System.out.println("IOException encountered: " + exc);
		}
	}
}
