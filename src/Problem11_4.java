// Reynerio Rubio
// ID# 109899097
// ESE 554: Computational Models for Computer Engineers
// Chapter 11 Assignments

// Write a rehash() method for the hash.java program. It should be
// called by insert() to move the entire hash table to an array
// about twice as large whenever the load factor exceeds 0.5. The
// new array size should be a prime number. Refer to the section
// “Expanding the Array” in this chapter. Don’t forget you’ll need
// to handle items that have been “deleted,” that is, written over
// with –1.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class DataItem {                                   // (could have more data)
    private int iData;              // data item (key)

    public DataItem(int ii)         // constructor
    {
        iData = ii;
    }

    public int getKey() {
        return iData;
    }
}

class HashTable {
    private DataItem[] hashArray;   // array holds hash table
    private int arraySize;
    private DataItem nonItem;       // for deleted items
    private int numItems;	        // used to determine loadFactor

    public HashTable(int size)      // constructor
    {
        arraySize = size;
        hashArray = new DataItem[arraySize];
        nonItem = new DataItem(-1);      // deleted item key is -1
        numItems = 0;
    }

    public void displayTable()
    {
        System.out.print("Table: ");
        for (int j = 0; j < arraySize; j++) {
            if (hashArray[j] != null)
                System.out.print(hashArray[j].getKey() + " ");
            else
                System.out.print("** ");
        }
        System.out.println("");
    }

    public int hashFunc(int key)
    {
        return key % arraySize;         // hash function
    }

    public void insert(DataItem item)      // insert a DataItem
    {                                      // (assumes table not full)
        int key = item.getKey();           // extract key
        int hashVal = hashFunc(key);       // hash the key until empty cell or -1,

        while (hashArray[hashVal] != null &&
                hashArray[hashVal].getKey() != -1) {
            ++hashVal;                 // go to next cell
            hashVal %= arraySize;      // wraparound if necessary
        }
        hashArray[hashVal] = item;    // insert item
        numItems++;
    }

    public DataItem delete(int key)     // delete a DataItem
    {
        int hashVal = hashFunc(key);        // hash the key
        while (hashArray[hashVal] != null)   // until empty cell,
        {                                   // found the key?
            if (hashArray[hashVal].getKey() == key) {
                DataItem temp = hashArray[hashVal]; // save item
                hashArray[hashVal] = nonItem;       // delete item
                numItems--;
                return temp;                        // return item
            }
            ++hashVal;                 // go to next cell
            hashVal %= arraySize;      // wraparound if necessary
        }
        return null;                  // can’t find item
    }

    public DataItem find(int key)    // find item with key
    {
        int hashVal = hashFunc(key);  // hash the key

        while (hashArray[hashVal] != null)   // until empty cell
        {                                   // found the key?
            if (hashArray[hashVal].getKey() == key)
                return hashArray[hashVal];   // yes, return item

            ++hashVal;                       // go to next cell
            hashVal %= arraySize;           // wraparound if necessary
        }
        return null;                  // can’t find item
    }

    public HashTable rehash()
    {
        int reHashSize = getPrime(arraySize*2);
        HashTable newTable = new HashTable(reHashSize);
        newTable.setNumItems(0);
        // re-insert into newTable
        for(int j = 0; j < arraySize; j++)
        {
            if(hashArray[j] != null && hashArray[j].getKey() != -1)
            {
                newTable.insert(hashArray[j]);
            }
        }
        return newTable;
    }

    public double getLoadFactor()
    {
        double loadFactor = numItems / (double)arraySize;
        return loadFactor;
    }

    private int getPrime(int min)	//returns 1st prime > min
    {
        for(int j = min+1; true; j++)
            if( isPrime(j) )
                return j;
    }

    // helper function to determine if a number is a prime number
    private boolean isPrime(int n)
    {
        for(int j = 2; (j*j <= n); j++)
        {
            if (n % j == 0)
                return false;
        }
        return true;
    }

    private void setNumItems(int n)
    {
        numItems = n;
    }
}


public class Problem11_4 {

    public static void main(String[] args) throws IOException
    {
        DataItem aDataItem; int aKey, size, n, keysPerCell;
        // get sizes
        System.out.print("Enter size of hash table: ");
        size = getInt();
        System.out.print("Enter initial number of items: ");
        n = getInt();
        keysPerCell = 10;

        // make table
        HashTable theHashTable = new HashTable(size);
        for(int j=0; j<n; j++)        // insert data
        {
            aKey = (int)(java.lang.Math.random() * keysPerCell * size);
            aDataItem = new DataItem(aKey);
            theHashTable.insert(aDataItem);

            if(theHashTable.getLoadFactor() >= 0.5)
            {
                System.out.println("Rehashing...");
                theHashTable = theHashTable.rehash();
            }
        }
        while(true)
        {
            System.out.print("\n==============================================");
            System.out.print("\n| PROBLEM 11.4 OPTIONS:                      |");
            System.out.print("\n==============================================");
            System.out.print("\n| s : show tree                              |");
            System.out.print("\n| i : insert                                 |");
            System.out.print("\n| d : delete                                 |");
            System.out.print("\n| f : find                                   |");
            System.out.print("\n=============================================");
            System.out.print("\nPlease select an option: ");
            char choice = getChar();

            switch(choice)
            {
                case 's':
                    theHashTable.displayTable();
                    break;
                case 'i':
                    System.out.print("Enter key value to insert: ");
                    aKey = getInt();
                    aDataItem = new DataItem(aKey);
                    theHashTable.insert(aDataItem);
                    if(theHashTable.getLoadFactor() >= 0.5)
                    {
                        System.out.println("Rehashing...");
                        theHashTable = theHashTable.rehash();
                    }
                    break;
                case 'd':
                    System.out.print("Enter key value to delete: ");
                    aKey = getInt();
                    theHashTable.delete(aKey);
                    break;
                case 'f':
                    System.out.print("Enter key value to find: ");
                    aKey = getInt();
                    aDataItem = theHashTable.find(aKey);
                    if(aDataItem != null)
                    {
                        System.out.println("Found " + aKey);
                    }
                    else
                        System.out.println("Could not find " + aKey);
                    break;
                default:
                    System.out.print("Invalid entry\n");
            }
        }
    }

    public static String getString() throws IOException
    {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }

    public static char getChar() throws IOException
    {
        String s = getString(); return s.charAt(0);
    }

    public static int getInt() throws IOException
    {
        String s = getString();
        return Integer.parseInt(s);
    }
}