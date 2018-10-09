import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class Person {
	private String name;
	private Stack<Integer> preferences = new Stack<>();
	private Person pairedWith;

	public Person(BufferedReader reader) throws IOException {
		String tempName = reader.readLine();
		String prefString = reader.readLine();
		this(tempName, prefString);
	}

	public Person(String name, String prefString) {
		this.name = name;
		List<String> tempPrefList = Arrays.asList(prefString.split(" "));
		Collections.reverse(tempPrefList); //We need to reverse the list so that the most preferred match is at the top of the stack
		for (String iPref : tempPrefList) {
			if (iPref == null || iPref.isEmpty())
				continue;
			preferences.push(Integer.parseInt(iPref));
		}
	}

	public boolean isPaired() {
		return pairedWith != null;
	}
}
