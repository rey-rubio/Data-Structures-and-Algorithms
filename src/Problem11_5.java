// Reynerio Rubio
// ID# 109899097
// ESE 554: Computational Models for Computer Engineers
// Chapter 11 Assignments

// Instead of using a linked list to resolve collisions, as in separate
// chaining, use a binary search tree. That is, create a hash table that
// is an array of trees. You can use the hashChain.java program (Listing
// 11.3) as a starting point and the Tree class from the tree.java program
// (Listing 8.1) in Chapter 8. To display a small tree-based hash table,
// you could use an inorder traversal of each tree.

// The advantage of a tree over a linked list is that it can be searched
// in O(logN) instead of O(N) time. This time savings can be a significant
// advantage if very high load factors are encountered. Checking 15 items
// takes a maximum of 15 comparisons in a list but only 4 in a tree.
//
// Duplicates can present problems in both trees and hash tables, so add
// some code that prevents a duplicate key from being inserted in the hash
// table. (Beware: The find() method in Tree assumes a non-empty tree.)
// To shorten the listing for this program, you can forget about deletion,
// which for trees requires a lot of code.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node11_5
{
    public int iData;			//data item (key)
    public double dData;		//data item
    public Node11_5 leftChild;
    public Node11_5 rightChild;

    public void displayNode()
    {
        System.out.print("{" + iData + ", " + dData + "} ");
    }
}

class Tree
{
    private Node11_5 root;			//first node of tree

    public Tree()
    { root = null; }

    public boolean isEmpty()
    { return (root == null) ? true : false; }

    public Node11_5 find(int key)	//find node with given key in NON-EMPTY TREE
    {
        Node11_5 current = root;
        while(current.iData != key)
        {
            if(key < current.iData)		//go left?
                current = current.leftChild;
            else						//or go right?
                current = current.rightChild;
            if(current == null)			//if no child
                return null;			//didn't find it
        }
        return current;					//found it
    } //end find()

    public void insert(int id, double dd)
    {
        Node11_5 newNode = new Node11_5();
        newNode.iData = id;
        newNode.dData = dd;
        if(root == null)				//no node in root
            root = newNode;
        else
        {
            Node11_5 current = root;
            Node11_5 parent;
            while(true)
            {
                parent = current;
                if(id < current.iData)	//go left?
                {
                    current = current.leftChild;
                    if(current == null) //if end of the line
                    {					//insert on left
                        parent.leftChild = newNode;
                        return;
                    }
                }
                else					//or go right?
                {
                    current = current.rightChild;
                    if(current == null)
                    {
                        parent.rightChild = newNode;
                        return;
                    }
                }
            }
        }
    } //end insert()

    public boolean delete(int key) //delete node with given key
    {								//assumes non-empty list
        Node11_5 current = root;
        Node11_5 parent = root;
        boolean isLeftChild = true;

        while(current.iData != key)		//search for node
        {
            parent = current;
            if(key < current.iData)		//go left?
            {
                isLeftChild = true;
                current = current.leftChild;
            }
            else
            {
                isLeftChild = false;
                current = current.rightChild;
            }
            if(current == null)			//end of the line,
                return false;			//didn't find it
        }
        //found node to delete

        //if no children, simply delete it
        if(current.leftChild==null && current.rightChild==null)
        {
            if(current == root)
                root = null;
            else if(isLeftChild)		//disconnect deleted node from parent
                parent.leftChild = null;
            else
                parent.rightChild = null;
        }

        //if no right child, replace with left subtree
        else if(current.rightChild == null)
        {
            if(current == root)
                root = current.leftChild;
            else if(isLeftChild)
                parent.leftChild = current.leftChild;
            else
                parent.rightChild = current.leftChild;
        }

        //if no left child, replace with right subtree
        else if(current.leftChild == null)
        {
            if(current == root)
                root = current.rightChild;
            else if(isLeftChild)
                parent.leftChild = current.rightChild;
            else
                parent.rightChild = current.rightChild;
        }

        else		//two children, replace with in-order successor
        {
            //get successor of node to delete (current)
            Node11_5 successor = getSuccessor(current);

            //connect parent of current to successor instead
            if(current == root)
                root = successor;
            else if(isLeftChild)
                parent.leftChild = successor;
            else
                parent.rightChild = successor;

            //connect successor to current's left child
            successor.leftChild = current.leftChild;
        }

        return true;
    } //end delete()

    public Node11_5 removeMax() //delete biggest node and return it
    {								//assumes non-empty list
        Node11_5 current = root;
        Node11_5 parent = root;
        boolean isLeftChild = false;

        //in case of empty tree/queue
        if(root == null) return null;

        //find rightmost descendant (max node)
        while(current.rightChild != null)
        {
            parent = current;
            current = current.rightChild;
        }
        //found node to delete
        Node11_5 result = current;

        //if no children, simply delete it
        if(current.leftChild==null && current.rightChild==null)
        {
            if(current == root)
                root = null;
            else if(isLeftChild)		//disconnect deleted node from parent
                parent.leftChild = null;
            else
                parent.rightChild = null;
        }

        //if no right child, replace with left subtree
        else if(current.rightChild == null)
        {
            if(current == root)
                root = current.leftChild;
            else if(isLeftChild)
                parent.leftChild = current.leftChild;
            else
                parent.rightChild = current.leftChild;
        }

        //if no left child, replace with right subtree
        else if(current.leftChild == null)
        {
            if(current == root)
                root = current.rightChild;
            else if(isLeftChild)
                parent.leftChild = current.rightChild;
            else
                parent.rightChild = current.rightChild;
        }

        else		//two children, replace with in-order successor
        {
            //get successor of node to delete (current)
            Node11_5 successor = getSuccessor(current);

            //connect parent of current to successor instead
            if(current == root)
                root = successor;
            else if(isLeftChild)
                parent.leftChild = successor;
            else
                parent.rightChild = successor;

            //connect successor to current's left child
            successor.leftChild = current.leftChild;
        }

        return result;
    } //end removeMax()

