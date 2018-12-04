// Reynerio Rubio
// ID# 109899097
// ESE 554: Computational Models for Computer Engineers
// Chapter 12 Assignments

// Problem 12.2
// In the heap.java program the insert() method inserts a new node
// in the heap and ensures the heap condition is preserved. Write
// a toss() method that places a new node in the heap array without
// attempting to maintain the heap condition. (Perhaps each new item
// can simply be placed at the end of the array.)
// Then write a restoreHeap() method that restores the heap condition
// throughout the entire heap. Using toss() repeatedly followed by a
// single restoreHeap() is more efficient than using insert() repeatedly
// when a large amount of data must be inserted at one time. See the
// description of heapsort for clues. To test your program, insert
// a few items, toss in some more, and then restore the heap.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Node12_2 {
    private int iData;             // data item (key)

    public Node12_2(int key)           // constructor
    {
        iData = key;
    }

    public int getKey() {
        return iData;
    }

    public void setKey(int id) {
        iData = id;
    }

}// end class Node


class Heap
{
    private Node12_2[] heapArray;
    private int maxSize;
    private int currentSize;

    public Heap(int mx)
    {
        maxSize = mx;
        currentSize = 0;
        heapArray = new Node12_2[maxSize];
    }

    public boolean isEmpty()
    {
        return currentSize==0;
    }

    public void toss(int key)
    {
        Node12_2 newNode = new Node12_2(key);
        heapArray[currentSize] = newNode;
        currentSize++;
    }

    public void restoreHeap()
    {
        for(int j=(currentSize/2)-1; j >= 0; j--)
            trickleDown(j);
    }

    public boolean insert(int key)
    {
        if(currentSize == maxSize)
            return false;
        Node12_2 newNode = new Node12_2(key);
        heapArray[currentSize] = newNode;
        trickleUp(currentSize++);
        return true;
    }

    public void trickleUp(int index)
    {
        int parent = (index-1) / 2;
        Node12_2 bottom = heapArray[index];
        while( index > 0 && heapArray[parent].getKey() < bottom.getKey() )
        {
            heapArray[index] = heapArray[parent];
            index = parent;
            parent = (parent-1)/2;
        }
        heapArray[index] = bottom;
    }

    public Node12_2 remove()
    {
        Node12_2 root = heapArray[0];
        heapArray[0] = heapArray[--currentSize];
        trickleDown(0);
        return root;
    }

    public void trickleDown(int index) {
        int largerChild;
        Node12_2 top = heapArray[index];        // save root

        while (index < currentSize / 2)        // while node has at
        {                                   //    least one child,
            int leftChild = 2 * index + 1;
            int rightChild = leftChild + 1;
            // find larger child
            if (rightChild < currentSize &&  // (rightChild exists?)
                    heapArray[leftChild].getKey() < heapArray[rightChild].getKey())
                largerChild = rightChild;
            else
                largerChild = leftChild;

            // top >= largerChild?
            if (top.getKey() >= heapArray[largerChild].getKey())
                break;
            // shift child up
            heapArray[index] = heapArray[largerChild];
            index = largerChild;            // go down
        }  // end while

        heapArray[index] = top;            // root to index
    }

    public boolean change(int index, int newValue)
    {
        if(index<0 || index>=currentSize)
            return false;
        int oldValue = heapArray[index].getKey();
        heapArray[index].setKey(newValue);

        if(oldValue < newValue)
            trickleUp(index);
        else
            trickleDown(index);
        return true;
    }

    public void displayHeap()
    {
        System.out.print("heapArray: ");
        for(int m = 0; m<currentSize; m++)
            if(heapArray != null)
                System.out.print(heapArray[m].getKey() + " ");
            else
                System.out.print("-- ");
        System.out.println();

        int nBlanks = 32;
        int itemsPerRow = 1;
        int column = 0;
        int j = 0;
        String dots = "............................";
        System.out.println(dots+dots);

        while(currentSize > 0)
        {
            if(column == 0)
                for(int k=0; k<nBlanks; k++)
                    System.out.print(' ');
            System.out.print(heapArray[j].getKey());
            if(++j == currentSize)
                break;

            if(++column == itemsPerRow)
            {
                nBlanks /= 2;
                itemsPerRow *= 2;
                column = 0;
                System.out.println();
            }
            else
                for(int k = 0; k<nBlanks*2-2; k++)
                    System.out.print(' ');
        }
        System.out.println("\n"+dots+dots);
    }
} //end class Heap

public class Problem12_2 {
    public static void main(String[] args) throws IOException
    {
        int value, value2;
        Heap theHeap = new Heap(31);
        boolean success;

        theHeap.insert(2);
        theHeap.insert(18);
        theHeap.insert(11);
        theHeap.insert(7);
        theHeap.insert(9);
        theHeap.insert(1);
        theHeap.insert(20);

        while(true)
        {
            System.out.print("\n==============================================");
            System.out.print("\n| PROBLEM 12.2 OPTIONS:                      |");
            System.out.print("\n==============================================");
            System.out.print("\n| s : show tree                              |");
            System.out.print("\n| i : insert                                 |");
            System.out.print("\n| r : remove                                 |");
            System.out.print("\n| c : current index                          |");
            System.out.print("\n| r : restore                                |");
            System.out.print("\n| t : toss                                   |");
            System.out.print("\n=============================================");
            System.out.print("\nPlease select an option: ");
            int choice = getChar();
            switch(choice)
            {
                case 's':
                    theHeap.displayHeap();
                    break;
                case 'i':
                    System.out.print("Please enter value to insert: ");
                    value = getInt();
                    success = theHeap.insert(value);
                    if (!success)
                        System.out.println("I'm sorry, can't insert -  the heap is full.");
                    break;
                case 'd':
                    if(!theHeap.isEmpty())
                        theHeap.remove();
                    else
                        System.out.println("I'm sorry, can't delete - the heap is empty.");
                    break;
                case 'c':
                    System.out.print("Please enter the current index of item: ");
                    value = getInt();
                    System.out.print("Please enter new key: ");
                    value2 = getInt();
                    success = theHeap.change(value, value2);
                    if(!success)
                        System.out.println("I'm sorry, that is an invalid index\n");
                    break;
                case 'r':
                    System.out.println("Restoring Heap...");
                    theHeap.restoreHeap();
                    System.out.println("Heap restored!");
                    theHeap.displayHeap();

                    break;
                case 't':
                    System.out.print("Please enter value to toss: ");
                    value = getInt();
                    theHeap.toss(value);
                    break;
                default:
                    System.out.println("I'm sorry, that is an jnvalid entry!\n");
            } //end switch
        } //end while
    } //end main

    public static String getString() throws IOException
    {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }

    public static char getChar() throws IOException
    {
        String s = getString();
        return s.charAt(0);
    }

    public static int getInt() throws IOException
    {
        String s = getString();
        return Integer.parseInt(s);
    }
}
