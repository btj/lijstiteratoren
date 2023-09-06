package lijstiteratoren;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import logicalcollections.LogicalSet;

/**
 * @invar | getIteratoren().stream().allMatch(i -> i.getLijst() == this)
 */
public abstract class Lijst {
	
	/**
	 * @invar | iteratoren != null
	 * @invar | iteratoren.stream().allMatch(i -> i != null && i.lijst == this)
	 * 
	 * @representationObject
	 * @peerObjects
	 */
	Set<Lijstiterator> iteratoren = new HashSet<>();
	
	abstract int getSizeInternal();
	
	/**
	 * @post | result != null
	 * @post | result.stream().allMatch(i -> i != null)
	 * @creates | result
	 * @peerObjects
	 */
	public Set<Lijstiterator> getIteratoren() {
		return Set.copyOf(iteratoren);
	}
	
	/**
	 * @post | result != null
	 * @creates | result
	 */
	public abstract int[] getElements();
	
	Lijst() {}
	
	/**
	 * @may_throw UnsupportedOperationException | true
	 * @mutates_properties | getElements()
	 * @post | getElements().length == old(getElements()).length + 1
	 * @post | Arrays.equals(getElements(), 0, old(getElements()).length, old(getElements()), 0, old(getElements()).length)
	 * @post | getElements()[getElements().length - 1] == element
	 */
	public abstract void add(int element);
	
	/**
	 * @throws IllegalStateException | !getIteratoren().isEmpty()
	 * @may_throw UnsupportedOperationException | true
	 * @pre | 0 < getElements().length
	 * @mutates_properties | getElements()
	 * @post | getElements().length == old(getElements()).length - 1
	 * @post | Arrays.equals(getElements(), 0, getElements().length, old(getElements()), 0, getElements().length)
	 */
	public abstract void removeLast();
	
	/**
	 * @mutates_properties | getIteratoren()
	 * @post | getIteratoren().equals(LogicalSet.plus(old(getIteratoren()), result))
	 * @post | !old(getIteratoren()).contains(result)
	 * @post | result.getIndex() == 0
	 */
	public Lijstiterator maakLijstiterator() {
		return new Lijstiterator(this);
	}
	
	public abstract boolean heeftZelfdeGedragAls(Lijst andere);

}
