/*
  Written by: Michael Baldwin
 */

import java.util.*;

public class Stack<T> {
	private List<T> data = new ArrayList<>();

	public void push(T obj) {
		data.add(obj);
	}

	public T pop() {
		T obj = peek();
		data.remove(data.size() - 1);
		return obj;
	}

	public T peek() {
		if (isEmpty())
			return null;
		return data.get(data.size() - 1);
	}

	public T top() {
		return peek();
	}

	public int size() {
		return data.size();
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public Iterator<T> iterator() {
		return data.iterator();
	}

	public Object[] toArray() {
		return data.toArray();
	}

	public void clear() {
		data.clear();
	}
}
