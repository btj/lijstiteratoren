package lijstiteratoren;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Arraylijst extends Lijst {

	/**
	 * @invar | elements != null
	 * 
	 * @representationObject
	 */
	int[] elements = new int[0];
	
	/**
	 * @post | getIteratoren().isEmpty()
	 * @post | getElements().length == 0
	 */
	public Arraylijst() {}
	
	@Override
	int getSizeInternal() {
		return elements.length;
	}
	
	@Override
	public int[] getElements() {
		return elements.clone();
	}

	/**
	 * @mutates_properties | getElements()
	 * @post | getElements().length == old(getElements()).length + 1
	 * @post | Arrays.equals(getElements(), 0, old(getElements()).length, old(getElements()), 0, old(getElements()).length)
	 * @post | getElements()[getElements().length - 1] == element
	 */
	@Override
	public void add(int element) {
		elements = Arrays.copyOf(elements, elements.length + 1);
		elements[elements.length - 1] = element;
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
		elements = Arrays.copyOf(elements, elements.length - 1);
	}
	
	@Override
	public boolean heeftZelfdeGedragAls(Lijst andere) {
		return andere instanceof Arraylijst l &&
				Arrays.equals(elements, l.elements);
	}
	
	public Iterator<Integer> reverseIterator() {
		return new Iterator<>() {
			
			int index = elements.length;

			@Override
			public boolean hasNext() {
				return 0 < index;
			}

			@Override
			public Integer next() {
				return elements[--index];
			}
			
		};
	}
	
	public void forEachElementAtOddIndex(Consumer<? super Integer> consumer) {
		for (int i = 1; i < elements.length; i += 2)
			consumer.accept(elements[i]);
	}
	
	public IntStream getDuplicatedNegatedNegativeElementsStream() {
		return Arrays.stream(elements).flatMap(e -> e < 0 ? IntStream.of(-e, -e) : null);
	}
	
}
