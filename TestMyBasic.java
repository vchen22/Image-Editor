
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.util.EmptyStackException;
import java.util.*;

//test more on resizeable

@FixMethodOrder(MethodSorters.JVM)
public class TestMyBasic {

    static Stack<Integer> obj = null;

    @Before
    public void testPush() {
        //obj = new Stack(10);
        obj = new Stack(10, 0.75f, 0.25f);
        for(int i=0;i<6;i++)
            obj.push(new Integer(i));
    }

    
    @Test 
    public void testIsEmptyFalse()
    {
        Assert.assertEquals(obj.isEmpty(), false);
    }

    @Test
    public void testEmptyTrue()
    {
        obj.pop();
        obj.pop();
        obj.pop();
        obj.pop();
        obj.pop();
        obj.pop();
        Assert.assertEquals(obj.isEmpty(), true);
    }
    

    
    @Test
    public void testGetCapacity()
    {
        Assert.assertEquals(obj.getCapacity(), 10);
    }

    @Test (expected = NullPointerException.class)
    public void testPushNull()
    {
        obj.push(null);
    }

    @Test (expected = EmptyStackException.class)
    public void testPopEmpty()
    {
        obj.clear();
        obj.pop();
    }
    

    @Test
    public void testpop() {

        //obj.pop();
        Assert.assertEquals(obj.pop(), new Integer(5));
    }

    
    @Test (expected = EmptyStackException.class)
    public void testPeekEmpty()
    {
        obj.clear();
        obj.peek();
    }

    @Test
    public void testpeek() {
        Assert.assertEquals(obj.peek(), new Integer(5));
        Assert.assertEquals(obj.pop(), new Integer(5));
    }

    @Test
    public void testMultiPush() {
        Integer[] arr = new Integer[]{11,22,33};
        obj.multiPush(arr);
        Assert.assertEquals(obj.pop(), new Integer(33));
        Assert.assertEquals(obj.peek(), new Integer(22));
    }
   


    /*
    //only for 1st constructor
    @Test (expected = StackOverflowError.class)
    public void testMultiPushOverflow()
    {
        Integer[] arr = new Integer[]{6,7,8,9,10,11};
        obj.multiPush(arr);
    }
    */
    

    
    @Test
    public void testClear()
    {
        obj.clear();
        Assert.assertEquals(obj.isEmpty(), true);
    }
    

    /*
    @Test
    public void testIsFullTrue()
    {
        Integer[] arr = new Integer[]{6,7,8,9};
        obj.multiPush(arr);
        Assert.assertEquals(obj.isFull(), true);
        Assert.assertEquals(obj.currentSize(), 10);
    }
    */
    

    
    @Test
    public void testIsFullFalse()
    {
        Assert.assertEquals(obj.isFull(), false);
    }

    @Test
    public void testCurrentSize()
    {
        Assert.assertEquals(obj.currentSize(), 6);
    }

    @Test
    public void testMultiPop()
    {
        Integer[] arr = new Integer[]{5,4,3,2,1,0};
        Assert.assertEquals(obj.multiPop(6), arr);
    }

    @Test (expected = EmptyStackException.class)
    public void testMultiPopEmpty()
    {
        obj.clear();
        obj.multiPop(5);
    }

    @Test
    public void testMultiPopGreater()
    {
        Integer[] arr = new Integer[]{5,4,3,2,1,0};
        Assert.assertEquals(obj.multiPop(10), arr);
    }

    @Test (expected = EmptyStackException.class)
    public void testReverseEmpty()
    {
        obj.clear();
        obj.reverse();
    }

    @Test
    public void testReverse()
    {
        Integer arr[] = new Integer[]{5,4,3,2,1,0,null,null,null,null};
        obj.reverse();
        System.out.println(Arrays.toString(obj.arr));
        System.out.println(Arrays.toString(arr));
        Assert.assertEquals(obj.peek(), new Integer(0));
    }
    

    /*
    @Test (expected = StackOverflowError.class)
    public void testPushUniqueFull()
    {
        Integer[] arr = new Integer[]{6,7,8,9};
        obj.multiPush(arr);
        obj.pushUnique(5);
    }
    */
    

    
    @Test (expected = NullPointerException.class)
    public void testPushUniqueNull()
    {
        obj.pushUnique(null);
    }

    @Test
    public void testPushUniqueTrue()
    {
        Assert.assertEquals(obj.pushUnique(6), true);
        Assert.assertEquals(obj.pushUnique(7), true);
    }

    @Test
    public void testPushUniqueFalse()
    {
        Assert.assertEquals(obj.pushUnique(5), false);
    }

    @Test (expected = EmptyStackException.class)
    public void testSearchEmpty()
    {
        obj.clear();
        obj.search(3);
    }

    @Test
    public void testSearch()
    {
        Assert.assertEquals(obj.search(5), 1);
        Assert.assertEquals(obj.search(3), 3);
        Assert.assertEquals(obj.search(7), -1);
    }

    @Test
    public void testIncreaseSize()
    {
        Integer[] arr = new Integer[]{6,7,8};
        obj.multiPush(arr);
        Assert.assertEquals(obj.getCapacity(), 20);
    }

    @Test
    public void testDecreaseSize()
    {
        obj.multiPop(5);
        Assert.assertEquals(obj.getCapacity(), 5);
    }
   

}
