/**
 * Created by Anton on 2017-03-09.
 */
public class PerformanceResult{
    public long elapsedTimeNanoSec;
    public boolean sorted;
    public PerformanceTester.SortType sortType;

    public void printResult(){
        System.out.println("Sorting type: " + sortType);
        System.out.println("Time(s): " + elapsedTimeNanoSec/1.0E9);
        System.out.println("Sorted: " + sorted);
        System.out.println();
    }

    public String toString(){
        return sortType +";"+elapsedTimeNanoSec/1.0E9+";"+sorted+"\n";
    }
}