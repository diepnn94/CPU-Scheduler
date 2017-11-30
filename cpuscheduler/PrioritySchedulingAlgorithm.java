/** PrioritySchedulingAlgorithm.java
 * 
 * A single-queue priority scheduling algorithm.
 *
 * @author: Charles Zhu
 * Spring 2016
 *
 */
package com.jimweller.cpuscheduler;

import java.util.*;

import com.jimweller.cpuscheduler.Process;

public class PrioritySchedulingAlgorithm extends BaseSchedulingAlgorithm implements OptionallyPreemptiveSchedulingAlgorithm {
 	private Vector<Process> jobs;
	private Comparator<Process> comparator;
	private boolean option = false;   
    PrioritySchedulingAlgorithm(){
        // Fill in this method
        /*------------------------------------------------------------*/
	activeJob = null;
	jobs = new Vector<Process>();
	comparator = new PriorityCompare();


        /*------------------------------------------------------------*/
    }

    /** Add the new job to the correct queue.*/
    public void addJob(Process p){
        // Remove the next lines to start your implementation
     //   throw new UnsupportedOperationException();
        
        // Fill in this method
        /*------------------------------------------------------------*/
	jobs.add(p);
	Collections.sort(jobs, comparator);


        /*------------------------------------------------------------*/
    }
    
    /** Returns true if the job was present and was removed. */
    public boolean removeJob(Process p){
        // Remove the next lines to start your implementation
     //   throw new UnsupportedOperationException();
        
        // Fill in this method
        /*------------------------------------------------------------*/
	if (activeJob == p)
		activeJob = null;
	return jobs.remove(p);


        /*------------------------------------------------------------*/
    }

    /** Transfer all the jobs in the queue of a SchedulingAlgorithm to another, such as
    when switching to another algorithm in the GUI */
    public void transferJobsTo(SchedulingAlgorithm otherAlg) {
        throw new UnsupportedOperationException();
    }


    /** Returns the next process that should be run by the CPU, null if none available.*/
    public Process getNextJob(long currentTime){
        // Remove the next lines to start your implementation
     //   throw new UnsupportedOperationException();
        
        // Fill in this method
        /*------------------------------------------------------------*/
	if (isJobFinished() == false && option == false)
		return activeJob;
	activeJob = jobs.get(0);
	return activeJob;


        /*------------------------------------------------------------*/
    }

    public String getName(){
        return "Single-Queue Priority";
    }

    /**
     * @return Value of preemptive.
     */
    public boolean isPreemptive(){
        // Remove the next lines to start your implementation
   //     throw new UnsupportedOperationException();
        
        // Fill in this method
        /*------------------------------------------------------------*/

	return option;

        /*------------------------------------------------------------*/
    }
    
    /**
     * @param v Value to assign to preemptive.
     */
    public void setPreemptive(boolean v){
        // Remove the next lines to start your implementation
 //       throw new UnsupportedOperationException();
        
        // Fill in this method
        /*------------------------------------------------------------*/
	option = v;


        /*------------------------------------------------------------*/
    }

    public class PriorityCompare implements Comparator<Process>
    {
	    
	public int compare(Process p1, Process p2)
	{
		if (p1.getPriorityWeight() != p2.getPriorityWeight())	
		{
			if (p1.getPriorityWeight() > p2.getPriorityWeight())
				return 1;
			else
				return -1;	
			
		}
		else
		{
			if (p1.getPID() > p2.getPID())
				return 1;
			else
				return -1;				
		}
	}	    
 }
    
}
