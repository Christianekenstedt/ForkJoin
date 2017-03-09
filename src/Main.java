import javax.swing.text.DateFormatter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        ArrayList<PerformanceResult> results = new ArrayList<>();

        warmup();

        int numbers = (int)1E8;

        PerformanceTester pt = new PerformanceTester(numbers);

        PerformanceResult javaSort = pt.test(0, PerformanceTester.SortType.JAVA_SORT);
        javaSort.printResult();
        results.add(javaSort);

        PerformanceResult javaParallellSort = pt.test(0, PerformanceTester.SortType.JAVA_PARALLELL_SORT);
        javaParallellSort.printResult();
        results.add(javaParallellSort);

        PerformanceResult quickSort = pt.test(0, PerformanceTester.SortType.QUICK_SORT);
        quickSort.printResult();
        results.add(quickSort);

        /*
        PerformanceResult mergeSort = pt.test(0, PerformanceTester.SortType.MERGE_SORT);
        mergeSort.printResult();
        results.add(mergeSort);
        */

        printToFile(results);
    }



    private static void warmup(){
        System.out.println("JVM warmup started");
        PerformanceTester pt = new PerformanceTester((int)1E6);
        pt.test(0, PerformanceTester.SortType.JAVA_SORT);
        pt.test(0, PerformanceTester.SortType.JAVA_PARALLELL_SORT);
        pt.test(0, PerformanceTester.SortType.MERGE_SORT);
        pt.test(0, PerformanceTester.SortType.QUICK_SORT);
        System.out.println("Warmup completed!\n\n");

    }


    private static void printToFile(List<PerformanceResult> results){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH_mm_ss");
        try {
            FileWriter fw = new FileWriter(df.format(new Date())+".csv");

            fw.write(PerformanceTester.SortType.QUICK_SORT.toString()+";");
            for(PerformanceResult pr : results){
                if(pr.sortType.equals(PerformanceTester.SortType.QUICK_SORT))
                    fw.write("" + pr.elapsedTimeNanoSec/1.0E9+";");
            }

            fw.write("\n");
            fw.write(PerformanceTester.SortType.MERGE_SORT.toString()+";");

            for(PerformanceResult pr : results){
                if(pr.sortType.equals(PerformanceTester.SortType.MERGE_SORT))
                    fw.write("" + pr.elapsedTimeNanoSec/1.0E9);
            }

            fw.write("\n");
            fw.write(PerformanceTester.SortType.JAVA_PARALLELL_SORT.toString()+";");

            for(PerformanceResult pr : results){
                if(pr.sortType.equals(PerformanceTester.SortType.JAVA_PARALLELL_SORT))
                    fw.write("" + pr.elapsedTimeNanoSec/1.0E9);
            }

            fw.write("\n");
            fw.write(PerformanceTester.SortType.JAVA_SORT.toString()+";");

            for(PerformanceResult pr : results){
                if(pr.sortType.equals(PerformanceTester.SortType.JAVA_SORT))
                    fw.write("" + pr.elapsedTimeNanoSec/1.0E9);
            }


            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
