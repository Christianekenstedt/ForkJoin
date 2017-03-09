import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Anton on 2017-03-09.
 */
public class PerformanceResult{
    private PerformanceIteration[] iterations;
    private int cores, length;
    private String name;

    public PerformanceResult(List<PerformanceIteration> iterationList, int cores, int length, String name){
        this.iterations = new PerformanceIteration[iterationList.size()];
        this.iterations = iterationList.toArray(this.iterations);
        this.cores = cores;
        this.length = length;
        this.name = name;
    }

    public void saveAsFile(){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH_mm_ss");
        try {
            FileWriter fw = new FileWriter( name + "_"+ df.format(new Date())+".txt");

            fw.write("cores;"+cores+"\n");
            fw.write("length;"+length+"\n");
            fw.write("iterations;"+iterations.length+"\n");
            fw.write("average;"+getAverage()+"\n");
            for(PerformanceIteration it : iterations){
                fw.write(it.getElapsedTimeNanoSec()+";");
            }

            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public float getAverage(){
        float sum = 0, counter=0;
        for(PerformanceIteration it : iterations){
            sum += it.getElapsedTimeNanoSec();
            counter++;
        }

        return sum/counter;
    }

    @Override
    public String toString(){
        String n = "Name:         " + name + "\n";
        String c = "Cores:        " + cores +"\n";
        String l = "ArrayLength:  " + length + "\n";
        String i = "Iterations:   " + iterations.length + "\n";
        String a = "Average:      " + getAverage() + "\n";
        return n + c + l + i + a;
    }
}