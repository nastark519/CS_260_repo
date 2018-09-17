package edu.wou.cs260.SpellChecker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
/**
 * @author Nathan Stark
 *
 * CS 260 Lab 5a 03/13/2017
 */

public class OpenChainHashSet<E> implements Set<E>, CompareCount {
	//fields
	private DLList<E>[] hashTable;
	private int tableSize;
	private int size;
	private int compCount;
	
	@SuppressWarnings("unchecked")
	public OpenChainHashSet(int tableSize) {
		hashTable = (DLList<E>[]) new DLList[tableSize];
		this.tableSize = tableSize;
		size = 0;
	}
	class hashIterator implements Iterator<E>{
		private DLList<E> curr;
		private int tableInc;
		private Iterator<E> it;

		public hashIterator() {
			tableInc = 0;
			curr = hashTable[tableInc];
			it = curr.iterator();
		}

		@Override
		public boolean hasNext() {
			if (!it.hasNext()) {
				tableInc ++;
				if (tableInc == hashTable.length) {
					return false;
				}else{
					curr = hashTable[tableInc];
					it = curr.iterator();
					return hasNext();
				}
			}
			return (curr != null);
		}

		@Override
		public E next() {
			if ((curr == null) && (tableInc > hashTable.length))
				throw new NoSuchElementException("This does not work.");
			if (!(it.hasNext())) {
				tableInc++;
				curr = hashTable[tableInc];
				return next();
			}
			return it.next();
		}
	}

	public static void main(String[] args) {
		
	}

	@Override
	public int getLastCompareCount() {
		
		return compCount;
	}

	@Override
	public boolean add(E item) {
		if (item == null){
			throw new NullPointerException("null not allowed.");
		}
		int index = (Math.abs(item.hashCode()) % tableSize);
		if ((hashTable[index] instanceof DLList )&&(hashTable[index].contains(item))) {
			return false;
		} else {
			if (hashTable[index] == null) {
				hashTable[index] = new DLList<E>();
				size++;
				return hashTable[index].add(item);
			} else {
				size++;
				return hashTable[index].add(item);
			}
		}
	}

	@Override
	public boolean addAll(Collection<? extends E> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		Arrays.fill(hashTable, null);
		size = 0;
	}

	@Override
	public boolean contains(Object item) {
		compCount = 0;
		if(item == null) throw new NullPointerException("null not allowed.");
		@SuppressWarnings("unchecked")
		E obj = (E)item;
		int index = (Math.abs(obj.hashCode()) % tableSize);
		compCount ++;
		if(!(hashTable[index] == null)){
			compCount += hashTable[index].getLastCompareCount();
			return hashTable[index].contains(obj);
		}else{
			return false;
		}
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		
		return (size == 0);
	}

	@Override
	public Iterator<E> iterator() {
		
		return new hashIterator();
	}

	@Override
	public boolean remove(Object item) {
		if (item == null)
			throw new NullPointerException("null not allowed.");
		@SuppressWarnings("unchecked")
		E obj = (E) item;
		int index = (Math.abs(obj.hashCode()) % tableSize);
		if ((!hashTable[index].contains(obj))||(hashTable[index] == null)) {
			return false;
		} else {
			size = size - 1;
			return hashTable[index].remove(obj);
		}
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		
		return size;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
