// Reynerio Rubio
// ID# 109899097
// ESE 554: Computational Models for Computer Engineers
// Chapter 4 Assignments

// 4.2 Create a Deque class based on the discussion of deques
// (double-ended queues) in this chapter. It should include
// insertLeft(), insertRight(), removeLeft(), removeRight(),
// isEmpty(), and isFull() methods. It will need to support
// wraparound at the end of the array, as queues do.


class Deque{

    private int maxSize;
    private long[] dequeArray;
    private int front;
    private int back;
    private int numItems;

    public Deque(int s)
    {
        maxSize = s +1;
        dequeArray = new long[maxSize];
        front = 0;
        back = -1;
        numItems = 0;
    }

    // insert from the back.
    public void insertLeft(long val)
    {
        if(isFull())
        {
            System.out.println("Dequeue full! Can't insertLeft.");
            return;
        }

        if(back == maxSize -1)
            back = -1;

        dequeArray[++back] = val;
        numItems++;
    }

    // insert from the front
    public void insertRight(long val)
    {
        if(isFull())
        {
            System.out.println("Dequeue full! Can't insertRight.");
            return;
        }

        if(front == 0)
            front = maxSize - 1;
        dequeArray[--front] = val;
        numItems++;
    }

    // remove from the back
    public long removeLeft()
    {
        if(isEmpty())
        {
            System.out.println("Dequeue full! Can't removeLeft.");
            return -1;
        }

        long removed = dequeArray[back];    // store removed value as return value for function
        dequeArray[back--] = 0;             // replace with 0 and decrement back
        if(back == -1)                      // wrap around
            back = maxSize -1;

        numItems--;                         // removed item

        return removed;                     // return value of removed item
    }

    // remove from the front.
    public long removeRight()
    {
        if(isEmpty())
        {
            System.out.println("Dequeue full! Can't removeRight.");
            return -1;
        }

        long removed = dequeArray[front];   // store removed value as return value for function
        dequeArray[front++] = 0;            // replace with 0 and increment front
        if(front == maxSize)                // wrap around
            front = 0;

        numItems--;                         // removed item

        return removed;                     // return value of removed item
    }

    public boolean isEmpty()
    {
        return (numItems == 0);
    }

    public boolean isFull()
    {
        return (numItems == maxSize);
    }

    public void print()
    {
        if(isEmpty())
        {
            System.out.println("Deque empty! Nothing to print.");
            return;
        }

        while( !isEmpty() )
        {
            long val = removeRight();
            System.out.print(val); System.out.print(" ");
        }

    }
}

public class Problem4_2 {

    public static void main(String[] args) {

        Deque test = new Deque(10);

        test.insertLeft(10);
        test.insertLeft(20);
        test.insertLeft(30);
        test.insertLeft(40);
        test.insertLeft(50);
        test.insertLeft(60);
        test.insertLeft(70);
        test.insertLeft(80);
        test.insertLeft(90);
        test.insertLeft(100);
        test.insertLeft(110);

        // deqeue full after these values
        test.insertLeft(120);
        test.insertRight(130);
        test.insertRight(140);
        test.insertLeft(150);
        test.removeRight();
        test.removeRight();

        test.insertLeft(50);
        test.insertLeft(60);
        test.insertLeft(70);
        test.insertLeft(80);

        test.removeRight();
        test.removeLeft();
        test.removeLeft();
        test.insertRight(200);
        test.insertLeft(300);

        test.print();
    }
}
