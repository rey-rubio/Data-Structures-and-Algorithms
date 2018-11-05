// Reynerio Rubio
// ID# 109899097
// ESE 554: Computational Models for Computer Engineers
// Chapter 7 Assignments

// Problem 7.2
// Modify the quickSort2.java program (Listing 7.4) to count the number of
// nCopies and nComparisons it makes during a sort and then display the totals.
// This program should duplicate the performance of the QuickSort2 Workshop
// applet, so the nCopies and nComparisons for inversely sorted data should agree.
// (Remember that a swap is three nCopies.)

// Problem 7.4
// Selection means finding the kth largest or kthsmallest element from an array.
// For example, you might want to select the 7th largest element. Finding the
// median (as in Programming Project 7.2) is a special case of selection. The
// same partitioning process can be used, but you look for an element with a
// specified index number rather than the middle element. Modify the program
// from Programming Project 7.2 to allow the selection of an arbitrary element.
// How small an array can your program handle?

class ArrayIns
{
    private long[] theArray;            // ref to array theArray
    private int nElems;                 // number of data items
    private long nCopies;                // number of nCopies
    private long nComparisons;           // number of nComparisons

    public ArrayIns(int maxSize)
    {
        theArray = new long[maxSize];
        nElems = 0;
        nCopies = 0;
        nComparisons = 0;
    }
    public int size()
    {
        return nElems;
    }
    public void insert(long value)
    {
        theArray[nElems++] = value;
    }

    public void display()
    {
        System.out.print("A= ");
        for(int i = 0; i < nElems; i++)
        {
            System.out.print(theArray[i] + " ");
        }
        System.out.println("");
    }

    public void quickSort()
    {
        System.out.println("Quick sorting...");
        nCopies = 0;
        nComparisons = 0;
        recQuickSort(0, nElems - 1);
        System.out.println("Copies = " + nCopies);
        System.out.println("Comparisons = " + nComparisons);
    }

    //modified to count the number of nCopies and nComparisons
    public void recQuickSort(int left, int right)
    {
        int size = right - left + 1;
        if(size <= 3)    // manual sort if small
        {
            nComparisons++;
            manualSort(left, right);
        }
        else            // quicksort if large
        {
            nComparisons++;
            long median = medianOf3(left, right);
            int partition = partitionIt(left, right, median);
            recQuickSort(left, partition-1);
            recQuickSort(partition+1, right);
        }

    }

    public int partitionIt(int left, int right, long pivot)
    {
        int leftPtr = left;
        int rightPtr = right-1;

        while(true)
        {
            while(theArray[++leftPtr] < pivot) nComparisons++; // find bigger
            // nop
            while(theArray[--rightPtr] > pivot) nComparisons++;  // find smaller
            // nop

            if(leftPtr >= rightPtr)         // if pointers cross,
            {
                nComparisons++;              // increment nComparisons
                break;                      // partition done
            }
            else
            {
                nComparisons++;              // increment nComparisons
                swap(leftPtr, rightPtr);    // swap elements
            }
        }
        swap(leftPtr, right-1);             // restore pivot
        return leftPtr;                     // return pivot location
    }


    public void swap(int index1, int index2)
    {
        long temp = theArray[index1];
        theArray[index1] = theArray[index2];
        theArray[index2] = temp;
        nCopies += 3;   // increment copies by 3
    }

    public long medianOf3(int left, int right)
    {
        int center = (left+right)/2;

        if(theArray[left] > theArray[center]) swap(left, center);   // order left & center
        if(theArray[left] > theArray[right]) swap(left, right);     // order left & right
        if(theArray[center] > theArray[right]) swap(center, right); // order center & right
        nComparisons += 3;

        swap(center, right-1);      // put pivot on right
        return theArray[right-1];   // return median value
    }

    public void manualSort(int left, int right)
    {
        int size = right-left+1;
        if(size <= 1)   // no sort necessary
        {
            nComparisons++;
            return;
        }

        nComparisons++;  // increment nComparisons

        if(size == 2)    // 2-sort left and right
        {
            nComparisons++;
            if(theArray[left] > theArray[right]) swap(left, right);
            nComparisons++;
            return;
        }
        else            // size is 3
        {               // 3-sort left, center, & right
            nComparisons++;      // increment nComparisons
            if(theArray[left] > theArray[right-1]) swap(left, right-1);     // left, center
            if(theArray[left] > theArray[right]) swap(left, right);         // left, right
            if(theArray[right-1] > theArray[right]) swap(right-1, right);   // center, right

            nComparisons += 3;   // increment nComparisons by 3
        }
    }

    // Problem 7.4
    // Selection means finding the kth largest or kthsmallest element from an array.
    // For example, you might want to select the 7th largest element. Finding the
    // median (as in Programming Project 7.2) is a special case of selection. The
    // same partitioning process can be used, but you look for an element with a
    // specified index number rather than the middle element. Modify the program
    // from Programming Project 7.2 to allow the selection of an arbitrary element.
    // How small an array can your program handle?
    public long find(int left, int right, int index)
    {
        if(right-left <= 0)
            return theArray[index];
        else
        {
            int partition = partitionIt2(left, right);

            if(partition == index)
                return theArray[index];
            else if(partition < index)
                return find(partition+1, right, index);
            else
                return find(left, partition-1, index);
        }
    }

    public int partitionIt2(int left, int right)
    {
        int leftPtr = left - 1;
        int rightPtr = right;

        if(rightPtr - leftPtr <= 0)
        {
            System.out.println("Partition is too small!");
            return -1;
        }

        long pivot = theArray[right];

        while(true)
        {
            while(leftPtr < right && theArray[++leftPtr] < pivot); // find bigger
            // nop
            while(rightPtr > left && theArray[--rightPtr] > pivot); // find smaller
            // nop

            if(leftPtr >= rightPtr)         // if pointers cross,
                break;                      // partition done
            else
                swap(leftPtr, rightPtr);    // swap elements
        }
        swap(leftPtr, right);             // restore pivot
        return leftPtr;                     // return pivot location
    }

}

public class Problem7_4 {

    public static void main(String[] args)
    {
        int maxSize = 25;                       // array size
        ArrayIns arr1 = new ArrayIns(maxSize);   // create the array
        for(int j=0; j< maxSize; j++)  // fill array with
        {                             // random numbers
            long n = (int)(java.lang.Math.random()*50);
            arr1.insert(n);
        }
        arr1.display();

        int k = 2;
        System.out.println("The " + k + "th smallest element is: " + arr1.find(0, arr1.size()-1, k - 1 ));
        k = 18;
        System.out.println("The " + k + "th smallest element is: " + arr1.find(0, arr1.size()-1, k - 1 ));
        k = 11;
        System.out.println("The " + k + "th smallest element is: " + arr1.find(0, arr1.size()-1, k - 1 ));
        k = 7;
        System.out.println("The " + k + "th smallest element is: " + arr1.find(0, arr1.size()-1, k - 1 ));
        k = 9;
        System.out.println("The " + k + "th smallest element is: " + arr1.find(0, arr1.size()-1, k - 1 ));
        arr1.quickSort();
        arr1.display();
    }
}
