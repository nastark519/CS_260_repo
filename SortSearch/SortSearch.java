/**
 * 
 */
package edu.wou.cs260;

import java.util.Random;

/**
 *  SortSearch sets up an array of random numbers of size listsize to test several
 *  sorting and searching algorithms which will then be instrumented to count the
 *  number of comparisons done and the execution times at various list sizes
 *  
 *  @author Mitchel Fry & Nathan Stark
 *  @version CS260 Lab #1, 01/23/2017
 */
public class SortSearch
{
    private int listsize = 10;
    private final int RANGE = 100000;
    private int[ ] numList;
    private Random rand;
    //time fields
    private long isNanoTime = 0;
    
    //comparison countng fields
    private long isCompCount;

    /**
     * Default constructor, just initialize the numList array 
     */
    public SortSearch( )
    {
        numList = new int[ listsize];
    }

    /**
     * Non-default constructor, initialize the numList array to the given size
     * 
     * @param size of array to create
     */
    public SortSearch( int size)
    {
        listsize = size;
        numList = new int[ listsize];
    }

    /**
     * Fills the array with listsize integers
     */
    public void fillArray( )
    {
        rand = new Random( );

        for ( int i = 0; i < listsize; i++) {
            numList[ i] = rand.nextInt( RANGE);
        }
    }

    /**
     * Prints the entire array in indexed order
     */
    public void printArray( )
    {
        for ( int i = 0; i < numList.length; i++) {
            System.out.println( "Element " + i + ": " + numList[ i]);
        }
    }

    //================== Sorting Methods =======================      
    /**
     * Sorts the array using Insertion Sort logic
     */
    public void insertionSort( )
    {
isCompCount = 0;
    	
long nanoTime = getSystemTime();

        for ( int i = 1; i < numList.length; i++) {
            // insert numList[i] into numList[0:i-1]
            int j, t = numList[ i];

            for ( j = i - 1; j >= 0 && t < numList[ j]; j--) {
isCompCount++;
                numList[ j + 1] = numList[ j]; //shuffle the empty space
            }

            numList[ j + 1] = t; //assign t into the empty space
        }
isNanoTime = getSystemTime() - nanoTime;
    }
 
    /**
     * Sorts the array using Insertion Sort logic
     */
    public void selectionSort( )
    {
        int t;

        for ( int i = 0; i < numList.length; i++) {
            t = i; //Index of the smallest element in this pass

            for ( int j = i; j < numList.length; j++) {
isCompCount++;//for if statements comparisons happen before the statement.
                if ( numList[ j] < numList[ t]) {
                    t = j;
                }
            }
            swap( i, t); //swap the values at these positions
        }
    }

 
    /**
     * Sorts the array using Bubble Sort logic (bad way to sort)
     */
    public void bubbleSort( )
    {

        for ( int out = numList.length - 1; out > 0; out--) { // outer loop (backward)
            for ( int in = 0; in < out; in++) { // inner loop (forward)
                if ( numList[ in] > numList[ in + 1]) { // out of order?
                    swap( in, in + 1);
                }
            }
        }
    }



    //================== Search Methods =======================    
    /**
     * A shell method for your linear search logic
     * 
     * @param searchNum the value to search for in the array 
     * @return returns true is searchNum is found in the array, otherwise false
     */
    public boolean containsLinear( int searchNum)
    {
        return false;
    }
  
    /**
     * A shell method for your binary search logic
     * 
     * @param searchNum the value to search for in the array 
     * @return returns true is searchNum is found in the array, otherwise false
     */
    public boolean containsBinary( int searchNum)
    {
        return false;
    }

    /**
     * This method can be used to print your results of a run.  Refactor this as appropriate for
     * what ever method you choose to collect your data points.  I would suggest writing to a 
     * comma delimited text file so that you can easily import the data into a spreadsheet for
     * graphing and analysis.
     * 
     * @param sortType A string stating the type of sort that was done
     */
    public void printSortingMetrics( String sortType )
    {
        //This is where you would the time, compares, and move counters
        System.out.println("****Emperical measures for: " + sortType);
    }
    
    //============ Private utility methods ==============
    /**
     * This value is the internal system time that is the measured in
     * the number of nano-seconds since Jan. 1, 1970.
     * 
     * @return The current time of the system clock
     */
    private long getSystemTime( )
    {
        return System.nanoTime( );
    }

    
    /**
     * Swap routine to re-order the ith and jth array values
     *
     * @param  i   an array location to be swapped with j
     * @param  j   an array location to be swapped with i
     */
    private void swap(int i, int j)
    {
        int temp = numList[i];
        numList[i] = numList[j];
        numList[j] = temp;
    }

    
    //============Program entry point MAIN======================
    /**
     * Standard class method "main".  Modify this to collect the
     * empherical metrics data on the sorting and searching methods
     */
    public static void main( String[ ] args)
    {
        int i = 1;
        for ( i = 1; i <= 10; i++) {
            SortSearch test = new SortSearch(10000 * i);

            test.fillArray();
            test.bubbleSort();
            //test.printArray();            
            test.printSortingMetrics("BubbleSort");
            
            test.fillArray();
            test.insertionSort();
            //test.printArray();
            test.printSortingMetrics("InsertionSort");
            
            test.fillArray();
            test.selectionSort();
            //test.printArray();
            test.printSortingMetrics("SelectionSort");
        }
    }
}