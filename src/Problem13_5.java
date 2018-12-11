// Reynerio Rubio
// ID# 109899097
// ESE 554: Computational Models for Computer Engineers
// Chapter 13 Assignments
// Problem 13_5
// The Knight’s Tour is an ancient and famous chess puzzle. The object is to move
// a knight from one square to another on an otherwise empty chess board until it
// has recentlyVisited every square exactly once. Write a program that solves this puzzle
// using a depth-first search. It’s best to make the board size variable so that
// you can attempt solutions for smaller boards. The regular 8×8 board can take
// years to solve on a desktop computer, but a 5×5 board takes only a minute or so.
//
// Refer to the section “Depth-First Search and Game Simulations” in this chapter.
// It may be easier to think of a new knight being created and remaining on the new
// square when a move is made. This way, a knight corresponds to a vertex, and a
// sequence of knights can be pushed onto the stack. When the board is completely
// filled with knights (the stack is full), you win. In this problem the board is
// traditionally numbered sequentially, from 1 at the upper-left corner to 64 at
// the lower-right corner (or 1 to 25 on a 5×5 board). When looking for its next
// move, a knight must not only make a legal knight’s move, it must also not move
// off the board or onto an already-occupied (recentlyVisited) square. If you make the
// program display the board and wait for a keypress after every move, you can
// watch the progress of the algorithm as it places more and more knights on the
// board, and then, when it gets boxed in, backtracks by removing some knights and
// trying a different series of moves. We’ll have more to say about the complexity
// of this problem in the next chapter.
import java.io.*;

class KnightStack
{
    private int[] ks;
    private int top;

    public KnightStack(int numVertices)      // constructor
    {
        ks = new int[numVertices];                 // make array
        top = -1;
    }

    public void push(int j)                 // put item on stack
    {
        ks[++top] = j;
    }

    public int pop()                        // take item off stack
    {
        return ks[top--];
    }

    public int peek()                       // peek at top of stack
    {
        return ks[top];
    }

    public boolean isEmpty()                // true if nothing on stack
    {
        return (top == -1);
    }

    public boolean isFull()                 // true if stack is full
    {
        return (top == ks.length-1);
    }

}

class VisitedStack
{
    private int[] st;
    private int top;

    public VisitedStack()               // constructor
    {
        st = new int[8];             // make array
        top = -1;
    }

    public void push(int j)             // put item on stack
    {
        st[++top] = j;
    }

    public int pop()                    // take item off stack
    {
        return st[top--];
    }

    public int peek()                   // peek at top of stack
    {
        return st[top];
    }

    public boolean isEmpty()            // true if nothing on stack
    {
        return (top == -1);
    }

    public boolean isFull()             // true if stack is full
    {
        return (top == st.length-1);
    }

}

class KnightVertex
{
    public char label;
    public boolean wasVisited;
    public int recentlyVisited;
    public VisitedStack visitedStack;

    public KnightVertex()
    {
        label = '*';
        wasVisited = false;
        recentlyVisited = -1;
        visitedStack = new VisitedStack();
    }

}
class KnightGraph
{
    private int size;
    private int numVertices;
    //private int nVerts;
    private KnightVertex knightVertices[];
    private int adjacencyMatrix[][];
    private KnightStack knightStack;
    private boolean userMoving;
    public KnightGraph(int s)
    {
        size = s;
        numVertices = size*size;
        knightVertices = new KnightVertex[numVertices];
        adjacencyMatrix = new int[numVertices][numVertices];
        userMoving = true;

        // initialize adjacency matrix with 0's
        for(int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                adjacencyMatrix[i][j] = 0;
            }
        }
        // initialize knightStack
        knightStack = new KnightStack(numVertices);

        // initialize board
        for(int i = 0; i < numVertices; i++)
            knightVertices[i] = new KnightVertex();

