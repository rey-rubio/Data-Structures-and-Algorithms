// Reynerio Rubio
// ID# 109899097
// ESE 554: Computational Models for Computer Engineers
// Chapter 9 Assignments

// Problem 9
// Implement an RB tree, by modifying and extending tree.java program in Chap 8.
// Main methods to add:
//      rotateLeft(Node n);
//      rotateRight(Node n);
//      A new insert(Node n) replaces old insert()
//      adjustAfterInsertion(Node n)
// Misc utility methods to add:
//      Add a ‘color’ field in Node;
//      check/set color of a Node;
//      flip colors of a parent and two children
// No need to handle removal

import java.io.*;
import java.util.*;

class Node9 {
    public int key;                     // data item (key)
    public double value;                // data item
    public boolean color;               // item color

    static final boolean RED = true;
    static final boolean BLACK = false;
    public Node9 leftChild;         // this node’s left child
    public Node9 rightChild;        // this node’s right child

    public Node9(int k, double v, boolean c)
    {
        this.key = k;
        this.value = v;
        this.color = c;
    }

    public Node9(int k, double v)
    {
        this.key = k;
        this.value = v;
    }
    public void displayNode()      // display ourself
    {
        System.out.print("{");
        System.out.print(key);
        System.out.print(", ");
        System.out.print(value);
        System.out.print(", ");
        if(color == RED)
            System.out.print("r");
        else
            System.out.print("b");
        System.out.print("} ");
    }
}

class RBT {

    static final boolean RED = true;
    static final boolean BLACK = false;
    private Node9 root;             // first node of tree

    public RBT()
    {
        root = null;
    }

    public Node9 find(int key)               // find node with given key
    {                                       // (assumes non-empty tree)
        Node9 current = root;                // start at root
        while(current.key != key)         // while no match,
        {
            if(key < current.key)         // go left?
                current = current.leftChild;
            else                            // or go right?
                current = current.rightChild;

            if(current == null)                 // if no child,
                return null;                    // didn’t find it
         }

         return current;                    // found it
    }  // end find()

    public void insert(int key, double value)
    {
        root = insert(root, key, value);
        root.color = BLACK;
    }

    private Node9 insert(Node9 n, int key, double value)
    {
        if(n == null)
            return new Node9(key,value,RED);
        
        if(key < n.key)
            n.leftChild = insert(n.leftChild, key, value);
        else if(key > n.key)
            n.rightChild = insert(n.rightChild, key, value);
        else
            n.value = value;

        n = adjustAfterInsertion(n);
        return n;
    }

    public Node9 rotateLeft(Node9 n)
    {
        //System.out.println("Rotating left...\n");
        Node9 temp = n.rightChild;
        n.rightChild = temp.leftChild;
        temp.leftChild = n;
        temp.color = n.color;
        n.color = RED;
        //displayTree();
        return temp;
    }

    public Node9 rotateRight(Node9 n)
    {
        //System.out.println("Rotating right...\n");
        Node9 temp = n.leftChild;
        n.leftChild = temp.rightChild;
        temp.rightChild = n;
        temp.color = n.color;
        n.color = RED;
        //displayTree();
        return temp;
    }

    private Node9 adjustAfterInsertion(Node9 n)
    {
        if (isRed(n.rightChild) && !isRed(n.leftChild))
            n = rotateLeft(n);
        if (isRed(n.leftChild) && isRed(n.leftChild.leftChild))
            n = rotateRight(n);
        if (isRed(n.leftChild) && isRed(n.rightChild))
            flipColors(n);
        return n;
    }


    private void flipColors (Node9 n) {
        n.color = RED;
        n.leftChild.color = n.rightChild.color = BLACK;
    }

    private boolean isRed (Node9 n)
    {
        if (n == null)
            return false;

        return n.color == RED;
    }

    public void traverse(int traverseType)
    {
        switch(traverseType)
        {
            case 1:
                preOrder(root);
                break;
            case 2:
                inOrder(root);
                break;
            case 3:
                postOrder(root);
                break;
        }
        System.out.println("");
    }

    private void preOrder(Node9 localRoot)
    {
        if(localRoot != null)
        {
            System.out.print(localRoot.key + " ");
            preOrder(localRoot.leftChild);
            preOrder(localRoot.rightChild);
        }
    }

    private void inOrder(Node9 localRoot)
    {
        if(localRoot != null)
        {
            inOrder(localRoot.leftChild);
            System.out.print(localRoot.key + " ");
            inOrder(localRoot.rightChild);
        }
    }

    private void postOrder(Node9 localRoot)
    {
        if(localRoot != null)
        {
            postOrder(localRoot.leftChild);
            postOrder(localRoot.rightChild);
            System.out.print(localRoot.key + " ");
        }
    }

    public void displayTree()
    {
        Stack<Node9> globalStack = new Stack<Node9>();
        globalStack.push(root);
        int nBlanks = 32;
        boolean isRowEmpty = false;
        System.out.println("`````````````````````````````````````````````````````````````````````````````````````````");
        while(isRowEmpty==false)
        {
            Stack<Node9> localStack = new Stack<Node9>();
            isRowEmpty = true;

            for(int j = 0; j < nBlanks; j++)
                System.out.print(" ");

            while(globalStack.isEmpty() == false)
            {
                Node9 temp = globalStack.pop();
                if(temp != null)
                {
                    System.out.print(temp.key);
                    System.out.print("(");
                    if(temp.color == RED)
                        System.out.print("r");
                    else
                        System.out.print("b");
                    System.out.print(")");
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
        System.out.println("`````````````````````````````````````````````````````````````````````````````````````````");
    } //end displayTree()
}
public class Problem9 {

    public static void main(String[] args) throws IOException
    {
        int value;
        RBT theTree = new RBT();

        theTree.insert(50, 1.5);
        theTree.insert(25, 1.2);
        theTree.insert(75, 1.7);
        theTree.insert(12, 1.5);
        theTree.insert(37, 1.2);
        theTree.insert(43, 1.7);
        theTree.insert(30, 1.5);
        theTree.insert(33, 1.2);
        theTree.insert(87, 1.7);
        theTree.insert(93, 1.5);
        theTree.insert(97, 1.5);

        while(true)
        {
            System.out.print("\n==============================================");
            System.out.print("\n| PROBLEM 9 OPTIONS:                         |");
            System.out.print("\n==============================================");
            System.out.print("\n| s : show tree                              |");
            System.out.print("\n| t : traverse                               |");
            System.out.print("\n| i : insert                                 |");
            System.out.print("\n| f : find                                   |");
            System.out.print("\n=============================================");
            System.out.print("\nPlease select an option: ");
            int choice = getChar();
            switch(choice)
            {
                case 's':
                    theTree.displayTree();
                    break;
                case 'i':
                    System.out.print("Enter value to insert: ");
                    value = getInt();
                    theTree.insert(value, value + 0.9);
                    break;
                case 'f':
                    System.out.print("Enter value to find: ");
                    value = getInt();
                    Node9 found = theTree.find(value);
                    if(found != null)
                    {
                        System.out.print("Found: ");
                        found.displayNode();
                        System.out.print("\n");
                    }
                    else
                        System.out.print("Could not find " + value + "\n");
                    break;
                case 't':
                    System.out.print("Enter type 1, 2, or 3: ");
                    value = getInt();
                    theTree.traverse(value);
                    break;
                default:
                    System.out.print("Invalid entry!\n");
            } // end switch
        } // end while
    } // end main()

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
