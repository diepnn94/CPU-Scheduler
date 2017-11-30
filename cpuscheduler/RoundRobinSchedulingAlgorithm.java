/** RoundRobinSchedulingAlgorithm.java
 * 
 * A scheduling algorithm that randomly picks the next job to go.
 *
 * @author: Kyle Benson
 * Winter 2013
 *
 */
package com.jimweller.cpuscheduler;

import java.util.*;

public class RoundRobinSchedulingAlgorithm extends BaseSchedulingAlgorithm {

    /** the time slice each process gets */
    private int quantum;
    private int tracking;
    private boolean rotate = true;
	private Vector <Process> jobs;
	private boolean firstItem = false;
	private int currentIndex = 0;
	private boolean flag = false;
    RoundRobinSchedulingAlgorithm() {
        // Fill in this method
        /*------------------------------------------------------------*/
	jobs = new Vector<Process> ();
	activeJob = null;
	quantum = 10;
	tracking = quantum + 1;


        /*------------------------------------------------------------*/
    }

    /** Add the new job to the correct queue. */
    public void addJob(Process p) {
        // Remove the next lines to start your implementation
     //   throw new UnsupportedOperationException();
        
        // Fill in this method
        /*------------------------------------------------------------*/
	jobs.add(p);


        /*------------------------------------------------------------*/
    }

    /** Returns true if the job was present and was removed. */
    public boolean removeJob(Process p) {
        // Remove the next lines to start your implementation
     //   throw new UnsupportedOperationException();
        
        // Fill in this method
        /*------------------------------------------------------------*/
	//System.out.println("testing" + quantum);
	if (activeJob == p)
		activeJob = null;
	tracking = quantum+1;
	boolean result = jobs.remove(p);
	if (currentIndex == 0 || currentIndex == jobs.size()) // first index and last index
		currentIndex = -1;//signal just been removed
	flag = true;
	return result;

        /*------------------------------------------------------------*/
    }

    /** Transfer all the jobs in the queue of a SchedulingAlgorithm to another, such as
    when switching to another algorithm in the GUI */
    public void transferJobsTo(SchedulingAlgorithm otherAlg) {
     //   throw new UnsupportedOperationException();
    }

    /**
     * Get the value of quantum.
     * 
     * @return Value of quantum.
     */
    public int getQuantum() {
        return quantum;
    }

    /**
     * Set the value of quantum.
     * 
     * @param v
     *            Value to assign to quantum.
     */
    public void setQuantum(int v) {
        this.quantum = v;
	tracking = quantum + 1;
    }

    /**
     * Returns the next process that should be run by the CPU, null if none
     * available.
     */
    public Process getNextJob(long currentTime) {
        // Remove the next lines to start your implementation
     //   throw new UnsupportedOperationException();
        
        // Fill in this method
        /*------------------------------------------------------------*/
	tracking--;
//	System.out.println( jobs.get(0).getPID() + "____"+ tracking);

	if (firstItem == false)//very first one
	{
		firstItem = true;
		activeJob = jobs.get(currentIndex);
		return activeJob;	
	}
	else if (isJobFinished() == false && tracking > 0)//if job is not finish and there is still time left
	{
		return activeJob;

	}
	else if (jobs.size() == 1 && isJobFinished() == false)//if there is one job left and it is not finished
	{
		tracking = quantum;
		activeJob = jobs.get(0);
		return activeJob;	
	}
	else if (jobs.size() < 0)//if there is no job left
	{
		activeJob = null;
		currentIndex = -1;
		return activeJob;
	}
	else//find the next one 
	{
		if (currentIndex == -1)//on the edge and just gone through removal
			currentIndex = 0;
		else if (flag == false)
			currentIndex = (currentIndex + 1)% jobs.size();//if did not go to removed(), increment the index; else, same index
		activeJob = jobs.get(currentIndex);
		tracking = quantum;
		flag = false;
	}
	return activeJob;
        /*------------------------------------------------------------*/
    }


    public String getName() {
        return "Round Robin";
    }
    
}
