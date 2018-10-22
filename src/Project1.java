/*
  Written by: Michael Baldwin
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Project1 {
	private static String testDataFilePath = System.getProperty("user.dir") + "/Project1TestData.txt";
	private static List<Person> groupA = new ArrayList<>();
	private static List<Person> groupB = new ArrayList<>();
	private static Stack<Person> engagements = new Stack<>();

	public static void main(String[] args) {
		//First we load the file, initialize the stacks, and build the lists
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(testDataFilePath))) {
			int groupSize = Integer.parseInt(reader.readLine());
			for (int i = 0; i < 2; i++) { //Loop over both groups
				for (int j = 0; j < groupSize; j++) { //Loop over each member in the group
					Person person = new Person(reader, groupSize);
					if (i == 0) //If we are currently in the first outer loop iteration, add to groupA, else add to groupB
						groupA.add(person);
					else
						groupB.add(person);
				}
			}
		} catch (IOException exc) {
			System.out.println("IOException encountered: " + exc);
			return;
		}

		//Now convert each value to a name and sort them per person
		for (Person iPerson : groupA)
			iPerson.convertAndSortPrefs(groupB);
		for (Person iPerson : groupB)
			iPerson.convertAndSortPrefs(groupA);

		//Now on to the actual algorithm
		while (!areMarriagesStable()) {
			for (Person iPerson : groupA) {
				if (!iPerson.isPaired()) {
					Person nextPref = iPerson.getNextPref();
					if (nextPref.isPreferredOver(nextPref)) {
						iPerson.pairWith(nextPref);
						nextPref.pairWith(iPerson);
					}
				}
			}
		}//*/

		for (Person iPerson : groupA) {
			System.out.println(iPerson.getName() + " - " + iPerson.getCurrentFiance().getName());
		}
	}

	private static boolean areMarriagesStable() {
		for (Person iPerson : groupA)
			if (!iPerson.isPaired())
				return false;
		return true;
	}
}
