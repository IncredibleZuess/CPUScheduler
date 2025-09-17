package com.scheduler;

public abstract class Process {
    private Character processID;
    private int priority;
    private int arrival;
    private int burstTime;
    private boolean[] times = new boolean[33];
    //TODO add implementation where the burst time is rather used
    private boolean isChecked;

    public Process(Character processID, int priority,int arrival, int burstTime){
        setProcessID(processID);
        setPriority(priority);
        setArrival(arrival);
        setBurstTime(burstTime);
    }

    //Getters
    public Character getProcessID(){
        return this.processID;
    }

    public boolean getTimes(int i){
        return this.times[i];
    }

    public int getPriority(){
        return this.priority;
    }

    public int getArrival(){
        return this.arrival;
    }

    public int getBurstTime(){
        return this.burstTime;
    }

    public Boolean getIsChecked(){
        return this.isChecked;
    }

    //Setters
    public void setProcessID(Character processID){
        this.processID = processID;
    }

    public void setTimes(int position, Boolean value){
        this.times[position] = value;
    }

    public void setPriority(int priority){
        this.priority = priority;
    }

    public void setArrival(int arrival){
        this.arrival = arrival;
    }

    public void setBurstTime(int burstTime){
        this.burstTime = burstTime;
    }

    public void setIsChecked(Boolean isChecked){
        this.isChecked = isChecked;
    }

    public abstract void schedule(int currentTime, int quantum);

}
