import org.junit.*;
import org.junit.runners.MethodSorters;

import java.util.Random;


@FixMethodOrder(MethodSorters.JVM)
public class TestImageEditor {

    static int[][] mat = new int[6][7];

    ImageEditor obj;

    @Before
    public void populate(){
        obj = new ImageEditor(mat);
        Random r = new Random(1234);
        for(int i=0;i<6;i++){
            for(int j=0;j<7;j++){
                mat[i][j] = r.nextInt(255);
            }
        }
    }

    @Test
    public void testDelete() {
        int temp = mat[0][0];
        obj.delete(0,0);
        obj.undo();
        Assert.assertEquals(temp, obj.mat[0][0], 1);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testDeleteIOOB()
    {
        obj.delete(10,1);
    }

    @Test
    public void testRedo() {
        Assert.assertFalse(obj.redo());
    }

    @Test
    public void testBlur()
    {
        int temp = mat[0][0];
        obj.blur(0,0,0.5f);
        Assert.assertEquals(temp*0.5f, obj.mat[0][0], 1);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testBlurIOOB()
    {
        obj.blur(0,10,0.5f);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testBlurIllegal()
    {
        obj.blur(0,0,1.5f);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testSharpenIOOB()
    {
        obj.sharpen(0,10,1.5f);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSharpenIllegal()
    {
        obj.sharpen(0,0,0.5f);
    }

    @Test
    public void testSharpen()
    {
        int temp = mat[0][0];
        obj.sharpen(0,0,1.5f);
        Assert.assertEquals(temp*1.5f, obj.mat[0][0], 1);
    }

    @Test
    public void testSharpen255()
    {
        int temp = mat[0][0];
        obj.sharpen(0,0,5.0f);
        Assert.assertEquals(255, obj.mat[0][0], 1);
    }

    @Test
    public void testUndo()
    {
        int temp = mat[0][0];
        obj.blur(0,0,0.5f);
        obj.sharpen(0,0,1.5f);
        obj.blur(0,0,0.75f);
        obj.undo();
        obj.undo();
        Assert.assertTrue(obj.undo());
        //Assert.assertFalse(obj.undo());
        Assert.assertEquals(temp, obj.mat[0][0], 1);
    }

    @Test
    public void testRedoEdits()
    {
        int temp = mat[0][0];
        obj.blur(0,0,0.5f);
        obj.sharpen(0,0,1.5f);
        obj.blur(0,0,0.75f);
        obj.undo();
        obj.undo();
        obj.undo();
        obj.redo();
        obj.redo();
        obj.redo();
        //obj.redo();
        Assert.assertFalse(obj.redo());
        //Assert.assertEquals(temp*0.5f*1.5f*0.75f, obj.mat[0][0], 1);

    }

    @Test
    public void testGetImage()
    {
        Assert.assertEquals(obj.getImage(), obj.mat);
    }


}
