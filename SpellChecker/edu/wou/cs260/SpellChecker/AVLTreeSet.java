package edu.wou.cs260.SpellChecker;

import edu.wou.cs260.SpellChecker.BSTreeSet.Node;

/**
*Lad # 4 
*@author Nathan Stark
*@version CS 260 Lab # 4a, 2/27/2017
*/

public class AVLTreeSet<T extends Comparable<T>> extends BSTreeSet<T> {

	public AVLTreeSet() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected Node addHelper(Node cRoot, T item){
		if(cRoot == null){
			size ++;
			return new Node(item);
		}else if(cRoot.item.compareTo(item) > 0){//go left
			cRoot.lChild = addHelper(cRoot.lChild, item);
		}else {
			cRoot.rChild = addHelper(cRoot.rChild, item);
		}
		return rebal(cRoot);
	}
	
	//private mechanism& policy methods
	
	private Node rebal(Node n){
		int bv = balVal(n);
		if(n == null){return null;}
		else if((bv < 2)&&(bv>-2)){
			fixHeight(n);
			return n;
		}else if(bv < -1){
			if(balVal(n.lChild)>0){
				return dRR(n);
			}else{return sRR(n);}
		}else{
			if(balVal(n.rChild)<0){
				return dLR(n);
			}else{return sLR(n);}
		}
	}
	
	private Node dRR(Node n){
		n.lChild = sLR(n.lChild);
		return sRR(n);
	}
	
	private Node dLR(Node n){
		n.rChild = sRR(n.rChild);
		return sLR(n);
	}
	
	private Node sRR(Node n){
		Node temp = n.lChild;
		n.lChild = temp.rChild;
		temp.rChild = n;
		fixHeight(n);
		fixHeight(temp);
		return temp;
	}
	
	private Node sLR(Node n){
		Node temp = n.rChild;
		n.rChild = temp.lChild;
		temp.lChild = n;
		fixHeight(n);
		fixHeight(temp);
		return temp;
	}
	
	private int balVal(Node n){
		if(n == null){
			return 0;
		}else{
			return getHeight(n.rChild) - getHeight(n.lChild);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
