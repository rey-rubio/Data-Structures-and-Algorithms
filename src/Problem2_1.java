// Reynerio Rubio (ID# 109899097)
//
// Problem2_1.java
// demonstrates array class with high-level interface
//
// Chapter 2, Problem 2.1
// To the HighArray class in the highArray program (Listing 2.3), add a method called getMedian()
// –1 if the array is empty. Add some code in main() to exercise this method. You can assume all the

// keys are positive numbers.
class highArray{
    private long[] a;               // ref to array a
    private int nElems;             // number of data items

    // constructor
    public highArray(int max){
        a = new long[max];          // create the array
        nElems = 0 ;                // no items yet
    }

    // find specified value
    public boolean find(long searchKey){
        int j;

        for(j = 0; j < nElems; j++)
            if(a[j] == searchKey)
                break;
        if(j == nElems)
            return false;   // can't find it
        else
            return true;    // found it
    }

    // put element into array
    public void insert(long value){
        a[nElems] = value;  // insert it
        nElems++;           // increment size
    }

    // delete element from array
    public boolean delete(long value){
        int j;

        for (j = 0; j < nElems; j++)
            if (value == a[j]) break;

        if (j == nElems)
            return false;      // can’t find it
        else{
            // found it
            // move higher ones down
            for (int k = j; k < nElems; k++)
                a[k] = a[k + 1];

            nElems--;           // decrement size
            return true;
        }
    }

    // return median
    public double getMedian(){
        double median;

        if(nElems <= 0)
        {
            return -1;
        }
        else
        {
            int midIndex = nElems / 2;;
            // get middle index
            if(nElems % 2 != 0){ // odd number of elements
                median = a[midIndex];
            }
            else{
                median = (a[midIndex] + a[midIndex-1]) / 2.0;
            }

            return median;
        }
    }



    // displays array contents
    public void display(){

        for(int j=0; j<nElems; j++)
            System.out.print(a[j] + " ");

        if(nElems <=0)
            System.out.println("Empty!");

        System.out.println("");
    }
}

// Reynerio Rubio (ID# 109899097)
//
// main method for testing highArray
// Chapter 2, Problem 2.1

class Problem2_1 {
    public static void main(String[] args) {
        int maxSize = 100;            // array size
        highArray arr;                // reference to array

        // create new array
        arr = new highArray(maxSize);
        arr.insert(77);
        arr.insert(99);
        arr.insert(44);
        arr.insert(55);
        arr.insert(22);
        arr.insert(88);
        arr.insert(11);
        arr.insert(00);
        arr.insert(66);
        arr.insert(33);
        arr.display();                // display items

        int searchKey = 35;           // search for item
        if (arr.find(searchKey))
            System.out.println("Found" + searchKey);
        else
            System.out.println("Can’t find " + searchKey);

        // delete 3 items
        arr.delete(00);
        arr.delete(55);
        arr.delete(99);
        arr.display();
        System.out.println("Median: " + arr.getMedian());    // display median


        // create new array
        highArray arr1 = new highArray(maxSize);
        System.out.println("Median: " + arr1.getMedian());    // display median
        arr1.insert(77);
        arr1.insert(55);
        arr1.insert(66);
        arr1.insert(55);
        arr1.display();
        System.out.println("Median: " + arr1.getMedian());    // display median

        arr1.insert(55);
        arr1.insert(66);
        arr1.insert(101);
        arr1.insert(55);
        arr1.insert(55);
        arr1.insert(55);
        arr1.insert(66);
        arr1.insert(55);
        arr1.display();
        System.out.println("Median: " + arr1.getMedian());    // display median


        highArray arr2 = new highArray(maxSize);
        arr2.insert(1);
        arr2.insert(2);
        arr2.insert(3);
        arr2.insert(5);
        arr2.insert(5);
        arr2.insert(218);
        arr2.insert(3);
        arr2.insert(218);
        arr2.insert(123);
        arr2.insert(123);
        arr2.insert(33);
        arr2.insert(55);
        arr2.display();
        System.out.println("Median: " + arr2.getMedian());    // display median
    }

}