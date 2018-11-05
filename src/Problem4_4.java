// Reynerio Rubio
// ID# 109899097
// ESE 554: Computational Models for Computer Engineers
// Chapter 4 Assignments

// 4.4 The priority queue shown in Listing 4.6 features
// fast removal of the high-priority item but slow
// insertion of new items. Write a program with a revised
// PriorityQ class that has fast O(1) insertion time but
// slower removal of the highpriority item. Include a
// method that displays the contents of the priority
// queue, as suggested in Programming Project 4.1.

import java.io.IOException;

class PriorityQ {
    // array in sorted order, from max at 0 to min at size-1
    private int maxSize;
    private long[] pqArray;
    private int nItems;

    //------------------------------------------------------------

    public PriorityQ(int s)                             // constructor
    {
        maxSize = s;
        pqArray = new long[maxSize];
        nItems = 0;
    }

    //------------------------------------------------------------

    public void insert(long item)                       //insert item
    {
        // FAST INSERTION
        if(isFull())
        {
            System.out.println("PriorityQ full! Can't insert.");
            return;
        }
        else
        {
            pqArray[nItems] = item;
            nItems++;
        }

        // SLOW INSERTION
//        int j;
//
//        if (nItems == 0)                                // if no items,
//            pqArray[nItems++] = item;                  // insert at 0
//        else                                            // if items,
//        {
//            for (j = nItems - 1; j >= 0; j--)           // start at end,
//            {
//                if (item > pqArray[j])                 // if new item larger,
//                    pqArray[j + 1] = pqArray[j];      // shift upward
//
//                else                                    // if smaller,
//                    break;                              // done shifting
//            }  // end for
//
//            pqArray[j+1] = item;                       // insert it
//            nItems++;
//        }
    }

    //------------------------------------------------------------

    public long remove()        // remove minimum item
    {
        // SLOW REMOVAL
        if(isEmpty())
        {
            System.out.println("PriorityQ empty! Can't remove.");
            return -1;
        }

        // ITERATE THROUGH ARRAY, SEARCH FOR SMALLEST VALUE, RETURN IT
        int tempIndex = 0;
        for(int i = 0; i < nItems; i++)
        {
            if(pqArray[i] < pqArray[tempIndex])     // found smaller value
                tempIndex = i;                      // keep track of value
        }
        long tempValue = pqArray[tempIndex];        // hold value to return

        // REMOVE AND SHIFT CONTENTS OF PriorityQ
        for(int i = tempIndex; i < nItems - 1; i++) // start at temp index
        {
            pqArray[i] = pqArray[i+1];
        }
        nItems--;                                   // decrement num of items

        return tempValue;

        // FAST REMOVAL
        // return pqArray[--nItems];
    }

    //------------------------------------------------------------

    public long peekMin()       // peek at minimum item
    {
        // ITERATE THROUGH ARRAY, SEARCH FOR SMALLEST VALUE, RETURN IT
        int tempIndex = 0;
        for(int i = 0; i < nItems; i++)
        {
            if(pqArray[i] < pqArray[tempIndex])     // found smaller value
                tempIndex = i;                      // keep track of value
        }
        return pqArray[tempIndex];

    }

    //------------------------------------------------------------

    public boolean isEmpty()    // true if queue is empty
    {
        return (nItems==0);
    }

    //------------------------------------------------------------

    public boolean isFull()     // true if queue is full
    {
        return (nItems == maxSize);
    }

    //------------------------------------------------------------


    public void print(boolean mode)       // print contents of PriorityQ
    {
        if(isEmpty())
        {
            System.out.println("PriorityQ empty! Nothing to print.");
            return;
        }

        if(!mode)  // print with no priority
        {
            for(int i = 0; i < nItems; i++)
            {
                System.out.print(pqArray[i] + " ");
            }
            System.out.println();
        }
        else       // print with according to priority
        {
            // remove and print queue in order
            while (!isEmpty())
            {
                long item = remove();
                System.out.print(item + " "); // 10, 20, 30, 40, 50
            }  // end while


        }




    }

}  // end class PriorityQ

// //////////////////////////////////////////////////////////////


public class Problem4_4 {
    public static void main(String[] args) throws IOException
    {
        PriorityQ thePQ = new PriorityQ(5);

        thePQ.insert(30);
        thePQ.insert(50);
        thePQ.insert(10);
        thePQ.insert(40);
        thePQ.insert(20);

        // priority queue full
        thePQ.insert(100);
        thePQ.insert(75);
        thePQ.insert(125);

        thePQ.remove();
        thePQ.remove();
        thePQ.remove();
        thePQ.remove();
        thePQ.remove();
        thePQ.remove();

        thePQ.print(false);          // nothing in queue

        thePQ.insert(2);
        thePQ.insert(18);
        thePQ.insert(11);
        thePQ.insert(1);
        thePQ.insert(18);
        thePQ.print(false);     // print without priority

        System.out.println("Min Value: " + thePQ.peekMin());

        thePQ.print(true);      // print with priority, contents erased

    }
}
