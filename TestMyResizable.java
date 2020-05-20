import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.EmptyStackException;

@FixMethodOrder(MethodSorters.JVM)
public class TestMyResizable {

    static Stack<Integer> obj = null;

    @Before
    public void testPush() {
        obj = new Stack(10, 0.75f, 0.25f);
        for(int i=0;i<20;i++)
            obj.push(new Integer(i));
    }

    @Test
    public void testMultiPush() {
        Integer[] arr = new Integer[]{11,22,33,12,90,20,10,11,20,20,12};
        obj.multiPush(arr);
        Assert.assertEquals(obj.pop(), new Integer(12));
    }

    /*
    @Test
    public void testMultiPop()
    {
        
    }
    */
}
