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
	/**
	 * The path to the TestData file. Change if you want,
	 * but it currently points to a file named "Project1TestData.txt" that is located at the project root.
	 */
	private static String testDataFilePath = System.getProperty("user.dir") + "/Project1TestData.txt";
	/**
	 * Should the code run the Gale-Shapely Algorithm, or the BackTracking solution?
	 * They might present different stable pairs, if two exist.
	 */
	private static boolean useBackTracking = true;
	/**
	 * The size of a single group.
	 */
	private static int groupSize;
	/**
	 * The first group of people that are to paired to the second group.
	 */
	private static List<Person> groupA = new ArrayList<>();
	/**
	 * The second group of people that are to paired to the first group.
	 */
	private static List<Person> groupB = new ArrayList<>();
	/**
	 * The stack of current pairings.
	 * Only used when attempting backtracking,
	 * not used for the Gale-Shapely algorithm.
	 */
	private static Stack<Person> engagements = new Stack<>();

	/**
	 * The main method. File loading, data creation, algorithm running, and solution outputting are all performed here.
	 *
	 * @param args Command-line arguments, not used in this project.
	 */
	public static void main(String[] args) {
		//First we load the file into a BufferedReader, grab the size of the groups, and load up each person.
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(testDataFilePath))) {
			int groupSize = Integer.parseInt(reader.readLine());
			for (int j = 0; j < groupSize; j++)
				groupA.add(new Person(reader, groupSize));
			for (int j = 0; j < groupSize; j++)
				groupB.add(new Person(reader, groupSize));
		} catch (IOException exc) {
			System.out.println("IOException encountered: " + exc);
			return;
		}

		//Now convert each value to a name and sort them per person
		for (Person iPerson : groupA)
			iPerson.convertAndSort(groupB);
		for (Person iPerson : groupB)
			iPerson.convertAndSort(groupA);

		//Now on to the actual algorithm(s)
		if (useBackTracking) {
			while (!areMarriagesStable()) {
				for(int i = 0; i < groupA.size(); i++) {

				}
			}
		} else {
			while (!areMarriagesStable()) {
				for (Person iPerson : groupA) {
					if (!iPerson.isPaired()) {
						Person nextPref = iPerson.getNextPref();
						if (nextPref.isPreferredOver(iPerson)) {
							iPerson.pairWith(nextPref);
							nextPref.pairWith(iPerson);
						}
					}
				}
			}
		}

		for (Person iPerson : groupA) {
			System.out.println(iPerson.getName() + " - " + iPerson.getCurrentFiance().getName());
		}
	}

	/**
	 * Have we achieved a stable marriage?
	 * NOTE: Because I'm using the Gale-Shapely algorithm, it is guaranteed to make a stable pairing, so there is no need to do actual stability checking.
	 * Instead I'm just checking to see if everyone has paired off, if so, it is stable.
	 * NOTE 2: Using this method, the algorithm only takes 6 steps to find a stable solution with the provided TestData file.
	 *
	 * @return True if pairing is finished, false otherwise.
	 */
	private static boolean areMarriagesStable() {
		for (Person iPerson : groupA)
			if (!iPerson.isPaired())
				return false;
		return true;
	}
}
