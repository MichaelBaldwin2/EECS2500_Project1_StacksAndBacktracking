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
	 * The size of each group.
	 */
	private static int groupSize;
	/**
	 * The first group of people that are to be paired to the second group.
	 */
	private static List<Person> groupA = new ArrayList<>();
	/**
	 * The second group of people that are to be paired to the first group.
	 */
	private static List<Person> groupB = new ArrayList<>();
	/**
	 * The stack of current pairings.
	 * Only used when attempting backtracking,
	 * not used for the Gale-Shapely algorithm.
	 */
	private static Stack<Pairing> pairings = new Stack<>();

	/**
	 * The main method. File loading, data creation, algorithm running, and solution outputting are all performed here.
	 *
	 * @param args Command-line arguments, not used in this project.
	 */
	public static void main(String[] args) {
		//First we load the file into a BufferedReader, grab the size of the groups, and load up each person.
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(testDataFilePath))) {
			groupSize = Integer.parseInt(reader.readLine());
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


		/* Now on to the actual algorithms.
		The first uses backtracking to find a stable pairing for all people.
		NOTE: I don't need to check for stability because the Gale-Shapely algorithm GUARANTEES a stable solution
		and the backtracking algorithm will, at worst, run every single possible solution. Which means that it will
		eventually find the Gale-Shapely solution, hence it WILL find a stable solution.
		 The process is a follows:
		  1. Loop over the people in the first group ,they are the proposers.
		  2. For each person, if they are NOT already paired, then start looping over their preferences (They are already in order).
		  3. For each preference:
		        a. If the pref is unpaired, then pair the person and their preference and push that pair to the stack.
		        b. If the pref is already paired, we have to backtrack.
		        ONLY unpair the person, reset their index counter (needed for starting at their top pref again),
		        unpair the previous person (top of the stack, which would be the last step),
		        pop the top of the stack, and move two steps back in the person counter to try again.

		The second algorithm is the Gale-Shapely algorithm found at <https://en.wikipedia.org/wiki/Stable_marriage_problem>.
			It's simple, fast, and specific to the stable marriage problem.
		  */

		//Backtracking -- START
		if (useBackTracking) {
			for (int i = 0; i < groupSize; i++) {
				Person iPerson = groupA.get(i);
				while (!iPerson.isPaired()) {
					Person iPref = iPerson.getNextPref();
					if (!iPerson.preferOverCurrent(iPref) || !iPref.preferOverCurrent(iPerson))
						continue; //No need to check pairing with this pref, they hate each other :)
					if (!iPref.isPaired())
						pairings.push(new Pairing(iPerson, iPref));
					else {
						iPerson.pairWith(null); //Unpair
						iPerson.resetPrefIndex();
						pairings.top().personA.pairWith(null); //Unpair
						pairings.pop();
						i -= 2;
						break; //Break out of while loop
					}
				}
			}

			//Print off the pairs.
			for (int i = 0; i < pairings.getRawData().size(); i++) {
				Pairing iPair = pairings.getRawData().get(i);
				System.out.println("Team " + i + ": " + iPair.personA.getName() + " and " + iPair.personB.getName());
				}
		} //Backtracking -- END

		//Gale-Shapely Algorithm -- START
		else {
			while (!isEveryonePaired()) {
				for (Person iPerson : groupA) {
					for (Person iPref : iPerson.getSortedPrefs()) {
						if (!iPerson.isPaired()) {
							if (iPref.preferOverCurrent(iPerson)) {
								iPref.pairWith(iPerson);
								iPerson.pairWith(iPref);
							}
						}
					}
				}
			}

			//Print off the pairs.
			for (int i = 0; i < groupA.size(); i++) {
				Person iPerson = groupA.get(i);
				System.out.println("Team " + i + ": " + iPerson.getName() + " and " + iPerson.getCurrentFiance().getName());
			}
		} //Gale-Shapely -- END
	}

	/**
	 * The Gale-Shapely algorithm is guaranteed to find a stable solution, so there is no need to do actual stability checking.
	 * Instead I'm just checking to see if everyone has paired off, if so, it is stable.
	 * NOTE 2: Using this method, the algorithm only takes 6 steps to find a stable solution with the provided TestData file.
	 *
	 * @return True if pairing is finished, false otherwise.
	 */
	private static boolean isEveryonePaired() {
		for (Person iPerson : groupA)
			if (!iPerson.isPaired())
				return false;
		return true;
	}
}
