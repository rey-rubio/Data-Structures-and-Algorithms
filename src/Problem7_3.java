// Reynerio Rubio
// ID# 109899097
// ESE 554: Computational Models for Computer Engineers
// Chapter 7 Assignments

// Problem 7.3
// In Exercise 3.2 in Chapter 3, we suggested that you could find the
// median of a set of data by sorting the data and picking the middle
// element. You might think using quicksort and picking the middle
// element would be the fastest way to find the median, but there’s
// an even faster way. It uses the partition algorithm to find the
// median without completely sorting the data.
//
// To see how this works, imagine that you partition the data, and,
// by chance, the pivot happens to end up at the middle element.
// You’re done! All the items to the right of the pivot are larger
// (or equal), and all the items to the left are smaller (or equal),
// so if the pivot falls in the exact center of the array, then it’s
// the median. The pivot won’t end up in the center very often, but
// we can fix that by repartitioning the partition that contains the
// middle element. Suppose your array has seven elements numbered
// from 0 to 6. The middle is element 3. If you partition this array
// and the pivot ends up at 4, then you need to partition again from
// 0 to 4 (the partition that contains 3), not 5 to 6. If the pivot
// ends up at 2, you need to partition from 2 to 6, not 0 to 1.
//
// You continue partitioning the appropriate partitions recursively,
// always checking if the pivot falls on the middle element. Eventually,
// it will, and you’re done. Because you need fewer partitions than
// in quicksort, this algorithm is faster.
//
// Extend Programming Project 7.1 to find the median of an array.
// You’ll make recursive calls somewhat like those in quicksort, but
// they will only partition each subarray, not completely sort it. The
// process stops when the median is found, not when the array is sorted

class ArrayPar {
    private long[] theArray;          // ref to array theArray
    private int nElems;               // number of data items

    // -------------------------------------------------------------
    public ArrayPar(int max)          // constructor
    {
        theArray = new long[max];      // create the array
        nElems = 0;                    // no items yet
    }
    //-------------------------------------------------------------

    public void insert(long value)    // put element into array
    {
        theArray[nElems] = value;       // insert it
        nElems++;                       // increment size
    }

    public int size()            // return number of items
    {
        return nElems;
    }

    public void display()             // displays array contents
    {
        System.out.print("A = ");
        for(int j=0; j<nElems; j++)    // for each element,
            System.out.print(theArray[j] + "\t");  // display it
        System.out.println("");
    }

    // Modify the partition.java program (Listing 7.2) so that the partitionIt() method
    // always uses the highest-index (right) element as the pivot, rather than an
    // arbitrary number. (This is similar to what happens in the quickSort1.java program
    // in Listing 7.3.) Make sure your routine will work for arrays of three or fewer
    // elements. To do so, you may need a few extra statements.
    public int partitionIt(int left, int right) {
        int leftPtr = left - 1;           // left    (after ++)
        int rightPtr = right;            // right excluding right-most pivot value   (after --)
        if(rightPtr - leftPtr <= 0)
        {
            System.out.println("Partition is too small!");
            return -1;
        }
        long pivot = theArray[right];
        System.out.println("Pivot = " + pivot);
        while (true) {

            // find bigger item
            while(leftPtr < right && theArray[++leftPtr] < pivot);
                // (nop)
            while(rightPtr > left && theArray[--rightPtr] > pivot);
                // (nop)
            if (leftPtr >= rightPtr)        // if pointers cross,
                break;                      //    partition done
            else                            // not crossed, so
                swap(leftPtr, rightPtr);    //    swap elements
        }

        swap(leftPtr, right);           // move pivot into the partition

        return leftPtr;                 // return pivot location
    }

    public void swap(int dex1, int dex2)  // swap two elements
    {
        System.out.println("Swapping " + theArray[dex1] + " and " + theArray[dex2]);


        long temp = theArray[dex1];       // A into temp
        theArray[dex1] = theArray[dex2];  // B into A
        theArray[dex2] = temp;            // temp into B
        System.out.print("\t");
        display();
    }

    // Extend Programming Project 7.1 to find the median of an array.
    // You’ll make recursive calls somewhat like those in quicksort, but
    // they will only partition each subarray, not completely sort it. The
    // process stops when the median is found, not when the array is sorted
    public long findMedian(int left, int right)
    {
        int mid = size()/2;
        if(right-left <= 0)
            return theArray[mid];
        else
        {
            int partition = partitionIt(left, right);

            if(partition == mid)
                return theArray[mid];
            else if(partition < mid)
                return findMedian(partition+1, right); // partition right side
            else
                return findMedian(left, partition-1); // partition left side
        }
    }
}

public class Problem7_3 {

    public static void main(String[] args) {
        int maxSize = 25;             // array size
        ArrayPar arr;                 // reference to array
        arr = new ArrayPar(maxSize);  // create the array

        for(int j=0; j<maxSize; j++)  // fill array with
        {                             // random numbers
            long n = (int)(java.lang.Math.random()*199);
            arr.insert(n);
        }

        arr.display();                // display unpartitioned array
        int size = arr.size();

        // partition array
        int partDex = arr.partitionIt(0, size-1);

        System.out.println("\n\nPartition is at index " + partDex);
        arr.display();                // display partitioned array

        System.out.println("\nMedian = " + arr.findMedian(0, arr.size()-1));
    }
}
