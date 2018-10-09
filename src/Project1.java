import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Stack;

public class Project1 {
	public static void main(String[] args) {
		//First we load the file, initialize the stacks, and build the lists
		Stack<Person> stackA = new Stack<>();
		Stack<Person> stackB = new Stack<>();
		Stack<Pairing> pairings = new Stack<>();
		int halfSize = 0;

		try (BufferedReader reader = Files.newBufferedReader(Paths.get(System.getenv("user.dir" + "/Project1TestData.txt")))) {
			if (reader.ready())
				halfSize = Integer.parseInt(reader.readLine());
			while(reader.ready()) {
				Person person = new Person(reader);
			}

		} catch (IOException exc) {
			System.out.println("IOException encountered: " + exc);
		}
	}
}
