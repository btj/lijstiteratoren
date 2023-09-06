package lijstiteratoren.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import lijstiteratoren.Arraylijst;
import lijstiteratoren.BerekendeLijst;
import lijstiteratoren.Lijstiterator;

class LijstiteratorenTest {

	@Test
	void test() {
		Arraylijst mijnArraylijst = new Arraylijst();
		assertEquals(Set.of(), mijnArraylijst.getIteratoren());
		assertArrayEquals(new int[] {}, mijnArraylijst.getElements());
		
		mijnArraylijst.add(10);
		assertEquals(Set.of(), mijnArraylijst.getIteratoren());
		assertArrayEquals(new int[] {10}, mijnArraylijst.getElements());
		
		mijnArraylijst.add(20);
		assertEquals(Set.of(), mijnArraylijst.getIteratoren());
		assertArrayEquals(new int[] {10, 20}, mijnArraylijst.getElements());
		
		Lijstiterator i1 = mijnArraylijst.maakLijstiterator();
		assertSame(mijnArraylijst, i1.getLijst());
		assertEquals(0, i1.getIndex());
		assertEquals(Set.of(i1), mijnArraylijst.getIteratoren());
		assertEquals(10, i1.next());
		assertEquals(1, i1.getIndex());
		assertEquals(20, i1.next());
		assertEquals(2, i1.getIndex());
		
		i1.deactiveer();
		assertNull(i1.getLijst());
		assertEquals(Set.of(), mijnArraylijst.getIteratoren());
		
		mijnArraylijst.removeLast();
		assertArrayEquals(new int[] {10}, mijnArraylijst.getElements());
		
		BerekendeLijst mijnBerekendeLijst = new BerekendeLijst();
		assertEquals(Set.of(), mijnBerekendeLijst.getIteratoren());
		assertArrayEquals(new int[] {}, mijnBerekendeLijst.getElements());
		
		mijnBerekendeLijst.add(10);
		assertEquals(Set.of(), mijnBerekendeLijst.getIteratoren());
		assertArrayEquals(new int[] {10}, mijnBerekendeLijst.getElements());
		
		mijnBerekendeLijst.add(11);
		assertEquals(Set.of(), mijnBerekendeLijst.getIteratoren());
		assertArrayEquals(new int[] {10, 11}, mijnBerekendeLijst.getElements());
		
		Lijstiterator i2 = mijnBerekendeLijst.maakLijstiterator();
		assertSame(mijnBerekendeLijst, i2.getLijst());
		assertEquals(0, i2.getIndex());
		assertEquals(Set.of(i2), mijnBerekendeLijst.getIteratoren());
		assertEquals(10, i2.next());
		assertEquals(1, i2.getIndex());
		assertEquals(11, i2.next());
		assertEquals(2, i2.getIndex());
		
		i2.deactiveer();
		assertNull(i2.getLijst());
		assertEquals(Set.of(), mijnBerekendeLijst.getIteratoren());
		
		mijnBerekendeLijst.removeLast();
		assertArrayEquals(new int[] {10}, mijnBerekendeLijst.getElements());
		
		Arraylijst jouwArraylijst = new Arraylijst();
		jouwArraylijst.add(10);
		
		BerekendeLijst jouwBerekendeLijst = new BerekendeLijst();
		jouwBerekendeLijst.add(10);
		
		assertTrue(mijnArraylijst.heeftZelfdeGedragAls(jouwArraylijst));
		assertFalse(mijnArraylijst.heeftZelfdeGedragAls(mijnBerekendeLijst));
		assertTrue(mijnBerekendeLijst.heeftZelfdeGedragAls(jouwBerekendeLijst));
		assertFalse(mijnBerekendeLijst.heeftZelfdeGedragAls(mijnArraylijst));
		
		mijnArraylijst.add(-100);
		mijnArraylijst.add(1000);
		mijnArraylijst.add(-10000);
		
		assertFalse(mijnArraylijst.heeftZelfdeGedragAls(jouwArraylijst));
		
		{
			ArrayList<Integer> result = new ArrayList<>();
			
			for (Iterator<Integer> i = mijnArraylijst.reverseIterator(); i.hasNext();)
				result.add(i.next());
			
			assertEquals(List.of(-10000, 1000, -100, 10), result);
		}
		
		{
			ArrayList<Integer> result = new ArrayList<>();
			
			mijnArraylijst.forEachElementAtOddIndex(e -> result.add(e));
			
			assertEquals(List.of(-100, -10000), result);
		}
		
		assertArrayEquals(new int[] {100, 100, 10000, 10000},
				mijnArraylijst.getDuplicatedNegatedNegativeElementsStream().toArray());
	}

}
