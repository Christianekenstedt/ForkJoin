/**
 * Created by Anton on 2017-03-09.
 */
public class PerformanceResult{
    public long elapsedTimeNanoSec;
    public boolean sorted;

    public String toString(){
        return "Time(ns): " + elapsedTimeNanoSec + "\nSorted: " + true;
    }
}