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
	 * The name of this person. Is really only used for textual output.
	 */
	private String name;
	/**
	 * The list of preference values of possible spouses, where 0 is the highest preference, and 3 is the lowest.
	 */
	private List<Integer> preferences;
	/**
	 * The index of the person this person is currently paired with.
	 */
	private int pairedWithIndex;

	/**
	 * Create a new person from the test data file.
	 * @param reader BufferedReader
	 * @param groupSize Total number of people up for marriage, divided by two.
	 * @throws IOException If the reader throws an exception, we're just gonna pass it on up the chain.
	 */
	public Person(BufferedReader reader, int groupSize) throws IOException {
		name = reader.readLine();
		preferences = new ArrayList<>(groupSize);

		String prefString = reader.readLine();
		String[] tempPrefList = prefString.split("\t");
		for (String iPref : tempPrefList) {
			preferences.add(Integer.parseInt(iPref));
		}
	}

	public String getName()
	{
		return name;
	}

	/**
	 * Gets the value of a specific partner at a specified index position.
	 * @param index The index position of the preference value to get.
	 * @return The preference value.
	 */
	public int getPrefValue(int index)
	{
		//I don't check if the parameter 'index' is within bounds because all I would do is throw an IndexOutOfBoundsException, which list.get(~) does anyways.
		return preferences.get(index);
	}

	/**
	 * Is this person currently paired.
	 * @return True if paired, false otherwise.
	 */
	public boolean isPaired() {
		return pairedWithIndex != -1;
	}
}
