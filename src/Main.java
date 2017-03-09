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

        int arrayLength = (int)1E8;
        int iterations = 20;

        //warmup();


        PerformanceTester pt = new PerformanceTester(arrayLength);
        /*ArrayList<PerformanceResult> results = new ArrayList<>();

        results.addAll(pt.test(0, PerformanceTester.SortType.JAVA_SORT, iterations));
        */

        PerformanceResult pr = pt.test(0, PerformanceTester.SortType.MERGE_SORT_PARALLELL);
        System.out.println(pr);

        //printToFile(results);
    }



    private static void warmup(){
        System.out.println("JVM warmup started");
        PerformanceTester pt = new PerformanceTester((int)1E6);
        pt.test(0, PerformanceTester.SortType.JAVA_SORT);
        pt.test(0, PerformanceTester.SortType.JAVA_PARALLELL_SORT);
        pt.test(0, PerformanceTester.SortType.MERGE_SORT);
        pt.test(0, PerformanceTester.SortType.QUICK_SORT);
        pt.test(0, PerformanceTester.SortType.MERGE_SORT_PARALLELL);
        System.out.println("Warmup completed!\n\n");

    }


    private static void printToFile(List<PerformanceResult> results){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH_mm_ss");
        try {
            FileWriter fw = new FileWriter(df.format(new Date())+".csv");

            fw.write(PerformanceTester.SortType.QUICK_SORT.toString()+";");
            for(PerformanceResult pr : results){
                if(pr.sortType.equals(PerformanceTester.SortType.QUICK_SORT))
                    fw.write("" + pr.elapsedTimeNanoSec+";");
            }

            fw.write("\n");
            fw.write(PerformanceTester.SortType.MERGE_SORT.toString()+";");

            for(PerformanceResult pr : results){
                if(pr.sortType.equals(PerformanceTester.SortType.MERGE_SORT))
                    fw.write("" + pr.elapsedTimeNanoSec+";");
            }

            fw.write("\n");
            fw.write(PerformanceTester.SortType.JAVA_PARALLELL_SORT.toString()+";");

            for(PerformanceResult pr : results){
                if(pr.sortType.equals(PerformanceTester.SortType.JAVA_PARALLELL_SORT))
                    fw.write("" + pr.elapsedTimeNanoSec+";");
            }

            fw.write("\n");
            fw.write(PerformanceTester.SortType.JAVA_SORT.toString()+";");

            for(PerformanceResult pr : results){
                if(pr.sortType.equals(PerformanceTester.SortType.JAVA_SORT))
                    fw.write("" + pr.elapsedTimeNanoSec+";");
            }


            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
