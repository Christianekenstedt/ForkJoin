import java.math.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        int numbers = (int)10E8;

        System.out.println(numbers);

        PerformanceTester pt = new PerformanceTester(numbers);

        PerformanceResult javaParallellSort = pt.test(0, PerformanceTester.SortType.JAVA_PARALLELL_SORT);
        System.out.println(javaParallellSort);
        PerformanceResult javaSort = pt.test(0, PerformanceTester.SortType.JAVA_SORT);
        System.out.println(javaSort);
    }

}
