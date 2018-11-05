// Reynerio Rubio (ID# 109899097)
//
// Problem2_4.java
// demonstrates ordered array class
//
// Chapter 2, Problem 2.4
// Modify the orderedArray.java program (Listing 2.4) so that the insert() and
// delete() routines, as well as find(), use a binary search, as suggested in the text.

class OrdArray{
        private long[] a;         // ref to array a
        private int nElems;     // number of data items

        // constructor
        public OrdArray(int max){
                a = new long[max];      // create array
                nElems = 0;
        }

        public int size(){
            return nElems;
        }

        public int find(long searchKey){
                int lowerBound = 0;
                int upperBound = nElems-1;
                int curIn;

                while(true) {

                        curIn = (lowerBound + upperBound ) / 2;
                        if(a[curIn]==searchKey)
                                return curIn;              // found it
                        else if(lowerBound > upperBound)
                                return nElems;             // can’t find it
                        else{
                                // divide range
                                if(a[curIn] < searchKey)
                                        lowerBound = curIn + 1; // it’s in upper half
                                else
                                        upperBound = curIn - 1; // it’s in lower half
                        }  // end else divide range
                }
        }

        // put element into array
        public void insert(long value){

                int lowerBound = 0;
                int upperBound = nElems - 1;
                int i = 0;

                // binary search
                while(true) {

                        if(lowerBound > upperBound)
                                break; // done

                        i = (lowerBound + upperBound ) / 2;

                        if(value > a[i]){  // next index
                                lowerBound = ++i;
                        }
                        else
                                upperBound = i - 1;
                }

                // move bigger ones up
                for(int j = nElems; j > i; j--)
                        a[j] = a[j-1];

                a[i] = value;                  // insert it
                nElems++;                       // increment size
        }

        public boolean delete(long value){
                int j = find(value);

                if(j==nElems)
                        return false;     // can’t find it
                else{
                        // found it
                        for(int k=j; k<nElems; k++)
                                a[k] = a[k+1];  // move bigger ones down

                        nElems--;       // decrement size
                        return true;
                }
        }

        // displays array contents
        public void display(){
                for(int j=0; j<nElems; j++)
                        System.out.print(a[j] + " ");
                System.out.println("");
        }

}

// Reynerio Rubio (ID# 109899097)
//
// main method for testing ordArray.java
//
// Chapter 2, Problem 2.4
class Problem2_4 {

        public static void main(String[] args){
                int maxSize = 100;  // array size
                OrdArray arr;       // reference to array
                arr = new OrdArray(maxSize);   // create the array

                arr.insert(77); // insert 10 items
                arr.insert(99);
                arr.insert(44);
                arr.insert(55);
                arr.insert(22);
                arr.insert(88);
                arr.insert(11);
                arr.insert(00);
                arr.insert(66);
                arr.insert(33);
                arr.insert(00);

                arr.insert(100);
                arr.insert(22);
                arr.insert(00);
                arr.insert(2);
                int searchKey = 55;            // search for item
                if(arr.find(searchKey) != arr.size())
                        System.out.println("Found " + searchKey);
                else
                        System.out.println("Can’t find " + searchKey);

                arr.display();                 // display items

                arr.delete(00);          // delete 3 items
                arr.delete(55);
                arr.delete(99);

                arr.display();                 // display items again
        }
}
