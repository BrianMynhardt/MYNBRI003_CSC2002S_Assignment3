public class Tree {
    private int x;
    private int y;
    private int extent;
    private float sunlight;

    public Tree(int Ty, int Tx, int Textent){
        this.x = Tx;
        this.y = Ty;
        this.extent = Textent;
    }


    public int getX() {
        return x;
    }

    public int getExtent() {
        return extent;
    }

    public int getY() {
        return y;
    }

    public float getSunlight(float [][] sunVals, int xSize, int ySize){
        sunlight = 0;
        for(int i=y; i < y+extent; i++){
            for(int j=x; j < x+extent; j++){
                if(j < xSize && i < ySize){
                    sunlight += sunVals[i][j];
                }
            }
        }
        return sunlight;
    }
    public float getSun(){
        return sunlight;
    }
    public void setSunlight(float sunVal){
        sunlight = sunVal;
    }

    @Override
    public String toString() {
        return x+" "+y+" "+extent;
    }
}
