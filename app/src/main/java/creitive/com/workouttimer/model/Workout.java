package creitive.com.workouttimer.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.util.ArrayList;
import java.util.List;

import creitive.com.workouttimer.BR;

/**
 * Model used for workouts
 * Time is represented in milliseconds
 */

public class Workout extends BaseObservable {

    private String workoutName;

    private Integer numberOfRounds;
    //Times are in seconds
    private Integer roundTime;
    private Integer breakTime;
    private Integer preparationTime;
    private Integer totalWorkoutTime;
    private int currentCycle = 0;//always starts from first cycle
    private int currentRound = 1;
    private List<WorkoutCycle> workoutCycleList;

    public Workout(String workoutName, Integer numberOfRounds, Integer roundTime, Integer breakTime, Integer preparationTime) {
        this.workoutName = workoutName;
        this.numberOfRounds = numberOfRounds;
        this.roundTime = roundTime;
        this.breakTime = breakTime;
        this.preparationTime = preparationTime;
        totalWorkoutTime = preparationTime + numberOfRounds * roundTime + (numberOfRounds - 1) * breakTime;
        calculateCycles();
    }

    private void calculateCycles() {
        int time = 0;
        workoutCycleList = new ArrayList<>();
        if (preparationTime > 0) {
            time += preparationTime;
            WorkoutCycle workoutCycle = new WorkoutCycle("Preparation time", preparationTime, time - preparationTime, time);
            workoutCycleList.add(workoutCycle);
        }
        for (int i = 0; i < numberOfRounds; i++) {
            time += roundTime;
            WorkoutCycle workoutCycle = new WorkoutCycle("Work time", roundTime, time - roundTime, time);
            workoutCycleList.add(workoutCycle);
            if (i < (numberOfRounds - 1)) {
                time += breakTime;
                WorkoutCycle workoutBreakCycle = new WorkoutCycle("Break time", breakTime, time - breakTime, time);
                workoutCycleList.add(workoutBreakCycle);
            }

        }
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public Integer getNumberOfRounds() {
        return numberOfRounds;
    }

    public void setNumberOfRounds(Integer numberOfRounds) {
        this.numberOfRounds = numberOfRounds;
    }

    public Integer getRoundTime() {
        return roundTime;
    }

    public void setRoundTime(Integer roundTime) {
        this.roundTime = roundTime;
    }

    public Integer getBreakTime() {
        return breakTime;
    }

    public void setBreakTime(Integer breakTime) {
        this.breakTime = breakTime;
    }

    public Integer getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(Integer preparationTime) {
        this.preparationTime = preparationTime;
    }

    public Integer getTotalWorkoutTime() {
        return totalWorkoutTime;
    }

    public void setTotalWorkoutTime(Integer totalWorkoutTime) {
        this.totalWorkoutTime = totalWorkoutTime;
    }

    public List<WorkoutCycle> getWorkoutCycleList() {
        return workoutCycleList;
    }

    public void setWorkoutCycleList(List<WorkoutCycle> workoutCycleList) {
        this.workoutCycleList = workoutCycleList;
    }


    public void reloadWorkout() {
        currentCycle = 0;
    }

    public void nextCycle() {
        setCurrentCycle(currentCycle + 1);
    }

    public WorkoutCycle getCurrentWorkoutCycle(){
        return workoutCycleList.get(currentCycle);
    }

    @Bindable
    public int getCurrentCycle() {
        return currentCycle;
    }

    public void setCurrentCycle(int currentCycle) {
        this.currentCycle = currentCycle;
        notifyPropertyChanged(BR.currentCycle);
    }

    public static List<Workout> getPredefinedWorkouts() {
        Workout mma = new Workout("MMA", 6, 4000, 2000, 3000);
        Workout box = new Workout("Boxing", 12, 5000, 3000, 2000);
        Workout run = new Workout("Running", 5, 10000, 2000, 0);
        List<Workout> workoutsList = new ArrayList<>();
        workoutsList.add(mma);
        workoutsList.add(box);
        workoutsList.add(run);
        return workoutsList;
    }
}
