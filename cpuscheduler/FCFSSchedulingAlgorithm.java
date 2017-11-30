/** FCFSSchedulingAlgorithm.java
 * 
 * A first-come first-served scheduling algorithm.
 *
 * @author: Charles Zhu
 * Spring 2016
 *
 */
package com.jimweller.cpuscheduler;

import java.util.*;

public class FCFSSchedulingAlgorithm extends BaseSchedulingAlgorithm {
	private Vector<Process> jobs;
	private Comparator<Process> comparator;
    FCFSSchedulingAlgorithm(){
        // Fill in this method
        /*------------------------------------------------------------*/
	activeJob = null;
	jobs = new Vector<Process>();
	comparator = new ArrivalTimeCompare(); 

        /*------------------------------------------------------------*/
    }

    /** Add the new job to the correct queue.*/
    public void addJob(Process p){
        // Remove the next lines to start your implementation
     //   throw new UnsupportedOperationException();
        
        // Fill in this method
        /*------------------------------------------------------------*/
	jobs.add(p);
	Collections.sort(jobs,comparator);


        /*------------------------------------------------------------*/
    }
    
    /** Returns true if the job was present and was removed. */
    public boolean removeJob(Process p){
        // Remove the next lines to start your implementation
     //   throw new UnsupportedOperationException();

        // Fill in this method
        /*------------------------------------------------------------*/
	if (p == activeJob)
		activeJob = null; //activejob is now finished, which is null
	
	return jobs.remove(p);


        /*------------------------------------------------------------*/
    }

    /** Transfer all the jobs in the queue of a SchedulingAlgorithm to another, such as
    when switching to another algorithm in the GUI */
    public void transferJobsTo(SchedulingAlgorithm otherAlg) {
     //   throw new UnsupportedOperationException();
    }

    /** Returns the next process that should be run by the CPU, null if none available.*/
    public Process getNextJob(long currentTime){
        // Remove the next lines to start your implementation
     //   throw new UnsupportedOperationException();
        
        // Fill in this method
        /*------------------------------------------------------------*/
	//find the job that has the earliest arrival time
	
	if (isJobFinished() == false)
		return activeJob;//make sure run until finish
	
	activeJob = jobs.get(0);
	return activeJob;


        /*------------------------------------------------------------*/
    }

    public String getName(){
        return "First-Come First-Served";
    }

    public class ArrivalTimeCompare implements Comparator<Process>
    {
	    public int compare(Process p1, Process p2)
	    {
		if (p1.getArrivalTime() != p2.getArrivalTime())
		{
			if (p1.getArrivalTime() > p2.getArrivalTime())
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
