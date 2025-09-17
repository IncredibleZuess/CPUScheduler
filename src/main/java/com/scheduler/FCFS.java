package com.scheduler;

public class FCFS extends Process{
    private int startTime;
    private int finishTime;
    public FCFS(Character processID, int priority,int arrival, int burstTime){
        super(processID, priority, arrival, burstTime);
    }

    //Getters
    public int getStartTime(){
        return this.startTime;
    }

    public int getFinishTime(){
        return this.finishTime;
    }

    //Setters
    public void setStartTime(int startTime){
        this.startTime = startTime;
    }

    public void setFinishTime(int finishTime){
        this.finishTime = finishTime;
    }

    public void schedule(int currentTime, int Quantum){
        int start = Math.max(currentTime, getArrival());
        setStartTime(start);
        setFinishTime(start+getBurstTime());
        for(int t=0; t<33; t++) setTimes(t, false);
        for(int t = start; t< getFinishTime();t++){
                if(t<33) setTimes(t, true);
            };
    }
}