    private Node11_5 getSuccessor(Node11_5 delNode)
    {
        Node11_5 successorParent = delNode;
        Node11_5 successor = delNode;
        Node11_5 current = delNode.rightChild;		//go to right child
        while(current != null)					//and then go to left child
        {										//until there are none left
            successorParent = successor;
            successor = current;
            current = current.leftChild;
        }

        if(successor != delNode.rightChild)		//if successor not right child,
        {										//make connections
            successorParent.leftChild = successor.rightChild;
            successor.rightChild = delNode.rightChild;
        }

        return successor;
    } //end getSuccessor()

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

    private void preOrder(Node11_5 localRoot)
    {
        if(localRoot != null)
        {
            System.out.print(localRoot.iData + " ");
            preOrder(localRoot.leftChild);
            preOrder(localRoot.rightChild);
        }
    }

    private void inOrder(Node11_5 localRoot)
    {
        if(localRoot != null)
        {
            inOrder(localRoot.leftChild);
            System.out.print(localRoot.iData + " ");
            inOrder(localRoot.rightChild);
        }
    }

    private void postOrder(Node11_5 localRoot)
    {
        if(localRoot != null)
        {
            postOrder(localRoot.leftChild);
            postOrder(localRoot.rightChild);
            System.out.print(localRoot.iData + " ");
        }
    }

    public void displayTree()
    {
        Stack<Node11_5> globalStack = new Stack<Node11_5>();
        globalStack.push(root);
        int nBlanks = 32;
        boolean isRowEmpty = false;
        System.out.println(
                ".......................................................");
        while(isRowEmpty==false)
        {
            Stack<Node11_5> localStack = new Stack<Node11_5>();
            isRowEmpty = true;

            for(int j = 0; j < nBlanks; j++)
                System.out.print(" ");

            while(globalStack.isEmpty() == false)
            {
                Node11_5 temp = globalStack.pop();
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
        System.out.println(
                ".......................................................");
    } //end displayTree()
} //end class Tree
class TreeHashTable
{
    private Tree[] hashArray;
    private int arraySize;

    public TreeHashTable(int size)
    {
        arraySize = size;
        hashArray = new Tree[arraySize];
        for(int j = 0; j < arraySize; j++)
            hashArray[j] = new Tree();
    }

    public void displayTable()
    {
        for(int j = 0; j < arraySize; j++)
        {
            System.out.print("(" + j + ")" + ": ");
            hashArray[j].traverse(2);
        }
    }

    public int hashFunc(int key)
    { return key % arraySize; }

    public void insert(Node11_5 theNode)
    {
        int key = theNode.iData;
        int hashVal = hashFunc(key);
        if(find(key) != null)
        {
            System.out.println("I'm sorry, but " + key + " already exists!");
            return;
        }
        else
            hashArray[hashVal].insert(theNode.iData, theNode.dData);
    }

    public void delete(int key)
    {
        int hashVal = hashFunc(key);
        if(!hashArray[hashVal].isEmpty())
        {
            hashArray[hashVal].delete(key);
        }

    }

    public Node11_5 find(int key)
    {
        int hashVal = hashFunc(key);
        if(!hashArray[hashVal].isEmpty())
        {
            Node11_5 node = hashArray[hashVal].find(key);
            return node;
        }
        else
            return null;
    }
}

public class Problem11_5 {
    public static void main(String[] args) throws IOException
    {
        int aKey;
        Node11_5 aDataItem;
        int size, n, keysPerCell = 100;
        System.out.print("Enter size of hash table: ");
        size = getInt();
        System.out.print("Enter initial number of items: ");
        n = getInt();

        TreeHashTable theHashTable = new TreeHashTable(size);

        for(int j = 0; j<n; j++)
        {
            aKey = (int)(java.lang.Math.random() * keysPerCell * size);
            aDataItem = new Node11_5();
            aDataItem.iData = aKey;
            theHashTable.insert(aDataItem);
        }

        while(true)
        {
            System.out.print("\n==============================================");
            System.out.print("\n| PROBLEM 11.5 OPTIONS:                      |");
            System.out.print("\n==============================================");
            System.out.print("\n| s : show tree                              |");
            System.out.print("\n| i : insert                                 |");
            System.out.print("\n| d : delete                                 |");
            System.out.print("\n| f : find                                   |");
            System.out.print("\n=============================================");
            System.out.print("\nPlease select an option: ");
            char choice = getChar();
            switch(choice)
            {
                case 's':
                    theHashTable.displayTable();
                    break;
                case 'i':
                    System.out.print("Enter key value to insert: ");
                    aKey = getInt();
                    aDataItem = new Node11_5();
                    aDataItem.iData = aKey;
                    theHashTable.insert(aDataItem);
                    break;
                case 'd':
                    System.out.print("Enter key value to delete: ");
                    aKey = getInt();
                    theHashTable.delete(aKey);
                    break;
                case 'f':
                    System.out.print("Enter key value to find: ");
                    aKey = getInt();
                    aDataItem = theHashTable.find(aKey);
                    if(aDataItem != null)
                        System.out.println("Found " + aKey);
                    else
                        System.out.println("Could not find " + aKey);
                    break;
                default:
                    System.out.println("Invalid Entry!");
            }
        }
    }

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
