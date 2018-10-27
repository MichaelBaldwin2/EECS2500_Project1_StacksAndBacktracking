/*
  Written by: Michael Baldwin
 */

public class Pairing {
	public Person personA, personB;

	public Pairing(Person personA, Person personB) {
		this.personA = personA;
		this.personB = personB;
		personA.pairWith(personB);
		personB.pairWith(personA);
	}
}
