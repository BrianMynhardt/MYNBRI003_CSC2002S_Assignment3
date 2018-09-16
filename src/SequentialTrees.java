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
    float[] sunValues;
    float totalSun = 0;
    float tempsum;

    List<Tree> trees = new ArrayList<Tree>();

    public SequentialTrees(String fName){
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
        for(Tree tree:trees){
            System.out.println(tree.getSunlight()+"");
        }

    }
    private static void tick(){
        startTime = System.currentTimeMillis();
    }
    private static float tock(){
        return (System.currentTimeMillis() - startTime) / 1000.0f;
    }

    public void sumVals(){
        int extent;

        for(Tree a:trees){
            extent = a.getExtent();
            tempsum=0;
            if (a.fits(X_SIZE,Y_SIZE)){
                for(int i=a.getY();i<a.getY()+extent; i++){
                    for(int j=a.getX();j<a.getX()+extent;j++){
                        tempsum += sunValues[(i)*(Y_SIZE) + j];

                    }
                }
                a.setSunlight(tempsum);
            }else{
                for( int j=a.getY();j<a.getY()+a.getExtent();j++ ){
                    for( int i=a.getX();i<a.getX()+a.getExtent(); i++){
                        if(i< X_SIZE && j<Y_SIZE){
                            tempsum+= sunValues[j*Y_SIZE+i];

                        }
                    }
                }
                a.setSunlight(tempsum);

            }
            totalSun += tempsum;

        }

    }
}
