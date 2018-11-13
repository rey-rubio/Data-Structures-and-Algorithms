// Reynerio Rubio
// ID# 109899097
// ESE 554: Computational Models for Computer Engineers
// Chapter 8 Assignments

// Problem 8.1
//  Start with the tree.java program (Listing 8.1) and modify it to create a
// binary tree from a string of letters (like A, B, and so on) entered by the
// user. Each letter will be displayed in its own node. Construct the tree so
// that all the nodes that contain letters are leaves. Parent nodes can contain
// some non-letter symbol like +. Make sure that every parent node has exactly
// two children. Don’t worry if the tree is unbalanced. Note that this will not
// be a search tree; there’s no quick way to find a given node. You may end up
// with something like this:
//                               +
//                   +                       E
//             +           D           -           -
//          +     C     -     -     -     -     -    -
//         A B   - -   - -   - -   - -   - -   - -   - -
// One way to begin is by making an array of trees. (A group of unconnected
// trees is called a forest.) Take each letter typed by the user and put it in
// a node. Take each of these nodes and put it in a tree, where it will be the
// root. Now put all these one-node trees in the array. Start by making a new
// tree with + at the root and two of the one-node trees as its children. Then
// keep adding one-node trees from the array to this larger tree. Don’t worry
// if it’s an unbalanced tree. You can actually store this intermediate tree
// in the array by writing over a cell whose contents have already been added
// to the tree. The routines find(), insert(), and delete(), which apply only
// to search trees, can be deleted. Keep the displayTree() method and the
// traversals because they will work on any binary tree.


// Problem 8.2
// Expand the program in Programming Project 8.1 to create a balanced tree.
// One way to do this is to make sure that as many leaves as possible appear
// in the bottom row. You can start by making a three-node tree out of each
// pair of onenode trees, making a new + node for the root. This results in
// a forest of threenode trees. Then combine each pair of three-node trees to
// make a forest of seven-node trees. As the number of nodes per tree grows,
// the number of trees shrinks, until finally there is only one tree left.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Node8_2 {
    public String sData;              // data item (key)
    public Node8_2 leftChild;         // this node’s left child
    public Node8_2 rightChild;        // this node’s right child

    public void displayNode()      // display ourself
    {
        System.out.print("{");
        System.out.print(sData);
        System.out.print("} ");
    }
}

class Tree8_2 {
    private Node8_2 root;             // first node of tree

    public Tree8_2(String s)                  // constructor
    {
        root = null;
        int numNodes = 0;

        // parse input string
        Node8_2[] array = new Node8_2[s.length()];
        for(int i = 0; i < s.length(); i++)
        {
            array[i] = new Node8_2();
            array[i].sData = String.valueOf(s.charAt(i));
            numNodes++;  // added a node
        }

        // Iterate until only 1 node left
        while(numNodes > 1)
        {
            int temp = 0;   // temp counter
            // iterate through all the nodes
            for(int i = 0; i < numNodes; i++)
            {
                // if odd index
                if(i % 2 == 1)
                {
                    Node8_2 tempNode = new Node8_2();
                    tempNode.sData = "+";
                    tempNode.leftChild = array[i-1];
                    tempNode.rightChild = array[i];
                    array[temp++] = tempNode;
                }
                // if even
                else//(i % 2 == 0 && i == numNodes - 1)
                {
                    array[temp] = array[i];
                }
            }
            numNodes -= temp;
        }
        root = array[0];
    }

    public void traverse(int traverseType)
    {
        switch(traverseType)
        {
            case 1:
                System.out.print("\nPreorder traversal: ");
                preOrder(root);
                break;
            case 2:
                System.out.print("\nInorder traversal:  ");
                inOrder(root);
                break;
            case 3:
                System.out.print("\nPostorder traversal: ");
                postOrder(root);
                break;
        }
        System.out.println();
    }

    private void preOrder(Node8_2 localRoot)
    {
        if(localRoot != null)
        {
            System.out.print(localRoot.sData + " ");
            preOrder(localRoot.leftChild);
            preOrder(localRoot.rightChild);
        }
    }

    private void inOrder(Node8_2 localRoot)
    {
        if(localRoot != null)
        {
            inOrder(localRoot.leftChild);
            System.out.print(localRoot.sData + " ");
            inOrder(localRoot.rightChild);
        }
    }

    private void postOrder(Node8_2 localRoot)
    {
        if(localRoot != null)
        {
            postOrder(localRoot.leftChild);
            postOrder(localRoot.rightChild);
            System.out.print(localRoot.sData + " ");
        }
    }

    public void displayTree() {
        Stack globalStack = new Stack();
        globalStack.push(root);
        int nBlanks = 32;
        boolean isRowEmpty = false;
        System.out.println("......................................................");

        while (isRowEmpty == false)
        {
            Stack localStack = new Stack();
            isRowEmpty = true;
            for (int j = 0; j < nBlanks; j++)
                System.out.print(" ");

            while (globalStack.isEmpty() == false)
            {
                Node8_2 temp = (Node8_2) globalStack.pop();
                if (temp != null) {
                    System.out.print(temp.sData);
                    localStack.push(temp.leftChild);
                    localStack.push(temp.rightChild);

                    if (temp.leftChild != null || temp.rightChild != null)
                        isRowEmpty = false;
                } else {
                    System.out.print("-");
                    localStack.push(null);
                    localStack.push(null);
                }

                for (int j = 0; j < nBlanks * 2 - 2; j++)
                    System.out.print(" ");
            }  // end while globalStack not empty

            System.out.println();
            nBlanks /= 2;

            while (localStack.isEmpty() == false)
                globalStack.push(localStack.pop());
        }
        System.out.println("......................................................");
    }
}



public class Problem8_2 {

    public static void main(String[] args) throws IOException
    {
        int value;
        Tree8_2 theTree = new Tree8_2("ABCDEFGHIJKLMNOP");

        while(true)
        {
            System.out.print("\n\n==============================================");
            System.out.print("\n| PROBLEM 8.2 OPTIONS:                       |");
            System.out.print("\n==============================================");
            System.out.print("\n| s : show tree                              |");
            System.out.print("\n| t : traverse                               |");
            System.out.print("\n=============================================");
            System.out.print("\nPlease select an option: ");

            int choice = getChar();
            switch(choice)
            {
                case 's':
                    theTree.displayTree();
                    break;
                case 't':
                    System.out.print("Enter type 1, 2 or 3: ");
                    value = getInt();
                    theTree.traverse(value);
                    break;
                default:
                    System.out.print("Invalid entry\n");
            }  // end switch
        }  // end while
    }  // end main()

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