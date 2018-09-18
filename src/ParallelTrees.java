import java.io.*;
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
    public String outer;
    static int cutOff;

    ArrayList<Tree> trees = new ArrayList<>();

    public ParallelTrees(String inName,String outName,int cut){
        this.out = outName;
        //this.outer = outname2;
        this.cutOff = cut;
        try{
            //Get First 2 lines of input for gridsize then work out size of values Array
            Scanner input = new Scanner(new File("./inputs/"+inName));
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
            totalSun = sum(trees);
            printOut();
        }

    static final ForkJoinPool fjPool = new ForkJoinPool();
    static float sum(ArrayList<Tree> arr){
        return fjPool.invoke(new SumArray(arr,0,arr.size(),X_SIZE,Y_SIZE,sunValues,cutOff));
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

    public void printOut(){
        try {//Printing Output
            FileWriter fw = new FileWriter("./outputs/"+out);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter output = new PrintWriter(bw);


            totalSun = totalSun/ TreeCount;
            output.print(totalSun);
            output.print(TreeCount);

            for(int i = 0; i < TreeCount; i++){
                Tree tree = trees.get(i);
                float sunlight = tree.getSunlight(sunValues, X_SIZE, Y_SIZE);
                output.print(sunlight);
            }
            output.close();

        }catch(Exception e){

        }
    }
}
