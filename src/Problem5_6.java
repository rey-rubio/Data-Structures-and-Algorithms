// Reynerio Rubio
// ID# 109899097
// ESE 554: Computational Models for Computer Engineers
// Chapter 5 Assignments

// Problem 5.6
// Let’s try something a little different: a two-dimensional linked list,
// which we’ll call a matrix. This is the list analogue of a two-dimensional
// array. It might be useful in applications such as spreadsheet programs.
// If a spreadsheet is based on an array, and you insert a new row near the
// top, you must move every cell in the lower rows N*M cells, which is
// potentially a slow process. If the spreadsheet is implemented by a matrix,
// you need only change N pointers.
//
// For simplicity, we’ll assume a singly linked approach (although a
// double-linked approach would probably be more appropriate for a
// spreadsheet). Each link (except those on the top row and left side) is
// pointed to by the link directly above it and by the link on its left. You
// can start at the upper-left link and navigate to, say, the link on the
// third row and fifth column by following the pointers down two rows and
// right four columns. Assume your matrix is created with specified dimensions
// (7 by 10, for example). You should be able to insert values in specified
// links and display the contents of the matrix.


class Node
{
    int val;
    int row, col;
    Node right;
    Node down;

    public Node(int v)
    {
        val = v;
    }

    public Node(int v, int r, int c)
    {
        val = v;
        row = r;
        col = c;
    }

    public void displayNode()
    {
        System.out.print(val + "\t");
    }



}

class TwoDimLinkedList
{
    private int rows, cols;

    private Node first;
    private Node cursor;

    public TwoDimLinkedList(int N, int M)
    {
        rows = N;
        cols = M;

        first = new Node(-1, 0 ,0);
        cursor = first;

        // construct matrix(N rows, M cols)
        Node n;
        for(int r = 0; r < N; r++)
        {
            n = cursor;
            for(int c = 0; c < M; c++)
            {
                // move cursor right for all but last column
                if(c != M - 1)
                {
                    n.right = new Node(-1, r, c + 1);      // create link to new Node
                    n = n.right;                                        // move to new node
                }
            }

            // move cursor down for all but last row
            if(r != N - 1)
            {
                cursor.down = new Node(-1,r+1,0);            // create link to new Node
                cursor = cursor.down;                            // move to new node
            }


        }

    }

    public void insert(int row, int col, int val)
    {
        if( (row > rows - 1) || (col > cols -1) || (row < 0) || (col < 0))
        {
            System.out.println("Can't Insert!");
            return;
        }

        // start cursor at 0,0
        cursor = first;
        // move cursor down rows
        for(int r = 0; r < row; r ++)
        {
            cursor = cursor.down;
        }

        // move cursor across columns
        for(int c = 0; c < col; c++)
        {
            cursor = cursor.right;
        }

        // finally at correct index, change index
        cursor.val = val;


    }

    public void delete(int key)
    {
        cursor = first;

        Node n;
        Node prev;
        // iterate through all rows
        for(int r = 0; r < rows; r++)
        {
            n = cursor;
            prev = cursor;

            // iterate through all columns
            for(int c = 0; c < cols; c++)
            {
                // FOUND NODE TO DELETE
                if(n.val == key)
                {
                    n.val = -1;

                    prev.right = n.right;
                    return;
                }

                // NOT FOUND YET
                // keep moving cursor right for all but last column
                if(c != cols - 1)
                {
                    prev = n;                       // keep track of previous node
                    n = n.right;                    // move to new node
                }

            }

            // move cursor down for all but last row
            if(r != rows - 1)
            {
                cursor = cursor.down;                    // move to new node
            }


        }
    }
    public Node getFirst()
    {
        return first;
    }

    public TwoDimLinkedListIterator getIterator()
    {
        return new TwoDimLinkedListIterator(this);
    }
    public void display()
    {

        System.out.println("=======================");
        System.out.println("Displaying Spreadsheet:");
        System.out.println("=======================");
        cursor = first;
        Node n;

        while(cursor != null)
        {
            n = cursor;
            while(n != null)
            {
                n.displayNode();
                n = n.right;
            }
            System.out.println("");

            cursor = cursor.down;
        }
    }

}

class TwoDimLinkedListIterator
{
    private Node curr;          // current node
    private Node prev;         // previous node
    private TwoDimLinkedList myList;
    public TwoDimLinkedListIterator(TwoDimLinkedList list)
    {
        myList = list;
        reset();
    }
    public void reset()            // start at ‘first’
    {
        curr = myList.getFirst();
        prev = null;
    }

    public void right()         // go to right
    {
        prev = curr;
        curr = curr.right;
    }

    public void left()         // go to left
    {
        curr = prev;
    }

    public void down()         // go down
    {
        prev = curr;
        curr = curr.down;
    }

    public void deleteCurrent()
    {
        prev.down = curr.down;
        prev.right = curr.right;
        reset();
    }

    public Node getCurrent()
    {
        return curr;
    }
}
public class Problem5_6 {

