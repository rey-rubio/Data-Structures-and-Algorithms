// Reynerio Rubio
// ID# 109899097
// ESE 554: Computational Models for Computer Engineers
// Chapter 12 Assignments

// Problem 12.5
// Write a program that implements the tree heap (the tree-based
// implementation of the heap) discussed in the text. Make sure
// you can remove the largest item, insert items, and change an
// item’s key.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Node12_5
{
    public int iData;
    public Node12_5 leftChild;
    public Node12_5 rightChild;
    public Node12_5 parent;
    boolean isLeftChild = false;

    public void display()
    {
        System.out.print(iData + " ");
    }
}

class TreeHeap
{
    private Node12_5 root;
    private int numNodes;

    public TreeHeap()
    {
        numNodes = 0;
        root = new Node12_5();
    }

    public boolean isEmpty()
    {
        return numNodes == 0;
    }

    public void insert(int key)
    {
        Node12_5 newNode = new Node12_5();
        newNode.iData = key;
        if(numNodes == 0)
        {
            root = newNode;
            numNodes++;
            return;
        }

        Node12_5 cursor = root;
        int temp = numNodes+1;
        int[] traversal = new int[temp];
        int i = 0;
        while(temp >= 1)
        {
            traversal[i] = temp % 2;
            temp /= 2;
            i++;
        }

        for(int j = i - 2; j > 0; j--)
        {
            if(traversal[j] == 1)
                cursor = cursor.rightChild;
            else
                cursor = cursor.leftChild;
        }

        if(cursor.leftChild != null)
        {
            cursor.rightChild = newNode;
            newNode.isLeftChild = false;
        }
        else
        {
            cursor.leftChild = newNode;
            newNode.isLeftChild = true;
        }
        newNode.parent = cursor;

        trickleUp(newNode);
        numNodes++;
        return;
    }

    public void trickleUp(Node12_5 newNode)
    {
        int bottom = newNode.iData;
        Node12_5 cursor = newNode;
        while(cursor.parent != null && cursor.parent.iData < bottom)
        {
            cursor.iData = cursor.parent.iData;
            cursor = cursor.parent;
        }
        cursor.iData = bottom;
        return;
    }


    public void trickleDown(Node12_5 newNode)
    {
        Node12_5 cursor = newNode;
        int top = newNode.iData;
        Node12_5 largerChild;
        while(cursor.leftChild != null || cursor.rightChild != null)
        {
            if(cursor.rightChild != null && (cursor.leftChild.iData < cursor.rightChild.iData))
                largerChild = cursor.rightChild;
            else
                largerChild = cursor.leftChild;

            if(top >= largerChild.iData )
                break;

            cursor.iData = largerChild.iData;
            cursor = largerChild;
        }
        cursor.iData = top;
        return;
    }

    //remove root node
    public Node12_5 remove()
    {
        Node12_5 removedNode = root;
        if(numNodes == 0)
            return null;

        if(numNodes == 1)
        {
            root = null;
            numNodes--;
            return removedNode;
        }

        Node12_5 cursor = root;
        int temp = numNodes;
        int[] traversal = new int[temp];
        int i = 0;

        while(temp >= 1)
        {
            traversal[i] = temp % 2;
            temp /= 2;
            i++;
        }

        for(int j = i-2; j >= 0; j--)
        {
            if(traversal[j] == 1)
                cursor = cursor.rightChild;
            else
                cursor = cursor.leftChild;
        }
        root.iData = cursor.iData;

        if(cursor.isLeftChild)
            cursor.parent.leftChild = null;
        else
            cursor.parent.rightChild = null;

        trickleDown(root);
        numNodes--;
        return removedNode;
    }


    public boolean change(int index, int newValue)
    {
        if(index<0 || index>numNodes)
            return false;

        Node12_5 current = root;
        int temp = index;
        int[] traversal = new int[temp];
        int i = 0;
        while(temp >= 1)
        {
            traversal[i] = temp % 2;
            temp /= 2;
            i++;
        }

        for(int j = i-2; j >= 0; j--)
        {
            if(traversal[j] == 1)
                current = current.rightChild;
            else
                current = current.leftChild;
        }

        int oldValue = current.iData;
        current.iData = newValue;

        if(oldValue < newValue)
            trickleUp(current);
        else
            trickleDown(current);

        return true;
    }

    public void display()
    {
        Stack<Node12_5> globalStack = new Stack<Node12_5>();
        globalStack.push(root);
        int nBlanks = 32;
        boolean isRowEmpty = false;
        System.out.println(".......................................................");
        while(isRowEmpty==false)
        {
            Stack<Node12_5> localStack = new Stack<Node12_5>();
            isRowEmpty = true;

            for(int j = 0; j < nBlanks; j++)
                System.out.print(" ");

            while(globalStack.isEmpty() == false)
            {
                Node12_5 temp = globalStack.pop();
                if(temp != null)
                {
                    System.out.print(temp.iData);
                    localStack.push(temp.leftChild);
                    localStack.push(temp.rightChild);

                    if(temp.leftChild != null ||
                            temp.rightChild != null)
                        isRowEmpty = false;
                }
                else
                {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for(int j = 0; j < nBlanks*2 - 2; j++)
                    System.out.print(" ");
            } //end while globalStack not empty
            System.out.println();
            nBlanks /= 2;
            while(localStack.isEmpty() == false)
                globalStack.push( localStack.pop() );
        } //end while isRowEmpty is false
        System.out.println(".......................................................");
    } //end display()
} //end class TreeHeap

class Problem12_5
{
    public static void main(String[] args) throws IOException
    {
        int value, value2;
        TreeHeap theHeap = new TreeHeap();
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
            System.out.print("\n| PROBLEM 12.5 OPTIONS:                      |");
            System.out.print("\n==============================================");
            System.out.print("\n| s : show tree                              |");
            System.out.print("\n| i : insert                                 |");
            System.out.print("\n| d : delete                                 |");
            System.out.print("\n| c : current index                          |");
            System.out.print("\n=============================================");
            System.out.print("\nPlease select an option: ");
            int choice = getChar();
            switch(choice)
            {
                case 's':
                    theHeap.display();
                    break;
                case 'i':
                    System.out.print("Please enter value to insert: ");
                    value = getInt();
                    theHeap.insert(value);
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