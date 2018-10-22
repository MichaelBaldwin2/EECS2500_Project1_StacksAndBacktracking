/*
  Written by: Michael Baldwin
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The Person class represents a single marry-able person.
 * It contains their name, preference values based upon the index locations of possible spouses,
 * and some helper functionality.
 */
public class Person {
	/**
	 * The name of this person.
	 */
	private String name;
	/**
	 * The list of preference values of possible spouses, where 0 is the highest preference, and 3 is the lowest.
	 */
	private List<Integer> prefValues;
	/**
	 * List of preferences as People instances.
	 */
	private List<Person> sortedPrefs;
	/**
	 * The person this person is currently paired with.
	 */
	private Person currentFiance;
	/**
	 * The current index of the sorted preferences that we are attempting to 'woo'
	 */
	private int sortedPrefIndex;

	/**
	 * Create a new person from the test data file.
	 *
	 * @param reader    BufferedReader
	 * @param groupSize Total number of people up for marriage, divided by two.
	 * @throws IOException If the reader throws an exception, we're just gonna pass it on up the chain.
	 */
	public Person(BufferedReader reader, int groupSize) throws IOException {
		name = reader.readLine();
		prefValues = new ArrayList<>(groupSize);
		sortedPrefs = new ArrayList<>(groupSize);

		String prefString = reader.readLine();
		String[] tempPrefList = prefString.split("\t");
		if(tempPrefList.length <= 1)
			tempPrefList = prefString.split(" ");

		for (String iPref : tempPrefList) {
			int value = Integer.parseInt(iPref);
			int clampedValue = value < 0 ? 0 : value >= groupSize ? groupSize - 1 : value; //I clamp the value to account for the value of '4' in the TestData
			prefValues.add(clampedValue);
			sortedPrefs.add(null);
		}
	}

	public void convertAndSortPrefs(List<Person> otherGroupNames)
	{
		for(int i = 0; i < otherGroupNames.size(); i++) {
			sortedPrefs.set(prefValues.get(i), otherGroupNames.get(i));
		}

	}

	public String getName() {
		return name;
	}

	public Person getNextPref()
	{
		return sortedPrefs.get(sortedPrefIndex++);
	}

	/**
	 * Is this person currently paired.
	 *
	 * @return True if paired, false otherwise.
	 */
	public boolean isPaired() {
		return currentFiance != null;
	}
}