    public static void main(String[] args)
    {

        System.out.println("Two Dimensional Linked List Map");
        TwoDimLinkedList spreadsheet = new TwoDimLinkedList(7, 10);

        spreadsheet.display();
        spreadsheet.insert(0,0,0);
        spreadsheet.insert(0,1,10);
        spreadsheet.insert(0,2,20);
        spreadsheet.insert(0,3,30);
        spreadsheet.insert(0,4,40);
        spreadsheet.insert(0,5,50);
        spreadsheet.insert(0,6,60);
        spreadsheet.insert(0,7,70);
        spreadsheet.insert(0,8,80);
        spreadsheet.insert(0,9,90);

        spreadsheet.insert(1,0,1);
        spreadsheet.insert(1,1,11);
        spreadsheet.insert(1,2,21);
        spreadsheet.insert(1,3,31);
        spreadsheet.insert(1,4,41);
        spreadsheet.insert(1,5,51);
        spreadsheet.insert(1,6,61);
        spreadsheet.insert(1,7,71);
        spreadsheet.insert(1,8,81);
        spreadsheet.insert(1,9,91);

        spreadsheet.insert(2,0,2);
        spreadsheet.insert(2,1,12);
        spreadsheet.insert(2,2,22);
        spreadsheet.insert(2,3,32);
        spreadsheet.insert(2,4,42);
        spreadsheet.insert(2,5,52);
        spreadsheet.insert(2,6,62);
        spreadsheet.insert(2,7,72);
        spreadsheet.insert(2,8,82);
        spreadsheet.insert(2,9,92);

        spreadsheet.insert(3,0,3);
        spreadsheet.insert(3,1,13);
        spreadsheet.insert(3,2,23);
        spreadsheet.insert(3,3,33);
        spreadsheet.insert(3,4,43);
        spreadsheet.insert(3,5,53);
        spreadsheet.insert(3,6,63);
        spreadsheet.insert(3,7,73);
        spreadsheet.insert(3,8,83);
        spreadsheet.insert(3,9,93);

        spreadsheet.insert(4,0,4);
        spreadsheet.insert(4,1,14);
        spreadsheet.insert(4,2,24);
        spreadsheet.insert(4,3,34);
        spreadsheet.insert(4,4,44);
        spreadsheet.insert(4,5,54);
        spreadsheet.insert(4,6,64);
        spreadsheet.insert(4,7,74);
        spreadsheet.insert(4,8,84);
        spreadsheet.insert(4,9,94);

        spreadsheet.insert(5,0,5);
        spreadsheet.insert(5,1,15);
        spreadsheet.insert(5,2,25);
        spreadsheet.insert(5,3,35);
        spreadsheet.insert(5,4,45);
        spreadsheet.insert(5,5,55);
        spreadsheet.insert(5,6,65);
        spreadsheet.insert(5,7,75);
        spreadsheet.insert(5,8,85);
        spreadsheet.insert(5,9,95);

        spreadsheet.insert(6,0,6);
        spreadsheet.insert(6,1,16);
        spreadsheet.insert(6,2,26);
        spreadsheet.insert(6,3,36);
        spreadsheet.insert(6,4,46);
        spreadsheet.insert(6,5,56);
        spreadsheet.insert(6,6,66);
        spreadsheet.insert(6,7,76);
        spreadsheet.insert(6,8,86);
        spreadsheet.insert(6,9,96);

        // DELETE 42 AND NAVIGATE TO SHOW THAT IT IS GONE
        spreadsheet.display();
        spreadsheet.delete(42);
        spreadsheet.display();

        System.out.println();
        TwoDimLinkedListIterator iter = spreadsheet.getIterator();

        iter.getCurrent().displayNode();
        System.out.println();

        iter.down();
        iter.getCurrent().displayNode();
        System.out.println();

        iter.down();
        iter.getCurrent().displayNode();
        System.out.println();


        iter.right();
        iter.getCurrent().displayNode();
        System.out.println();

        iter.right();
        iter.getCurrent().displayNode();
        System.out.println();

        iter.right();
        iter.getCurrent().displayNode();
        System.out.println();

        iter.right();
        iter.getCurrent().displayNode();
        System.out.println();

        iter.left();
        iter.getCurrent().displayNode();
        System.out.println();


        iter.right();
        iter.getCurrent().displayNode();
        System.out.println();

        iter.right();
        iter.getCurrent().displayNode();
        System.out.println();

        iter.deleteCurrent();       // delete 62 and reset cursor

        spreadsheet.display();

        // ITERATE THROUGH, DELETE 5, DISPLAY SPREADSHEET
        System.out.println();
        iter.getCurrent().displayNode();
        System.out.println();

        iter.down();
        iter.getCurrent().displayNode();
        System.out.println();

        iter.down();
        iter.getCurrent().displayNode();
        System.out.println();

        iter.down();
        iter.getCurrent().displayNode();
        System.out.println();

        iter.down();
        iter.getCurrent().displayNode();
        System.out.println();

        iter.down();
        iter.getCurrent().displayNode();
        System.out.println();

        iter.deleteCurrent();
        spreadsheet.display();

        // 5, 42, and 62 deleted at end

    }
}
