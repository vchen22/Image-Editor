import java.util.*;

/**
 * Author: Vicki Chen
 * CSE12 Login: cs12sp19af
 * Date: 4/26/19
 * File: ImageEditor.java
 * Source of Help: PA4 write up, Piazza, CSE12 tutors
 *
 * This file contains the class ImageEditor.
 * Provides functionality on how to manipulate an image
 **/

/**
 * This class contains methods that lets you delete, blur, sharpen a pixel,
 * undo and redo function calls, and get the image.
 * */
public class ImageEditor {

    int[][] mat;
    Stack<int[][]> undo;
    Stack<int[][]> redo;

    private static final int PIX_MAX = 255;
    private static final int ARR_CAP = 10;
    private static final float LOAD_FAC = 0.5f;
    private static final float SHRINK_FAC = 0.25f;

    public ImageEditor(int[][] mat) {
        this.mat = mat;
        this.undo = new Stack(ARR_CAP, LOAD_FAC, SHRINK_FAC);
        this.redo = new Stack(ARR_CAP, LOAD_FAC, SHRINK_FAC);
    }

    /**
     * Set the value of at location i, j to 0
     * @param i row
     * @param j column
     * @return void
     * */
    void delete(int i, int j) throws IndexOutOfBoundsException{
        if (i < 0 || i > mat.length-1 || j < 0 || j > mat[0].length-1)
        {
            throw new IndexOutOfBoundsException();
        }

        //store current state to undo stack
        undo.push(this.getImageCopy());

        //set that pixel to 0
        mat[i][j] = 0;

        //cannot call redo after edit call
        redo.clear();

    }

    /**
     * Blurs pixel at location i, j (decrease sharpness)
     * @param i row
     * @param j column
     * @param blurFactor How much to blur
     * @return void
     * */
    void blur(int i, int j, float blurFactor) throws 
        IndexOutOfBoundsException, IllegalArgumentException{
            if (i < 0 || i > mat.length-1 || j < 0 || j > mat[0].length-1)
            {
                throw new IndexOutOfBoundsException();
            }

            if (blurFactor < 0 || blurFactor > 1)
            {
                throw new IllegalArgumentException();
            }

            //calculate new pixel value and round to nearest int
            int newValue = Math.round(blurFactor*mat[i][j]);

            //store current state into undo stack
            undo.push(this.getImageCopy());

            //set new calculated value to index
            mat[i][j] = newValue;

            //cannot call redo after edit call
            redo.clear();
        }

    /**
     * Sharpen the pixel at location i, j
     * @param i row
     * @param j col
     * @param sharpenFactor How much to sharpen
     * @return void
     * */
    void sharpen(int i, int j, float sharpenFactor) throws 
        IndexOutOfBoundsException, IllegalArgumentException{
            if (i < 0 || i > mat.length-1 || j < 0 || j > mat[0].length-1)
            {
                throw new IndexOutOfBoundsException();
            }

            if (sharpenFactor < 1)
            {
                throw new IllegalArgumentException();
            }

            //calculate new pixel value and round to the nearest int
            int newValue = Math.round(sharpenFactor*mat[i][j]);

            //check if calculation is over 255, if is, set it to the max value
            if (newValue > PIX_MAX)
            {
                newValue = PIX_MAX;
            }

            //store current state to undo stack
            undo.push(this.getImageCopy());

            //set new value to index
            mat[i][j] = newValue;

            //can't call redo after edit function
            redo.clear();

        }

    /**
     * Reverse the effect of last executed edit function
     * @param none
     * @return If anything there us anything to execute
     * */
    boolean undo(){

        //can't undo when there is not previous function calls
        if (undo.isEmpty() == true)
        {
            return false;
        }
        else
        {
            //store current state into redo stack - in case redo is called
            //after
            redo.push(this.getImageCopy());

            //get previous state
            int[][] state = undo.pop();

            //set current state to previous
            this.mat = state;

            return true;
        }

    }

    /**
     * Execute the most recent function undone by undo command
     * @param none
     * @return if there is anything to execute
     * */
    boolean redo(){

        //can't redo there are no undo function calls previously
        if (redo.isEmpty() == true)
        {
            return false;
        }
        else
        {   
            //store current state to undo stack
            undo.push(this.getImageCopy());

            //get previous state
            int[][] state = redo.pop();

            //set current state to previous
            this.mat = state;

            return true;
        }

    }

    /**
     * Deepy copy the current 2D image
     * @param none
     * @return getMat the copied image
     * */
    int[][] getImageCopy()
    {
        //deep copy
        int[][] getMat = new int[mat.length][mat[0].length];

        //iterate through to get all elements in instance
        for (int i = 0; i < mat.length; i++)
        {
            for (int j = 0; j < mat[0].length; j++)
            {
                getMat[i][j] = mat[i][j];
            }
        }

        return getMat;
    }

    /**
     * Returns the current 2D image
     * @param none
     * @return mat The 2D image
     * */
    int[][] getImage(){

        return mat;
    }


}
