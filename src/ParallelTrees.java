import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelTrees {

    private static int X_SIZE;
    private static int Y_SIZE;
    private int TreeCount;
    static float[][] sunValues;
    float totalSun = 0;
    static long startTime = 0;
    public int counter=0;
    public String out;
    static int cutOff;

    ArrayList<Tree> trees = new ArrayList<>();

    public ParallelTrees(String inName,String outName,int cut){
        this.out = outName;
        this.cutOff = cut;
        try{
            //Get First 2 lines of input for gridsize then work out size of values Array
            Scanner input = new Scanner(new File(inName));
            String[] tempArr = input.nextLine().split(" ");
            this.X_SIZE = Integer.parseInt(tempArr[1]);
            this.Y_SIZE = Integer.parseInt(tempArr[0]);


            //Populate Float array
            System.gc();
            sunValues = new float[Y_SIZE][X_SIZE];
            String floats =input.nextLine();
            String[] split = floats.split(" ");
            for(int y = 0; y < Y_SIZE; y++) {
                for (int x = 0; x < X_SIZE; x++) {
                    sunValues[y][x] = Float.parseFloat(split[counter]);
                    counter++;
                }
            }
            System.gc();

            //Get Tree count
            TreeCount = Integer.parseInt(input.nextLine());;
            //Populate tree list.
            for(int i = 0; i<TreeCount;i++){
                tempArr = input.nextLine().split(" ");
                trees.add(new Tree(Integer.parseInt(tempArr[1]),Integer.parseInt(tempArr[0]),Integer.parseInt(tempArr[2])));
                //System.out.println(trees.get(i).getX());
            }



        }catch(Exception e){

        }
        tick();
        totalSun=sum(trees);
        float time = tock();
        printOut(time);

    }
    static final ForkJoinPool fjPool = new ForkJoinPool();
    static float sum(ArrayList<Tree> arr){
        return fjPool.invoke(new SumArray(arr,0,arr.size(),X_SIZE,Y_SIZE,sunValues,cutOff));
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

    public float[][] getSunValues() {
        return sunValues;
    }

    public void printOut(float total){
        try {//Printing Output
            File output = new File(out);
            PrintWriter pw = new PrintWriter(output);

            totalSun = totalSun/ TreeCount;
            pw.print(totalSun);
            pw.print(TreeCount);

            for(int i = 0; i < TreeCount; i++){
                Tree tree = trees.get(i);
                float sunlight = tree.getSunlight(sunValues, X_SIZE, Y_SIZE);
                pw.println(sunlight);
            }

            pw.print("\n" + total);
            pw.close();
        }catch(FileNotFoundException e){

        }
    }
}
