import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anton on 2017-03-09.
 */
public class PerformanceTester {
    private float[] templateArray;

    public PerformanceTester(int length){
        templateArray = new float[length];

        for(int i = 0; i < templateArray.length; i++){
            templateArray[i] = (float)Math.random();
        }
    }

    public List<PerformanceResult> test(int cores, SortStrategy sortStrategy, int iterations){
        ArrayList<PerformanceResult> results = new ArrayList<>();

        System.out.println("Sorting...");
        System.out.println("0/"+iterations);

        for(int i = 0; i < iterations;i++){
            results.add(test(cores, sortStrategy));
            System.out.println((i+1)+"/"+iterations + "("+(int)((((float)i/(float)iterations)*100))+"%)");
        }

        System.out.println("Array sorted.");
        return results;
    }

    public PerformanceResult test(int cores, SortStrategy sortStrategy){
        garbageCollect();

        float[] arr = new float[templateArray.length];

        System.arraycopy(templateArray, 0, arr, 0, templateArray.length);

        PerformanceResult pr = new PerformanceResult();

        long startTimeNS = System.nanoTime();

        sortStrategy.sort(arr, cores);

        pr.elapsedTimeNanoSec = System.nanoTime() - startTimeNS;

        pr.sorted = checkIfSorted(arr);
        return pr;
    }

    private void garbageCollect(){

        System.gc();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkIfSorted(float[] arr){

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }

        return true;
    }
}
