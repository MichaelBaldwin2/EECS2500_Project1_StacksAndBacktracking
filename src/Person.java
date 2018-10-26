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
	private ArrayList<Person> sortedPrefs;
	/**
	 * The person this person is currently paired with.
	 */
	private Person currentFiance;

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

		//Read and split the preferences at each 'tab' character.
		//If that doesn't work, split at spaces (which shouldn't work, unless TestData has been updated)
		String prefString = reader.readLine();
		String[] tempPrefList = prefString.split("\t");
		if (tempPrefList.length <= 1)
			tempPrefList = prefString.split(" ");

		for (String iPref : tempPrefList) {
			int value = Integer.parseInt(iPref);
			int clampedValue = value < 0 ? 0 : value >= groupSize ? groupSize - 1 : value; //Clamp the value to account for the value of '4' in the TestData
			prefValues.add(clampedValue);
			sortedPrefs.add(null);
		}
	}

	/**
	 * This method will convert all the preference values to actual Person objects,
	 * and will also set them in the correct order, with highest preference first.
	 *
	 * @param otherGroup The list of Persons in the other group.
	 */
	public void convertAndSort(List<Person> otherGroup) {
		for (int i = 0; i < otherGroup.size(); i++) {
			sortedPrefs.set(prefValues.get(i), otherGroup.get(i));
		}
	}

	/**
	 * @return The name of this person.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return The current fiance, or null if there is none.
	 */
	public Person getCurrentFiance() {
		return currentFiance;
	}

	public List<Person> getSortedPrefs() {
		return sortedPrefs;
	}

	/**
	 * Is this person currently paired.
	 *
	 * @return True if paired, false otherwise.
	 */
	public boolean isPaired() {
		return currentFiance != null;
	}

	/**
	 * Gets a preference in the sorted preference list at the specified index.
	 * @param i The index to retrieve.
	 * @return The preference at the index.
	 */
	public Person getPrefAtIndex(int i) {
		return sortedPrefs.get(i);
	}

	/**
	 * Checks if the supplied Person is a preferred match over their current partner (if there is one).
	 *
	 * @param person The person to check against.
	 * @return True if this new person is a better match, or false otherwise.
	 */
	public boolean preferresThisOverCurrent(Person person) {
		if (!isPaired()) //If this person is currently unattached then they definitely prefer the proposed person
			return true;
		return sortedPrefs.indexOf(person) < sortedPrefs.indexOf(currentFiance);
	}

	/**
	 * This method will pair this Person with the supplied Person. It will break any match between this person and their previous partner.
	 *
	 * @param person The new Person to match with.
	 */
	public void pairWith(Person person) {
		if (isPaired())
			currentFiance.currentFiance = null;
		currentFiance = person;
	}

	/**
	 * Unpairs this person. Same thing as just calling pairWith(null).
	 */
	public void unpair() {
		pairWith(null);
	}
}
