/**
 * 
 */
package edu.wou.cs260;

import java.util.Random;

/**
 *  SortSearch sets up an array of random numbers of size list size to test several
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
    
    //comparison counting fields
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
     * Fills the array with list size integers
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
isCompCount = 0;
        int t;
long nanoTime = getSystemTime();
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
isNanoTime = getSystemTime() - nanoTime;
    }

 
    /**
     * Sorts the array using Bubble Sort logic (bad way to sort)
     */
    public void bubbleSort( )
    {
isCompCount = 0;
long nanoTime = getSystemTime();
        for ( int out = numList.length - 1; out > 0; out--) { // outer loop (backward)
            for ( int in = 0; in < out; in++) { // inner loop (forward)
isCompCount++;
                if ( numList[ in] > numList[ in + 1]) { // out of order?
                    swap( in, in + 1);
                }
            }
        }
isNanoTime = getSystemTime() - nanoTime;
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
isCompCount = 0;
long nanoTime = getSystemTime();
    	int index = 0;
    	while(index < numList.length -1) {
isCompCount++;
    		if(numList[index] == searchNum) {
    			return true;
    			}
isCompCount++;
    		if(numList[index] < searchNum) {
    			return false;
    			}
    		index++;
    		}
isNanoTime = getSystemTime() - nanoTime;
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
    	int low = 0;
    	int high = numList.length -1;
isCompCount = 0;
long nanoTime = getSystemTime();

    	while(high >= low) {
    		int middle = (low + high) / 2;
isCompCount++;
    		if(numList[middle] == searchNum) {
    			return true;
    			}
isCompCount++;
    		if(numList[middle] < searchNum) {
    			low = middle + 1;
    			}
isCompCount++;
    		if(numList[middle] > searchNum) {
    			high = middle - 1;
    			}
    		}
isNanoTime = getSystemTime() - nanoTime;
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
        System.out.println("Count of Comparisons: "+ isCompCount + " Time Count: " + isNanoTime);
        System.out.println();
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
     * Empirical metrics data on the sorting and searching methods
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
            
            //test.fillArray();
            //test.containsBinary();
            //test.printArray();
            //test.printSortingMetrics("BinarySearch");
        }
    }
}