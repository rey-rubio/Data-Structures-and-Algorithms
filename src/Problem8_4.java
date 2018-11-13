// Reynerio Rubio
// ID# 109899097
// ESE 554: Computational Models for Computer Engineers
// Chapter 8 Assignments

// Problem 8.4
// Write a program that transforms a postfix expression into a tree
// such as that shown in Figure 8.11 in this chapter. You’ll need to
// modify the Tree8_2 class from the tree.java program (Listing 8.1) and
// the ParsePost class from the postfix.java program (Listing 4.8) in
// Chapter 4. There are more details in the discussion of Figure 8.11.
// After the tree is generated, traversals of the tree will yield the
// prefix, infix, and postfix equivalents of the algebraic expression.
// The infix version will need parentheses to avoid generating
// ambiguous expressions. In the inOrder() method, display an opening
// parenthesis before the first recursive call and a closing parenthesis
// after the second recursive call.


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Node8_4 {
    public String sData;              // data item (key)
    public Node8_4 leftChild;         // this node’s left child
    public Node8_4 rightChild;        // this node’s right child

    public void displayNode()      // display ourself
    {
        System.out.print("{");
        System.out.print(sData);
        System.out.print("} ");
    }
}


class Tree8_4 {
    private Node8_4 root;             // first node of tree
    private Stack<Node8_4> nodeStack;

    public Tree8_4(String s)                  // constructor
    {
        // parse input string
        nodeStack = new Stack<Node8_4>();
        for(int i = 0; i < s.length(); i++)
        {
            Node8_4 tempNode;
            if(s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '/' || s.charAt(i) == '*')
            {
                tempNode = new Node8_4();
                tempNode.sData = Character.toString(s.charAt(i));
                tempNode.rightChild = nodeStack.pop();
                tempNode.leftChild = nodeStack.pop();
                nodeStack.push(tempNode);
            }
            else
            {
                tempNode = new Node8_4();
                tempNode.sData = Character.toString(s.charAt(i));
                nodeStack.push(tempNode);
            }
        }

        root = nodeStack.pop();
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

    private void preOrder(Node8_4 localRoot)
    {
        if(localRoot != null)
        {
            System.out.print("(");
            inOrder(localRoot.leftChild);
            System.out.print(localRoot.sData + " ");
            inOrder(localRoot.rightChild);
            System.out.print(")");
        }
    }

    private void inOrder(Node8_4 localRoot)
    {
        if(localRoot != null)
        {
            inOrder(localRoot.leftChild);
            System.out.print(localRoot.sData + " ");
            inOrder(localRoot.rightChild);
        }
    }

    private void postOrder(Node8_4 localRoot)
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
                Node8_4 temp = (Node8_4) globalStack.pop();
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



public class Problem8_4 {

    public static void main(String[] args) throws IOException {
        int value;
        //Tree8_4 theTree = new Tree8_4("ABCDEF+/-*+");

        Tree8_4 theTree = new Tree8_4( "ABC+*");

        while (true) {
            System.out.print("\n\n==============================================");
            System.out.print("\n| PROBLEM 8.4 OPTIONS:                       |");
            System.out.print("\n==============================================");
            System.out.print("\n| s : show tree                              |");
            System.out.print("\n| t : traverse                               |");
            System.out.print("\n=============================================");
            System.out.print("\nPlease select an option: ");

            int choice = getChar();
            switch (choice) {
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

    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }

    public static char getChar() throws IOException {
        String s = getString();
        return s.charAt(0);
    }

    public static int getInt() throws IOException {
        String s = getString();
        return Integer.parseInt(s);

    }
}