// Reynerio Rubio
// ID# 109899097
// ESE 554: Computational Models for Computer Engineers
// Chapter 6 Assignments

// Problem 6.4
// Write a program that solves the knapsack problem for an arbitrary
// knapsack capacity and series of weights. Assume the weights are
// stored in an array. Hint: The arguments to the recursive knapsack()
// function are the target weight and the array index where the remaining
// items start.


class Knapsack
{
    private int target;
    private int size;
    private int[] knapsack;

    public Knapsack(int m, int[] w)
    {
        this.target = m;
        this.knapsack = new int[w.length];
    }

    public int knapsack(int[] weights, int w, int start)
    {
        // check for invalid start
        if (start == weights.length)
        {
            return -1;
        }
        // check for correct combination
        if (w + weights[start] == this.target)
        {
            knapsack[0] = weights[start];
            return 0;
        }
        // check for overflow
        if (w + weights[start] > this.target)
        {
            return knapsack(weights, w, start + 1);
        }
        // check for underflow
        int i = knapsack(weights, w + weights[start], start + 1);
        if (i == -1)
        {
            if (start < weights.length - 1) {
                i = knapsack(weights, w, start + 1);
                return i;
            }
            return i;
        }
        // found match
        knapsack[i + 1] = weights[start];
        this.size = i + 2;
        return i + 1;
    }

    public void display()
    {
        if(this.size == 0)
        {
            System.out.println("The knapsack is empty");
            return;
        }

        System.out.print("Knapsack: ");
        for(int i = 0; i < this.size; i++) {
            System.out.print(knapsack[i]);
            if(i != this.size - 1) System.out.print(", ");
        }
        System.out.println();
    }
}


public class Problem6_4 {

    public static void main(String[] args)
    {
        int[] myWeights1 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int capacity1 = 20;
        System.out.println("\nCapacity1: " + capacity1);
        Knapsack knapsack1 = new Knapsack(capacity1, myWeights1);

        knapsack1.knapsack(myWeights1, 0, 0);
        knapsack1.display();


        int[] myWeights2 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int capacity2 = 10;
        System.out.println("\nCapacity1: " + capacity2);
        Knapsack knapsack2 = new Knapsack(capacity2, myWeights1);
        knapsack2.knapsack(myWeights2,0,0);
        knapsack2.display();

        int[] myWeights3 = new int[]{2, 18, 11, 7, 9, 1, 18, 96};
        int capacity3 = 18;
        System.out.println("\nCapacity3: " + capacity3);
        Knapsack knapsack3 = new Knapsack(capacity3, myWeights1);
        knapsack3.knapsack(myWeights3,0,0);
        knapsack3.display();

    }
}
