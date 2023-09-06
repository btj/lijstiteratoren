package lijstiteratoren;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @invar | IntStream.range(1, getElements().length).allMatch(i ->
 *        |     getElements()[i - 1] < getElements()[i] &&
 *        |     getElements()[i - 1] + 1 == getElements()[i]
 *        | )
 */
public class BerekendeLijst extends Lijst {
	
	/**
	 * @invar | 0 <= size
	 */
	int start;
	int size;
	
	@Override
	int getSizeInternal() {
		return size;
	}
	
	@Override
	public int[] getElements() {
		int[] result = new int[size];
		for (int i = 0; i < size; i++)
			result[i] = start + i;
		return result;
	}
	
	/**
	 * @throws UnsupportedOperationException | getElements().length != 0 && element != (long)getElements()[0] + getElements().length
	 * @mutates_properties | getElements()
	 * @post | getElements().length == old(getElements()).length + 1
	 * @post | Arrays.equals(getElements(), 0, old(getElements()).length, old(getElements()), 0, old(getElements()).length)
	 * @post | getElements()[getElements().length - 1] == element
	 */
	@Override
	public void add(int element) {
		if (size == 0) {
			start = element;
		} else if (element != (long)start + size)
			throw new UnsupportedOperationException();
		size++;
	}
	
	/**
	 * @throws IllegalStateException | !getIteratoren().isEmpty()
	 * @pre | 0 < getElements().length
	 * @mutates_properties | getElements()
	 * @post | getElements().length == old(getElements()).length - 1
	 * @post | Arrays.equals(getElements(), 0, getElements().length, old(getElements()), 0, getElements().length)
	 */
	@Override
	public void removeLast() {
		if (!iteratoren.isEmpty())
			throw new IllegalStateException();
		size--;
	}
	
	@Override
	public boolean heeftZelfdeGedragAls(Lijst andere) {
		return andere instanceof BerekendeLijst l &&
				start == l.start &&
				size == l.size;
	}

}
