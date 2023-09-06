package lijstiteratoren;

import logicalcollections.LogicalSet;

/**
 * @invar | getLijst() == null ||
 *        | getLijst().getIteratoren().contains(this) &&
 *        | 0 <= getIndex() && getIndex() <= getLijst().getElements().length
 */
public class Lijstiterator {

	/**
	 * @invar | 0 <= index
	 * @invar | lijst == null || lijst.iteratoren.contains(this) && index <= lijst.getSizeInternal()
	 * 
	 * @peerObject
	 */
	Lijst lijst;
	int index;
	
	/**
	 * @peerObject
	 */
	public Lijst getLijst() { return lijst; }
	
	/**
	 * @throws IllegalStateException | getLijst() == null
	 */
	public int getIndex() {
		if (lijst == null)
			throw new IllegalStateException();
		return index;
	}
	
	Lijstiterator(Lijst lijst) {
		this.lijst = lijst;
		lijst.iteratoren.add(this);
	}
	
	/**
	 * @throws IllegalStateException | getLijst() == null
	 * @mutates_properties | getLijst(), getLijst().getIteratoren()
	 * @post | getLijst() == null
	 * @post | old(getLijst()).getIteratoren().equals(LogicalSet.minus(old(getLijst().getIteratoren()), this))
	 */
	public void deactiveer() {
		if (lijst == null)
			throw new IllegalStateException();
		lijst.iteratoren.remove(this);
		lijst = null;
	}
	
	/**
	 * @throws IllegalStateException | getLijst() == null
	 * @throws IllegalStateException | getIndex() == getLijst().getElements().length
	 * @mutates_properties | getIndex()
	 * @post | getIndex() == old(getIndex()) + 1
	 * @post | result == getLijst().getElements()[old(getIndex())]
	 */
	public int next() {
		if (lijst == null || index == lijst.getSizeInternal())
			throw new IllegalStateException();
		return lijst.getElements()[index++];
	}
	
}
