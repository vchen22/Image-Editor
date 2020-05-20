import java.util.EmptyStackException;

/**
 * Author: Vicki Chen
 * CSE12 Login: cs12sp19af
 * Date: 4/26/19
 * File: Stack.java
 * Source of Help: PA4 write up, Piazza, CSE12 tutors
 *
 * This file contains the Stack class.
 * It gives functionality to the stack linear structure
 **/

/**
 * This class contains methods that checks the state of the stack, adds and 
 * remove elements, reverse the stack, and find the distance between the top
 * and an element
 * @param <E> Stack type
 **/
class Stack<E>
{
    float loadFactor;
    float shrinkFactor;
    int top; // Index of the top element
    E arr[];
    int capacity;

    private static final int HALF_CAP = 2;
    private static final int DOUBLE_CAP = 2;

    /**
     * Initializes stack with fixed capacity, it doesn't grow or shrink
     * @param capacity Given capacity
     * */
    Stack(int capacity)
    {
        this.capacity = capacity;
        arr = (E[]) new Object[capacity];
        top = 0;
    }

    /**
     * Intializes the stack with some initial capcity, but can grow in size
     * @param capacity Given capacity
     * @param loadFactor Growing factor
     * */
    Stack(int capacity, float loadFactor)
    {
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        arr = (E[]) new Object[capacity];
        top = 0;
    }

    /**
     * Intializes the stack with some initial capacity and can grow and shrink
     * in size
     * @param capacity Given capacity
     * @param loadFactor Grow factor
     * @param shrinkFactor Shrink factor
     * */
    Stack(int capacity, float loadFactor, float shrinkFactor)
    {
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        this.shrinkFactor = shrinkFactor;
        arr = (E[]) new Object[capacity];
        top = 0;
    }

    /**
     * Check if stack has no elements
     * @param none
     * @return Whether stack is empty or not
     * */
    boolean isEmpty()
    {
        //no elements
        if (top == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Return the total capacity of stack, the maximum number of elements it
     * can store currently
     * @param none
     * @return capacity Capacity of stack
     * */
    int getCapacity()
    {
        return this.capacity;
    }

    /**
     * Adds an element at the top of the stack and grow the size of the size
     * @param x Element to add
     * @return void
     * */
    void push(E x) throws StackOverflowError, NullPointerException
    {        
        if (this.isFull() == true && loadFactor == 0)
        {
            throw new StackOverflowError();
        }

        if (x == null)
        {
            throw new NullPointerException();
        }

        //check if need to grow in size
        if (loadFactor > 0 && (float)top/capacity >= loadFactor)
        {
            this.capacity = DOUBLE_CAP*capacity;
            E[] arrTemp = (E[]) new Object[capacity];

            //iterate through to pull all element into arr with new capacity
            for (int i = 0; i < top; i++)
            {
                arrTemp[i] = arr[i];
            }

            //set arr to the instance
            this.arr = arrTemp;
        }

        //put element on top and change top index
        arr[top] = x;
        top++;

    }

    /**
     * Removes the object at the top of the stack and returns it and shrink
     * the size of the stack
     * @param none
     * @return Removed object
     * */
    E pop() throws EmptyStackException
    {
        if (this.isEmpty() == true)
        {
            throw new EmptyStackException();
        }

        //check if need to shrink in size
        if (shrinkFactor > 0 && (float)top/capacity <= shrinkFactor)
        {
            this.capacity = capacity/HALF_CAP;

            E[] arrTemp = (E[]) new Object[capacity];

            //iterate through all elements into arr with new capacity
            for (int i = 0; i < top; i++)
            {
                arrTemp[i] = arr[i];
            }

            //set arr to the instance
            this.arr = arrTemp;
        }  

        //change top index
        top--;
        return arr[top];
    }

    /**
     * Returns the element at the top of the stack without removing it from
     * the stack
     * @param none
     * @return Element at top of stack
     * */
    E peek() throws EmptyStackException{
        if (this.isEmpty() == true)
        {
            throw new EmptyStackException();
        }
        else
        {
            //topmost element
            return arr[top-1];
        }
    }

    /**
     * Remove all elements from stack and doesn't change the capacity of stack
     * @param none
     * @return void
     * */
    void clear(){

        top = 0;
    }

    /**
     * Check is stack is full
     * @param none
     * @return State of fullness of stack
     * */
    boolean isFull(){
        //no more space
        if (top == this.getCapacity())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Returns the total number of the current elements in the stack
     * not the capacity of stack
     * @param none
     * @return Number of elements in the stack
     * */
    int currentSize(){

        return top;
    }

    /**
     * Pop a given number of elements from the stack and returns them as an 
     * array
     * @param k Number of element to pop
     * @return  kArr Array of elements that was popped
     * */
    E[] multiPop(int k) throws EmptyStackException{

        if (this.isEmpty() == true)
        {
            throw new EmptyStackException();
        }

        //pop all the elements is in array
        if (k > this.currentSize())
        {
            E[] kArr = (E[]) new Object[this.currentSize()];

            //iterate through array to put removed elements into another arr
            for (int i = 0; i < kArr.length; i++)
            {
                kArr[i] = this.pop();
            }

            return kArr;
        }

        //pop certain amount of element
        E[] kArr = (E[]) new Object[k];

        //iterate through to put that number of removed elements into an arr
        for (int i = 0; i < k; i++)
        {
            kArr[i] = this.pop();
        }

        return kArr;
    }

    /**
     * Adds all the element from the array at the top of the stack one by one
     * @param arr Array of elements to add
     * @return void
     * */
    void multiPush(E[] arr) throws StackOverflowError, NullPointerException{

        if (this.isFull() == true && loadFactor == 0)
        {
            throw new StackOverflowError();
        }

        if (arr == null)
        {
            throw new NullPointerException();
        }

        //iterate to use to helper method to add each element in given arr
        for (int i = 0; i < arr.length; i++)
        {
            this.push(arr[i]);
        }
    }

    /**
     * Reverse the elements in the stack
     * @param none
     * @return void
     * */
    void reverse() throws EmptyStackException{

        if (this.isEmpty() == true)
        {
            throw new EmptyStackException();
        }

        //swap elements with its corresponding indices
        //ignore nulls
        for (int i = 0; i < top/2; i++)
        {
            E temp1 = this.arr[i];
            E temp2 = this.arr[top-1-i];

            arr[i] = temp2;
            arr[top-1-i] = temp1;
        }
    }

    /**
     * Adds an element at the top of the stack, only if the current top
     * element of the stack is not the same as the element that we want to
     * push
     * @param x Element to add
     * @return Success in adding or not
     * */
    boolean pushUnique(E x) throws StackOverflowError, NullPointerException{
        if (this.isFull() == true && loadFactor == 0)
        {
            throw new StackOverflowError();
        }

        if (x == null)
        {
            throw new NullPointerException();
        }

        //check topmost element's value
        if (this.peek().equals(x))
        {
            return false;
        }
        else
        {
            //call helper method to add if its unique
            this.push(x);
            return true;
        }
    }

    /**
     * Returns the distance of the element from the top of the stack
     * @param x Element to search
     * @return distance The distance between elements
     * */
    int search(E x) throws EmptyStackException{

        if (this.isEmpty() == true)
        {
            throw new EmptyStackException();
        }

        int distance = -1;

        //iterate to see if element exist
        for (int i = top-1; i >= 0; i--)
        {
            //once found calculate distance
            if (arr[i].equals(x))
            {
                distance = top-i;
            }
        }

        return distance;
    }
}
