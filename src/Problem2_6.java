// Reynerio Rubio (ID# 109899097)
//
// Problem2_6.java
// demonstrates array class with high-level interface
//
// Chapter 2, Problem 2.6
// Write a noDups() method for the HighArray class of the highArray.java program (Listing 2.3).
// This method should remove all duplicates from the array. That is, if three items with the key 17
// appear in the array, noDups() should remove two of them. Don’t worry about maintaining the order of the items.
// One approach is to first compare every item with all the other items and overwrite any duplicates with a
// null (or a distinctive value that isn’t used for real keys). Then remove all the nulls. Of course, the array
// size will be reduced. keys are positive numbers.
class HighArray{
    private long[] a;               // ref to array a
    private int nElems;             // number of data items

    // constructor
    public HighArray(int max){
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

    // get rid of duplicates
    public void noDups()
    {
        long value;
        for(int i = 0; i < nElems; i++){
            value = a[i];
            for(int j = i +1; j < nElems; j++){
                if(a[j] == value)
                    a[j] = -1;
            }
        }

        while(delete(-1)) { }

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
// main method for testing highArray.java
// Chapter 2, Problem 2.6

class Problem2_6 {

    public static void main(String[] args) {
        int maxSize = 100;            // array size
        HighArray arr3 = new HighArray(maxSize);    // create new array
        arr3.insert(77);
        arr3.insert(99);
        arr3.insert(11);
        arr3.insert(00);
        arr3.insert(66);
        arr3.insert(44);
        arr3.insert(44);
        arr3.insert(55);
        arr3.insert(22);
        arr3.insert(44);
        arr3.insert(55);
        arr3.insert(22);
        arr3.insert(88);
        arr3.insert(11);
        arr3.insert(00);
        arr3.insert(66);
        arr3.insert(44);
        arr3.insert(55);
        arr3.insert(22);
        arr3.insert(33);
        arr3.insert(77);
        arr3.insert(99);
        arr3.insert(44);
        arr3.insert(218);
        arr3.insert(55);
        arr3.insert(22);
        arr3.insert(88);
        arr3.insert(11);
        arr3.insert(00);
        arr3.insert(104);
        arr3.insert(66);
        arr3.insert(33);
        arr3.insert(77);
        arr3.insert(102);
        arr3.insert(99);
        arr3.insert(44);
        arr3.insert(55);
        arr3.insert(22);
        arr3.insert(88);
        arr3.insert(109);
        arr3.insert(11);
        arr3.insert(00);
        arr3.insert(66);
        arr3.insert(33);
        arr3.insert(218);
        arr3.insert(218);
        arr3.insert(218);
        arr3.insert(218);
        System.out.println("Array before noDups(): ");
        arr3.display();
        arr3.noDups();
        System.out.println("Array before noDups(): ");
        arr3.display();

        // create new array
        HighArray arr4 = new HighArray(maxSize);
        arr4.insert(1);
        arr4.insert(2);
        arr4.insert(3);
        arr4.insert(5);
        arr4.insert(5);
        arr4.insert(3);
        arr4.insert(213);
        arr4.insert(123);
        arr4.insert(123);
        arr4.insert(33);
        arr4.insert(55);
        arr4.insert(2373);
        arr4.insert(73);
        arr4.insert(213);
        arr4.insert(88);
        arr4.insert(0);
        arr4.insert(0);
        arr4.insert(0);
        arr4.insert(53);
        arr4.insert(453);
        arr4.insert(77);
        arr4.insert(4534);
        arr4.insert(453);
        arr4.insert(2418);
        arr4.insert(55);
        arr4.insert(22);
        arr4.insert(4530);
        arr4.insert(11);
        arr4.insert(00);
        arr4.insert(453);
        arr4.insert(66);
        arr4.insert(453);
        arr4.insert(77);
        arr4.insert(102);
        arr4.insert(452);
        arr4.insert(4243);
        arr4.insert(55);
        arr4.insert(212);
        arr4.insert(88);
        arr4.insert(783);
        arr4.insert(11);
        arr4.insert(5345);
        arr4.insert(453);
        arr4.insert(5243);
        arr4.insert(44453);
        arr4.insert(7513);
        arr4.insert(4534);
        arr4.insert(218);
        System.out.println("Array before noDups(): ");
        arr4.display();
        arr4.noDups();
        System.out.println("Array after noDups(): ");
        arr4.display();
    }
}