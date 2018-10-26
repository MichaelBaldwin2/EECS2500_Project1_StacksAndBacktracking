/*
  Written by: Michael Baldwin
 */

import java.util.ArrayList;
import java.util.List;

/**
 * My version of a stack. It uses a list instead of an array to hold the data. It's resizable, and it can return the raw data for iteration.
 */
public class Stack<T> {
	/**
	 * The backing list of data that actually contains the objects for this stack.
	 */
	private List<T> data = new ArrayList<>();

	/**
	 * Pushes a new item onto the top of the stack.
	 *
	 * @param obj The item to push onto the top of the stack.
	 */
	public void push(T obj) {
		data.add(obj);
	}

	/**
	 * Removes the top of the stack (if not empty).
	 */
	public void pop() {
		if (isEmpty())
			return; //throw new StackUnderflowException(); This exception is not included with the jdk, so I'm not actually throwing it.
		data.remove(data.size() - 1);
	}

	/**
	 * @return The object at the top of the stack (if not empty). Same as top().
	 */
	public T peek() {
		if (isEmpty())
			return null;
		return data.get(data.size() - 1);
	}

	/**
	 * @return The object at the top of the stack (if not empty). Same as peek().
	 */
	public T top() {
		return peek();
	}

	/**
	 * Gets the size of the stack.
	 *
	 * @return An integer size, or 0(zero) if empty.
	 */
	public int size() {
		return data.size();
	}

	/**
	 * Is this stack empty.
	 *
	 * @return True if empty, false if not.
	 */
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * @return The backing list of data for this stack. Only useful for iteration, like printing contents to the console.
	 */
	public List<T> getRawData() {
		return data;
	}
}
