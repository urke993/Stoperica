package creitive.com.workouttimer.model;

/**
 * Created by Creitive 31 on 13-Oct-17.
 */

public class WorkoutCycle {
    private String cycleName;
    private int totalCycleTimeInMillis;
    private int cycleStartTimeInMillis;
    private int cycleEndTimeInMillis;

    public WorkoutCycle(String cycleName, int totalCycleTimeInMillis, int cycleStartTimeInMillis, int cycleEndTimeInMillis) {
        this.cycleName = cycleName;
        this.totalCycleTimeInMillis = totalCycleTimeInMillis;
        this.cycleStartTimeInMillis = cycleStartTimeInMillis;
        this.cycleEndTimeInMillis = cycleEndTimeInMillis;
    }


    public String getCycleName() {
        return cycleName;
    }

    public void setCycleName(String cycleName) {
        this.cycleName = cycleName;
    }

    public int getTotalCycleTimeInMillis() {
        return totalCycleTimeInMillis;
    }

    public void setTotalCycleTimeInMillis(int totalCycleTimeInMillis) {
        this.totalCycleTimeInMillis = totalCycleTimeInMillis;
    }

    public int getCycleStartTimeInMillis() {
        return cycleStartTimeInMillis;
    }

    public void setCycleStartTimeInMillis(int cycleStartTimeInMillis) {
        this.cycleStartTimeInMillis = cycleStartTimeInMillis;
    }

    public int getCycleEndTimeInMillis() {
        return cycleEndTimeInMillis;
    }

    public void setCycleEndTimeInMillis(int cycleEndTimeInMillis) {
        this.cycleEndTimeInMillis = cycleEndTimeInMillis;
    }
}
