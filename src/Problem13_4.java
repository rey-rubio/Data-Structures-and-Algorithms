// Reynerio Rubio
// ID# 109899097
// ESE 554: Computational Models for Computer Engineers
// Chapter 13 Assignments


// Problem13.4
// Implement Warshall’s algorithm to find the transitive closure for a graph. You
// could start with the code from Programming Project 13.3. It’s useful to be able
// to display the adjacency matrix at various stages of the algorithm’s operation.

class StackX
{
    private final int SIZE = 20;
    private int[] st;
    private int top;

    public StackX()             // constructor
    {
        st = new int[SIZE];     // make array
        top = -1;
    }

    public void push(int j)     // put item on stack
    {
        st[++top] = j;
    }

    public int pop()            // take item off stack
    {
        return st[top--];
    }

    public int peek()           // peek at top of stack
    {
        return st[top];
    }

    public boolean isEmpty()    // true if nothing on stack
    {
        return (top == -1);
    }
}

class Vertex
{
    public char label;          // label (e.g. ‘A’)
    public boolean wasVisited;

    public Vertex(char lab)     // constructor
    {
        label = lab;
        wasVisited = false;
    }
}

class WarshallGraph
{
    private final int MAX_VERTS = 20;
    private Vertex vertexList[];            // list of vertices
    private int adjMat[][];                 // adjacency matrix
    private int nVerts;                     // current number of vertices
    private StackX theStack;

    public WarshallGraph()
    {
        vertexList = new Vertex[MAX_VERTS]; // adjacency matrix
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        for (int j = 0; j < MAX_VERTS; j++)      // set adjacency
            for (int k = 0; k < MAX_VERTS; k++)  // matrix to 0
                adjMat[j][k] = 0;
        theStack = new StackX();
    }


    public void addVertex(char lab)
    {
        vertexList[nVerts++] = new Vertex(lab);
    }


    public void addEdge(int start, int end)
    {
        adjMat[start][end] = 1;
    }

    public void displayVertex(int v)
    {
        System.out.print(vertexList[v].label);
    }

    // DFS starting from initial node/vertex
    public void dfs(int init)
    {
        vertexList[init].wasVisited = true;         // mark it
        displayVertex(init);                        // display it
        theStack.push(init);                        // push it

        while (!theStack.isEmpty())                // until stack empty,
        {
            // get an unvisited vertex adjacent to stack top
            int v = getAdjUnvisitedVertex(theStack.peek());

            if (v == -1)                             // if no such vertex,
                theStack.pop();
            else                                    // if it exists,
            {
                vertexList[v].wasVisited = true;    // mark it
                displayVertex(v);                   // display it
                theStack.push(v);                   // push it
            }
        }

        // stack is empty, so we’re done
        for (int j = 0; j < nVerts; j++)            // reset flags
        {
            vertexList[j].wasVisited = false;
        }
    }

    // returns an unvisited vertex adj to v
    public int getAdjUnvisitedVertex(int v)
    {
        for (int j = 0; j < nVerts; j++)
        {
            if (adjMat[v][j] == 1 && vertexList[j].wasVisited == false)
                return j;
        }
        return -1;
    }

    // Returns adjacency matrix adjMat
    public int[][] getAdjMat()
    {
        return adjMat;
    }

    // Displays the a "Connectivity Table" from Problem 13.3
    public void displayConnectivityTable()
    {
        for (int v = 0; v < nVerts; v++)
        {
            dfs(v);
            System.out.println();
        }
    }

    // Implement's Warshall's algorithm to find the transitive closure for a graph
    public int[][] warshallsAlgorithm()
    {

        // Warshall's Adjacency Matrix
        int[][] warshallsAdjMatrix = new int[MAX_VERTS][MAX_VERTS];

        // copy original adjMat
        for (int i = 0; i < nVerts; i++)
        {
            for (int j = 0; j < nVerts; j++)
            {
                warshallsAdjMatrix[i][j] = adjMat[i][j];
            }
        }

        // iterate through each row
        for (int r = 0; r < nVerts; r++)
        {
            // iterate through each column
            for (int c = 0; c < nVerts; c++)
            {
                // if there is an edge here
                if (adjMat[r][c] == 1)
                {
                    // for each vertex, look for other edges that connect here
                    for (int v = 0; v < nVerts; v++)
                    {
                        if (adjMat[v][r] == 1)
                            warshallsAdjMatrix[v][c] = 1;
                    }
                }
            }
        }
        return warshallsAdjMatrix;
    }



    // Displays Matrix to console
    public void displayMatrix(int[][] matrix)
    {
        // print row headers
        for (int i = 0; i < nVerts; i++)
        {

            System.out.print("=====");
        }
        System.out.println();
        System.out.print("  | ");
        for (int i = 0; i < nVerts; i++)
        {
            System.out.print(vertexList[i].label + "\t");
        }

        System.out.println(" |");
        for (int i = 0; i < nVerts; i++)
        {

            System.out.print("=====");
        }
        System.out.println();
        for (int r = 0; r < nVerts; r++)
        {
            System.out.print(vertexList[r].label + " | ");
            for (int c = 0; c < nVerts; c++)
            {

                System.out.print(matrix[r][c] + "\t");
            }
            System.out.println(" |");
        }

        for (int i = 0; i < nVerts; i++)
        {

            System.out.print("=====");
        }
    }
}

class Problem13_4 {
    public static void main(String[] args)
    {
        System.out.print("\n==============================================");
        System.out.print("\n| PROBLEM 13.4                               |");
        System.out.print("\n==============================================\n");


        // Create Warshall Graph
        WarshallGraph theGraph = new WarshallGraph();
        theGraph.addVertex('A');    // 0 (start for dfs)
        theGraph.addVertex('B');    // 1
        theGraph.addVertex('C');    // 2
        theGraph.addVertex('D');    // 3
        theGraph.addVertex('E');    // 4
        theGraph.addEdge(0, 2);         // A --> C
        theGraph.addEdge(1, 0 );        // B --> A
        theGraph.addEdge(1, 4);         // B --> E
        theGraph.addEdge(3, 4);         // D --> E
        theGraph.addEdge(4, 2);         // E --> C

        // Display OLD Adjacency Matrix
        System.out.print("\n==============================================");
        System.out.print("\n| ADJACENCY MATRIX                           |");
        System.out.print("\n==============================================\n");
        theGraph.displayMatrix(theGraph.getAdjMat());


        // Display Connectivity Table
        System.out.print("\n==============================================");
        System.out.print("\n| CONNECTIVITY TABLE                         |");
        System.out.print("\n==============================================\n");
        theGraph.displayConnectivityTable();

        // Call Warshall's Algorithm to find the transitive closure for a graph
        int[][] newAdjMat = theGraph.warshallsAlgorithm();

        // Display WARSHALL'S Adjacency Matrix
        System.out.print("\n==============================================");
        System.out.print("\n| WARSHALL'S ADJACENCY MATRIX                |");
        System.out.print("\n==============================================\n");
        theGraph.displayMatrix(newAdjMat);
    }
}
