import java.io.File;
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
    float tempsum;

    List<Tree> trees = new ArrayList<Tree>();

    public SequentialTrees(String inName){
        totalSun = 0;
        try{
            //Get First 2 lines of input for gridsize then work out size of values Array
            Scanner input = new Scanner(new File(inName));
            String[] tempArr = input.nextLine().split(" ");
            this.X_SIZE = Integer.parseInt(tempArr[0]);
            this.Y_SIZE = Integer.parseInt(tempArr[1]);

            //Populate Float array
            System.gc();
            sunValues = new float[Y_SIZE][X_SIZE];
            Scanner floats = new Scanner(input.nextLine());
            for(int y = 0; y < Y_SIZE; y++){
                for(int x=0; x < X_SIZE; x++){
                    sunValues[y][x] = floats.nextFloat();
                }
            }
            floats.close();
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
        tick();//Give trees values
        sumVals();
        float time = tock();
        System.out.println("Run took "+ time +" seconds");
        //Output
        System.out.println(totalSun/TreeCount);
        System.out.println(TreeCount);
        //for(Tree tree:trees){
        //    System.out.println(tree.getSunlight()+"");
        //}

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
}
