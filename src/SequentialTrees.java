import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SequentialTrees {
    String temp;
    static long startTime = 0;
    private int X_SIZE;
    private int Y_SIZE;
    private int TreeCount;
    float[][] sunValues;
    float totalSun;
    public int counter=0;
    String out;

    List<Tree> trees = new ArrayList<Tree>();

    public SequentialTrees(String inName,String outName){
        totalSun = 0;
        this.out = outName;
        try{
            //Get First line of input for gridsize
            Scanner input = new Scanner(new File(inName));
            String[] tempArr = input.nextLine().split(" ");
            this.X_SIZE = Integer.parseInt(tempArr[0]);
            this.Y_SIZE = Integer.parseInt(tempArr[1]);

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
            TreeCount = Integer.parseInt(input.nextLine());

            //Populate tree list.
            for(int i = 0; i<TreeCount;i++){
                tempArr = input.nextLine().split(" ");
                trees.add(new Tree(Integer.parseInt(tempArr[1]),Integer.parseInt(tempArr[0]),Integer.parseInt(tempArr[2])));
                //System.out.println(trees.get(i).getX());
            }



        }catch(Exception e){

        }
        tick();
        sumVals();
        float time = tock();
        printOut(time);



    }
    private static void tick(){
        startTime = System.currentTimeMillis();
    }
    private static float tock(){
        return (System.currentTimeMillis() - startTime) / 1000.1f;
    }

    public void sumVals(){
        for(Tree a:trees) {
            totalSun+=a.getSunlight(sunValues,X_SIZE,Y_SIZE);

        }
    }

    public void printOut(float total){
        try {//Printing Output
            File output = new File(out);
            PrintWriter pw = new PrintWriter(output);

            totalSun = totalSun/ TreeCount;
            pw.print(totalSun);
            pw.print(TreeCount);

            for (int i = 0; i < TreeCount; i++) {
                pw.print(trees.get(i).getSun());
            }

            pw.print("\n" + total);
            pw.close();
        }catch(FileNotFoundException e){

        }
    }
}
