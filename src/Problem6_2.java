// Reynerio Rubio
// ID# 109899097
// ESE 554: Computational Models for Computer Engineers
// Chapter 6 Assignments

// Problem 6.2
// In Chapter 8, “Binary Trees,” we’ll look at binary trees, where every
// branch has (potentially) exactly two sub-branches. If we draw a binary
// tree on the screen using characters, we might have 1 branch on the top
// row, 2 on the next row, then 4, 8, 16, and so on. Here’s what that
// looks like for a tree 16 characters wide:

// --------X-------
// ----X-------X---
// --X---X---X---X-
// -X-X-X-X-X-X-X-X
// XXXXXXXXXXXXXXXX

// (Note that the bottom line should be shifted a half character-width
// right, but there’s nothing we can do about that with character-mode
// graphics.) You can draw this tree using a recursive makeBranches()
// method with arguments left and right, which are the endpoints of a
// horizontal range. When you first enter the routine, left is 0 and
// right is the number of characters (including dashes) in all the lines,
// minus 1. You draw an X in the center of this range. Then the method
// calls itself twice: once for the left half of the range and once for
// the right half. Return when the range gets too small. You will probably
// want to put all the dashes and Xs into an array and display the array
// all at once, perhaps with a display() method. Write a main() program
// to draw the tree by calling makeBranches() and display(). Allow main()
// to determine the line length of the display (32, 64, or whatever).
// Ensure that the array that holds the characters for display is no
// larger than it needs to be. What is the relationship of the number of
// lines (five in the picture here) to the line width?


import java.util.Arrays;


class BinaryTree
{
    private char[][] binaryTree;
    private int height;
    private int length;

    public BinaryTree(int length)
    {
        // make sure length is not negative
        if (length <= 0)
        {
            System.err.println("Length must be positive");
            return;
        }

        // make sure length is a power of 2
        int temp = length;
        while(temp != 1)
        {
            if(temp % 2 != 0)
            {
                System.err.println("Length must be power of 2");
                return;
            }
            temp /= 2;
        }

        // calculate height of tree given length
        this.length = length;
        this.height = (int) Math.ceil(Math.log(length + 1) / Math.log(2));

        // initialize tree to new height and length
        this.binaryTree = new char[height][length];

        // fill the tree with dashes
        for(int i = 0; i < height; i++)
        {
            Arrays.fill(binaryTree[i], '-');
        }
    }

    // recursive method to make the branches of the binary tree
    public void makeBranches(int left, int right, int level) {

        // calculate middle
        int middle = ((right - left + 1) / 2) + left;

        // set the middle of this branch as X
        this.binaryTree[level][middle] = 'X';

        // base
        if (left == right) {
            return;
        }
        // recursive left
        makeBranches(left, middle - 1, level + 1);

        // recursive right
        makeBranches(middle, right, level + 1);
    }

    // display contents of the binary tree
    public void display()
    {
        System.out.println("DISPLAYING BINARY TREE OF LENGTH " + length + ":");
        for (char[] x : binaryTree) {
            for (char c : x)
            {
                System.out.print(c);
            }
            System.out.println();
        }
        System.out.println();
    }
}


public class Problem6_2 {

    public static void main(String[] args) {

        // create binary trees

        int mylength8 = 8;
        int mylength16 = 16;
        int mylength32 = 32;
        int mylength64 = 64;
        int mylength128 = 128;
        int mylength256 = 256;
        int mylength512 = 512;


        BinaryTree testTree1 = new BinaryTree(0);
        BinaryTree testTree2 = new BinaryTree(-1);
        BinaryTree testTree3 = new BinaryTree(5);

        BinaryTree myTree8 = new BinaryTree(mylength8);
        BinaryTree myTree16 = new BinaryTree(mylength16);
        BinaryTree myTree32 = new BinaryTree(mylength32);
        BinaryTree myTree64 = new BinaryTree(mylength64);
        BinaryTree myTree128 = new BinaryTree(mylength128);
        BinaryTree myTree256 = new BinaryTree(mylength256);
        BinaryTree myTree512 = new BinaryTree(mylength512);

        // make branches
        int left = 0;
        int right8 = mylength8 - 1;
        int right16 = mylength16 - 1;
        int right32 = mylength32 - 1;
        int right64 = mylength64 - 1;
        int right128 = mylength128 - 1;
        int right256 = mylength256 - 1;
        int right512 = mylength512 - 1;
        myTree8.makeBranches(left, right8, 0);
        myTree16.makeBranches(left, right16, 0);
        myTree32.makeBranches(left, right32, 0);
        myTree64.makeBranches(left, right64, 0);
        myTree128.makeBranches(left, right128, 0);
        myTree256.makeBranches(left, right256, 0);
        myTree512.makeBranches(left, right512, 0);

        // diaplay the trees
        myTree8.display();
        myTree16.display();
        myTree32.display();
        myTree64.display();
        myTree128.display();
        myTree256.display();
        myTree512.display();

    }
}