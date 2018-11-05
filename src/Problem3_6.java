// Reynerio Rubio (ID# 109899097)
//
// insertSort.java
// demonstrates insertion sort
// -------------------------------------------------------------

// Problem 3.6
// Here’s an interesting way to remove duplicates from an array.
// The insertion sort uses a loop-within-a-loop algorithm that compares
// every item in the array with every other item. If you want to remove
// duplicates, this is one way to start. (See also Exercise 2.6 in
// Chapter 2.) Modify the insertionSort() method in the insertSort.java
// program so that it removes duplicates as it sorts. Here’s one approach:
// When a duplicate is found, write over one of the duplicated items with
// a key value less than any normally used (such as –1, if all the normal
// keys are positive). Then the normal insertion sort algorithm, treating
// this new key like any other item, will put it at index 0. From now on
// the algorithm can ignore this item. The next duplicate will go at
// index 1, and so on. When the sort is finished, all the removed dups
// (now represented by –1 values) will be found at the beginning of the
// array. The array can then be resized and shifted down so it starts at 0



class ArrayIns3_6
{
    private long[] a;                       // ref to array a
    private int nElems;                     // number of data items

    public ArrayIns3_6(int max)                // constructor
    {
        a = new long[max];                  // create the array
        nElems = 0;                         // no items yet
    }

    public void insert(long value)          // put element into array
    {
        a[nElems] = value;                  // insert it
        nElems++;                           // increment size
    }

    public void display()                   // displays array contents
    {
        for (int j = 0; j < nElems; j++)    // for each element
            System.out.print(a[j] + " ");   // display it

        System.out.println("");
    }

    public void insertionSort()
    {
        int in, out;
        int dups = 0;
        for (out = 1; out < nElems; out++)      // out is dividing line
        {
            long temp = a[out];                 // remove marked item
            in = out;                           // start shifts at out

            while (in > 0 && a[in - 1] >= temp) // until one is smaller,
            {
                // check for duplicates
                if(temp == a[in-1])
                {
                    temp = -1;
                }


                a[in] = a[in - 1];              // shift item to right
                --in;                           // go left one position
            }

            a[in] = temp;                       // insert marked item

            if(temp == -1) dups++;
        }  // end for

        System.out.println("Duplicates found: " + dups);
        if(dups > 0)
        {
            for(int i = 0; i < nElems - dups; i++)
            {
                a[i] = a[i + dups];
            }
            nElems = nElems - dups;
        }


    }

    public void noDups()
    {

        // Check if array has less than 2 elements
        if (nElems < 2)
            return;

        // noDupArray will temporarily hold values of unique elements
        long[] noDupArray = new long[nElems];

        // unique will hold number of unique elements
        int unique = 0;
        for (int curr = 0; curr < nElems; curr++)
        {
            // If curr is unique (not equal to next), store value in noDupArray
            if (a[curr] != a[curr + 1])
            {
                noDupArray[unique] = a[curr];
                unique++;
            }
        }

        // Copy noDupArray into original array
        for (int i = 0; i < unique; i++)
            a[i] = noDupArray[i];

        // Modify nElems
        nElems = unique;

    }


}

class Problem3_6 {
    public static void main(String[] args) {
        int maxSize = 100;                  // array size
        ArrayIns3_6 arr;                       // reference to array
        arr = new ArrayIns3_6(maxSize);        // create the array
        arr.insert(99);
        arr.insert(77);
        arr.insert(99);
        arr.insert(44);
        arr.insert(55); arr.insert(88);
        arr.insert(11);
        arr.insert(00);
        arr.insert(66);
        arr.insert(99);
        arr.insert(99);
        arr.insert(55);
        arr.insert(22);
        arr.insert(88);
        arr.insert(11);
        arr.insert(00);
        arr.insert(66);
        arr.insert(99);
        arr.insert(99);
        arr.insert(55); arr.insert(88);
        arr.insert(11);
        arr.insert(00);
        arr.insert(66);
        arr.insert(99);
        arr.insert(99);
        arr.insert(55);
        arr.insert(22);
        arr.insert(88);
        arr.insert(99);
        arr.insert(99);

        System.out.println("UNSORTED: ");
        arr.display();                // display items
        arr.insertionSort();          // insertion-sort them

        System.out.println("SORTED: ");
        arr.display();                // display them again
    }
}
