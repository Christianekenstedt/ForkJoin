import java.math.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        warmup();


        int numbers = (int)1E8;

        PerformanceTester pt = new PerformanceTester(numbers);

        PerformanceResult javaSort = pt.test(0, PerformanceTester.SortType.JAVA_SORT);
        System.out.println(javaSort);
        PerformanceResult javaParallellSort = pt.test(0, PerformanceTester.SortType.JAVA_PARALLELL_SORT);
        System.out.println(javaParallellSort);
        PerformanceResult quickSort = pt.test(0, PerformanceTester.SortType.QUICK_SORT);
        System.out.println(quickSort);
        PerformanceResult mergeSort = pt.test(0, PerformanceTester.SortType.MERGE_SORT);
        System.out.println(mergeSort);
    }



    private static void warmup(){
        System.out.println("JVM warmup started");
        PerformanceTester pt = new PerformanceTester((int)1E6);
        pt.test(0, PerformanceTester.SortType.JAVA_SORT);
        pt.test(0, PerformanceTester.SortType.JAVA_PARALLELL_SORT);
        pt.test(0, PerformanceTester.SortType.MERGE_SORT);
        pt.test(0, PerformanceTester.SortType.QUICK_SORT);
        System.out.println("Warmup completed\n\n");
    }

}
