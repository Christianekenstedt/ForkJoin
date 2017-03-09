import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        int arrayLength = (int)1E8;                       //length of array
        int iterations = 20;                               //number of iterations to test
        SortStrategy strategy = new ParallelMergeSort(); //sorting strategy to use
        String fileName = "PMergeSort";                   //save results with this filename



        PerformanceTester pt = new PerformanceTester(arrayLength);
        ArrayList<PerformanceResult> results = new ArrayList<>();

        results.addAll(pt.test(1, strategy, iterations));
        printToFile(results, fileName+"_c1");

        results.clear();
        results.addAll(pt.test(2, new ParallelMergeSort(), iterations));
        printToFile(results, fileName+"_c2");

        results.clear();
        results.addAll(pt.test(4, new ParallelMergeSort(), iterations));
        printToFile(results, fileName+"_c4");

        results.clear();
        results.addAll(pt.test(6, new ParallelMergeSort(), iterations));
        printToFile(results, fileName+"_c6");

        results.clear();
        results.addAll(pt.test(8, new ParallelMergeSort(), iterations));
        printToFile(results, fileName+"_c8");

    }


    private static void printToFile(List<PerformanceResult> results, String name){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH_mm_ss");
        try {
            FileWriter fw = new FileWriter( name + "_"+ df.format(new Date())+".txt");

            for(PerformanceResult pr : results){
                fw.write(pr.elapsedTimeNanoSec+";");
            }

            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
