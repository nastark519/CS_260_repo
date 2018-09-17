/**
 * 
**/

package edu.wou.cs260.SpellChecker;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Set;

import edu.wou.cs260.SpellChecker.DLList.DLLNode;

/**
*Lad # 3 
*@author Nathan Stark
*@version CS 260 Lab # 3, 2/22/2017
 */
public class BSTreeSet<T extends Comparable<T>> implements Set<T>, CompareCount {

	
	//fields
	protected Node root;
	protected int size;
	protected int compCount; 
	
	class Node {
		// fields
		T item;
		int height;
		Node lChild;
		Node rChild;

		// methods
		// 3 constructors
		Node() {
			this(null, null, null);
		}

		Node(T item) {
			this(null, item, null);
		}

		Node(Node lChild, T item, Node rChild) {
			this.lChild = lChild;
			this.item = item;
			this.rChild = rChild;
			height = 0;
		}
	}
	
	class BSTreeSetIterator implements Iterator<T>{
		
		Queue<Node> myQ = new DLList<Node>();

		BSTreeSetIterator(){
			myQ.add(root);
		}
		
		@Override
		public boolean hasNext() {
			
			return (!myQ.isEmpty());
		}

		@Override
		public T next() {
			
			if(myQ.isEmpty())
				throw new NoSuchElementException("No more items.");
			Node temp = myQ.remove();
			
			if(temp.lChild != null){
				myQ.add(temp.lChild);
			}
			if(temp.rChild != null){
				myQ.add(temp.rChild);
			}
			return temp.item;
		}
		
	}

	/**
	 * 
	 */
	public BSTreeSet() {
		// TODO Auto-generated constructor stub
	}
	
	protected int getHeight(Node n){
		if(n == null){
			return -1;
		}else{
			return n.height;
		}
	}
	
	protected void fixHeight(Node n){
		if(n == null){ return; }
		else{
			n.height = 1 + Math.max(getHeight(n.lChild),getHeight(n.rChild));
		}
			
	}
	
	protected Node findNode(Node cRoot, T e){
		if(e == null) throw new NullPointerException("Can't use null.");
		Node curr = new Node(e);
		if(curr.equals(cRoot)){
			return curr;
		}else if(curr.item.compareTo(cRoot.item) > 0){
			cRoot = cRoot.lChild;
			findNode(cRoot, e);
		}else {
			cRoot = cRoot.rChild;
			findNode(cRoot, e);
		}
		return null;
	}
	
	protected Node addHelper(Node cRoot, T item){
		if(cRoot == null){
			size ++;
			return new Node(item);
		}else if(cRoot.item.compareTo(item) > 0){//go left
			cRoot.lChild = addHelper(cRoot.lChild, item);
		}else {
			cRoot.rChild = addHelper(cRoot.rChild, item);
		}
		return cRoot;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLastCompareCount() {
		
		return compCount;
	}

	@Override
	public boolean add(T e) {
		if(e == null) throw new NullPointerException("Can't add null.");
		root = addHelper(root, e);
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		root = null;
		size = 0;
	}

	@Override
	public boolean contains(Object o) {
		compCount = 0;
		@SuppressWarnings("unchecked")
		T obj = (T)o;
		return containsHelper(root, obj);
	}
	
	protected boolean containsHelper(Node cRoot, T item){
		
		compCount ++;
		if(cRoot == null){
			return false;
		}else if(cRoot.item.equals(item)){
			return true;
		}else if(cRoot.item.compareTo(item) > 0){//go left
			return containsHelper(cRoot.lChild, item);
		}else {
			return containsHelper(cRoot.rChild, item);
		}
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		return (size == 0);
	}

	@Override
	public Iterator<T> iterator() {
		
		return new BSTreeSetIterator();
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
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

	@SuppressWarnings("hiding")
	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

}
