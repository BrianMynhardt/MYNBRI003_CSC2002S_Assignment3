import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelTrees {

    private static int X_SIZE;
    private static int Y_SIZE;
    private int TreeCount;
    static float[] sunValues;
    float totalSun = 0;
    static long startTime = 0;

    Tree[] trees;

    public ParallelTrees(String fName){
        try{
            //Get First 2 lines of input for gridsize then work out size of values Array
            Scanner input = new Scanner(new File(fName));
            String[] tempArr = input.nextLine().split(" ");
            this.X_SIZE = Integer.parseInt(tempArr[0]);
            this.Y_SIZE = Integer.parseInt(tempArr[1]);
            sunValues = new float[Y_SIZE*X_SIZE];

            //Populate Float array
            int counter =0;
            Scanner floats = new Scanner(input.nextLine());
            while(floats.hasNextFloat()){
                sunValues[counter++] = floats.nextFloat();
            }
            floats.close();

            //Get Tree count
            TreeCount = Integer.parseInt(input.nextLine());
            trees = new Tree[TreeCount];
            //Populate tree list.
            for(int i = 0; i<TreeCount;i++){
                tempArr = input.nextLine().split(" ");
                trees[i] = new Tree(Integer.parseInt(tempArr[1]),Integer.parseInt(tempArr[0]),Integer.parseInt(tempArr[2]));
                //System.out.println(trees.get(i).getX());
            }



        }catch(Exception e){

        }
        tick();//Give trees values
        totalSun= sum(trees);
        float time = tock();
        System.out.println("Run took "+ time +" seconds");
        //Output
        System.out.println(totalSun/TreeCount);
        System.out.println(TreeCount);
        //for(Tree tree:trees){
        //    System.out.println(tree.getSunlight()+"");
        //}
    }
    static final ForkJoinPool fjPool = new ForkJoinPool();
    static float sum(Tree[] arr){
        return fjPool.invoke(new SumArray(arr,0,arr.length,X_SIZE,Y_SIZE,sunValues));
    }
    private static void tick(){
        startTime = System.currentTimeMillis();
    }
    private static float tock(){
        return (System.currentTimeMillis() - startTime) / 1000.1f;
    }

    public int getX_SIZE() {
        return X_SIZE;
    }

    public int getY_SIZE() {
        return Y_SIZE;
    }

    public float[] getSunValues() {
        return sunValues;
    }
}