        // initialize knight moves
        for(int i = 0; i < size; i++)
            for(int j = 0; j < size; j++)
                knightMoves(i, j);
    }

    private void knightMoves(int i, int j)
    {
        int vertex = i * size + j;

        // identify moves
        // Left 1
        if(i - 1 >=  0)
        {
            // Up 2
            if(j + 2 < size)
                addEdge(vertex, vertex - size + 2);
            // Down 2
            if(j - 2 >= 0)
                addEdge(vertex, vertex - size - 2);
        }
        // Right 1, Up/Down 2
        if(i + 1 < size)
        {
            // Up 2
            if(j + 2 < size)
                addEdge(vertex, vertex + size + 2);
            // Down 2
            if(j - 2 >= 0)
                addEdge(vertex, vertex + size - 2);

        }
        // Down 2
        if(i - 2 >= 0)
        {
            // Left 1
            if(j - 1 >= 0)
                addEdge(vertex, vertex - (size * 2) - 1);
            // Right 1
            if(j + 1 < size)
                addEdge(vertex, vertex - (size * 2) + 1);

        }

        // Up 2,
        if(i + 2 < size) {
            // Left 1
            if (j - 1 >= 0)
                addEdge(vertex, vertex + (size * 2) - 1);
            // Right 1
            if (j + 1 < size)
                addEdge(vertex, vertex + (size * 2) + 1);
        }

    }

    private void addEdge(int start, int end)
    {
        adjacencyMatrix[start][end] = 1;
    }

    private void displayVertex(int v)
    {
        System.out.print(knightVertices[v].label);
    }

    // DFS starting from initial node/vertex
    private boolean dfs(int start) throws IOException   // depth-first search
    {
        knightVertices[start].wasVisited = true;        // mark it
        knightStack.push(start);                        // push it
        int moves = 0;

        while(!knightStack.isEmpty())                   // until stack empty
        {
            moves++;
            int v = knightStack.peek();
            knightVertices[v].label = '?';

            // DISPLAY BOARD

            // WAIT FOR USER INPUT
            if(userMoving)
            {
                displayKnightBoard(moves);
                userKeyPress();
            }

            if(knightStack.isFull())	// check stop condition
            {
                for(int i = 0; i < numVertices; i++) // reset flags
                {
                    knightVertices[i].wasVisited = false;
                    knightVertices[i].label = '-';
                    knightVertices[i].recentlyVisited = -1;

                    while(!knightVertices[i].visitedStack.isEmpty())
                        knightVertices[i].visitedStack.pop();

                    KnightStack temp = new KnightStack(getNumVertices());

                    while(!knightStack.isEmpty())
                        temp.push(knightStack.pop());

                    while(!temp.isEmpty())
                        System.out.print(temp.pop() + " ");
                }
                knightVertices[0] = new KnightVertex();
                System.out.println();
                return true;
            }

            // next unvisited node is at the top of the knightStack
            int u = getNextVertex(knightStack.peek());

            if(u == -1)
            {   // if not exist
                int temp = knightStack.pop();
                // reset flags
                knightVertices[temp].label = '*';
                knightVertices[temp].wasVisited = false;
                knightVertices[temp].recentlyVisited = -1;
            }
            else
            {   // if it does exist
                int temp = knightStack.peek();
                //set flags
                knightVertices[u].wasVisited = true;
                knightVertices[temp].label = '!';
                knightVertices[temp].recentlyVisited = u;
                knightStack.push(u);
            }
        }

        // reset flags
        for(int j = 0; j < numVertices; j++)
        {
            knightVertices[j].wasVisited = false;
            knightVertices[j].label = '*';
            knightVertices[j].recentlyVisited = -1;
            while(!knightVertices[j].visitedStack.isEmpty())
                knightVertices[j].visitedStack.pop();
        }

        return false;
    }

    public int getNextVertex(int v)
    {
        for(int j = knightVertices[v].recentlyVisited + 1; j < numVertices; j++)
            if(adjacencyMatrix[v][j] == 1 && knightVertices[j].wasVisited == false)
                return j;
        return -1;
    }

    public void displayKnightBoard(int move)
    {
        System.out.print("\n===========================");
        System.out.print("\n| Move # " + move + "               |");
        System.out.print("\n===========================\n");
        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
                System.out.print(knightVertices[i * size + j].label + "\t");
            System.out.println();
        }
        System.out.println("==============================================");
    }



    public void userKeyPress() throws IOException
    {
        System.out.print("Press any key to see next move or 'q' finish simulation...");
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();

        if(s.isEmpty())
        {
            userMoving = true;
        }
        else if(s.equals("q"))
            userMoving = false;
        else
            userMoving = true;
    }

    public int getNumVertices()
    {
        return numVertices;
    }

    public int getSize()
    {
        return size;
    }

    public void simulate() throws IOException {
        boolean simulationDone = false;
        System.out.println("Simulating...");
        for(int i = 0; i < getNumVertices(); i++)
        {

            System.out.println("Starting at square " + i);
            simulationDone = dfs(i);
            if(simulationDone)
                System.out.println("Found!");
            else
                System.out.println("I'm sorry, not found");

            System.out.println();
        }
    }

}

class Problem13_5
{
    public static void main(String[] args) throws IOException
    {
        System.out.print("\n==============================================");
        System.out.print("\n| PROBLEM 13.5                               |");
        System.out.print("\n==============================================\n");

        // Create Knight Graph
        KnightGraph theGraph = new KnightGraph(5);


        System.out.print("\n==============================================");
        System.out.print("\n| KNIGHT GRAPH                               |");
        System.out.print("\n==============================================\n");

        theGraph.simulate();

    }
}