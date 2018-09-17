import java.util.ArrayList;
import java.util.concurrent.RecursiveTask;

public class SumArray extends RecursiveTask<Float> {
	private int X_SIZE;
	private int Y_SIZE;
	float[][] sunValues;
	int lo; // arguments
	int hi;
	ArrayList<Tree> trees;
	static int SEQUENTIAL_CUTOFF;

	float totalSun = 0; // result

	SumArray(ArrayList<Tree> trees, int l, int h, int xsize, int ysize, float[][] sunvals,int cutOff) {
		this.lo = l;
		this.hi = h;
		this.trees = trees;
		this.X_SIZE = xsize;
		this.Y_SIZE = ysize;
		this.sunValues = sunvals;
		this.SEQUENTIAL_CUTOFF=cutOff;
		totalSun = 0;
	}


	protected Float compute() {// return answer - instead of run
		if ((hi - lo) < SEQUENTIAL_CUTOFF) {

			for (int i = lo; i < hi; i++) {
				Tree tree = trees.get(i);
				totalSun+=tree.getSunlight(sunValues,X_SIZE,Y_SIZE);
			}
			return totalSun;
		} else {
			SumArray left = new SumArray(trees, lo, (hi + lo) / 2, X_SIZE, Y_SIZE, sunValues,SEQUENTIAL_CUTOFF);
			SumArray right = new SumArray(trees, (hi + lo) / 2, hi, X_SIZE, Y_SIZE, sunValues,SEQUENTIAL_CUTOFF);

			// order of next 4 lines
			// essential – why?
			left.fork();
			float rightAns = right.compute();
			float leftAns = left.join();
			return leftAns + rightAns;
		}
	}
}