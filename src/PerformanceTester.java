import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anton on 2017-03-09.
 */
public class PerformanceTester {

    public static PerformanceResult test(int length, int cores, SortStrategy sortStrategy, int iterations, String name){
        ArrayList<PerformanceIteration> performanceIterations = new ArrayList<>();

        //warmup
        test(length, cores, sortStrategy);

        for(int i = 0; i < iterations;i++){
            performanceIterations.add(test(length, cores, sortStrategy));
            System.out.println((i+1)+"/"+iterations + "("+(int)((((float)(i+1)/(float)iterations)*100))+"%)");
        }

        PerformanceResult pr = new PerformanceResult(performanceIterations, cores, length, name);

        return pr;
    }

    private static PerformanceIteration test(int length, int cores, SortStrategy sortStrategy){
        garbageCollect();

        float[] arr = generateArray(length);

        long startTimeNS = System.nanoTime();

        sortStrategy.sort(arr, cores);

        long elapsedTime = System.nanoTime() - startTimeNS;

        PerformanceIteration pi = new PerformanceIteration(elapsedTime, checkIfSorted(arr));

        return pi;
    }

    private static void garbageCollect(){

        System.gc();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static float[] generateArray(int length){
        float[]arr = new float[length];

        for(int i = 0; i < arr.length; i++)
            arr[i] = (float)Math.random();

        return arr;
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
