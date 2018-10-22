/*
  Written by: Michael Baldwin
 */

import java.util.*;

public class Stack<T> {
	private List<T> data = new ArrayList<>();

	public void push(T obj) {
		data.add(obj);
	}

	public T pop()
	{
		if(isEmpty())
			return null;
		int lastIndex = data.size() - 1;
		T obj = data.get(lastIndex);
		data.remove(lastIndex);
		return obj;
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
