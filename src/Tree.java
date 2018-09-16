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

    public boolean fits(int mapx, int mapy){

        if (this.x + extent >mapx-1 || this.y + extent > mapy-1){

            return false;
        }else{
            return true;
        }

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

    public float getSunlight() {
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
