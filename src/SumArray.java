import java.util.concurrent.RecursiveTask;

public class SumArray extends RecursiveTask<Float>  {
	private int X_SIZE;
	private int Y_SIZE;
	float[] sunValues;
	float totalSun = 0;
	float tempsum;

	int lo; // arguments
	int hi;
	Tree[] arr;
	static final int SEQUENTIAL_CUTOFF=1000;

	float ans = 0; // result

	SumArray(Tree[] a, int l, int h,int xsize,int ysize,float[] sunvals) {
		lo=l; hi=h; arr=a; X_SIZE=xsize; Y_SIZE=ysize; sunValues=sunvals;
	}


	protected Float compute(){// return answer - instead of run
		if((hi-lo) < SEQUENTIAL_CUTOFF) {
			float ans = 0;
			for(int i=lo; i < hi; i++)
				ans += SumTree(arr[i]);
			return ans;
		}
		else {
			SumArray left = new SumArray(arr,lo,(hi+lo)/2,X_SIZE,Y_SIZE,sunValues);
			SumArray right= new SumArray(arr,(hi+lo)/2,hi,X_SIZE,Y_SIZE,sunValues);

			// order of next 4 lines
			// essential – why?
			left.fork();
			float rightAns = right.compute();
			float leftAns  = left.join();
			return leftAns + rightAns;
		}
	}
	public float SumTree(Tree current){
		int extent = current.getExtent();
		tempsum=0;
		if (current.fits(X_SIZE,Y_SIZE)){
			for(int i=current.getY();i<current.getY()+extent; i++){
				for(int j=current.getX();j<current.getX()+extent;j++){
					tempsum += sunValues[(i)*(Y_SIZE) + j];

				}
			}
			current.setSunlight(tempsum);
		}else{
			for( int j=current.getY();j<current.getY()+extent;j++ ){
				for( int i=current.getX();i<current.getX()+extent; i++){
					if(i< X_SIZE && j<Y_SIZE){
						tempsum+= sunValues[j*Y_SIZE+i];
					}
				}
			}
			current.setSunlight(tempsum);

		}
		return tempsum;

	}
}


