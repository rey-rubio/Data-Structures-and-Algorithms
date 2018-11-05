// Reynerio Rubio (ID# 109899097)
//
// insertSort.java
// demonstrates insertion sort
// -------------------------------------------------------------

// Problem 3.3
// To the insertSort.java program (Listing 3.3), add a method
// called noDups() that removes duplicates from a previously sorted array
// without disrupting the order. (You can use the insertionSort() method
// to sort the data, or you can simply use main() to insert the data in
// sorted order.) One can imagine schemes in which all the items from the
// place where a duplicate was discovered to the end of the array would
// be shifted down one space every time a duplicate was discovered, but
// this would lead to slow O(N2) time, at least when there were a lot of
// duplicates. In your algorithm, make sure no item is moved more than
// once, no matter how many duplicates there are. This will give you an
// algorithm with O(N) time.



class ArrayIns3_3
{
    private long[] a;                       // ref to array a
    private int nElems;                     // number of data items

    public ArrayIns3_3(int max)                // constructor
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

        for (out = 1; out < nElems; out++)      // out is dividing line
        {
            long temp = a[out];                 // remove marked item
            in = out;                           // start shifts at out

            while (in > 0 && a[in - 1] >= temp) // until one is smaller,
            {
                a[in] = a[in - 1];              // shift item to right
                --in;                           // go left one position
            }

            a[in] = temp;                       // insert marked item
        }  // end for
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

class Problem3_3
{
    public static void main(String[] args)
    {
        int maxSize = 100;                  // array size
        ArrayIns3_3 arr;                       // reference to array
        arr = new ArrayIns3_3(maxSize);        // create the array
        arr.insert(77);               // insert 10 items
        arr.insert(99);
        arr.insert(44);
        arr.insert(55);
        arr.insert(22);
        arr.insert(88);
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

        System.out.println("NO DUPLICATES: ");
        arr.noDups();
        arr.display();                // display them again
    }
}
