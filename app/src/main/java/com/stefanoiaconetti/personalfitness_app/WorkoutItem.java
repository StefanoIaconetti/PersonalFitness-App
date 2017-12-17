package com.stefanoiaconetti.personalfitness_app;

/**
 * Created by Stefano on 12/17/2017.
 */

public class WorkoutItem {
    private String step;
    private String desc;

    public WorkoutItem(){

    }
    public WorkoutItem(String step, String desc){
        this.step = step;
        this.desc = desc;
    }

    public String getStep() { return step;}

    public void setStep(String step){ this.step = step;}

    public String getDesc() { return desc;}

    public void setDesc(String desc){ this.desc = desc;}

}

