/**
 * 
 */
package edu.wou.cs260.SpellChecker;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * @author Nathan Stark
 *
 */
public class DLList<T> implements List<T>, CompareCount, Queue<T> {

	private DLLNode head = null;
	private DLLNode tail = null;
	private int size = 0;
	private int compCount;
	
	class DLLNode {
		// fields
		DLLNode prev;
		T data;
		DLLNode next;

		// methods
		// 3 constructors
		DLLNode() {
			this(null, null, null);
		}

		DLLNode(T d) {
			this(null, d, null);
		}

		DLLNode(DLLNode p, T d, DLLNode n) {
			prev = p;
			data = d;
			next = n;
		}
	}
	
	class DLLIterator implements Iterator<T>{
		
		DLLNode curr = head;
		
		@Override
		public boolean hasNext() {
			
			return (curr != null);
		}

		@Override
		public T next() {
			if(curr == null)
				throw new NoSuchElementException("This does not work.");
			T temp = curr.data;
			curr = curr.next;
			return temp;
		}
		
	}

	/**
	 * 
	 */
	public DLList() {
		
	}

	/* (non-Javadoc)
	 * @see edu.wou.cs260.SpellChecker.CompareCount#getLastCompareCount()
	 */
	@Override
	public int getLastCompareCount() {
		
		return compCount;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * 
	 */
	private void addAtEnd(T item)
	{
		DLLNode temp = new DLLNode(item);
		
		temp.prev = tail;
		tail.next = temp;
		tail = temp;
		
		size++;
	}
	
	/**
	 * 
	 */
	private void addEmpty(T item)
	{
		DLLNode temp = new DLLNode(item);
		
		tail = temp;
		head = temp;
		
		size++;
		
	}
	
	/**
	 * 
	 */
	private void addAtFront(T item)
	{
		DLLNode temp = new DLLNode(item);
		
		temp.next = head;
		head.prev = temp;
		head = temp;
		
		size++;
	}
	
	/**
	 * 
	 */
	private void addMiddle(T item, DLLNode after)
	{
		DLLNode temp = new DLLNode(item);
		
		temp.next = after;
		temp.prev = after.prev;
		temp.prev.next = temp;
		after.prev = temp;
		
		size++;
	}
	
	/**
	 * 
	 */
	private DLLNode findNode(int pos)
	{
		if((pos < 0)||(pos >= size)) 
			throw new IndexOutOfBoundsException("Bad index.");
		
		int i = 0;
		DLLNode curr = head;
		while(i < pos)
		{
			curr = curr.next;
			i++;
		}
		return curr;
	}
	
	private DLLNode findNode(T item)
	{
		if(item == null) 
			throw new NullPointerException("nulls not allowed.");
		
		DLLNode curr = head;
		while(curr != null)
		{
			compCount++;
			if(curr.data.equals(item))
				return curr;
			curr = curr.next;
		}
		return null;
	}

	/**
	 * Add new Item to the double linked list.
	 * @return true always.
	 */
	@Override
	public boolean add(T e) 
	{
		if(e == null) 
			throw new NullPointerException("Do not allow.");
		if(isEmpty()){
			addEmpty(e);
		}else {
			addAtEnd(e);
		}
		return true;
	}

	@Override
	public void add(int index, T e) 
	{
		if(e == null)
			throw new NullPointerException("Can't add a null");
		if((index < 0))
			if(index >= size)
				throw new IndexOutOfBoundsException("Bad index");
		if(isEmpty()){
			addEmpty(e);
		}else if(index == size){
			addAtEnd(e);
		}else if(index == 0){
			addAtFront(e);
		}else
			addMiddle(e, findNode(index));
	}

	@Override
	public boolean addAll(Collection<? extends T> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(int arg0, Collection<? extends T> arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		
		head = null;
		tail = null;
		size = 0;
	}

	@Override
	public boolean contains(Object t) {
		
		compCount = 0;
		
		@SuppressWarnings("unchecked")
		T obj = (T)t;
		compCount++;
		if(findNode(obj) != null){
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public T get(int pos) 
	{
		return findNode(pos).data;
	}

	@Override
	public int indexOf(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		
		return (size == 0);
	}

	@Override
	public Iterator<T> iterator() {
		
		return new DLLIterator();
	}

	@Override
	public int lastIndexOf(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator<T> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<T> listIterator(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object t) 
	{
		if(t == null){
			throw new NullPointerException("null not allowed.");
		}
		int i = 0;
		DLLNode temp = head;
		while(i < size){
			if(temp.data.equals(t)){
				remove(i);//I don't know why my removes keep throwing NullPointerExceptions??
				return true;
			}
			temp = temp.next;
			i++;
		}
		return false;
	}

	@Override
	public T remove(int pos) 
	{
		DLLNode temp = findNode(pos);
		if((pos == 0)&&(size == 1)){
			clear();
			return temp.data;
		}else if(pos == 0){
			head = head.next;
			head.prev = null;
			size--;
			return temp.data;
		}else if(pos == (size -1)){
			tail = tail.prev;
			tail.next = null;
			size--;
			return temp.data;
		}else{
			temp.next.prev = temp.prev;
			temp.prev.next = temp.next;
			size--;
			return temp.data;
		}
	}


	@Override
	public T set(int arg0, T arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() 
	{
		return size;
	}

	@Override
	public List<T> subList(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T element() 
	{
		if(isEmpty())
			throw new NoSuchElementException("The list is empty.");
		DLLNode curr = findNode(0);
		return curr.data;
	}

	@Override
	public T peek() 
	{
		if(size == 0){
			return null;
		}
		DLLNode curr = findNode(0);
		return curr.data;
	}

	@Override
	public T poll() 
	{
		if(isEmpty()){
			return null;
		}else{
			return remove();
		}
	}

	@Override
	public T remove() 
	{
		if(isEmpty())
			throw new NoSuchElementException("The list is empty.");
		
		DLLNode curr = findNode(0);
		remove(0);
		return curr.data;
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
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("hiding")
	@Override
	public <T> T[] toArray(T[] arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean offer(T e) 
	{
		return add(e);
	}
}
