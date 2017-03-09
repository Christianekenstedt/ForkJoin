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

    public List<PerformanceResult> test(int cores, SortType sortType, int iterations){
        ArrayList<PerformanceResult> results = new ArrayList<>();
        System.out.println("Beginning batch job of " + sortType.toString());
        System.out.println("0/"+iterations);
        for(int i = 0; i < iterations;i++){
            results.add(test(cores, sortType));
            System.out.println((i+1)+"/"+iterations + "("+(((float)i/(float)iterations)*100)+"%)");
        }

        System.out.println(sortType.toString() + " completed.");
        return results;
    }

    public PerformanceResult test(int cores, SortType sortType){

        System.gc();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        float[] arr = new float[templateArray.length];

        System.arraycopy(templateArray, 0, arr, 0, templateArray.length);

        PerformanceResult pr = new PerformanceResult();
        pr.sortType = sortType;

        long startTimeNS;

        switch(sortType){
            case QUICK_SORT:
                startTimeNS = System.nanoTime();
                QuickSort.sort(arr);
                pr.elapsedTimeNanoSec = System.nanoTime() - startTimeNS;
                break;
            case MERGE_SORT:
                startTimeNS = System.nanoTime();
                MergeSort.sort(arr);
                pr.elapsedTimeNanoSec = System.nanoTime() - startTimeNS;
                break;
            case JAVA_PARALLELL_SORT:
                startTimeNS = System.nanoTime();
                java.util.Arrays.parallelSort(arr);
                pr.elapsedTimeNanoSec = System.nanoTime() - startTimeNS;
                break;
            case JAVA_SORT:
                startTimeNS = System.nanoTime();
                java.util.Arrays.sort(arr);
                pr.elapsedTimeNanoSec = System.nanoTime() - startTimeNS;
                break;
            default:
                pr.elapsedTimeNanoSec = Long.MAX_VALUE;
                break;
        }

        pr.sorted = checkIfSorted(arr);
        return pr;
    }

    public static boolean checkIfSorted(float[] arr){

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }

        return true;
    }


    public enum SortType{
        QUICK_SORT,
        MERGE_SORT,
        JAVA_SORT,
        JAVA_PARALLELL_SORT
    }

}
